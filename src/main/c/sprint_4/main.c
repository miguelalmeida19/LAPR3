#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <ctype.h>
#include "main.h"
#include "US409.h"
#include "Container.h"
#include "US410ASM.h"
#include "US410.h"
#include "US411.h"
#ifdef _WIN32
#include <Windows.h>
#else
#include <unistd.h>
#endif

char* main_color = "[0;33m";
char* second_color = "";
char* logo = "\n"
             " █████╗ ██████╗  ██████╗  █████╗ ██████╗ \n"
             "██╔══██╗██╔══██╗██╔═══██╗██╔══██╗██╔══██╗\n"
             "███████║██████╔╝██║██╗██║██║  ╚═╝██████╔╝\n"
             "██╔══██║██╔══██╗╚██████╔╝██║  ██╗██╔═══╝ \n"
             "██║  ██║██║  ██║ ╚═██╔═╝ ╚█████╔╝██║     \n"
             "╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝    ╚════╝ ╚═╝     \n\n";

void wait(int time){
#ifdef _WIN32
    Sleep(time);
#else
    usleep(time*1000);
#endif
}

void questions(){

    char teams[3][30]={"[1] Porto","[2] Benfica","[3] Sporting"};
    char* question1 = "\n\n\n\033[0;33mHello, before we begin, we would like to ask you a few questions out of curiosity...\033[0m";
    printf("%s", question1);

    wait(1000);

    char* question2 = "\nWhat is your football team? (Type the number only)\n";
    int loop;

    printf("\n%s\n", question2);

    for(loop=0;loop<3;loop++){
        printf("%s\n",teams[loop]);
    }

    printf("\n");

    char team[1];

    scanf("%s", team);

    if (strcmp(&team[0], "1")==0){
        main_color="\33[0;36m";
    }else if (strcmp(&team[0], "2")==0){
        main_color="\33[0;31m";
    }else if (strcmp(&team[0], "3")==0){
        main_color="\33[0;32m";
    }else {
        main_color="\33[0;37m";
    }

    wait(500);

    printf("\nOk, that was it! 😊\n");

    wait(1000);

}

void validationParameters(int argc, char* argv[]){
   
 
      if(argc != 5){
          printf("\033[0;31m[!]\033[0m You didn't pass the correct number of parameters.\n  -Usage\n     ./main cargo_manifest_id posX posY posZ\n");
          exit(0);
      }
      char r = *argv[1];
      //printf("cheiro: %d\n",isdigit(r));
      for(int n = 1; n < argc; n++){
          r = *argv[n];
          if(!isdigit(r)){
              printf("\033[0;31m[!]\033[0m One or more arguments are not a valid integer. Please try again with valid input values.\n");
            exit(0);
          }
     }
}

float energiaTotal(int TemperaturaExterna, float TempoViagem, int Quantidade){
    float Resistencia = 1.23 * pow(10, -1);
    float ConstanteStefan = 5.67 * pow(10, -8);
    float Area = 29.74;
    int TemperaturaRefrigerado = -5;    

    float fluxoDeCalor = CalcularQ(TemperaturaExterna, TemperaturaRefrigerado, Resistencia);
    
    float temperaturaKelvin = CelsiusToKelvin(TemperaturaExterna);
    
    float PotenciaIrradiada = potIrrad(ConstanteStefan, temperaturaKelvin, Area);

    return energiaTotalContainers(TempoViagem, fluxoDeCalor, PotenciaIrradiada, Quantidade);
}

int main(int argc, char* argv[]) {
    system("clear");
    validationParameters(argc, argv);

    int cargo_manifest_id = atoi(argv[1]);
    
    puts(logo);
    a = fill_array((Container *) a, cargo_manifest_id);

    //get the data needed for the next US

    float tempo = 0;
    printf("Please, digit the time of the trip in hours: ");
    scanf("%5f",&tempo);
    tempo = tempo * 3600; //time in seconds
    printf("\n");
    printf("Please, insert the exterior temperature: ");
    int temperature = 0;
    scanf("%5d",&temperature);

   // ----------------------------------- US410 :parting_face: -------------------------------------------- 
    int res = calcularEnergia(a, num_containers, atoi(argv[2]),atoi(argv[3]),atoi(argv[4]), tempo, temperature);
    if(res < 0){
	    printf("\nThe container in the position (%d %d %d) is not refrigerated :)\n",atoi(argv[2]),atoi(argv[3]),atoi(argv[4]));
    }else{
	    printf("\nEnergy: %d J\n", res);
    }

    int cont = 0;

    for(int x = 0; x < 4; x++){
        for(int y = 0; y < 5; y++){
            for(int z = 0; z < 5; z++){

                int res = isrefrigerated(a,num_containers,x,y,z);
                if(res == 1){
	                cont++;
                }
            }
        }
    }

    float energiaTotalBarco = number_of_generators * power_generator;
    float energiaTotalContentores = energiaTotal(temperature, tempo, cont);

    printf("\nTotal Ship Power: %E J", energiaTotalBarco*tempo);
    printf("\nEnergy needed for the %d refrigerated containers: %E J\n", cont, energiaTotalContentores);

    if(energiaTotalContentores > energiaTotalBarco*tempo){
        printf("\n\033[0;31m[!]\033[0m The total energy that the boat can deliver is less than the total energy that reefer containers need!\n\n");
    }


    // --------------------------------------- (º_º) ------------------------------------------------------
}

