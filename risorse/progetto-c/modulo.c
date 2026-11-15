#include "modulo.h"

long modulo_somma(const int *v, size_t n) {
    long s = 0;
    for (size_t i = 0; i < n; i++) {
        s += v[i];
    }
    return s;
}
