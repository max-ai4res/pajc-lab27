/* Modello di programma a piu' file: main.c usa il modulo attraverso il suo header. */
#include <stdio.h>
#include <stdlib.h>
#include "modulo.h"

int main(void) {
    int v[] = {1, 2, 3, 4};
    printf("somma = %ld\n", modulo_somma(v, sizeof v / sizeof v[0]));
    return EXIT_SUCCESS;
}
