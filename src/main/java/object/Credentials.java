package object;

public class Credentials {
    private String email;
    private String password;

    //конструктор с параметрами
    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //конструктор без параметров
    public Credentials() {
    }
    public static Credentials from(User user) {
        return new Credentials(user.getEmail(), user.getPassword());
    }

    //геттеры и сеттеры полей
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
