package advertisement.advertisementservice.service;

import advertisement.advertisementservice.domain.BrandModel;
import advertisement.advertisementservice.repository.BrandModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandModelService {

    private BrandModelRepository brandModelRepository;

    public BrandModelService(
            BrandModelRepository brandModelRepository
    ) {
        this.brandModelRepository = brandModelRepository;
    }

    public BrandModel save(BrandModel brandModel) {
        return this.brandModelRepository.save(brandModel);
    }

    public BrandModel findById(long id){
        return this.brandModelRepository.findById(id);
    }

    public BrandModel findByModel(String model){
        return this.brandModelRepository.findByModel(model);
    }

    public List<BrandModel> findAll(){
        return this.brandModelRepository.findAll();
    }

    public void deleteById(long id){
        this.brandModelRepository.deleteById(id);
    }

}
