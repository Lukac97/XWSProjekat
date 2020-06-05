package advertisement.advertisementservice.controller;

import advertisement.advertisementservice.domain.*;
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

    @Autowired
    public VehicleController (
            VehicleService vehicleService,
            BrandModelService brandModelService,
            FuelTypeService fuelTypeService,
            TransmissionTypeService transmissionTypeService,
            VehicleClassService vehicleClassService,
            PricelistService pricelistService,
            AvailableDatesService availableDatesService
    ) {
        this.vehicleService = vehicleService;
        this.brandModelService = brandModelService;
        this.fuelTypeService = fuelTypeService;
        this.transmissionTypeService = transmissionTypeService;
        this.vehicleClassService = vehicleClassService;
        this.pricelistService = pricelistService;
        this.availableDatesService = availableDatesService;
    }


    @PostMapping("/test")
    public ResponseEntity<?> testTest(){
        return ResponseEntity.ok("test test test TEST test TEST");
    }

    @PostMapping("/createNewAd/{id}")
    public ResponseEntity<?> createNewAd(@PathVariable("id") String id, @RequestBody CreateAdDTO newAd){
        Vehicle v = new Vehicle();
        v.setAgentId(Long.parseLong(id));
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

    @PostMapping("/updateAvailability/{id}")
    public ResponseEntity<?> updateVehicleAvailability(@PathVariable("id") String id, @RequestBody UpdateAvailabiltyDTO updateDTO){
        long agentId = Long.parseLong(id);
        Vehicle v = vehicleService.findById(updateDTO.getVehicleId());
        
        if(LocalDate.parse(updateDTO.getStartDate()).isAfter(LocalDate.parse(updateDTO.getEndDate()))){
            return ResponseEntity.ok("Wrong dates entered!");
        }
        
        if(v.getAgentId() != agentId){
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
            for (AvailableDates avda : v.getVehicleAvailability()) {
                if(updateStartDate.isAfter(avda.getStartDate()) && updateStartDate.isBefore(avda.getEndDate())
                        && updateEndDate.isAfter(avda.getEndDate())){
                    avda.setEndDate(updateStartDate);
                    //----
                }else if(updateStartDate.isBefore(avda.getStartDate())
                        && updateEndDate.isAfter(avda.getStartDate()) && updateEndDate.isBefore(avda.getEndDate())){
                    avda.setStartDate(updateEndDate);
                }else if(updateStartDate.isAfter(avda.getStartDate()) && updateStartDate.isBefore(avda.getEndDate())
                        && updateEndDate.isBefore(avda.getEndDate())){
                    v.getVehicleAvailability().remove(avda);
                    v.getVehicleAvailability().add(new AvailableDates(avda.getStartDate(),updateStartDate));
                    v.getVehicleAvailability().add(new AvailableDates(updateEndDate, avda.getEndDate()));
                }else if((updateStartDate.isBefore(avda.getStartDate()) || updateStartDate.isEqual(avda.getStartDate()))
                        && (updateEndDate.isAfter(avda.getEndDate()) || updateEndDate.isEqual(avda.getEndDate()))){
                    v.getVehicleAvailability().remove(avda);
                }
            }
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
    public ResponseEntity<?> createNewModel(@RequestBody BrandModel bm){
        for(BrandModel brandmod : brandModelService.findAll()){
            if(brandmod.getModel().equals(bm.getModel())){
                return ResponseEntity.ok("Model already exists!");
            }
        }
        brandModelService.save(bm);
        return ResponseEntity.ok("Successfully created new model!");
    }

    @PostMapping("/newClass/{id}")
    public ResponseEntity<?> createNewVehicleClass(@RequestBody VehicleClass vc){
        for(VehicleClass vehcla : vehicleClassService.findAll()){
            if(vehcla.getVehicleClass().equals(vc.getVehicleClass())){
                return ResponseEntity.ok("Class already exists!");
            }
        }
        vehicleClassService.save(vc);
        return ResponseEntity.ok("Successfully created new class!");
    }

    @PostMapping("/newFuel/{id}")
    public ResponseEntity<?> createNewFuelType(@RequestBody FuelType ft){
        for(FuelType fuelt : fuelTypeService.findAll()){
            if(fuelt.getFuelType().equals(ft.getFuelType())){
                return ResponseEntity.ok("Fuel already exists!");
            }
        }
        fuelTypeService.save(ft);
        return ResponseEntity.ok("Successfully created new fuel!");
    }

    @PostMapping("/newTransmission/{id}")
    public ResponseEntity<?> createNewTransmissionType(@RequestBody TransmissionType tt){
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
