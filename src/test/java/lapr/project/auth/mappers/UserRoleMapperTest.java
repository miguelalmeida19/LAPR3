package lapr.project.auth.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import lapr.project.auth.domain.model.UserRole;
import lapr.project.auth.mappers.dto.UserRoleDTO;
import org.junit.jupiter.api.Test;

class UserRoleMapperTest {
    @Test
    void testToDTO() {
        UserRoleMapper userRoleMapper = new UserRoleMapper();
        assertTrue(userRoleMapper.toDTO(new ArrayList<UserRole>()).isEmpty());
    }

    @Test
    void testToDTO2() {
        UserRoleMapper userRoleMapper = new UserRoleMapper();

        ArrayList<UserRole> userRoleList = new ArrayList<UserRole>();
        userRoleList.add(new UserRole("42", "The characteristics of someone or something"));
        List<UserRoleDTO> actualToDTOResult = userRoleMapper.toDTO(userRoleList);
        assertEquals(1, actualToDTOResult.size());
        UserRoleDTO getResult = actualToDTOResult.get(0);
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("42", getResult.getId());
    }
}

