package com.wjj.studentmanager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdatePasswordReq {
    @NotNull(message = "请填写旧密码")
    String password;
    @NotNull(message = "请填写第一次新密码")
    String newPassword1;
    @NotNull(message = "请填写第二次新密码")
    String newPassword2;
}
