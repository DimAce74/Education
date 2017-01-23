package ru.itis.models;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message implements BaseModel {
    @Id
    @Column(name = "id")
    @Access(AccessType.FIELD)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Access(AccessType.FIELD)
    @ManyToOne
    @JoinColumn(name = "chat_id",
            foreignKey = @ForeignKey(name = "chat_message_fk"))
    private Chat chat;

    @Access(AccessType.FIELD)
    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "user_message_fk"))
    private ChatUser chatUser;

    @Access(AccessType.FIELD)
    @Column(name = "text")
    private String text;

    public Message() {
    }

    public Message(Builder builder) {
        this.id = builder.id;
        this.chat = builder.chat;
        this.chatUser = builder.chatUser;
        this.text = builder.text;
    }

    public static class Builder {
        private Integer id;
        private Chat chat;
        private ChatUser chatUser;
        private String text;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder chat(Chat chat) {
            this.chat = chat;
            return this;
        }

        public Builder chatUser(ChatUser chatUser) {
            this.chatUser = chatUser;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public Chat getChat() {
        return chat;
    }

    public ChatUser getChatUser() {
        return chatUser;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!getId().equals(message.getId())) return false;
        if (!getChat().equals(message.getChat())) return false;
        if (!getChatUser().equals(message.getChatUser())) return false;
        return getText() != null ? getText().equals(message.getText()) : message.getText() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getChat().hashCode();
        result = 31 * result + getChatUser().hashCode();
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        return result;
    }
}
