package renting.rentingservice.service;

import renting.rentingservice.domain.Cart;
import renting.rentingservice.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private CartRepository cartRepository;

    public CartService(
            CartRepository cartRepository
    ) {
        this.cartRepository = cartRepository;
    }

    public Cart save(Cart cart) {
        return this.cartRepository.save(cart);
    }

    public Cart findById(long id){
        return this.cartRepository.findById(id);
    }

    public List<Cart> findByVehicleId(long vehicleId){
        return this.cartRepository.findByVehicleId(vehicleId);
    }

    public List<Cart> findByCustomerId(long customerId){
        return this.cartRepository.findByCustomerId(customerId);
    }

    public List<Cart> findAll(){
        return this.cartRepository.findAll();
    }

    public void deleteById(long id){
        this.cartRepository.deleteById(id);
    }

}
