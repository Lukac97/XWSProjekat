package renting.rentingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import renting.rentingservice.domain.BundleRent;
import renting.rentingservice.domain.Cart;
import renting.rentingservice.domain.RentRequest;
import renting.rentingservice.domain.StartEnd;
import renting.rentingservice.dto.NewCartDTO;
import renting.rentingservice.dto.NewRentRequestDTO;
import renting.rentingservice.service.BundleRentService;
import renting.rentingservice.service.CartService;
import renting.rentingservice.service.RentRequestService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class RentRequestController {

    private CartService cartService;
    private RentRequestService rentRequestService;
    private BundleRentService bundleRentService;

    @Autowired
    public RentRequestController(
            CartService cartService,
            RentRequestService rentRequestService,
            BundleRentService bundleRentService
    ) {
        this.cartService = cartService;
        this.rentRequestService = rentRequestService;
        this.bundleRentService = bundleRentService;
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
        LocalDate startDate = LocalDate.parse(rentReq.getStartDate());
        LocalDate endDate = LocalDate.parse(rentReq.getEndDate());
//        TODO: Check if selected dates are valid and free + get agentID/check if its valid
//        for(Vehicle v : vehicleService.findAll()){
//            provera za vreme
//        }

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
}
