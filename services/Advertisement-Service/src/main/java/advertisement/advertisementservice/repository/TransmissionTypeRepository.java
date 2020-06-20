package advertisement.advertisementservice.repository;

import advertisement.advertisementservice.domain.TransmissionType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransmissionTypeRepository extends CrudRepository<TransmissionType, Long> {
    TransmissionType findById(long id);
    TransmissionType findByTransmissionType(String transmissionType);
    List<TransmissionType> findAll();

    TransmissionType save(TransmissionType transmissionType);

    @Modifying
    void deleteById(long id);

}
