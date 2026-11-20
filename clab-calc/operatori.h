#ifndef __OPERATORI__
#define __OPERATORI__

typedef int (*op_binario)(int, int);

typedef struct {
    const char* nome;
    char simbolo;
    op_binario op;
} operatore;

void add_operatore(const char *nome, unsigned char simbolo, op_binario op);
operatore* get_operatore(unsigned char simbolo);
void init_op_map();

#endif