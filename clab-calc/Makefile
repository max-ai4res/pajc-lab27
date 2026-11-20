# Makefile generico di un programma C a piu' file: compila ogni .c della cartella in
# un .o e li linka tutti in bin/main. Niente da elencare: un sorgente nuovo entra da solo.
#   make        compila cio' che e' cambiato e linka
#   make run    compila se serve e lancia bin/main
#   make asan   ricompila tutto con AddressSanitizer
#   make clean  cancella bin/, gli oggetti e i file di dipendenza
# Le dipendenze dagli header le scrive il compilatore (-MMD -MP: un .d per ogni .o):
# cambiato un .h, make ricompila solo i .c che lo includono.
CC      = gcc
CFLAGS  = -std=c17 -Wall -Wextra -Werror -g -MMD -MP
LDLIBS  = -lm
PROG    = bin/main
SRC     = $(wildcard *.c)
OBJ     = $(SRC:.c=.o)

$(PROG): $(OBJ) | bin
	$(CC) $(CFLAGS) -o $@ $(OBJ) $(LDLIBS)

%.o: %.c
	$(CC) $(CFLAGS) -c -o $@ $<

bin:
	mkdir -p bin

run: $(PROG)
	./$(PROG)

asan: CFLAGS += -fsanitize=address
asan: clean $(PROG)

clean:
	rm -rf bin *.o *.d

-include $(OBJ:.o=.d)

.PHONY: run asan clean
