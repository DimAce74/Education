package ru.itis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;
    private String name;
    private Integer age;
    private List<AutoDto> listAutoDto;

    public UserDto() {
    }

    private UserDto (Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.listAutoDto = builder.listAutoDto;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private Integer age;
        private List<AutoDto> listAutoDto;

        public Builder id (Integer value){
            this.id = value;
            return this;
        }

        public Builder name (String value){
            this.name = value;
            return this;
        }

        public Builder age (Integer value){
            this.age = value;
            return this;
        }

        public Builder listAutoDto (List<AutoDto> value){
            this.listAutoDto = value;
            return this;
        }

        public UserDto build(){
            return new UserDto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<AutoDto> getListAutoDto() {
        return listAutoDto;
    }
}
