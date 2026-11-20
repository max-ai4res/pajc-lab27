#include <stdio.h>
#include <stdlib.h>
#include "operatori.h"

operatore* _op_map[256];

void add_operatore(const char *nome, unsigned char simbolo, op_binario op) {
    if(_op_map[simbolo] != NULL) {
        free(_op_map[simbolo]);
        _op_map[simbolo] = NULL;
    }

    operatore *o = malloc(sizeof(operatore));
    o->nome = nome;
    o->simbolo = simbolo;
    o->op = op;
    _op_map[simbolo] = o;
}

operatore* get_operatore(unsigned char simbolo) {
    return _op_map[simbolo];
}

void delete_op_map() {
    for(int i=0; i<256; i++) {
        if(_op_map[i] != NULL) {
            free(_op_map[i]);
            _op_map[i] = NULL;
        }
    }
}

void clear_op_map() {
    for(int i=0; i<256; i++)
        _op_map[i] = NULL;
}


int somma(int a, int b) { return a + b; }
int sottrazione(int a, int b) { return a - b; }
int moltiplica(int a, int b) { return a * b; }
int divisione(int a, int b) { return a / b; }

void init_op_map() {
    clear_op_map();

    add_operatore("somma",      '+', somma);
    add_operatore("sottrai",    '-', sottrazione);
    add_operatore("moltiplica", '*', moltiplica);
    add_operatore("dividi",     '/', divisione);
}