package advertisement.advertisementservice.service;

import advertisement.advertisementservice.domain.TransmissionType;
import advertisement.advertisementservice.repository.TransmissionTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransmissionTypeService {

    private TransmissionTypeRepository transmissionTypeRepository;

    public TransmissionTypeService(
            TransmissionTypeRepository transmissionTypeRepository
    ) {
        this.transmissionTypeRepository = transmissionTypeRepository;
    }

    public TransmissionType save(TransmissionType transmissionType) {
        return this.transmissionTypeRepository.save(transmissionType);
    }

    public TransmissionType findById(long id){
        return this.transmissionTypeRepository.findById(id);
    }

    public TransmissionType findByTransmissionType(String transmissionType){
        return this.transmissionTypeRepository.findByTransmissionType(transmissionType);
    }

    public List<TransmissionType> findAll(){
        return this.transmissionTypeRepository.findAll();
    }

    public void deleteById(long id){
        this.transmissionTypeRepository.deleteById(id);
    }

}
