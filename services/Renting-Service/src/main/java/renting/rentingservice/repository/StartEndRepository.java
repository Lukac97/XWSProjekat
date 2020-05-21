package renting.rentingservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import renting.rentingservice.domain.StartEnd;

import java.util.List;

@Repository
public interface StartEndRepository extends CrudRepository<StartEnd, Long> {
    StartEnd findById(long id);
    List<StartEnd> findAll();

    StartEnd save(StartEnd startEnd);

    @Modifying
    void deleteById(long id);

}
