package lapr.project.store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import lapr.project.auth.domain.model.UserRole;

import org.junit.jupiter.api.Test;

class OrganizationRoleStoreTest {
    @Test
    void testConstructor() {
        List<UserRole> roles = (new OrganizationRoleStore()).getRoles();
        assertEquals(7, roles.size());
        UserRole getResult = roles.get(5);
        assertEquals("ADMINISTRATOR - Responsible for coordinating the software use", getResult.toString());
        UserRole getResult1 = roles.get(2);
        assertEquals(
                "MEDICAL LAB TECHNICIAN - Medical laboratory technicians assist physicians in the diagnosis and treatment"
                        + " of diseases by performing tests on tissue, blood, and other body fluids.",
                getResult1.toString());
        assertEquals("MEDICAL LAB TECHNICIAN", getResult1.getId());
        assertEquals(
                "Medical laboratory technicians assist physicians in the diagnosis and treatment of diseases by performing"
                        + " tests on tissue, blood, and other body fluids.",
                getResult1.getDescription());
        assertEquals("ADMINISTRATOR", getResult.getId());
        assertEquals("Responsible for coordinating the software use", getResult.getDescription());
        UserRole getResult2 = roles.get(4);
        assertEquals("LABORATORY COORDINATOR", getResult2.getId());
        assertEquals("Responsible for coordinating activities in the science labs.", getResult2.getDescription());
        UserRole getResult3 = roles.get(1);
        assertEquals("SPECIALIST DOCTOR", getResult3.getId());
        assertEquals("Doctor who has completed advanced education and training in a specific field of medicine.",
                getResult3.getDescription());
        UserRole getResult4 = roles.get(6);
        assertEquals("CLIENT", getResult4.getId());
        assertEquals("Client person", getResult4.getDescription());
        UserRole getResult5 = roles.get(0);
        assertEquals("TRAFFIC MANAGER", getResult5.getId());
        assertEquals("Responsible for scheduling appointments, answer patient inquiries and handle patient requests.",
                getResult5.getDescription());
        assertEquals("CLIENT - Client person", getResult4.toString());
        assertEquals(
                "TRAFFIC MANAGER - Responsible for scheduling appointments, answer patient inquiries and handle patient"
                        + " requests.",
                getResult5.toString());
        assertEquals("LABORATORY COORDINATOR - Responsible for coordinating activities in the science labs.",
                getResult2.toString());
        assertEquals("SPECIALIST DOCTOR - Doctor who has completed advanced education and training in a specific field of"
                + " medicine.", getResult3.toString());
    }

    @Test
    void testConstructor2() {
        List<UserRole> roles = (new OrganizationRoleStore()).getRoles();
        assertEquals(7, roles.size());
        UserRole getResult = roles.get(2);
        assertEquals("MEDICAL LAB TECHNICIAN", getResult.getId());
        assertEquals(
                "Medical laboratory technicians assist physicians in the diagnosis and treatment of diseases by performing"
                        + " tests on tissue, blood, and other body fluids.",
                getResult.getDescription());
        UserRole getResult1 = roles.get(5);
        assertEquals("ADMINISTRATOR", getResult1.getId());
        assertEquals("Responsible for coordinating the software use", getResult1.getDescription());
        UserRole getResult2 = roles.get(4);
        assertEquals("LABORATORY COORDINATOR", getResult2.getId());
        assertEquals("Responsible for coordinating activities in the science labs.", getResult2.getDescription());
        UserRole getResult3 = roles.get(1);
        assertEquals("SPECIALIST DOCTOR", getResult3.getId());
        assertEquals("Doctor who has completed advanced education and training in a specific field of medicine.",
                getResult3.getDescription());
        UserRole getResult4 = roles.get(6);
        assertEquals("CLIENT", getResult4.getId());
        assertEquals("Client person", getResult4.getDescription());
        UserRole getResult5 = roles.get(0);
        assertEquals("TRAFFIC MANAGER", getResult5.getId());
        assertEquals("Responsible for scheduling appointments, answer patient inquiries and handle patient requests.",
                getResult5.getDescription());
    }

    @Test
    void testConstructor3() {
        List<UserRole> roles = (new OrganizationRoleStore()).getRoles();
        assertEquals(7, roles.size());
        UserRole getResult = roles.get(5);
        assertEquals("ADMINISTRATOR - Responsible for coordinating the software use", getResult.toString());
        UserRole getResult1 = roles.get(2);
        assertEquals(
                "MEDICAL LAB TECHNICIAN - Medical laboratory technicians assist physicians in the diagnosis and treatment"
                        + " of diseases by performing tests on tissue, blood, and other body fluids.",
                getResult1.toString());
        assertEquals("MEDICAL LAB TECHNICIAN", getResult1.getId());
        assertEquals(
                "Medical laboratory technicians assist physicians in the diagnosis and treatment of diseases by performing"
                        + " tests on tissue, blood, and other body fluids.",
                getResult1.getDescription());
        assertEquals("ADMINISTRATOR", getResult.getId());
        assertEquals("Responsible for coordinating the software use", getResult.getDescription());
        UserRole getResult2 = roles.get(4);
        assertEquals("LABORATORY COORDINATOR", getResult2.getId());
        assertEquals("Responsible for coordinating activities in the science labs.", getResult2.getDescription());
        UserRole getResult3 = roles.get(1);
        assertEquals("SPECIALIST DOCTOR", getResult3.getId());
        assertEquals("Doctor who has completed advanced education and training in a specific field of medicine.",
                getResult3.getDescription());
        UserRole getResult4 = roles.get(6);
        assertEquals("CLIENT", getResult4.getId());
        assertEquals("Client person", getResult4.getDescription());
        UserRole getResult5 = roles.get(0);
        assertEquals("TRAFFIC MANAGER", getResult5.getId());
        assertEquals("Responsible for scheduling appointments, answer patient inquiries and handle patient requests.",
                getResult5.getDescription());
        assertEquals("CLIENT - Client person", getResult4.toString());
        assertEquals(
                "TRAFFIC MANAGER - Responsible for scheduling appointments, answer patient inquiries and handle patient"
                        + " requests.",
                getResult5.toString());
        assertEquals("LABORATORY COORDINATOR - Responsible for coordinating activities in the science labs.",
                getResult2.toString());
        assertEquals("SPECIALIST DOCTOR - Doctor who has completed advanced education and training in a specific field of"
                + " medicine.", getResult3.toString());
    }

    @Test
    void testConstructor4() {
        List<UserRole> roles = (new OrganizationRoleStore()).getRoles();
        assertEquals(7, roles.size());
        UserRole getResult = roles.get(2);
        assertEquals("MEDICAL LAB TECHNICIAN", getResult.getId());
        assertEquals(
                "Medical laboratory technicians assist physicians in the diagnosis and treatment of diseases by performing"
                        + " tests on tissue, blood, and other body fluids.",
                getResult.getDescription());
        UserRole getResult1 = roles.get(5);
        assertEquals("ADMINISTRATOR", getResult1.getId());
        assertEquals("Responsible for coordinating the software use", getResult1.getDescription());
        UserRole getResult2 = roles.get(4);
        assertEquals("LABORATORY COORDINATOR", getResult2.getId());
        assertEquals("Responsible for coordinating activities in the science labs.", getResult2.getDescription());
        UserRole getResult3 = roles.get(1);
        assertEquals("SPECIALIST DOCTOR", getResult3.getId());
        assertEquals("Doctor who has completed advanced education and training in a specific field of medicine.",
                getResult3.getDescription());
        UserRole getResult4 = roles.get(6);
        assertEquals("CLIENT", getResult4.getId());
        assertEquals("Client person", getResult4.getDescription());
        UserRole getResult5 = roles.get(0);
        assertEquals("TRAFFIC MANAGER", getResult5.getId());
        assertEquals("Responsible for scheduling appointments, answer patient inquiries and handle patient requests.",
                getResult5.getDescription());
    }
}

