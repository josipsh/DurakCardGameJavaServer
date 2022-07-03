package hr.algebra.sharedModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class MessageDto implements Externalizable {
    private static final long serialVersionUID = 2L;

    private UserDto user;
    private String content;
    private boolean isUserAuthor;

    public MessageDto() {
    }

    public MessageDto(UserDto user, String content, boolean isUserAuthor) {
        this.user = user;
        this.content = content;
        this.isUserAuthor = isUserAuthor;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(user);
        out.writeUTF(content);
        out.writeBoolean(isUserAuthor);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        user = (UserDto) in.readObject();
        content = in.readUTF();
        isUserAuthor = in.readBoolean();
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isUserAuthor() {
        return isUserAuthor;
    }

    public void setUserAuthor(boolean userAuthor) {
        isUserAuthor = userAuthor;
    }

    @Override
    public String toString() {
        return "user = " + user + " | message = " + content;
    }
}
