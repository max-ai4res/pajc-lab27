# Risorse da riusare

Materiale che torna da un esercizio all'altro. Non sono progetti: si copiano.

| Cartella | Che cos'è | Come si usa |
|---|---|---|
| `esercizio-c/` | modello di esercizio C: un `Makefile` generico e un `main.c` minimo | `cp -r risorse/esercizio-c MioEsercizio`, poi si scrive in `main.c` |
| `progetto-c/` | modello di programma C a più file: `main.c`, un modulo `.c`/`.h`, `Makefile` che compila ogni `.c` in un `.o` e li linka in `bin/main` | `cp -r risorse/progetto-c MioProgetto`; un sorgente nuovo entra da solo, non c'è niente da elencare |

Il `Makefile` di `esercizio-c` compila ogni sorgente della cartella in `bin/<nome>` (regola
`bin/%: %.c`: `main.c` → `bin/main`), `make run` lancia `bin/main`, `make asan` lo ricompila
con AddressSanitizer, `make clean` pulisce. È la forma che i task e il debugger di VS Code
(`.vscode/` nella radice del repository, guida in `VSCODE.md`) si aspettano: con `main.c`
aperto, `⌘⇧B` / `Ctrl⇧B` compila e F5 fa il debug, anche dentro queste cartelle.

Il `Makefile` di `progetto-c` non elenca né i sorgenti (`wildcard`) né le dipendenze dagli
header: le scrive il compilatore con `-MMD -MP`, un file `.d` accanto a ogni `.o`, che il
`Makefile` include con `-include`. Cambiato un `.h`, si ricompilano solo i `.c` che lo
includono. I due modelli non si mescolano: in `esercizio-c` ogni sorgente è un programma a
sé, in `progetto-c` tutti i sorgenti formano un programma solo (un solo `main`).
