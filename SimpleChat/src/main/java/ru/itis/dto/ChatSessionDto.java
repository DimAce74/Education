package ru.itis.dto;


public class ChatSessionDto implements BaseDto {
    private Integer id;
    private String token;
    private Integer userId;
    private Integer lastMessageId;

    public ChatSessionDto() {
    }


    public ChatSessionDto(Builder builder) {
        this.id = builder.id;
        this.token = builder.token;
        this.userId = builder.userId;
        this.lastMessageId = builder.lastMessageId;
    }

    public static class Builder {
        private Integer id;
        private String token;
        private Integer userId;
        private Integer lastMessageId;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder lastMessageId(Integer lastMessageId) {
            this.lastMessageId = lastMessageId;
            return this;
        }

        public ChatSessionDto build() {
            return new ChatSessionDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getLastMessageId() {
        return lastMessageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatSessionDto chatSessionDto = (ChatSessionDto) o;

        if (!getId().equals(chatSessionDto.getId())) return false;
        if (!getToken().equals(chatSessionDto.getToken())) return false;
        if (!getUserId().equals(chatSessionDto.getUserId())) return false;
        return getLastMessageId() != null ? getLastMessageId().equals(chatSessionDto.getLastMessageId()) : chatSessionDto.getLastMessageId() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getToken().hashCode();
        result = 31 * result + getUserId().hashCode();
        result = 31 * result + (getLastMessageId() != null ? getLastMessageId().hashCode() : 0);
        return result;
    }
}
