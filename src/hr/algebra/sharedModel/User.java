package hr.algebra.sharedModel;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String userName;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String userName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.middleName = "";
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getUserName() {
        return userName;
    }
}
