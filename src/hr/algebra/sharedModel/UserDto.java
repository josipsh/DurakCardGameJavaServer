package hr.algebra.sharedModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserDto implements Externalizable {
    private static final long serialVersionUID = 1L;

    private int userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String userName;

    public UserDto() {
    }

    public UserDto(int userId, String firstName, String lastName, String userName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = "";
        this.userName = userName;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(userId);
        out.writeUTF(firstName);
        out.writeUTF(lastName);
        out.writeUTF(middleName);
        out.writeUTF(userName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        userId = in.readInt();
        firstName = in.readUTF();
        lastName = in.readUTF();
        middleName = in.readUTF();
        userName = in.readUTF();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + " - " + firstName + " " + lastName;
    }
}
