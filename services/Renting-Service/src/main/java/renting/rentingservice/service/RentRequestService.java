package renting.rentingservice.service;

import org.springframework.stereotype.Service;
import renting.rentingservice.domain.RentRequest;
import renting.rentingservice.repository.RentRequestRepository;

import java.util.List;

@Service
public class RentRequestService {

    private RentRequestRepository rentRequestRepository;

    public RentRequestService(
            RentRequestRepository rentRequestRepository
    ) {
        this.rentRequestRepository = rentRequestRepository;
    }

    public RentRequest save(RentRequest rentRequest) {
        return this.rentRequestRepository.save(rentRequest);
    }

    public RentRequest findById(long id){
        return this.rentRequestRepository.findById(id);
    }

    public List<RentRequest> findAll(){
        return this.rentRequestRepository.findAll();
    }

    public void deleteById(long id){
        this.rentRequestRepository.deleteById(id);
    }

}
