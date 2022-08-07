package lapr.project.auth.domain.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.util.*;
import java.util.Objects;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class User implements Serializable {

    private final Email id;
    private final Password password;
    private final String name;
    private final Set<UserRole> roles = new HashSet<>();

    public User(Email id, Password pwd, String name)
    {
        if ( (!ObjectUtils.allNotNull(id, pwd)) || StringUtils.isBlank(name))
            throw new IllegalArgumentException("User cannot have an id, password or name as null/blank.");

        this.id = id;
        this.password = pwd;
        this.name = name.trim();
    }

    public Email getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Password getPassword() {
        return password;
    }

    public boolean hasId(Email id)
    {
        return this.id.equals(id);
    }

    public boolean hasPassword(String pwd)
    {
        return this.password.checkPassword(pwd);
    }

    public boolean addRole(UserRole role)
    {
        if (role != null)
            return this.roles.add(role);
        return false;
    }

    public boolean removeRole(UserRole role)
    {
        if (role != null)
            return this.roles.remove(role);
        return false;
    }

    public boolean hasRole(UserRole role)
    {
        return this.roles.contains(role);
    }

    public boolean hasRole(String roleId)
    {
        for(UserRole role: this.roles)
        {
            if (role.hasId(roleId))
                return true;
        }
        return false;
    }

    public List<UserRole> getRoles()
    {
        List<UserRole> list = new ArrayList<>(this.roles);
        return Collections.unmodifiableList(list);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + this.id.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        User obj = (User) o;
        return Objects.equals(this.id, obj.id);
    }

    @Override
    public String toString()
    {
        return String.format("%s - %s", this.id.toString(), this.name);
    }
}