package renting.rentingservice.service;

import org.springframework.stereotype.Service;
import renting.rentingservice.domain.StartEnd;
import renting.rentingservice.repository.StartEndRepository;

import java.util.List;

@Service
public class StartEndService {

    private StartEndRepository startEndRepository;

    public StartEndService(
            StartEndRepository startEndRepository
    ) {
        this.startEndRepository = startEndRepository;
    }

    public StartEnd save(StartEnd startEnd) {
        return this.startEndRepository.save(startEnd);
    }

    public StartEnd findById(long id){
        return this.startEndRepository.findById(id);
    }

    public List<StartEnd> findAll(){
        return this.startEndRepository.findAll();
    }

    public void deleteById(long id){
        this.startEndRepository.deleteById(id);
    }

}
