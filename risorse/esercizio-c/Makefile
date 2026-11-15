# Makefile generico di un esercizio C: ogni sorgente della cartella diventa un
# eseguibile in bin/ con lo stesso nome (main.c -> bin/main, prova.c -> bin/prova).
#   make        compila tutto
#   make run    compila se serve e lancia bin/main
#   make asan   ricompila bin/main con AddressSanitizer (errori di memoria con file e riga)
#   make clean  cancella bin/
# C17 con tutti gli avvisi, che sono errori (-Werror); -g per il debugger; -lm la libreria
# matematica (serve su Linux). Le ricette vogliono il TAB, non gli spazi.
CC      = gcc
CFLAGS  = -std=c17 -Wall -Wextra -Werror -g
LDLIBS  = -lm
SRC     = $(wildcard *.c)
BIN     = $(patsubst %.c,bin/%,$(SRC))

all: $(BIN)

bin/%: %.c | bin
	$(CC) $(CFLAGS) -o $@ $< $(LDLIBS)

bin:
	mkdir -p bin

run: bin/main
	./bin/main

asan: main.c | bin
	$(CC) $(CFLAGS) -fsanitize=address -o bin/main $< $(LDLIBS)

clean:
	rm -rf bin

.PHONY: all run asan clean
