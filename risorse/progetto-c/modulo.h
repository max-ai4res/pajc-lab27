/* Modello di modulo: l'header dichiara, il .c definisce. La guardia evita la doppia
 * inclusione. Il prefisso (modulo_) tiene insieme le funzioni dello stesso modulo. */
#ifndef MODULO_H
#define MODULO_H

#include <stddef.h>

/* Somma degli n valori di v. */
long modulo_somma(const int *v, size_t n);

#endif
