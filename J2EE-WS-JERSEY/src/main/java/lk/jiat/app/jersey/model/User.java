package lk.jiat.app.jersey.model;

import jakarta.enterprise.context.ApplicationScoped;
import lk.jiat.app.jersey.annotation.UserBind;

import java.io.Serializable;


@ApplicationScoped
//@UserBind
public class User implements Serializable {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

