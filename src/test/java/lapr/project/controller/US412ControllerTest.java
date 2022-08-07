package lapr.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class US412ControllerTest {
    @Test
    void testEnergiaTotalFornecer7C() {
        assertEquals(6324324.324324324, US412Controller.energiaTotalFornecer7C());
    }

    @Test
    void testEnergiaTotalFornecerMenos5C() {
        assertEquals(1829268.2926829269, US412Controller.energiaTotalFornecerMenos5C());
    }

    @Test
    void testEnergiaTotalFornecerParametros() {
        assertEquals(1621.6216216216212, (new US412Controller()).energiaTotalFornecerParametros(10.0, 10.0));
        assertEquals(0.0, (new US412Controller()).energiaTotalFornecerParametros(7.0, 10.0));
        assertEquals(-8108.108108108107, (new US412Controller()).energiaTotalFornecerParametros(-8.0, 10.0));
        assertEquals(-3513.513513513513, (new US412Controller()).energiaTotalFornecerParametros(0.5, 10.0));
    }

    @Test
    void testEnergiaTotalFornecerRefrigeradoParametros() {
        assertEquals(1219.5121951219512, (new US412Controller()).energiaTotalFornecerRefrigeradoParametros(10.0, 10.0));
        assertEquals(0.0, (new US412Controller()).energiaTotalFornecerRefrigeradoParametros(-5.0, 10.0));
        assertEquals(-243.90243902439025, (new US412Controller()).energiaTotalFornecerRefrigeradoParametros(-8.0, 10.0));
        assertEquals(447.1544715447155, (new US412Controller()).energiaTotalFornecerRefrigeradoParametros(0.5, 10.0));
    }

    @Test
    void testPotenciaNecessariaParaumaViagem7Graus() {
        assertEquals(10992.960427612317, (new US412Controller()).potenciaNecessariaParaumaViagem7Graus(10.0));
    }

    @Test
    void testEnergiaTotalComPotencia7() {
        assertEquals(111551.2258977448, (new US412Controller()).energiaTotalComPotencia7(10.0, 10.0));
        assertEquals(105360.14859447855, (new US412Controller()).energiaTotalComPotencia7(7.0, 10.0));
        assertEquals(91468.8567947136, (new US412Controller()).energiaTotalComPotencia7(0.0, 10.0));
        assertEquals(1668447.6546613637, (new US412Controller()).energiaTotalComPotencia7(274.15, 10.0));
    }

    @Test
    void testPotenciaNecessariaParaumaViagemMenos5Graus() {
        assertEquals(10992.960427612317, (new US412Controller()).potenciaNecessariaParaumaViagemMenos5Graus(10.0));
    }

    @Test
    void testEnergiaTotalComPotenciaMenos5() {
        assertEquals(111149.11647124511, (new US412Controller()).energiaTotalComPotenciaMenos5(10.0, 10.0));
        assertEquals(88491.5005889613, (new US412Controller()).energiaTotalComPotenciaMenos5(-5.0, 10.0));
        assertEquals(95659.14464353803, (new US412Controller()).energiaTotalComPotenciaMenos5(0.0, 10.0));
        assertEquals(1546737.3712071779, (new US412Controller()).energiaTotalComPotenciaMenos5(274.15, 10.0));
    }
}

