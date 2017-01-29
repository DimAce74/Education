package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

}
