package com.mx.framework.entity.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Table(name = "user")
@Data
public class User {
    @Id
    private Integer id;

    @NotNull(message = "名字不能为空。")
    @Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})"
            ,message="213用户名必须是2-5位中文或者6-16位英文和数字的组合")
    private String name;


    private String pwd;

    private Date createDate;

    private String avatar;

    private String status;

    private String tel;

    @NotNull(message = "邮箱不能空")
    @Email(message = "邮箱不合法。")
    private String mailbox;

}