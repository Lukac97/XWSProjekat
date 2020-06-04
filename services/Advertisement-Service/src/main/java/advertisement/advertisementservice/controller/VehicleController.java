package advertisement.advertisementservice.controller;

import advertisement.advertisementservice.repository.CreateAdDTO;
import advertisement.advertisementservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController (
            VehicleService vehicleService
    ) {
        this.vehicleService = vehicleService;
    }


    @PostMapping("/test")
    public ResponseEntity<?> testTest(){
        return ResponseEntity.ok("test test test TEST test TEST");
    }

    @PostMapping("/createNewAd/{id}")
    public ResponseEntity<?> createNewAd(@PathVariable("id") String id, @RequestBody CreateAdDTO newAd){
        return ResponseEntity.ok("POST METODA");
    }

    @GetMapping("/getTest")
    public ResponseEntity<?> getTest(){
        return ResponseEntity.ok("GET MEASJEOANMIPSR");
    }
}
