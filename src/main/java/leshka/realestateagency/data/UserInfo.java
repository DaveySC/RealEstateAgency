package leshka.realestateagency.data;



public class UserInfo {
     public static enum Role {
        ADMIN,
        BUYER,
        REALTOR
    }
    private static UserInfo userInfo;
    private String login;
    private String password;
    private String name;
    private String secondName;
    private String address;
    private String age;
    private Role role;

    public void setAll(String login, String password, String name, String secondName, String address, String age, Role role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.secondName = secondName;
        this.address = address;
        this.age = age;
        this.role = role;
    }


    private UserInfo() {
        this.login = null;
        this.password = null;
        this.name = null;
        this.secondName = null;
        this.address = null;
        this.age = null;
        this.role = null;
    }

    public static UserInfo getInstance() {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    public void clearAll() {
        this.login = null;
        this.password = null;
        this.name = null;
        this.secondName = null;
        this.address = null;
        this.age = null;
        this.role = null;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", address='" + address + '\'' +
                ", age='" + age + '\'' +
                ", role=" + role +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public Role getRole() {
        return role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
