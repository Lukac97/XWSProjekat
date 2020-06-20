package advertisement.advertisementservice.repository;

import advertisement.advertisementservice.domain.VehicleClass;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleClassRepository extends CrudRepository<VehicleClass, Long> {
    VehicleClass findById(long id);
    VehicleClass findByVehicleClass(String vehicleClass);
    List<VehicleClass> findAll();

    VehicleClass save(VehicleClass vehicleClass);

    @Modifying
    void deleteById(long id);

}
