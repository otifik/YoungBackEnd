package com.young.safedrive.model;

/**
 * @ Author : autumn
 * @ Date   : created in 17:40 2022/2/10
 */
public class UserModel {

    private Integer id;
    private String username;
    private String account;
    private String password;
    private String head_address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHead_address() {
        return head_address;
    }

    public void setHead_address(String head_address) {
        this.head_address = head_address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", head_address='" + head_address + '\'' +
                '}';
    }
}
