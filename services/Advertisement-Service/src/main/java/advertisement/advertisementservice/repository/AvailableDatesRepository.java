package advertisement.advertisementservice.repository;

import advertisement.advertisementservice.domain.AvailableDates;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableDatesRepository extends CrudRepository<AvailableDates, Long> {
    AvailableDates findById(long id);
    List<AvailableDates> findAll();

    AvailableDates save(AvailableDates availableDates);

    @Modifying
    void deleteById(long id);

}
