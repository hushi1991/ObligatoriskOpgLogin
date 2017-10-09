package njl.model.entities;

public class User {

    private int user_id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String password1;
    private String password2;

    public User(int user_id, String name, String address, String email, String password, String password1, String password2) {
        this.user_id = user_id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.password1 = password1;
        this.password2 = password2;
    }

    public User() {
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String toString()
    {
        return name + " " + address + " " + email + " " + password;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
