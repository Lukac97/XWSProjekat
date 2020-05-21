package advertisement.advertisementservice.service;

import advertisement.advertisementservice.domain.Pricelist;
import advertisement.advertisementservice.repository.PricelistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricelistService {

    private PricelistRepository pricelistRepository;

    public PricelistService(
            PricelistRepository pricelistRepository
    ) {
        this.pricelistRepository = pricelistRepository;
    }

    public Pricelist save(Pricelist pricelist) {
        return this.pricelistRepository.save(pricelist);
    }

    public Pricelist findById(long id){
        return this.pricelistRepository.findById(id);
    }

    public List<Pricelist> findAll(){
        return this.pricelistRepository.findAll();
    }

    public void deleteById(long id){
        this.pricelistRepository.deleteById(id);
    }

}
