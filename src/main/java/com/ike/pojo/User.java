package com.ike.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User extends BaseRowModel {
    private Long id;

    @ExcelProperty(index = 0, value = "用户名")
    private String username;

    @ExcelProperty(index = 1, value = "姓名")
    private String name;

    private Byte gender;

    private Long positionId;

    private Long departmentId;

    @ExcelProperty(index = 5, value = "电话号码")
    private String phone;

    @ExcelProperty(index = 6, value = "邮箱")
    private String email;

    private Byte status;

    private String salt;

    private String password;

    private LocalDateTime loginDate;

    private LocalDateTime lastLoginDate;

    //=======外键集合=============
    private List<Role> roles = new ArrayList<>();  //权限集合
    private List<String> permissions = new ArrayList<>(); //角色集合

    public User() {
    }

    public User(String username, String name, Byte gender, Long positionId, Long departmentId, String phone, String email, Byte status, String salt, String password, LocalDateTime loginDate, LocalDateTime lastLoginDate) {
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.positionId = positionId;
        this.departmentId = departmentId;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.salt = salt;
        this.password = password;
        this.loginDate = loginDate;
        this.lastLoginDate = lastLoginDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "User{" +
                       "id=" + id +
                       ", username='" + username + '\'' +
                       ", name='" + name + '\'' +
                       ", gender=" + gender +
                       ", positionId=" + positionId +
                       ", departmentId=" + departmentId +
                       ", phone='" + phone + '\'' +
                       ", email='" + email + '\'' +
                       ", status=" + status +
                       ", salt='" + salt + '\'' +
                       ", password='" + password + '\'' +
                       ", loginDate=" + loginDate +
                       ", lastLoginDate=" + lastLoginDate +
                       ", roles=" + roles +
                       ", permissions=" + permissions +
                       '}';
    }
}