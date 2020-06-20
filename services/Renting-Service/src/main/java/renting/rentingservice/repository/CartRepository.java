package renting.rentingservice.repository;

import renting.rentingservice.domain.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Cart findById(long id);
    List<Cart> findByVehicleId(long vehicleId);
    List<Cart> findByCustomerId(long customerId);
    List<Cart> findAll();

    Cart save(Cart cart);

    @Modifying
    void deleteById(long id);

}
