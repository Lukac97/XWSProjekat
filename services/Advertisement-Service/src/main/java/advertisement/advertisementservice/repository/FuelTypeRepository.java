package advertisement.advertisementservice.repository;

import advertisement.advertisementservice.domain.FuelType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelTypeRepository extends CrudRepository<FuelType, Long> {
    FuelType findById(long id);
    FuelType findByFuelType(String fuelType);
    List<FuelType> findAll();

    FuelType save(FuelType fuelType);

    @Modifying
    void deleteById(long id);

}
