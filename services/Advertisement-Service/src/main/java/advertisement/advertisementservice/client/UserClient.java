package advertisement.advertisementservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user", url = "localhost:8080/user")
public interface UserClient {
    @GetMapping("/type/{id}")
    public String getUserType(@PathVariable("id") long id);
}
