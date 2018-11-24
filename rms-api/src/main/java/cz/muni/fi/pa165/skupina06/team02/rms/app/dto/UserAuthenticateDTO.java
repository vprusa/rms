package cz.muni.fi.pa165.skupina06.team02.rms.app.dto;

/**
 * @author Vojtech Prusa
 * 
 *         DTO class of Household to transfer data between services
 * 
 */
public class UserAuthenticateDTO {

    private Long userId;
    private String password;

    /**
     * @return
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter
     * 
     * @param userId value
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter
     * 
     * @return passwrd
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter
     * 
     * @param password string
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
