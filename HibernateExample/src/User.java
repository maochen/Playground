public class User {
    private int id;
    private String username;
    private String password;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("Id: " + this.getId() + ", ");
        buf.append("Username: " + this.getUsername() + ", ");
        buf.append("Password: " + this.getPassword() + ", ");
        buf.append("Email: " + this.getEmail());

        return buf.toString();
    }

}
