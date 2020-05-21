package renting.rentingservice.service;

import renting.rentingservice.domain.BundleRent;
import renting.rentingservice.repository.BundleRentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BundleRentService {

    private BundleRentRepository bundleRentRepository;

    public BundleRentService(
            BundleRentRepository bundleRentRepository
    ) {
        this.bundleRentRepository = bundleRentRepository;
    }

    public BundleRent save(BundleRent bundleRent) {
        return this.bundleRentRepository.save(bundleRent);
    }

    public BundleRent findById(long id){
        return this.bundleRentRepository.findById(id);
    }

    public List<BundleRent> findAll(){
        return this.bundleRentRepository.findAll();
    }

    public void deleteById(long id){
        this.bundleRentRepository.deleteById(id);
    }

}
