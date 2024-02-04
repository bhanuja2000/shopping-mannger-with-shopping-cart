
  //user class work with   username and password.
public class User {
 
    // Private instance variables to store user information
    private String username;
    private String password;

    // Constructor to initialize a User object with a username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // getters and setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
