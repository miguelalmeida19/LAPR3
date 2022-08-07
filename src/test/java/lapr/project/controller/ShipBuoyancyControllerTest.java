package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ShipBuoyancyControllerTest {

    @Test
    void testCalculate() {
        double[] actualCalculateResult = ShipBuoyancyController.calculate(10.0, 10.0, 10.0, 10.0, 10.0, 10);
        assertEquals(3, actualCalculateResult.length);
        assertEquals(245.16625, actualCalculateResult[0]);
        assertEquals(25.0, actualCalculateResult[1]);
        assertEquals(0.05015045135406204, actualCalculateResult[2]);
    }

}

