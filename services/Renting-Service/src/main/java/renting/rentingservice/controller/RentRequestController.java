package renting.rentingservice.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import renting.rentingservice.client.AdvertisementClient;
import renting.rentingservice.client.UserClient;
import renting.rentingservice.domain.*;
import renting.rentingservice.dto.*;
import renting.rentingservice.service.BundleRentService;
import renting.rentingservice.service.CartService;
import renting.rentingservice.service.RentHistoryService;
import renting.rentingservice.service.RentRequestService;
import org.json.*;

import javax.xml.ws.Response;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class RentRequestController {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;
    
    private CartService cartService;
    private RentRequestService rentRequestService;
    private RentHistoryService rentHistoryService;
    private BundleRentService bundleRentService;
    private AdvertisementClient advertisementClient;
    private UserClient userClient;

    @Autowired
    public RentRequestController(
            CartService cartService,
            RentRequestService rentRequestService,
            BundleRentService bundleRentService,
            RentHistoryService rentHistoryService,
            AdvertisementClient advertisementClient,
            UserClient userClient
    ) {
        this.cartService = cartService;
        this.rentRequestService = rentRequestService;
        this.bundleRentService = bundleRentService;
        this.rentHistoryService = rentHistoryService;
        this.advertisementClient = advertisementClient;
        this.userClient = userClient;
    }

    @GetMapping("/containertest")
    public String containerTest(){
        return this.advertisementClient.containerTest();
    }

    @PostMapping("/newRentRequest")
    public ResponseEntity<?> addVehicleToCart(@RequestBody NewRentRequestDTO rentReq){
        if(rentReq.getAgentId().size() == 0){
            return ResponseEntity.ok("Agent list is empty!");
        }
        if((Long)rentReq.getCustomerId() == null){
            return ResponseEntity.ok("Customer id is null!");
        }
        if(rentReq.getVehicleId().size() == 0){
            return ResponseEntity.ok("Vehicle list is empty!");
        }
        if(rentReq.getVehicleId().size() != rentReq.getAgentId().size()){
            return ResponseEntity.ok("There must be the same number of agents and vehicles!");
        }
        if(!this.userClient.getUserType(rentReq.getCustomerId()).equals("CUSTOMER")){
            return ResponseEntity.ok("You are not a customer!");
        }
        LocalDate startDate = LocalDate.parse(rentReq.getStartDate());
        LocalDate endDate = LocalDate.parse(rentReq.getEndDate());
//        TODO: Check if selected dates are valid and free + get agentID/check if its valid
//        for(Vehicle v : vehicleService.findAll()){
//            provera za vreme
//        }

        CheckAvailabiltyDTO dto = new CheckAvailabiltyDTO();

        List<Long> vidList = new ArrayList<Long>();
        for(Long vid : rentReq.getVehicleId()) {
            vidList.add(vid);
        }
        dto.setVehicleId(vidList);
        dto.setStartDate(rentReq.getStartDate());
        dto.setEndDate(rentReq.getEndDate());

        String responseGotten = advertisementClient.checkIfVehicleIsAvailable(dto);

        if(responseGotten.equals("false")){
            return ResponseEntity.ok("Desired period of time is not available for one of the selected vehicles!");
        }else if(!responseGotten.equals("true")){
            return ResponseEntity.ok(responseGotten);
        }


        HashMap<Long, List<RentRequest>> agentCounter = new HashMap<Long, List<RentRequest>>();

        for(int i=0; i<rentReq.getVehicleId().size(); i++){
            RentRequest rerq = new RentRequest();
            rerq.setVehicleId(rentReq.getVehicleId().get(i));
            rerq.setAgentId(rentReq.getAgentId().get(i));
            rerq.setRentalPeriod(new StartEnd(startDate, endDate));
            rerq.setCustomerId(rentReq.getCustomerId());
            rerq.setStatus(RentRequest.StatusReq.PENDING);
            if(agentCounter.containsKey(rentReq.getAgentId().get(i))){
                List<RentRequest> tempRq = agentCounter.get(rentReq.getAgentId().get(i));
                tempRq.add(rerq);
                agentCounter.put(rentReq.getAgentId().get(i), tempRq);
            }else{
                List<RentRequest> tempRq = new ArrayList<RentRequest>();
                tempRq.add(rerq);
                agentCounter.put(rentReq.getAgentId().get(i), tempRq);
            }
        }

        if(!rentReq.isSeparate()){
            for(Long key : agentCounter.keySet()){
                if(agentCounter.get(key).size() > 1){
                    BundleRent brent = new BundleRent();
                    brent.setRequests(new ArrayList<RentRequest>());
                    for(RentRequest rerq : agentCounter.get(key)){
                        brent.getRequests().add(rerq);
                    }
                    bundleRentService.save(brent);
                }else{
                    rentRequestService.save(agentCounter.get(key).get(0));
                }
            }
        }else{
            for(Long key : agentCounter.keySet()){
                for(RentRequest rerq : agentCounter.get(key)){
                    rentRequestService.save(rerq);
                }
            }
        }

        return ResponseEntity.ok("Successfully created new rent requests under state: PENDING.");
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptRentRequest(@RequestBody AcceptRequestDTO arDTO){
        if((Long)arDTO.getAgentId() == null){
            return ResponseEntity.ok("Agent id is null");
        }
        if((Long)arDTO.getRentreqId() == null){
            return ResponseEntity.ok("Please enter which rent request you want to accept");
        }
        RentRequest rrq = rentRequestService.findById(arDTO.getRentreqId());

        if(!this.userClient.getUserType(arDTO.getAgentId()).equals("AGENT")){
            return ResponseEntity.ok("You are not an agent!");
        }
        if(rrq.getAgentId() != arDTO.getAgentId()){
            return ResponseEntity.ok("This request does not belong to you!");
        }

        if(rrq.getBundle() == null){
            UpdateAvailabiltyDTO dto = new UpdateAvailabiltyDTO();
            dto.setAvailable(false);
            dto.setStartDate(rrq.getRentalPeriod().getStartTime().toString());
            dto.setEndDate(rrq.getRentalPeriod().getEndTime().toString());
            dto.setVehicleId(rrq.getVehicleId());
            String response = this.advertisementClient.updateVehicleAvailability(rrq.getAgentId(), dto);

            if(!response.equals("Successfully update availability of chosen vehicle!")){
                return ResponseEntity.ok(response);
            }
            RentHistory rh = new RentHistory(rrq);
            rh.setStatus(RentHistory.StatusHist.PAID);
            rentHistoryService.save(rh);
            rentRequestService.deleteById(rrq.getId());
            cancelRequests(new CancelRequestsDTO(rrq.getVehicleId(), rrq.getRentalPeriod().getStartTime().toString(),
                    rrq.getRentalPeriod().getEndTime().toString()));
        }else{
            List<RentRequest> reqs = rentRequestService.findByBundle(rrq.getBundle().getId());
            for(RentRequest req: reqs){
                UpdateAvailabiltyDTO dto = new UpdateAvailabiltyDTO();
                dto.setAvailable(false);
                dto.setStartDate(req.getRentalPeriod().getStartTime().toString());
                dto.setEndDate(req.getRentalPeriod().getEndTime().toString());
                dto.setVehicleId(req.getVehicleId());
                String response = this.advertisementClient.updateVehicleAvailability(req.getAgentId(), dto);

                if(!response.equals("Successfully update availability of chosen vehicle!")){
                    return ResponseEntity.ok(response);
                }
                RentHistory rh = new RentHistory(req);
                rh.setStatus(RentHistory.StatusHist.PAID);
                rentHistoryService.save(rh);
                rentRequestService.deleteById(req.getId());
                cancelRequests(new CancelRequestsDTO(req.getVehicleId(), req.getRentalPeriod().getStartTime().toString(),
                        req.getRentalPeriod().getEndTime().toString()));
            }
        }

        return ResponseEntity.ok("Successfully accepted selected rent requests!");
    }

    @PostMapping("/cancelRequests")
    public ResponseEntity<?> cancelRequests(@RequestBody CancelRequestsDTO dto){
        if((Long) dto.getVehicleId() != null && dto.getStartDate() != null && dto.getEndDate() != null) {
            LocalDate startDate = LocalDate.parse(dto.getStartDate());
            LocalDate endDate = LocalDate.parse(dto.getEndDate());

            for (RentRequest rq : rentRequestService.findByVehicleId(dto.getVehicleId())) {
                if (!(rq.getRentalPeriod().getEndTime().isBefore(startDate) || rq.getRentalPeriod().getStartTime().isAfter(endDate))) {
                    if (rq.getBundle() == null) {
                        rentRequestService.deleteById(rq.getId());
                        RentHistory rh = new RentHistory(rq);
                        rh.setStatus(RentHistory.StatusHist.CANCELED);
                        rentHistoryService.save(rh);
                    } else {
                        for (RentRequest rreq : rentRequestService.findByBundle(rq.getBundle().getId())) {
                            rentRequestService.deleteById(rreq.getId());
                            RentHistory rh = new RentHistory(rreq);
                            rh.setStatus(RentHistory.StatusHist.CANCELED);
                            rentHistoryService.save(rh);
                        }
                    }
                }
            }

            return ResponseEntity.ok("Requests that take part in selected period of time have been successfully canceled!");
        }else if((Long) dto.getReqId() != null){
            RentRequest rreq = rentRequestService.findById(dto.getReqId());
            RentHistory rh = new RentHistory(rreq);
            rh.setStatus(RentHistory.StatusHist.CANCELED);
            rentRequestService.deleteById(dto.getReqId());
            rentHistoryService.save(rh);

            return ResponseEntity.ok("Selected request has been canceled!");
        }else{
            return ResponseEntity.ok("Please enter all required information!");
        }

    }

    @PostMapping("/canmessage")
    public ResponseEntity<?> canMessage(@RequestBody MessageCommentDTO msgDTO){
        rentHistoryService.findAll();
        for(RentHistory rh : rentHistoryService.findAll()){
            if(rh.getStatus() == RentHistory.StatusHist.PAID &&
                    rh.getCustomerId() ==  msgDTO.getCustomerId() && rh.getAgentId() == msgDTO.getAgentId()){
                return ResponseEntity.ok(true);
            }
        }

        return ResponseEntity.ok(false);
    }

    @PostMapping("/cancomment")
    public ResponseEntity<?> canComment(@RequestBody MessageCommentDTO msgDTO){
        rentHistoryService.findAll();
        for(RentHistory rh : rentHistoryService.findAll()){
            if(rh.getStatus() == RentHistory.StatusHist.PAID &&
                    rh.getCustomerId() ==  msgDTO.getCustomerId() && rh.getVehicleId() == msgDTO.getVehicleId() &&
                    rh.getRentalPeriod().getEndTime().isBefore((new Timestamp(System.currentTimeMillis())).toLocalDateTime().toLocalDate())){
                return ResponseEntity.ok(true);
            }
        }

        return ResponseEntity.ok(false);
    }

    @GetMapping("/rentingHistory/noStatement/{id}")
    public ResponseEntity<?> getFinishedRentHistory(@PathVariable("id") String agentid){
        long id = Long.parseLong(agentid);
        List<RentHistoryGetDTO> rhlist = new ArrayList<RentHistoryGetDTO>();
        for(RentHistory rh : rentHistoryService.findByAgentId(id)){
            if(rh.getRentalPeriod().getEndTime().isBefore(
                    (new Timestamp(System.currentTimeMillis())).toLocalDateTime().toLocalDate())
                    && rh.getRentStatement()==null && rh.getStatus() == RentHistory.StatusHist.PAID){
                RentHistoryGetDTO rhdto = new RentHistoryGetDTO(rh);
                rhlist.add(rhdto);
            }
        }

        return ResponseEntity.ok(rhlist);
    }

    @PostMapping("/rentingHistory/statement")
    public ResponseEntity<?> addStatementToRentHistory(@RequestBody StatementDTO dto){
        if((Long) dto.getRenthistId() == null){
            return ResponseEntity.ok("Please enter which rent history item you want to make a statement for!");
        }
        if(dto.getStatement() == null){
            return ResponseEntity.ok("Please enter your statement!");
        }
        if((Double) dto.getTravelledKm() == null){
            return ResponseEntity.ok("Please enter travelled kilometers!");
        }

        RentHistory rh = rentHistoryService.findById(dto.getRenthistId());
        RentStatement rs = new RentStatement(dto.getTravelledKm(), dto.getStatement());
        rh.setRentStatement(rs);
        rentHistoryService.save(rh);

        return ResponseEntity.ok("Successfully updated statement of selected rent history!");

    }

}
