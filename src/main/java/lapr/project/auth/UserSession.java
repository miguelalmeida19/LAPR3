package lapr.project.auth;

import lapr.project.auth.domain.model.Email;
import lapr.project.auth.domain.model.User;
import lapr.project.auth.mappers.UserRoleMapper;
import lapr.project.auth.mappers.dto.UserRoleDTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


public class UserSession implements Serializable{

    private User user;

    public UserSession()
    {
        this.user = null;
    }

    public UserSession(User user)
    {
        if (user == null)
            throw new IllegalArgumentException("Argument cannot be null.");
        this.user = user;
    }

    public void doLogout()
    {
        this.user = null;
    }

    public boolean isLoggedIn()
    {
        return this.user != null;
    }

    public boolean isLoggedInWithRole(String roleId)
    {
        if (isLoggedIn())
        {
            return this.user.hasRole(roleId);
        }
        return false;
    }

    public String getUserName()
    {
        if (isLoggedIn())
            this.user.getName();
        return null;
    }

    public Email getUserId()
    {
        if (isLoggedIn())
            return this.user.getId();
        return null;
    }

    public String getPassword(){
        if (isLoggedIn())
            return this.user.getPassword().getString();
        return null;
    }

    public List<UserRoleDTO> getUserRoles()
    {
        if (isLoggedIn()) {
            UserRoleMapper mapper = new UserRoleMapper();
            return mapper.toDTO(this.user.getRoles());
        }
        return Collections.EMPTY_LIST;
    }
}
