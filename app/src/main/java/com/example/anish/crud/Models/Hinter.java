package com.example.anish.crud.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


@Entity
public class Hinter {

    @Id(autoincrement = true)
    private Long id;
    private String website;
    private String email;
    private String username;
    private String hint;
    @Generated(hash = 438062839)
    public Hinter(Long id, String website, String email, String username,
            String hint) {
        this.id = id;
        this.website = website;
        this.email = email;
        this.username = username;
        this.hint = hint;
    }
    @Generated(hash = 930142599)
    public Hinter() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getWebsite() {
        return this.website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHint() {
        return this.hint;
    }
    public void setHint(String hint) {
        this.hint = hint;
    }
}
