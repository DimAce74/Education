package ru.itis.dto;


public class ChatSessionDto implements BaseDto {
    private Integer id;
    private String token;
    private ChatUserDto chatUserDto;
    private MessageDto lastMessageDto;

    public ChatSessionDto() {
    }


    public ChatSessionDto(Builder builder) {
        this.id = builder.id;
        this.token = builder.token;
        this.chatUserDto = builder.chatUserDto;
        this.lastMessageDto = builder.lastMessageDto;
    }

    public static class Builder {
        private Integer id;
        private String token;
        private ChatUserDto chatUserDto;
        private MessageDto lastMessageDto;

        public Builder id(Integer value) {
            this.id = value;
            return this;
        }

        public Builder token(String value) {
            this.token = value;
            return this;
        }

        public Builder chatUserDto(ChatUserDto value) {
            this.chatUserDto = value;
            return this;
        }

        public Builder lastMessageDto(MessageDto value) {
            this.lastMessageDto = value;
            return this;
        }

        public ChatSessionDto build() {
            return new ChatSessionDto(this);
        }
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public ChatUserDto getChatUserDto() {
        return chatUserDto;
    }

    public MessageDto getLastMessageDto() {
        return lastMessageDto;
    }


}
