package user.userservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import user.userservice.domain.Agent;

import java.util.List;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long> {

    boolean existsById(long id);

    Agent findById(long id);
    Agent findByCompanyName(String companyName);
    List<Agent> findAll();

    Agent save(Agent agent);

    @Modifying
    void deleteById(long id);

}
