package lapr.project.auth.domain.store;

import lapr.project.auth.domain.model.UserRole;
import lapr.project.auth.domain.shared.Constants;
import lapr.project.data.ExportToDatabase;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class UserRoleStore implements Serializable {

    private final Set<UserRole> store = new HashSet<>();

    public UserRole create(String id, String description)
    {
        return new UserRole(id,description);
    }

    public boolean add(UserRole role)
    {
        if (role != null && !exists(role)) return this.store.add(role);
        return false;
    }

    public boolean remove(UserRole role)
    {
        if (role != null)
            return this.store.remove(role);
        return false;
    }

    public Optional<UserRole> getById(String id)
    {
        for(UserRole role: this.store)
        {
            if(role.hasId(id))
                return Optional.of(role);
        }
        return Optional.empty();
    }

    public boolean exists(String id)
    {
        Optional<UserRole> result = getById(id);
        return result.isPresent();
    }

    public boolean exists(UserRole role)
    {
        return this.store.contains(role);
    }

    public UserRole getRandomRole(){
        UserRole role = (UserRole)store.toArray()[ExportToDatabase.randBetween(0, store.size()-1)];
        if (role.getId().equals(Constants.ROLE_CLIENT_ID)){
            role = getRandomRole();
        }
        return role;
    }

    public Set<UserRole> getStore() {
        return store;
    }
}