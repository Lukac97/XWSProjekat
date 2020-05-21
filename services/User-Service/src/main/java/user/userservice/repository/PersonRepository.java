package user.userservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import user.userservice.domain.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    boolean existsById(long id);

    Person findById(long id);
    Person findByEmail(String email);
    List<Person> findAll();

    Person save(Person person);

    @Modifying
    void deleteById(long id);

}
