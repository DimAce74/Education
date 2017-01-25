package ru.itis.models;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
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
    @Column(name = "password")
    private String password;

    @Access(AccessType.FIELD)
    @Column(name = "login")
    private String login;

    @ManyToMany(fetch = FetchType.LAZY, cascade =
            {javax.persistence.CascadeType.DETACH,
                    javax.persistence.CascadeType.MERGE,
                    javax.persistence.CascadeType.PERSIST,
                    javax.persistence.CascadeType.REFRESH},
            targetEntity = Chat.class)
    @JoinTable(name = "chat_member",
            joinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "chat_id", nullable = false, updatable = false),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<Chat> chatList=new ArrayList<>();

    public ChatUser() {
    }

    public ChatUser(Builder builder) {
        this.id = builder.id;
        this.password = builder.password;
        this.login = builder.login;
        this.chatList = builder.chatList;
    }

    public static class Builder {
        private Integer id;
        private String password;
        private String login;
        private List<Chat> chatList;

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

        public Builder chatList(List<Chat> value){
            this.chatList=value;
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

    public List<Chat> getChatList() {return chatList;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatUser chatUser = (ChatUser) o;

        if (!getId().equals(chatUser.getId())) return false;
        if (!getPassword().equals(chatUser.getPassword())) return false;
        if (!getLogin().equals(chatUser.getLogin())) return false;
        return getChatList() != null ? getChatList().equals(chatUser.getChatList()) : chatUser.getChatList() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + (getChatList() != null ? getChatList().hashCode() : 0);
        return result;
    }
}
