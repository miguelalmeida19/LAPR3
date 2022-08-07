package lapr.project.auth.mappers;

import lapr.project.auth.domain.model.UserRole;
import lapr.project.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.List;

public class UserRoleMapper {

    public UserRoleDTO toDTO(UserRole role)
    {
        return new UserRoleDTO(role.getId(),role.getDescription());
    }

    public List<UserRoleDTO> toDTO(List<UserRole> roles)
    {
        List<UserRoleDTO> rolesDTO = new ArrayList<>();
        for(UserRole role:roles)
        {
            rolesDTO.add(this.toDTO(role));
        }
        return rolesDTO;
    }
}