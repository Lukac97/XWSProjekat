package user.userservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "AGENT")
public class Agent extends Person implements Serializable{

    @Column(nullable = false, unique = true)
    private String companyName;

    public Agent(long id, String email, String password, String phoneNumber, String companyName) {
        super(id, email, password, phoneNumber);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
