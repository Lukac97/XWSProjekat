package user.userservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "ADMINISTRATOR")
public class Administrator  extends Person implements Serializable{

    @Column(nullable = false, unique = true)
    private String adminName;

    public Administrator(long id, String email, String password, String phoneNumber, String adminName) {
        super(id, email, password, phoneNumber);
        this.adminName = adminName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
