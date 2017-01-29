package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatUserDto{

    private Integer id;
    private String password;
    private String login;
    private String name;
    private List<ChatDto> chatDtoList;

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

}
