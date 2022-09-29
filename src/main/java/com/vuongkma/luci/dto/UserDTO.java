package com.vuongkma.luci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {
    @NotBlank(message = "The user id is required", groups = {OnUpdateUser.class, OnDeleteUser.class})
    private Long id;

    @NotBlank(message = "The user name is required", groups = OnCreateUser.class)
    public String username;

    @NotBlank(message = "The email is required", groups = OnCreateUser.class)
    @Email(message = "Invalid email")
    public String email;
    public String name;
    public String phone;

    @NotBlank(message = "Please enter in your password", groups = {OnCreateUser.class})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

}