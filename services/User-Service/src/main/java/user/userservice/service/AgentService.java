package user.userservice.service;

import org.springframework.stereotype.Service;
import user.userservice.domain.Agent;
import user.userservice.repository.AgentRepository;

import java.util.List;

@Service
public class AgentService {

    private AgentRepository agentRepository;

    public AgentService(
            AgentRepository agentRepository
    ) {
        this.agentRepository = agentRepository;
    }

    public Agent save(Agent agent) {
        return this.agentRepository.save(agent);
    }

    public boolean existsById(Long consumerId) {
        if (!this.agentRepository.existsById(consumerId)) {
            return false;
        }
        return true;
    }

    public Agent findById(long id){
        return this.agentRepository.findById(id);
    }

    public Agent findByCompanyName(String companyName){
        return this.agentRepository.findByCompanyName(companyName);
    }

    public List<Agent> findAll(){
        return this.agentRepository.findAll();
    }

    public void deleteById(long id){
        this.agentRepository.deleteById(id);
    }

}
