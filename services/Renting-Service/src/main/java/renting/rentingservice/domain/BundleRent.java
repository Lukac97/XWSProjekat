package renting.rentingservice.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BundleRent implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bundlerent_id", nullable = false, unique = true)
    private long id;

    @OneToMany(mappedBy = "bundle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RentRequest> requests;

    public BundleRent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<RentRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<RentRequest> requests) {
        this.requests = requests;
    }
}
