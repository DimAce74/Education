package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatUserDto{

    private Integer id;
    private String password;
    private String login;
    private String name;
    private List<ChatDto> chatDtoList;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();


    public ChatUserDto() {
    }

    public ChatUserDto(Builder builder) {
        this.id = builder.id;
        this.password = builder.password;
        this.login = builder.login;
        this.name = builder.name;
        this.chatDtoList = builder.chatDtoList;
    }

    public static class Builder {
        private Integer id;
        private String password;
        private String login;
        private String name;
        private List<ChatDto> chatDtoList;

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

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder chatDtoList(List<ChatDto> value){
            this.chatDtoList =value;
            return this;
        }

        public ChatUserDto build() {
            return new ChatUserDto(this);
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

    public String getName() {
        return name;
    }

    public List<ChatDto> getChatDtoList() {return chatDtoList;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatUserDto that = (ChatUserDto) o;

        if (!getId().equals(that.getId())) return false;
        if (!encoder.matches(getPassword(), that.getPassword())) return false;
        if (!getLogin().equals(that.getLogin())) return false;
        if (!getName().equals(that.getName())) return false;
        return getChatDtoList() != null ? getChatDtoList().equals(that.getChatDtoList()) : that.getChatDtoList() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getChatDtoList() != null ? getChatDtoList().hashCode() : 0);
        return result;
    }
}
