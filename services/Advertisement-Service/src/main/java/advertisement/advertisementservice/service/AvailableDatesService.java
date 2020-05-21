package advertisement.advertisementservice.service;

import org.springframework.stereotype.Service;
import advertisement.advertisementservice.domain.AvailableDates;
import advertisement.advertisementservice.repository.AvailableDatesRepository;

import java.util.List;

@Service
public class AvailableDatesService {

    private AvailableDatesRepository availableDatesRepository;

    public AvailableDatesService(
            AvailableDatesRepository availableDatesRepository
    ) {
        this.availableDatesRepository = availableDatesRepository;
    }

    public AvailableDates save(AvailableDates availableDates) {
        return this.availableDatesRepository.save(availableDates);
    }

    public AvailableDates findById(long id){
        return this.availableDatesRepository.findById(id);
    }

    public List<AvailableDates> findAll(){
        return this.availableDatesRepository.findAll();
    }

    public void deleteById(long id){
        this.availableDatesRepository.deleteById(id);
    }

}
