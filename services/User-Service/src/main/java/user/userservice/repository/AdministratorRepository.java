package user.userservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import user.userservice.domain.Administrator;

import java.util.List;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Long> {

    boolean existsById(long id);

    Administrator findById(long id);
    Administrator findByAdminName(String adminName);
    List<Administrator> findAll();

    Administrator save(Administrator administrator);

    @Modifying
    void deleteById(long id);

}
