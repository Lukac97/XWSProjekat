package user.userservice.service;

import org.springframework.stereotype.Service;
import user.userservice.domain.Customer;
import user.userservice.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(
            CustomerRepository customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public boolean existsById(Long consumerId) {
        if (!this.customerRepository.existsById(consumerId)) {
            return false;
        }
        return true;
    }

    public Customer findById(long id){
        return this.customerRepository.findById(id);
    }

    public List<Customer> findAll(){
        return this.customerRepository.findAll();
    }

    public void deleteById(long id){
        this.customerRepository.deleteById(id);
    }

}
