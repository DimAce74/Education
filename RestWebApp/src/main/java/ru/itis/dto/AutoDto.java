package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutoDto {
    private Integer id;
    private String model;
    private String color;
    private UserDto userDto;

    public AutoDto() {
    }

    private AutoDto(Builder builder) {
        this.id = builder.id;
        this.model = builder.model;
        this.color = builder.color;
        this.userDto = builder.userDto;
    }

    public static class Builder {
        private Integer id;
        private String model;
        private String color;
        private UserDto userDto;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder user(UserDto userDto) {
            this.userDto = userDto;
            return this;
        }

        public AutoDto build() {
            return new AutoDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
