package renting.rentingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import renting.rentingservice.domain.Cart;
import renting.rentingservice.dto.NewCartDTO;
import renting.rentingservice.service.CartService;

@RestController
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController (
            CartService cartService
    ) {
        this.cartService = cartService;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addVehicleToCart(@RequestBody NewCartDTO newCart){
        if((Long)newCart.getCustomerId() == null){
            return ResponseEntity.ok("Customer id cant be null.");
        }
        if((Long)newCart.getVehicleId() == null){
            return ResponseEntity.ok("Vehicle id cant be null.");
        }
        for(Cart c :cartService.findByCustomerId(newCart.getCustomerId())){
            if(c.getVehicleId() == newCart.getVehicleId()){
                return ResponseEntity.ok("Vehicle (id:"+newCart.getVehicleId()+") already exists in cart belonging to user (id:"+newCart.getCustomerId()+").");
            }
        }
        Cart cart = new Cart();
        cart.setCustomerId(newCart.getCustomerId());
        cart.setVehicleId(newCart.getVehicleId());
        cartService.save(cart);
        return ResponseEntity.ok("Vehicle with id : ("+cart.getVehicleId()+") has been added to user(id:"+cart.getCustomerId()+") cart!");
    }
}
