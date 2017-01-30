package ru.itis.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chat_user")
public class ChatUser implements BaseModel, Serializable {
    @Id
    @Column(name = "id")
    @Access(AccessType.FIELD)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Access(AccessType.FIELD)
    @Column(name = "password", nullable = false)
    private String password;

    @Access(AccessType.FIELD)
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Access(AccessType.FIELD)
    @Column(name = "name")
    private String name;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "chat_member",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private List<Chat> chatList=new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "token_storage",
            joinColumns=@JoinColumn(name="user_id")
    )
    @Column(name="token")
    private List<String> tokens;

    public ChatUser() {
    }

    public ChatUser(Builder builder) {
        this.id = builder.id;
        this.password = builder.password;
        this.login = builder.login;
        this.name = builder.name;
        this.chatList = builder.chatList;
        this.tokens = builder.tokens;
    }

    public static class Builder {
        private Integer id;
        private String password;
        private String login;
        private String name;
        private List<Chat> chatList;
        private List<String> tokens;

        public Builder id(Integer value) {
            this.id = value;
            return this;
        }

        public Builder password(String value) {
            this.password = value;
            return this;
        }

        public Builder login(String value) {
            this.login = value;
            return this;
        }

        public Builder name (String value) {
            this.name = value;
            return this;
        }

        public Builder chatList(List<Chat> value){
            this.chatList=value;
            return this;
        }

        public Builder tokens(List<String> value){
            this.tokens = value;
            return this;
        }

        public ChatUser build() {
            return new ChatUser(this);
        }
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {return name;}

    public List<Chat> getChatList() {return chatList;}

    public List<String> getTokens() {
        return tokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatUser chatUser = (ChatUser) o;

        if (!getId().equals(chatUser.getId())) return false;
        if (!getPassword().equals(chatUser.getPassword())) return false;
        if (!getLogin().equals(chatUser.getLogin())) return false;
        if (!getName().equals(chatUser.getName())) return false;
        if (getChatList() != null ? !getChatList().equals(chatUser.getChatList()) : chatUser.getChatList() != null)
            return false;
        return getTokens() != null ? getTokens().equals(chatUser.getTokens()) : chatUser.getTokens() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getChatList() != null ? getChatList().hashCode() : 0);
        result = 31 * result + (getTokens() != null ? getTokens().hashCode() : 0);
        return result;
    }
}
