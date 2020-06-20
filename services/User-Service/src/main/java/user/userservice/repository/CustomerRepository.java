package user.userservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import user.userservice.domain.Customer;
import user.userservice.domain.Person;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    boolean existsById(long id);

    Customer findById(long id);
    List<Customer> findAll();

    Customer save(Customer customer);

    @Modifying
    void deleteById(long id);

}
