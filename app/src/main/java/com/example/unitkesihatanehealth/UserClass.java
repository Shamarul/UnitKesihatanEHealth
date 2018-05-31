package com.example.unitkesihatanehealth;

public class UserClass {
    public String password, fullname, privilege, id, location, address;

    public UserClass (String fullname, String password, String privilege, String id, String location, String address) {

        this.password = password;
        this.fullname = fullname;
        this.id = id;
        this.privilege = privilege;
        this.location = location;
        this.address = address;

    }
}
