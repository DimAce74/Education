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
    private Integer userId;

    @OneToOne
    @JoinColumn(name = "last_message_id")
    private Integer lastMessageId;

    public ChatSession() {
    }


    public ChatSession(Builder builder) {
        this.id = builder.id;
        this.token = builder.token;
        this.userId = builder.userId;
        this.lastMessageId = builder.lastMessageId;
    }

    public static class Builder {
        private Integer id;
        private String token;
        private Integer userId;
        private Integer lastMessageId;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder lastMessageId(Integer lastMessageId) {
            this.lastMessageId = lastMessageId;
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

    public Integer getUserId() {
        return userId;
    }

    public Integer getLastMessageId() {
        return lastMessageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatSession chatSession = (ChatSession) o;

        if (!getId().equals(chatSession.getId())) return false;
        if (!getToken().equals(chatSession.getToken())) return false;
        if (!getUserId().equals(chatSession.getUserId())) return false;
        return getLastMessageId() != null ? getLastMessageId().equals(chatSession.getLastMessageId()) : chatSession.getLastMessageId() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getToken().hashCode();
        result = 31 * result + getUserId().hashCode();
        result = 31 * result + (getLastMessageId() != null ? getLastMessageId().hashCode() : 0);
        return result;
    }
}
