package advertisement.advertisementservice.service;

import advertisement.advertisementservice.domain.FuelType;
import advertisement.advertisementservice.repository.FuelTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelTypeService {

    private FuelTypeRepository fuelTypeRepository;

    public FuelTypeService(
            FuelTypeRepository fuelTypeRepository
    ) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    public FuelType save(FuelType fuelType) {
        return this.fuelTypeRepository.save(fuelType);
    }

    public FuelType findById(long id){
        return this.fuelTypeRepository.findById(id);
    }

    public FuelType findByFuelType(String fuelType){
        return this.fuelTypeRepository.findByFuelType(fuelType);
    }

    public List<FuelType> findAll(){
        return this.fuelTypeRepository.findAll();
    }

    public void deleteById(long id){
        this.fuelTypeRepository.deleteById(id);
    }

}
