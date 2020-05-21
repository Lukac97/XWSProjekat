package advertisement.advertisementservice.repository;

import advertisement.advertisementservice.domain.Vehicle;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    Vehicle findById(long id);
    List<Vehicle> findAll();

    Vehicle save(Vehicle vehicle);

    @Modifying
    void deleteById(long id);

}
