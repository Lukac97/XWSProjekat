package renting.rentingservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import renting.rentingservice.domain.RentRequest;

import java.util.List;

@Repository
public interface RentRequestRepository extends CrudRepository<RentRequest, Long> {
    RentRequest findById(long id);
    List<RentRequest> findAll();

    RentRequest save(RentRequest rentRequest);

    @Modifying
    void deleteById(long id);

}
