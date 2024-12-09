package com.example.lab7_jt.beans;

import com.example.lab7_jt.entities.User;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import com.example.lab7_jt.service.UserService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.hibernate.service.spi.InjectService;

import java.io.Serializable;
import java.util.UUID;

@Named
@SessionScoped
@FacesConverter(value = "userConverter", managed = true)
public class UserConverter implements Converter, Serializable {
    @Inject
    private UserService userService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        UUID teacherId = UUID.fromString(value);
        return userService.getUserById(teacherId);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof User) {
            User user = (User) value; // Cast to User
            return user.getId().toString(); // Return the user id as a string
        }
        return ""; // Return empty string if not a User
    }
}

