package com.young.safedrive.model;

/**
 * @ Author : autumn
 * @ Date   : created in 21:48 2022/2/10
 */
public class ContactModel {
    private Integer id;
    private String account;
    private String remark;
    private String phone_number;
    private String head_address;
    private Boolean isEmergencyContact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getHead_address() {
        return head_address;
    }

    public void setHead_address(String head_address) {
        this.head_address = head_address;
    }

    public Boolean getIsEmergencyContact() {
        return isEmergencyContact;
    }

    public void setIsEmergencyContact(Boolean emergencyContact) {
        isEmergencyContact = emergencyContact;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", remark='" + remark + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", head_address='" + head_address + '\'' +
                ", isEmergencyContact=" + isEmergencyContact +
                '}';
    }
}
