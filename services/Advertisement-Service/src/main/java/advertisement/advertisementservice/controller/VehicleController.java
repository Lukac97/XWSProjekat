package advertisement.advertisementservice.controller;

import advertisement.advertisementservice.client.UserClient;
import advertisement.advertisementservice.domain.*;
import advertisement.advertisementservice.dto.CheckAvailabiltyDTO;
import advertisement.advertisementservice.dto.CreateAdDTO;
import advertisement.advertisementservice.dto.UpdateAvailabiltyDTO;
import advertisement.advertisementservice.dto.VehicleSearchDTO;
import advertisement.advertisementservice.service.*;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleController {
    private VehicleService vehicleService;
    private BrandModelService brandModelService;
    private FuelTypeService fuelTypeService;
    private TransmissionTypeService transmissionTypeService;
    private VehicleClassService vehicleClassService;
    private PricelistService pricelistService;
    private AvailableDatesService availableDatesService;
    private UserClient userClient;

    @Autowired
    public VehicleController (
            VehicleService vehicleService,
            BrandModelService brandModelService,
            FuelTypeService fuelTypeService,
            TransmissionTypeService transmissionTypeService,
            VehicleClassService vehicleClassService,
            PricelistService pricelistService,
            AvailableDatesService availableDatesService,
            UserClient userClient
    ) {
        this.vehicleService = vehicleService;
        this.brandModelService = brandModelService;
        this.fuelTypeService = fuelTypeService;
        this.transmissionTypeService = transmissionTypeService;
        this.vehicleClassService = vehicleClassService;
        this.pricelistService = pricelistService;
        this.availableDatesService = availableDatesService;
        this.userClient = userClient;
    }


    @PostMapping("/test")
    public ResponseEntity<?> testTest(){
        return ResponseEntity.ok("test test test TEST test TEST");
    }

    @GetMapping("/containertest")
    public String containerTest(){
        return "THIS IS VEHICLE_CONTROLLER!!!!!!!!!!1";
    }

    @PostMapping("/createNewAd/{id}")
    public ResponseEntity<?> createNewAd(@PathVariable("id") long id, @RequestBody CreateAdDTO newAd){
        if((Long) id == null){
            return ResponseEntity.ok("You are not logged in!");
        }
        if(!userClient.getUserType(id).equals("AGENT")) {
            return ResponseEntity.ok("You do not have the permission to do this!");
        }
        if(newAd.getModel() == null || newAd.getFuelType() == null || newAd.getTransmissionType() == null ||
                newAd.getVehicleClass() == null ){
            return ResponseEntity.ok("Please fill out all required fields!");
        }
        if(newAd.getStartDates() == null || newAd.getEndDates()==null ||
                newAd.getStartDates().size()!=newAd.getEndDates().size() || newAd.getStartDates().size()==0 ||
                newAd.getEndDates().size()==0){
            return ResponseEntity.ok("Please arrange vehicle availability properly!");
        }
        Vehicle v = new Vehicle();
        v.setAgentId(id);
        v.setVehicleBrandModel(brandModelService.findByModel(newAd.getModel()));
        v.setFuelType(fuelTypeService.findByFuelType(newAd.getFuelType()));
        v.setTransmissionType(transmissionTypeService.findByTransmissionType(newAd.getTransmissionType()));
        v.setVehicleClass(vehicleClassService.findByVehicleClass(newAd.getVehicleClass()));

        v.setMileage(newAd.getMileage());
        v.setCdwProtection(newAd.isCdwProtection());
        v.setLocation(newAd.getLocation());
        v.setSeats(newAd.getSeats());
        v.setChildrenSeats(newAd.getChildrenSeats());
        v.setMaxKm(newAd.getMaxKm());
        v.setVehicleAvailability(new ArrayList<AvailableDates>());
        for(int i = 0; i < newAd.getStartDates().size(); i++){
            v.getVehicleAvailability().add(new AvailableDates(
                    LocalDate.parse(newAd.getStartDates().get(i)),
                    LocalDate.parse(newAd.getEndDates().get(i))
            ));
        }

        if(newAd.getPricelistId() != null){
            v.setVehiclePricelist(pricelistService.findById(Long.parseLong(newAd.getPricelistId())));
        }

        vehicleService.save(v);

        return ResponseEntity.ok("New ad has been created!");
    }

    @PostMapping("/isAvailable")
    public ResponseEntity<?> checkIfVehicleIsAvailable(@RequestBody CheckAvailabiltyDTO checkDTO){
        if(checkDTO.getVehicleId() == null){
            return ResponseEntity.ok("No vehicle ids");
        }
        if(checkDTO.getVehicleId().size() == 0){
            return ResponseEntity.ok("List empty");
        }
        if(checkDTO.getEndDate() == null){
            return ResponseEntity.ok("No end date");
        }
        if(checkDTO.getStartDate() == null){
            return ResponseEntity.ok("No start date");
        }
        LocalDate startDate = LocalDate.parse(checkDTO.getStartDate());
        LocalDate endDate = LocalDate.parse(checkDTO.getEndDate());

        boolean available = true;

        for(long vid : checkDTO.getVehicleId()){
            if(vehicleService.findById(vid) == null){
                return ResponseEntity.ok("One of the vehicles doesnt have that id");
            }
            Vehicle v = vehicleService.findById(vid);
            if(!available){
                break;
            }
            if(v.getVehicleAvailability() != null) {
                for (AvailableDates ad : v.getVehicleAvailability()) {
                    if (endDate.isBefore(ad.getStartDate()) || startDate.isAfter(ad.getEndDate())) {
                        available = false;
                        break;
                    }
                }
            }
        }

        String result = "true";
        if(!available){
            result = "false";
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateAvailability/{id}")
    public ResponseEntity<?> updateVehicleAvailability(@PathVariable("id") long id, @RequestBody UpdateAvailabiltyDTO updateDTO){
        if(!userClient.getUserType(id).equals("AGENT")) {
            return ResponseEntity.ok("You do not have the permission to do this!");
        }
        if(updateDTO.getStartDate() == null || updateDTO.getEndDate() == null){
            return ResponseEntity.ok("Please enter start and end date!");
        }
        if(LocalDate.parse(updateDTO.getStartDate()).isAfter(LocalDate.parse(updateDTO.getEndDate()))){
            return ResponseEntity.ok("Wrong dates entered!");
        }
        if((Long) updateDTO.getVehicleId() == null){
            return ResponseEntity.ok("No vehicle entered!");
        }

        Vehicle v = vehicleService.findById(updateDTO.getVehicleId());

        if(v.getAgentId() != id){
            return ResponseEntity.ok("This ad does not belong to you!");
        }
        
        LocalDate updateStartDate = LocalDate.parse(updateDTO.getStartDate());
        LocalDate updateEndDate = LocalDate.parse(updateDTO.getEndDate());

        AvailableDates newAvDa = new AvailableDates();
        boolean foundStart = false;
        boolean foundEnd = false;

        if(updateDTO.isAvailable()) {
            for (AvailableDates avda : v.getVehicleAvailability()) {
                if(updateStartDate.isAfter(avda.getStartDate()) && updateStartDate.isBefore(avda.getEndDate())
                        && updateEndDate.isAfter(avda.getEndDate())){
                    newAvDa.setStartDate(avda.getStartDate());
                    v.getVehicleAvailability().remove(avda);
                    foundStart = true;
                    //----
                }else if(updateStartDate.isBefore(avda.getStartDate())
                        && updateEndDate.isAfter(avda.getStartDate()) && updateEndDate.isBefore(avda.getEndDate())){
                    newAvDa.setEndDate(avda.getEndDate());
                    v.getVehicleAvailability().remove(avda);
                    foundEnd = true;
                }else if(updateStartDate.isAfter(avda.getStartDate()) && updateStartDate.isBefore(avda.getEndDate())
                        && updateEndDate.isBefore(avda.getEndDate())){
                    return ResponseEntity.ok("Interval of time already available!");
                }else if((updateStartDate.isBefore(avda.getStartDate()) || updateStartDate.isEqual(avda.getStartDate()))
                        && (updateEndDate.isAfter(avda.getEndDate()) || updateEndDate.isEqual(avda.getEndDate()))){
                    v.getVehicleAvailability().remove(avda);
                }
            }

            if(!foundStart){
                newAvDa.setStartDate(updateStartDate);
            }
            if(!foundEnd){
                newAvDa.setEndDate(updateEndDate);
            }
            v.getVehicleAvailability().add(newAvDa);
        }else{
            if(v.getVehicleAvailability()==null){
                return ResponseEntity.ok("vehicle availability je null");
            }
            if(v.getVehicleAvailability().size()==0){
                return ResponseEntity.ok("No availability defined");
            }
            List<AvailableDates> avda = v.getVehicleAvailability();
            List<AvailableDates> avdanew = new ArrayList<AvailableDates>();
            for (int i=0; i<avda.size(); i++) {

                if(updateStartDate.isAfter(avda.get(i).getStartDate()) && updateStartDate.isBefore(avda.get(i).getEndDate())
                        && updateEndDate.isBefore(avda.get(i).getEndDate())){
                    avdanew.add(new AvailableDates(avda.get(i).getStartDate(),updateStartDate));
                    avdanew.add(new AvailableDates(updateEndDate, avda.get(i).getEndDate()));
                    break;
                }else if(updateStartDate.isAfter(avda.get(i).getEndDate()) || updateEndDate.isBefore(avda.get(i).getStartDate())){
                    avdanew.add(avda.get(i));
                }else{
                    return ResponseEntity.ok("Wrong start/end dates");
                }
            }
            v.setVehicleAvailability(avdanew);
        }
        vehicleService.save(v);

        return ResponseEntity.ok("Successfully update availability of chosen vehicle!");
    }

    @PostMapping("/searchVehicles")
    public ResponseEntity<?> basicSearch(@RequestBody VehicleSearchDTO searchParams){
        List<Vehicle> vehicleList = vehicleService.findAll();
        List<VehicleSearchDTO> searchList = new ArrayList<VehicleSearchDTO>();


        if(searchParams.getLocation()==null){
            return ResponseEntity.ok("Please enter the location.");
        }
        if(searchParams.getStartDates().size() == 0 || searchParams.getEndDates().size() == 0){
            return ResponseEntity.ok("Please fill out the time interval.");
        }
        if(LocalDate.parse(searchParams.getStartDates().get(0)).atStartOfDay().isBefore(LocalDateTime.now().plusDays(2))){
            return ResponseEntity.ok("You need to select starting date at least 48 hours from now.");
        }

        LocalDate reqStart = LocalDate.parse(searchParams.getStartDates().get(0));
        LocalDate reqEnd = LocalDate.parse(searchParams.getEndDates().get(0));

        for(Vehicle v : vehicleList){
            if(searchParams.getLocation().equals(v.getLocation())){
                boolean available = false;
                for(AvailableDates avda : v.getVehicleAvailability()){
                    if((reqStart.isAfter(avda.getStartDate()) || reqStart.isEqual(avda.getStartDate()))
                            && (reqEnd.isBefore(avda.getEndDate()) || reqEnd.isEqual(avda.getEndDate()))){
                        available = true;
                        break;
                    }
                }
                if(available){
                    searchList.add(new VehicleSearchDTO(v));
                }
            }
        }

        return ResponseEntity.ok(searchList);

    }

    @GetMapping("/getVehicle/{id}")
    public ResponseEntity<?> getVehicleFromAd(@PathVariable("id") String id){
        return ResponseEntity.ok(new VehicleSearchDTO(vehicleService.findById(Long.parseLong(id))));

    }

    @PostMapping("/newModel/{id}")
    public ResponseEntity<?> createNewModel(@PathVariable("id") long id, @RequestBody BrandModel bm){
        if(!userClient.getUserType(id).equals("ADMINISTRATOR")){
            return ResponseEntity.ok("You are not the administrator!");
        }

        for(BrandModel brandmod : brandModelService.findAll()){
            if(brandmod.getModel().equals(bm.getModel())){
                return ResponseEntity.ok("Model already exists!");
            }
        }
        brandModelService.save(bm);
        return ResponseEntity.ok("Successfully created new model!");
    }

    @PostMapping("/newClass/{id}")
    public ResponseEntity<?> createNewVehicleClass(@PathVariable("id") long id, @RequestBody VehicleClass vc){
        if(!userClient.getUserType(id).equals("ADMINISTRATOR")){
            return ResponseEntity.ok("You are not the administrator!");
        }

        for(VehicleClass vehcla : vehicleClassService.findAll()){
            if(vehcla.getVehicleClass().equals(vc.getVehicleClass())){
                return ResponseEntity.ok("Class already exists!");
            }
        }
        vehicleClassService.save(vc);
        return ResponseEntity.ok("Successfully created new class!");
    }

    @PostMapping("/newFuel/{id}")
    public ResponseEntity<?> createNewFuelType(@PathVariable("id") long id, @RequestBody FuelType ft){
        if(!userClient.getUserType(id).equals("ADMINISTRATOR")){
            return ResponseEntity.ok("You are not the administrator!");
        }

        for(FuelType fuelt : fuelTypeService.findAll()){
            if(fuelt.getFuelType().equals(ft.getFuelType())){
                return ResponseEntity.ok("Fuel already exists!");
            }
        }
        fuelTypeService.save(ft);
        return ResponseEntity.ok("Successfully created new fuel!");
    }

    @PostMapping("/newTransmission/{id}")
    public ResponseEntity<?> createNewTransmissionType(@PathVariable("id") long id, @RequestBody TransmissionType tt){
        if(!userClient.getUserType(id).equals("ADMINISTRATOR")){
            return ResponseEntity.ok("You are not the administrator!");
        }

        for(TransmissionType transt : transmissionTypeService.findAll()){
            if(transt.getTransmissionType().equals(tt.getTransmissionType())){
                return ResponseEntity.ok("Transmission already exists!");
            }
        }
        transmissionTypeService.save(tt);
        return ResponseEntity.ok("Successfully created new transmission!");
    }

//    @PostMapping("/advancedSearchVehicles")
//    public ResponseEntity<?> vehicleAdSearch(@RequestBody VehicleSearchDTO searchParams){
//        List<Vehicle> vehicleList = vehicleService.findAll();
//        List<VehicleSearchDTO> searchList = new ArrayList<VehicleSearchDTO>();
//
//        for(Vehicle v : vehicleList){
//            if((searchParams.getModel()==null || searchParams.getModel().equals(v.getVehicleBrandModel().getModel()))
//                    && (searchParams.getFuelType()==null || searchParams.getFuelType().equals(v.getFuelType().getFuelType()))
//                    && (searchParams.getTransmissionType()==null || searchParams.getTransmissionType().equals(v.getTransmissionType().getTransmissionType()))
//                    && (searchParams.){
//
//            }
//        }
//
//
//        return ResponseEntity.ok("GET MEASJEOANMIPSR");
//    }
}
