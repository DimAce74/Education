package ru.itis.dto;


public class MessageDto implements BaseDto {
    private Integer id;
    private ChatDto chatDto;
    private ChatUserDto chatUserDto;
    private String text;

    public MessageDto() {
    }

    public MessageDto(Builder builder) {
        this.id = builder.id;
        this.chatDto = builder.chatDto;
        this.chatUserDto = builder.chatUserDto;
        this.text = builder.text;
    }

    public static class Builder {
        private Integer id;
        private ChatDto chatDto;
        private ChatUserDto chatUserDto;
        private String text;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder chat(ChatDto chatDto) {
            this.chatDto = chatDto;
            return this;
        }

        public Builder chatUser(ChatUserDto chatUserDto) {
            this.chatUserDto = chatUserDto;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
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

    public ChatUserDto getChatUserDto() {
        return chatUserDto;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageDto messageDto = (MessageDto) o;

        if (!getId().equals(messageDto.getId())) return false;
        if (!getChatDto().equals(messageDto.getChatDto())) return false;
        if (!getChatUserDto().equals(messageDto.getChatUserDto())) return false;
        return getText() != null ? getText().equals(messageDto.getText()) : messageDto.getText() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getChatDto().hashCode();
        result = 31 * result + getChatUserDto().hashCode();
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        return result;
    }
}
