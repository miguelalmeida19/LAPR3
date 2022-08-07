package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CenterOfMassControllerTest {
    @Test
    void testCenterOfMassOfVessel() {
        double[] actualCenterOfMassOfVesselResult = CenterOfMassController.centerOfMassOfVessel(10.0, 10.0, 10.0, 10.0,
                10.0, 10.0, 10.0, 10.0, "Position");
        assertEquals(3, actualCenterOfMassOfVesselResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[1]);
        assertEquals(10.0, actualCenterOfMassOfVesselResult[2]);
    }

    @Test
    void testCenterOfMassOfVessel2() {
        double[] actualCenterOfMassOfVesselResult = CenterOfMassController.centerOfMassOfVessel(10.0, 10.0, 10.0, 10.0,
                10.0, 10.0, 10.0, 10.0, (String) "stern");
        assertEquals(3, actualCenterOfMassOfVesselResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[1]);
        assertEquals(10.0, actualCenterOfMassOfVesselResult[2]);
    }

    @Test
    void testCenterOfMassOfVessel3() {
        double[] actualCenterOfMassOfVesselResult = CenterOfMassController.centerOfMassOfVessel(10.0, 10.0, 10.0, 10.0,
                10.0, 10.0, 10.0, 10.0, (String) "bow");
        assertEquals(3, actualCenterOfMassOfVesselResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[1]);
        assertEquals(10.0, actualCenterOfMassOfVesselResult[2]);
    }

    @Test
    void testCenterOfMassOfVessel4() {
        double[] actualCenterOfMassOfVesselResult = CenterOfMassController.centerOfMassOfVessel(10.0, 10.0, 10.0, 10.0,
                10.0, 10.0, 10.0, 10.0, "Position");
        assertEquals(3, actualCenterOfMassOfVesselResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[1]);
        assertEquals(10.0, actualCenterOfMassOfVesselResult[2]);
    }

    @Test
    void testCenterOfMassOfVessel5() {
        double[] actualCenterOfMassOfVesselResult = CenterOfMassController.centerOfMassOfVessel(10.0, 10.0, 10.0, 10.0,
                10.0, 10.0, 10.0, 10.0, "stern");
        assertEquals(3, actualCenterOfMassOfVesselResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[1]);
        assertEquals(10.0, actualCenterOfMassOfVesselResult[2]);
    }

    @Test
    void testCenterOfMassOfVessel6() {
        double[] actualCenterOfMassOfVesselResult = CenterOfMassController.centerOfMassOfVessel(10.0, 10.0, 10.0, 10.0,
                10.0, 10.0, 10.0, 10.0, "bow");
        assertEquals(3, actualCenterOfMassOfVesselResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselResult[1]);
        assertEquals(10.0, actualCenterOfMassOfVesselResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, "Position", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.2, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers2() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 8.0, 10.0, 10.0, 10.0, "Position", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.2, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers3() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 10.0, 1.0, 10.0, 10.0, "Position", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(4.1, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.36, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers4() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, "stern", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.2, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers5() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, "bow", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.2, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers6() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, "middle", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.2, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers7() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 8.0, 2.0, 10.0, 10.0, "Position", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(4.2, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.36, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers8() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, "Position", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.2, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers9() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(Double.NaN, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, "Position", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(Double.NaN, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.6, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers10() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 10.0, 2.0, 10.0, 10.0, "Position", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(4.2, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.36, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers11() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, "stern", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.2, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers12() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, "bow", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.2, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers13() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, "middle", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(5.0, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.2, actualCenterOfMassOfVesselWithContainersResult[2]);
    }

    @Test
    void testCenterOfMassOfVesselWithContainers14() {
        double[] actualCenterOfMassOfVesselWithContainersResult = CenterOfMassController
                .centerOfMassOfVesselWithContainers(14.0, 10.0, 10.0, 10.0, 10.0, 2.0, 10.0, 10.0, "Position", 10);
        assertEquals(3, actualCenterOfMassOfVesselWithContainersResult.length);
        assertEquals(7.0, actualCenterOfMassOfVesselWithContainersResult[0]);
        assertEquals(4.2, actualCenterOfMassOfVesselWithContainersResult[1]);
        assertEquals(8.36, actualCenterOfMassOfVesselWithContainersResult[2]);
    }
}

