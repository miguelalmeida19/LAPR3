package lapr.project.auth;

import lapr.project.auth.domain.model.User;
import lapr.project.auth.domain.model.UserRole;
import lapr.project.auth.domain.store.UserRoleStore;
import lapr.project.auth.domain.store.UserStore;
import lapr.project.data.Persistence;

import java.io.Serializable;
import java.util.Optional;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class AuthFacade implements Serializable {
    private UserSession userSession;

    private final UserRoleStore roles ;
    private final UserStore users;

    /**
     * Constructor
     */
    public AuthFacade()
    {
        this.userSession = new UserSession();
        this.roles = new UserRoleStore();
        this.users = new UserStore();
    }


    /**
     * This method adds a new User Role
     * @param id
     * @param description
     * @return
     */
    public boolean addUserRole(String id, String description)
    {
        UserRole role = this.roles.create(id, description);
        return this.roles.add(role);
    }

    /**
     * This method adds a new User
     * @param name
     * @param email
     * @param pwd
     * @return
     */
    public boolean addUser(String name, String email, String pwd)
    {
        User user = this.users.create(name, email, pwd);
        return this.users.add(user);
    }

    public boolean addUserWithRole(String name, String email, String pwd, String roleId)
    {
        Optional<UserRole> roleResult = this.roles.getById(roleId);
        if (!roleResult.isPresent())
            return false;

        User user = this.users.create(name,email,pwd);
        user.addRole(roleResult.get());
        return this.users.add(user);
    }

    public boolean addUserWithRoles(String name, String email, String pwd, String[] rolesId)
    {
        User user = this.users.create(name, email, pwd);
        for (String roleId: rolesId)
        {
            Optional<UserRole> roleResult = this.roles.getById(roleId);
            roleResult.ifPresent(user::addRole);
        }

        return this.users.add(user);
    }

    public boolean existsUser(String email)
    {
        return this.users.exists(email);
    }

    public UserSession doLogin(String email, String password)
    {
        this.userSession = Persistence.doLogin(email, password);
        return this.userSession;
    }

    public void doLogout()
    {
        this.userSession.doLogout();
    }

    public UserSession getCurrentUserSession()
    {
        return this.userSession;
    }

    public UserStore getUsers() {
        return users;
    }

    public UserRoleStore getRoles() {
        return roles;
    }

    public UserSession getUserSession() {
        return userSession;
    }
}