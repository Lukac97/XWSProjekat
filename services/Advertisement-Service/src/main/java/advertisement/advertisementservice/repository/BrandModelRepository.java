package advertisement.advertisementservice.repository;

import advertisement.advertisementservice.domain.BrandModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandModelRepository extends CrudRepository<BrandModel, Long> {
    BrandModel findById(long id);
    BrandModel findByModel(String model);
    List<BrandModel> findAll();

    BrandModel save(BrandModel brandModel);

    @Modifying
    void deleteById(long id);

}
