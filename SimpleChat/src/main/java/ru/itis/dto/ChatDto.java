package ru.itis.dto;

import java.util.List;


public class ChatDto {

    private Integer id;

    private String name;

    private List<ChatUserDto> chatUserDtoList;

    public ChatDto() {
    }

    public ChatDto(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.chatUserDtoList = builder.chatUserDtoList;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private List<ChatUserDto> chatUserDtoList;


        public Builder id(Integer value){
            this.id = value;
            return  this;
        }

        public Builder name(String value){
            this.name = value;
            return this;
        }

        public Builder chatUserDtoList(List<ChatUserDto> value){
            this.chatUserDtoList = value;
            return this;
        }

        public ChatDto build(){
            return new ChatDto(this);
        }
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public List<ChatUserDto> getChatUserDtoList(){return chatUserDtoList;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatDto chatDto = (ChatDto) o;

        if (!getId().equals(chatDto.getId())) return false;
        if (!getName().equals(chatDto.getName())) return false;
        return getChatUserDtoList() != null ? getChatUserDtoList().equals(chatDto.getChatUserDtoList()) : chatDto.getChatUserDtoList() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getChatUserDtoList() != null ? getChatUserDtoList().hashCode() : 0);
        return result;
    }
}
