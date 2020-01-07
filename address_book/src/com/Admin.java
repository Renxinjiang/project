package com;

/**
 * @PackageName: com
 * @ClassName: Admin
 * @Description:
 * @author: 呆呆
 * @date: 2020/1/6
 */
public class Admin {
    private String name ;
    private String address;
    private String phone ;
    private String postcode ;
    private String email ;
    private String homePhone ;

    public Admin() { }

    public Admin(String name, String address, String phone, String postcode, String email, String homePhone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.postcode = postcode;
        this.email = email;
        this.homePhone = homePhone;
    }

    // 	// VO值对象    包括get和set方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String password) {
        this.phone = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
}
