package advertisement.advertisementservice.repository;

import advertisement.advertisementservice.domain.AvailableDates;
import advertisement.advertisementservice.domain.Pricelist;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricelistRepository extends CrudRepository<Pricelist, Long> {
    Pricelist findById(long id);
    List<Pricelist> findAll();

    Pricelist save(Pricelist pricelist);

    @Modifying
    void deleteById(long id);

}
