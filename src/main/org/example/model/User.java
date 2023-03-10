package org.example.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class User {

    private int id;
    private String name;
    private String email;
    private String psw;
    private boolean is_active;
    private Timestamp createdTs;
    private Timestamp updateTs;
    private Set<Role> roles;
    private Office office;

    public boolean getIsActive() {
        return is_active;
    }

    public boolean isActive() {
        return is_active;
    }
}