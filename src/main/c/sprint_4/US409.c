#include "main.h"
#include "Container.h"
#include "US409.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

static void insert(Container *c, int container_ID, int temperature, short posX, short posY, short posZ)
{
    c->container_ID = container_ID;
    c->temperature = temperature;
    c->posX = posX;
    c->posY = posY;
    c->posZ = posZ;

}

char *getfield1(const char line[102400], int num) {
    const char *p = line;
    size_t len;
    char *res;
    for (;;) {
        len = strcspn(p, ";\n");
        if (--num <= 0)
            break;
        p += len;
        if (*p == ';')
            p++;
    }
    res = malloc(len + 1);
    if (res) {
        memcpy(res, p, len);
        res[len] = '\0';
    }
    return res;
}

int get_containers_for_manifest(int cargo_manifest_id){
    int counter = 0;

    FILE *stream = fopen("file.csv", "r"); //Opening the csv data file
    if (stream == NULL) { //Checking if there was any error while opening it
        fprintf(stderr, "Error opening input file!\n");
        exit(1);
    }
    int i = 0;
    char line1[102400];
    while (fgets(line1, 102400, stream)) {
        char *tmp2 = getfield1(line1, 2); //gets column 2 content
        if (i > -1 && i < 60000) {
            if (atoi(tmp2)==cargo_manifest_id){
                counter++;
            }
        }
        //Deallocates the memory previously allocated
        free(tmp2);
        i++;
    }
    fclose(stream); //closes the stream, because it's the end of the file
    return counter;
}

Container* fill_array(Container * a, int cargo_manifest_id) {
    num_containers = get_containers_for_manifest(cargo_manifest_id);
    /* array of int * with size num_containers */
    a = (Container *) (struct Container *) malloc(num_containers * sizeof(Container));
    if(a == NULL ){
        printf (" Error reserving memory .\n " ); exit (1);
    }
    //######################################

    int counter = 0;

    FILE *stream = fopen("file.csv", "r"); //Opening the csv data file
    if (stream == NULL) { //Checking if there was any error while opening it
        fprintf(stderr, "Error opening input file!\n");
        exit(1);
    }
    int i = 0;
    char line1[102400];
    while (fgets(line1, 102400, stream)) {
        if (i>1) {
            int cargo_mani = atoi(getfield1(line1, 2));
            int container_id = atoi(getfield1(line1, 8)); //gets column 2 content
            int temperature = atoi(getfield1(line1, 9)); //gets column 2 content
            short posX = (short) atoi(getfield1(line1, 3));
            short posY = (short) atoi(getfield1(line1, 4));
            short posZ = (short) atoi(getfield1(line1, 5));
            
            if (cargo_mani==cargo_manifest_id){
                if (counter==0){
                    vehicleid =(short)atoi(getfield1(line1, 1)); //gets column 1 content
                    number_of_generators =(short)atoi(getfield1(line1, 7)); //gets column 7 content
                    power_generator =atoi(getfield1(line1, 6)); //gets column 6 content
                }

                insert(&a[counter], container_id,  temperature, posX, posY, posZ);

                counter++;
            }
        }
        i++;
    }
    fclose(stream); //closes the stream, because it's the end of the file

    //######################################

    printf("Displaying Information:\n");
    for(i = 0; i < num_containers-1; ++i)
        printf("ContainerId: %d\tTemperature: %d\tPosX: %d\tPosY: %d\tPosZ: %d\n", (a+i)->container_ID, (a+i)->temperature, ((a+i)->posX), (a+i)->posY, (a+i)->posZ);


    printf("\nVehicle Id: %d\n", vehicleid);
    printf("Number Of Generators: %d\n", number_of_generators);
    printf("Power Generator: %d\n",  power_generator);
    return a;
}