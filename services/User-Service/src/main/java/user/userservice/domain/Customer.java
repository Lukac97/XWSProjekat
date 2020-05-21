package user.userservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "CUSTOMER")
public class Customer extends Person implements Serializable{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;


    public Customer(long id, String email, String password, String phoneNumber, String name, String surname) {
        super(id, email, password, phoneNumber);
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


}
