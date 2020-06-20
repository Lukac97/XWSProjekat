package renting.rentingservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import renting.rentingservice.domain.RentStatement;

import java.util.List;

@Repository
public interface RentStatementRepository extends CrudRepository<RentStatement, Long> {
    RentStatement findById(long id);
    List<RentStatement> findAll();

    RentStatement save(RentStatement rentStatement);

    @Modifying
    void deleteById(long id);

}
