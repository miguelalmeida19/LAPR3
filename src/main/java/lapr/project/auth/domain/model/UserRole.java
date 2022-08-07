package lapr.project.auth.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class UserRole implements Serializable {

    private final String id;
    private final String description;

    public UserRole(String id, String description)
    {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(description))
            throw new IllegalArgumentException("UserRole id and/or description cannot be blank.");

        this.id = extractId(id);
        this.description = description;
    }

    private String extractId(String id)
    {
        return id.trim().toUpperCase();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasId(String id)
    {
        if (StringUtils.isBlank(id))
            return false;
        return this.id.equals(extractId(id));
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
        UserRole obj = (UserRole) o;
        return Objects.equals(this.id, obj.id);
    }

    @Override
    public String toString()
    {
        return String.format("%s - %s", this.id, this.description);
    }
}