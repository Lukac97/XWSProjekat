package advertisement.advertisementservice.service;

import advertisement.advertisementservice.domain.Vehicle;
import advertisement.advertisementservice.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(
            VehicleRepository vehicleRepository
    ) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle save(Vehicle vehicle) {
        return this.vehicleRepository.save(vehicle);
    }

    public Vehicle findById(long id){
        return this.vehicleRepository.findById(id);
    }

    public List<Vehicle> findAll(){
        return this.vehicleRepository.findAll();
    }

    public void deleteById(long id){
        this.vehicleRepository.deleteById(id);
    }

}
