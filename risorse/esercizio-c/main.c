/* Modello di esercizio: un main che compila pulito con -Wall -Wextra -Werror.
 * argc e argv sono marcati (void) perche' un parametro non usato e' un avviso. */
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    (void)argc;
    (void)argv;
    printf("ciao\n");
    return EXIT_SUCCESS;
}
