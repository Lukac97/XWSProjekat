package advertisement.advertisementservice.service;

import advertisement.advertisementservice.domain.VehicleClass;
import advertisement.advertisementservice.repository.VehicleClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleClassService {

    private VehicleClassRepository vehicleClassRepository;

    public VehicleClassService(
            VehicleClassRepository vehicleClassRepository
    ) {
        this.vehicleClassRepository = vehicleClassRepository;
    }

    public VehicleClass save(VehicleClass vehicleClass) {
        return this.vehicleClassRepository.save(vehicleClass);
    }

    public VehicleClass findById(long id){
        return this.vehicleClassRepository.findById(id);
    }

    public VehicleClass findByVehicleClass(String vehicleClass){
        return this.vehicleClassRepository.findByVehicleClass(vehicleClass);
    }

    public List<VehicleClass> findAll(){
        return this.vehicleClassRepository.findAll();
    }

    public void deleteById(long id){
        this.vehicleClassRepository.deleteById(id);
    }

}
