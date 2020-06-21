package renting.rentingservice.client;

import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import renting.rentingservice.dto.CheckAvailabiltyDTO;
import renting.rentingservice.dto.UpdateAvailabiltyDTO;

@FeignClient(name = "advertisement")
public interface AdvertisementClient {
    @GetMapping("/containertest")
    String containerTest();

    @PostMapping("/isAvailable")
    String checkIfVehicleIsAvailable(@RequestBody CheckAvailabiltyDTO checkDTO);

    @PostMapping("/updateAvailability/{id}")
    public String updateVehicleAvailability(@PathVariable("id") long id, @RequestBody UpdateAvailabiltyDTO updateDTO);
}
