package com.px.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 彭翔
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotNull
    private int id;
    @NotEmpty
    private String username;
    @JsonIgnore
    private String password;
    private double balance;
    private String type;
    private  String phone;
    private String idCard;
    private  String trueName;
    private String userPic;
}
