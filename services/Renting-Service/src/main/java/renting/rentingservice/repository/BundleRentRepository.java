package renting.rentingservice.repository;

import renting.rentingservice.domain.BundleRent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BundleRentRepository extends CrudRepository<BundleRent, Long> {
    BundleRent findById(long id);
    List<BundleRent> findAll();

    BundleRent save(BundleRent bundleRent);

    @Modifying
    void deleteById(long id);

}
