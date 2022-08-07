#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "US314.h"
#include "asm.h"
const int X = 4;
const int Y = 5;
const int Z = 5;
const int capacity = X*Y*Z; //real capacity of the ship.
int cargo[4][5][5] = {0}; //initialize all positions of the matrix to 0

int *ptr1;

/*
This method gets the content of a specific column and line of the csv file
*/
char *getfield(char line[102400], int num) {
    const char *p = line;
    size_t len;
    char *res;
    for (;;) {
        len = strcspn(p, ",\n");
        if (--num <= 0)
            break;
        p += len;
        if (*p == ',')
            p++;
    }
    res = malloc(len + 1);
    if (res) {
        memcpy(res, p, len);
        res[len] = '\0';
    }
    return res;
}

/*
This method fills the matrix with all the containers and positions for a given cargo manifest id
*/
void fill_matrix(int matrix[X][Y][Z], int cargo_manifest_id) {
    FILE *stream = fopen("cargo_manifest_container.csv", "r"); //Opening the csv data file
    if (stream == NULL) { //Checking if there was any error while opening it
        fprintf(stderr, "Error opening input file!\n");
        exit(1);
    }
    int i = 0;
    char line1[102400];
    while (fgets(line1, 102400, stream)) {
        char *tmp1 = getfield(line1, 1); //gets column 1 content
        char *tmp2 = getfield(line1, 2); //gets column 2 content
        char *tmp3 = getfield(line1, 3); //gets column 3 content
        char *tmp4 = getfield(line1, 4); //gets column 4 content
        char *tmp5 = getfield(line1, 5); //gets column 5 content
        if (i > -1 && i < 60000) {
            if (atoi(tmp1)==cargo_manifest_id){
                matrix[atoi(tmp3)][atoi(tmp4)][atoi(tmp5)] = atoi(tmp2);
            }
        }
        //Deallocates the memory previously allocated
        free(tmp1);
        free(tmp2);
        free(tmp3);
        free(tmp4);
        free(tmp5);
        i++;
    }
    fclose(stream); //closes the stream, because it's the end of the file
}

void us314showoff(){
    long as = free_occupied_slots();
    printf(" \033[33;1m -----------------  \033[1;31mShip cargo status \033[33;1m -----------------\033[0m\n");
    printf("\033[36;1m  [*] Free slots:\033[0m %ld\n", (as & 0xFFFFFFFF00000000)>>32);
   printf("\033[36;1m  [*] Occupied slots:\033[0m %ld\n\n", as& 0xFFFFFFFF);

}

/*
This is the main, where everything is called
*/
int main(int argc, char* argv[]) {
    if(argc!=5){
        printf("\n\033[1;31m[*]\033[0m The needed parameters to run this program was not passed. Please try again with the needed data.\n");
        printf("\033[36;1m[*]\033[0m Usage:\n");
        printf("   ./main containerID positionX positionY positionZ\n");
        exit(0);
    }
    int cargo_manifest_id = atoi(argv[1]);
    if(cargo_manifest_id == 0){
        printf("\033[0;31m[!]\033[0m The cargo manifest id provided isn't a valid integer. Please try again with a valid number.\n");
        exit(0);
    }
    fill_matrix(cargo, cargo_manifest_id);
    for(int i=0; i<Z; i++){
        printf("\033[1;31m[z:%d]\n\033[0m", i);
        for (int j=0; j<Y; j++){
            if (j==0){
                for (int g=0; g<X; g++){
                    if (g==0){
                        printf("\t");
                    }
                    printf("\033[0;34m[x=%d]\t\t\033[0m", g);
                }
                printf("\n");
            }
            for (int m=0; m<X; m++){
                if (m==0){
                    printf("\033[0;36m[y=%d]\t\033[0m", j);
                }
                printf("%-10d\t", cargo[m][j][i]);
            }
            printf("\n");
        }
        printf("\n");
    }
    us314showoff();

    int res = check_if_container_is_there(atoi(argv[2]), atoi(argv[3]), atoi(argv[4]));
    printf("\n\033[1;31mAssembly:\033[0m %d \n", res);

    int array[3][3] = {{1,2,3}, {2,3,4}, {3,4,5}};
    ptr1 = (int *) array;
    printf("\n\033[1;31mTotal number of occupied positions:\033[0m %d\n", count());
}
