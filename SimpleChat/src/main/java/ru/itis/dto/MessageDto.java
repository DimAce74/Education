package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDto implements BaseDto {
    private Integer id;
    private ChatDto chatDto;
    private String userName;
    private String text;

    public MessageDto() {
    }

    public MessageDto(Builder builder) {
        this.id = builder.id;
        this.chatDto = builder.chatDto;
        this.userName = builder.userName;
        this.text = builder.text;
    }

    public static class Builder {
        private Integer id;
        private ChatDto chatDto;
        private String userName;
        private String text;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder chat(ChatDto value) {
            this.chatDto = value;
            return this;
        }

        public Builder userName(String value) {
            this.userName = value;
            return this;
        }

        public Builder text(String value) {
            this.text = value;
            return this;
        }

        public MessageDto build() {
            return new MessageDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public ChatDto getChatDto() {
        return chatDto;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

}
