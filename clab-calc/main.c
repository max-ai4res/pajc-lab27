#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "operatori.h"

void calcola(const int *a, const int *b, int *c, int size, 
    op_binario op) {
    for(int i=0; i<size; i++)
        c[i] = op(a[i], b[i]);
}

void visualizza(const int *a, int size) {
    for(int i=0; i<size; i++)
        printf("| %3d |\n", a[i]);
}

int main() {
    init_op_map();
    
    int a[] = {1,2,3,4,5};
    int b[] = {6,7,8,9,10};
    int c[5];
    char ops = '+';
    operatore *op = get_operatore(ops);
    calcola(a, b, c, 5, op->op);
    visualizza(c, 5);
}
