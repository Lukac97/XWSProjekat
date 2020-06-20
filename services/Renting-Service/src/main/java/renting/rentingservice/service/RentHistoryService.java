package renting.rentingservice.service;

import org.springframework.stereotype.Service;
import renting.rentingservice.domain.RentHistory;
import renting.rentingservice.repository.RentHistoryRepository;

import java.util.List;

@Service
public class RentHistoryService {

    private RentHistoryRepository rentHistoryRepository;

    public RentHistoryService(
            RentHistoryRepository rentHistoryRepository
    ) {
        this.rentHistoryRepository = rentHistoryRepository;
    }

    public RentHistory save(RentHistory rentHistory) {
        return this.rentHistoryRepository.save(rentHistory);
    }

    public RentHistory findById(long id){
        return this.rentHistoryRepository.findById(id);
    }

    public List<RentHistory> findByAgentId(long agentId){
        return this.rentHistoryRepository.findByAgentId(agentId);
    }

    public List<RentHistory> findAll(){
        return this.rentHistoryRepository.findAll();
    }

    public void deleteById(long id){
        this.rentHistoryRepository.deleteById(id);
    }

}
