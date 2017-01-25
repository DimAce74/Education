package ru.itis.models;


import javax.persistence.*;

@Entity
@Table(name = "session")
public class ChatSession implements BaseModel {
    @Id
    @Column(name = "id")
    @Access(AccessType.FIELD)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Access(AccessType.FIELD)
    @Column(name = "token")
    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private ChatUser chatUser;

    @OneToOne
    @JoinColumn(name = "last_message_id")
    private Message lastMessage;

    public ChatSession() {
    }


    public ChatSession(Builder builder) {
        this.id = builder.id;
        this.token = builder.token;
        this.chatUser = builder.chatUser;
        this.lastMessage = builder.lastMessage;
    }

    public static class Builder {
        private Integer id;
        private String token;
        private ChatUser chatUser;
        private Message lastMessage;

        public Builder id(Integer value) {
            this.id = value;
            return this;
        }

        public Builder token(String value) {
            this.token = value;
            return this;
        }

        public Builder chatUser(ChatUser value) {
            this.chatUser = value;
            return this;
        }

        public Builder lastMessage(Message value) {
            this.lastMessage = value;
            return this;
        }

        public ChatSession build() {
            return new ChatSession(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public ChatUser getChatUser() {
        return chatUser;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatSession that = (ChatSession) o;

        if (!getId().equals(that.getId())) return false;
        if (!getToken().equals(that.getToken())) return false;
        if (!getChatUser().equals(that.getChatUser())) return false;
        return getLastMessage() != null ? getLastMessage().equals(that.getLastMessage()) : that.getLastMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getToken().hashCode();
        result = 31 * result + getChatUser().hashCode();
        result = 31 * result + (getLastMessage() != null ? getLastMessage().hashCode() : 0);
        return result;
    }
}
