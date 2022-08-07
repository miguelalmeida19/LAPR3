#include <math.h>

float potIrrad(float constanteStefan, float temperaturaKelvin, float area){
    return (constanteStefan * pow(temperaturaKelvin, 4) * area);
}

float CelsiusToKelvin(int temperaturaExterna){
    return (temperaturaExterna + 273.15);
}

float CalcularQ(int temperaturaExterna, int temperaturaRefrigerado, float resistencia){
    return ((temperaturaExterna - temperaturaRefrigerado)/resistencia);
}

float energiaTotalContainers(float tempoViagem, float fluxoDeCalor, float PotenciaIrradiada, int quantidade){
    return (((fluxoDeCalor * tempoViagem) + (PotenciaIrradiada * tempoViagem)) * quantidade);
}
