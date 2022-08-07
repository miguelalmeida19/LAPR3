package lapr.project.controller;

public class US412Controller {

    private static final double RESISTENCIAALUMINIOPOLIURETANO = (1.85 * Math.pow(10, -2)) ;
    private static final double RESISTENCIAALUMINIOSILICA = (1.23 * Math.pow(10, -1));
    private static final double constante = 5.67 * Math.pow(10, -8);
    private static final double TEMPERATURAAMBIENTE = 20.0;
    private static final int TEMPOSEGUNDOS = 9000;
    private static final double AREASUPERIORCONTENTOR = 29.74;

    /**
     * This method calculates the fluxo of calor
     * @param t1
     * @param t2
     * @param rt
     * @return
     */
    private static double fluxoCalorQ(double t1, double t2, double rt){
        return (t1 - t2) / rt;
    }

    /**
     * This method calculates the total energy to forneceite to 7 degree
     * @return
     */
    public static double energiaTotalFornecer7C(){
        double temperaturaInterna = 7.0;
        double qAluminioPoliuretano = fluxoCalorQ(TEMPERATURAAMBIENTE, temperaturaInterna, RESISTENCIAALUMINIOPOLIURETANO);
        return qAluminioPoliuretano * TEMPOSEGUNDOS;
    }

    /**
     * This method calculates the total energy to forneceite to -5 degree
     * @return
     */
    public static double energiaTotalFornecerMenos5C(){
        double temperaturaInterna= -5.0;
        double qAluminioSilica= fluxoCalorQ(TEMPERATURAAMBIENTE,temperaturaInterna,RESISTENCIAALUMINIOSILICA);
        return qAluminioSilica * TEMPOSEGUNDOS;
    }

    /**
     * This method calculates the total energy
     * @param tempAmbiente
     * @param segundos
     * @return
     */
    public double energiaTotalFornecerParametros(double tempAmbiente, double segundos){
        double temperaturaInterna = 7.0;
        double qAluminioPoliuretano = fluxoCalorQ(tempAmbiente, temperaturaInterna, RESISTENCIAALUMINIOPOLIURETANO);
        return qAluminioPoliuretano * segundos;
    }

    /**
     * This method calculates the total energy to forneceite to refrigerated with parameters
     * @param tempAmbiente
     * @param segundos
     * @return
     */
    public double energiaTotalFornecerRefrigeradoParametros(double tempAmbiente, double segundos){
        double temperaturaInterna = -5.0;
        double qAluminioSilica= fluxoCalorQ(tempAmbiente,temperaturaInterna,RESISTENCIAALUMINIOSILICA);
        return qAluminioSilica * segundos;
    }

    /**
     * This method calculates the necessary potency for a trip of 7 degree
     * @param temp
     * @return
     */
    public double potenciaNecessariaParaumaViagem7Graus(double temp){
        double potencia;
        double temperature = temp + 274.15;
        potencia = constante * Math.pow(temperature, 4) * AREASUPERIORCONTENTOR;
        return potencia;
    }

    /**
     * This method calculates the total energy with potency with 7 degree
     * @param temp
     * @param segundos
     * @return
     */
    public double energiaTotalComPotencia7(double temp, double segundos){
        double energiaFinal= energiaTotalFornecerParametros(temp, segundos);
        double potencia=potenciaNecessariaParaumaViagem7Graus(temp);
        potencia= potencia*segundos;
        energiaFinal= energiaFinal+potencia;
        return energiaFinal;
    }

    /**
     * This method calculates the necessary potency for a trip of -5 degree
     * @param temp
     * @return
     */
    public double potenciaNecessariaParaumaViagemMenos5Graus(double temp){
        double temperature = temp + 274.15;
        return constante * Math.pow(temperature, 4) * AREASUPERIORCONTENTOR;
    }

    /**
     * This method calculates the total energy with potency with -5 degree
     * @param temp
     * @param segundos
     * @return
     */
    public double energiaTotalComPotenciaMenos5(double temp, double segundos){
        double energiaFinal= energiaTotalFornecerRefrigeradoParametros(temp, segundos);
        double potencia=potenciaNecessariaParaumaViagemMenos5Graus(temp);
        potencia= potencia*segundos;
        energiaFinal= energiaFinal+potencia;
        return energiaFinal;
    }
}