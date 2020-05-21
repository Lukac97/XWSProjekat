package user.userservice.service;

import org.springframework.stereotype.Service;
import user.userservice.domain.Administrator;
import user.userservice.repository.AdministratorRepository;

import java.util.List;

@Service
public class AdministratorService {

    private AdministratorRepository administratorRepository;

    public AdministratorService(
            AdministratorRepository administratorRepository
    ) {
        this.administratorRepository = administratorRepository;
    }

    public Administrator save(Administrator administrator) {
        return this.administratorRepository.save(administrator);
    }

    public boolean existsById(Long consumerId) {
        if (!this.administratorRepository.existsById(consumerId)) {
            return false;
        }
        return true;
    }

    public Administrator findById(long id){
        return this.administratorRepository.findById(id);
    }

    public Administrator findByEmail(String adminName){
        return this.administratorRepository.findByAdminName(adminName);
    }

    public List<Administrator> findAll(){
        return this.administratorRepository.findAll();
    }

    public void deleteById(long id){
        this.administratorRepository.deleteById(id);
    }

}
