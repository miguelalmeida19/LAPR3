package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ColorMapControllerTest {

    @Test
    void colorMapNotNull() {
        ColorMapController c = new ColorMapController();
        Map<Object, Integer> m =  c.colorMap(4,false);
        assertNotNull(m);
    }
}