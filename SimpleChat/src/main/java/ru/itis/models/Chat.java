package ru.itis.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
public class Chat implements BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Access(AccessType.FIELD)
    private Integer id;

    @Column(name = "chat_name")
    @Access(AccessType.FIELD)
    private String name;

    @ManyToMany
    @JoinTable(name = "chat_member",
    joinColumns = @JoinColumn(name = "chat_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<ChatUser> chatUserList;

    public Chat() {
    }

    public Chat (Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.chatUserList = builder.chatUserList;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private List<ChatUser> chatUserList;


        public Builder id(Integer value){
            this.id = value;
            return  this;
        }

        public Builder name(String value){
            this.name = value;
            return this;
        }

        public Builder chatUserList(List<ChatUser> value){
            this.chatUserList = value;
            return this;
        }

        public Chat build(){
            return new Chat(this);
        }
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public List<ChatUser> getChatUserList(){return chatUserList;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (!getId().equals(chat.getId())) return false;
        if (!getName().equals(chat.getName())) return false;
        return getChatUserList() != null ? getChatUserList().equals(chat.getChatUserList()) : chat.getChatUserList() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getChatUserList() != null ? getChatUserList().hashCode() : 0);
        return result;
    }
}
