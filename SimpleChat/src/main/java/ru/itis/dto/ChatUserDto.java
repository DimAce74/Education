package ru.itis.dto;

import java.util.List;

public class ChatUserDto{

    private Integer id;
    private String password;
    private String login;
    private List<ChatDto> chatDtoList;

    public ChatUserDto() {
    }

    public ChatUserDto(Builder builder) {
        this.id = builder.id;
        this.password = builder.password;
        this.login = builder.login;
        this.chatDtoList = builder.chatDtoList;
    }

    public static class Builder {
        private Integer id;
        private String password;
        private String login;
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

    public List<ChatDto> getChatDtoList() {return chatDtoList;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatUserDto chatUserDto = (ChatUserDto) o;

        if (!getId().equals(chatUserDto.getId())) return false;
        if (!getPassword().equals(chatUserDto.getPassword())) return false;
        if (!getLogin().equals(chatUserDto.getLogin())) return false;
        return getChatDtoList() != null ? getChatDtoList().equals(chatUserDto.getChatDtoList()) : chatUserDto.getChatDtoList() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + (getChatDtoList() != null ? getChatDtoList().hashCode() : 0);
        return result;
    }
}
