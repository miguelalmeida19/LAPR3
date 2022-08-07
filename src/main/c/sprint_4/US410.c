#include "Container.h"
#include "US410ASM.h"
#include <stddef.h>
#include <stdio.h>

float rt = 0.123;
int temperatureRefrigerated = -5;
float calcularQ(float t1, float t2){
    return (t1-t2) / rt;

}

float calcularE(float q, float time){

    return q*time;

}

Container* getContainerByPos(Container *a,int size, int x, int y, int z){
    for(int n = 0; n < size; n++){
        if( (a+n)->posX == x && (a+n)->posY == y && (a+n)->posZ == z){
            
            return (a+n);
        }
    }
    return a;    
}

float calcularEnergia(Container *a,int size, int x, int y, int z,float tempo, int temperature){
    
    int refrigerated = isrefrigerated(a,size,x,y,z);
    if(refrigerated == 1){

        Container* c = getContainerByPos(a,size,x,y,z);
        
        float res = calcularQ(temperature, c->temperature);
    
        float s = calcularE(res, tempo);
        return s;
    }else{
       return -1;
    }
    return -1;
}



