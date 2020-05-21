package renting.rentingservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import renting.rentingservice.domain.RentHistory;

import java.util.List;

@Repository
public interface RentHistoryRepository extends CrudRepository<RentHistory, Long> {
    RentHistory findById(long id);
    List<RentHistory> findAll();

    RentHistory save(RentHistory rentHistory);

    @Modifying
    void deleteById(long id);

}
