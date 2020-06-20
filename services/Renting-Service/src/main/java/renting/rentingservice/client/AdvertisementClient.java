package renting.rentingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "advertisement")
public interface AdvertisementClient {
    @GetMapping("/containertest")
    String containerTest();
}
