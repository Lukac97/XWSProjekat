package renting.rentingservice.service;

import org.springframework.stereotype.Service;
import renting.rentingservice.domain.RentStatement;
import renting.rentingservice.repository.RentStatementRepository;

import java.util.List;

@Service
public class RentStatementService {

    private RentStatementRepository rentStatementRepository;

    public RentStatementService(
            RentStatementRepository rentStatementRepository
    ) {
        this.rentStatementRepository = rentStatementRepository;
    }

    public RentStatement save(RentStatement rentStatement) {
        return this.rentStatementRepository.save(rentStatement);
    }

    public RentStatement findById(long id){
        return this.rentStatementRepository.findById(id);
    }

    public List<RentStatement> findAll(){
        return this.rentStatementRepository.findAll();
    }

    public void deleteById(long id){
        this.rentStatementRepository.deleteById(id);
    }

}
