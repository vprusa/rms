package cz.muni.fi.pa165.skupina06.team02.rms.app.entity;

import javax.validation.constraints.NotNull;
import javax.persistence.*;

/**
 * Class holding basic user info
 * @author Martin Lacko
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String password;

//  TODO: After implementing:
//      - add households property

    @OneToMany(mappedBy = "dedicatedBuyer")
    private ShoppingItem items;

    /**
     * Constructor with specific ID
     * @param userID Specific ID for this user
     */
    public User(Long userID) {
        this.id = userID;
    }

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * @return ID of this user
     */
    public Long getId() {
        return id;
    }


    /**
     * @return Email of this user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return First name of this user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return Last name of this user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return (Representation) of password for this user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param firstName New first name of this user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName New last name of this user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param password New password for this user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}