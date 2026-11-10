# Visual Studio Code per la parte C del corso

Il materiale C del laboratorio (`LC-…`, `BC-…`) è una cartella per esercizio con un `Makefile`:
`make` compila, `make run` esegue, `make asan` ricompila con AddressSanitizer, `make clean`
pulisce. L'eseguibile si chiama come il sorgente (`main.c` → `bin/main`, `stack.c` →
`bin/stack`). VS Code non compila da solo: chiama lo stesso `make` del terminale, e il
debugger lancia l'eseguibile che `make` ha prodotto. I progetti Java restano in Eclipse,
che usa questa stessa cartella come workspace: le due cose convivono.

In breve, per tutti e tre i sistemi:

1. la toolchain (gcc, make, git) come nella tabella qui sotto;
2. VS Code, con il comando `code` nel terminale;
3. dentro la cartella del repository, `./setup-vscode.sh`: controlla tutto, installa ciò
   che manca (chiedendo prima), aggiunge le impostazioni per il C al tuo profilo VS Code;
4. `code .` e, con un `main.c` aperto, `⌘⇧B` (`Ctrl⇧B`) compila e **F5** fa il debug.

| Sistema | Toolchain | VS Code |
|---|---|---|
| macOS | `xcode-select --install` (Command Line Tools: `gcc` è clang, make, lldb) | app da code.visualstudio.com; da `⌘⇧P` «Shell Command: Install 'code' command in PATH» |
| Linux | `sudo apt install build-essential gdb git` (Fedora: `dnf install gcc make gdb git`) | pacchetto .deb/.rpm da code.visualstudio.com, o snap |
| Windows | **WSL** con Ubuntu, poi lo stesso `apt` di Linux dentro Ubuntu | VS Code **su Windows** con l'estensione *WSL*; si apre il repository da Ubuntu con `code .` |

## macOS

1. Terminale: `xcode-select --install` e la finestra che si apre. Al termine `gcc --version`
   risponde «Apple clang»: va bene, `gcc` qui è clang e i comandi del corso sono gli stessi.
2. Installa VS Code, aprilo, `⌘⇧P` → «Shell Command: Install 'code' command in PATH».
3. Nel terminale: `git clone https://github.com/max-ai4res/pajc-lab27.git`, poi
   `cd pajc-lab27 && ./setup-vscode.sh`, infine `code .`.

Al primo debug macOS chiede la password per «Developer Tools Access»: è lldb che si attacca
al processo, si dà una volta sola. AddressSanitizer su macOS trova overflow, use-after-free
e double free ma **non i leak**: per quelli c'è `leaks --atExit -- ./bin/main`.

## Linux

1. `sudo apt install build-essential gdb git` (Ubuntu, Debian; `dnf` su Fedora, `pacman` su Arch).
2. VS Code dal sito (pacchetto .deb o .rpm) o `sudo snap install code --classic`.
3. `git clone https://github.com/max-ai4res/pajc-lab27.git`, `cd pajc-lab27 && ./setup-vscode.sh`, `code .`.

CodeLLDB scarica il suo lldb la prima volta che parte un debug: serve la rete. Su Linux
AddressSanitizer segnala anche i leak alla fine del programma (`make asan`).

## Windows: WSL

La via consigliata è **WSL** (Windows Subsystem for Linux): un Ubuntu dentro Windows, con
gli stessi comandi, lo stesso `make` e lo stesso AddressSanitizer di Linux. VS Code gira su
Windows e lavora dentro Ubuntu attraverso l'estensione *WSL*.

1. PowerShell **da amministratore**: `wsl --install`, poi riavvia. Al primo avvio di Ubuntu
   scegli nome utente e password (la password non si vede mentre la scrivi).
2. Nel terminale Ubuntu: `sudo apt update && sudo apt install build-essential gdb git`.
3. Installa VS Code **su Windows** da code.visualstudio.com (lascia spuntato «Add to PATH»)
   e, dalla vista Estensioni, l'estensione **WSL** (`ms-vscode-remote.remote-wsl`).
4. Nel terminale Ubuntu, **nella home di Linux e non su `/mnt/c`**:
   `cd ~ && git clone https://github.com/max-ai4res/pajc-lab27.git`, poi
   `cd pajc-lab27 && ./setup-vscode.sh`, infine `code .`: VS Code si apre su Windows
   con in basso a sinistra «WSL: Ubuntu».
5. Le estensioni C/C++ e CodeLLDB devono stare **dentro WSL**: nella vista Estensioni
   compaiono nella sezione «WSL: Ubuntu – Installed». Se non ci sono, VS Code le propone
   all'apertura della cartella (`.vscode/extensions.json`): «Install in WSL».

Perché nella home di Linux: i file su `/mnt/c` sono lenti da compilare e hanno permessi
strani. Lo script imposta anche `git config --global core.autocrlf false`, così i sorgenti
restano con il fine riga LF che `make` e `gcc` si aspettano.

### Windows senza WSL: MSYS2

Se WSL non è possibile: installa [MSYS2](https://www.msys2.org), nel terminale «MSYS2 UCRT64»
`pacman -S mingw-w64-ucrt-x86_64-gcc make git`, aggiungi `C:\msys64\ucrt64\bin` al PATH di
Windows e lancia `./setup-vscode.sh` da quel terminale. Gli eseguibili escono come
`bin/main.exe`; AddressSanitizer con MinGW non c'è e il debugger è meno affidabile.

## Come si lavora

Si apre **tutta la cartella** `pajc-lab27` (`code .`), non il singolo esercizio: i task e il
debugger lavorano sulla cartella del file che si ha davanti (`${fileDirname}`), quindi
basta aprire il `main.c` dell'esercizio.

| Azione | Come | Che cosa fa |
|---|---|---|
| compilare | `⌘⇧B` / `Ctrl⇧B` (task `make`) | `make` nella cartella del file aperto; gli errori nel pannello *Problems*, un clic porta alla riga |
| eseguire | *Terminal › Run Task… › make run* | `make run`: compila se serve e lancia `bin/main` nel terminale |
| cercare errori di memoria | *Run Task… › make asan* | ricompila con AddressSanitizer ed esegue: l'errore esce con file e riga |
| debug | **F5** («Debug (lldb) bin/<questo file>») | compila il sorgente aperto e lo lancia nel debugger: breakpoint con un clic a sinistra del numero di riga, variabili e stack a sinistra, `F10` passo, `F11` dentro una funzione, `F5` continua |
| debug con ASan | menu a tendina accanto a ▷ nella vista Run → «… con AddressSanitizer» | come sopra, ma con l'eseguibile strumentato: si ferma sull'errore |
| argomenti | `args` in `.vscode/launch.json` (es. `["4"]` per `bin/vla`) | la riga di comando dell'eseguibile nel debugger |
| un sorgente fra tanti | task `make bin/<questo file>` | in una cartella con più sorgenti (`LezC01-Esempi`) compila solo quello aperto |

Nel debugger si può guardare la memoria come nella lezione: con il programma fermo, nella
*Debug Console* `memory read -c 16 &x` stampa i sedici byte a partire da `x`, `p &x` ne
stampa l'indirizzo, `p *p` ciò che un puntatore indica.

## Che cosa c'è in `.vscode/`

| File | Contenuto |
|---|---|
| `extensions.json` | le estensioni consigliate: C/C++, CodeLLDB, WSL |
| `tasks.json` | i task `make`, `make bin/<questo file>`, `make run`, `make asan`, `make clean`, nella cartella del file aperto, con il *problem matcher* di gcc |
| `launch.json` | le due configurazioni di debug con CodeLLDB (normale e con AddressSanitizer) |
| `settings.json` | IntelliSense su C17 con `-Wall -Wextra`, quattro spazi nel C e TAB nei `Makefile`, fine riga LF, eseguibili e file di Eclipse nascosti dall'esplora file |

Lo script `setup-vscode.sh` aggiunge al **profilo utente** di VS Code (`settings.json` del
tuo utente, non del repository) sei chiavi, così valgono anche nelle cartelle tue fuori dal
repository: `C_Cpp.default.cStandard: c17`, `C_Cpp.default.compilerArgs: [-Wall, -Wextra]`,
`C_Cpp.debugShortcut: false`, `[c]` (quattro spazi, niente formattazione al salvataggio),
`[makefile]` (TAB), `files.eol: \n`. Le aggiunge solo se mancano, senza toccare il resto,
dopo una copia di sicurezza `settings.json.bak-…` accanto al file.

## Problemi frequenti

- **`code: command not found`** su macOS: da VS Code, `⌘⇧P` → «Shell Command: Install 'code'
  command in PATH», poi riapri il terminale. In WSL: VS Code va installato su Windows e il
  terminale Ubuntu riaperto.
- **Il bottone ▷ «Run C/C++ File»** in alto a destra compila da solo, **senza**
  `-std=c17 -Wall -Wextra -Werror`: nel repository è spento (`C_Cpp.debugShortcut`). Si usano
  i task e F5.
- **`missing separator`** da `make`: una ricetta è indentata con spazi invece del TAB. Il
  `settings.json` del repository fa inserire TAB nei `Makefile`; in un file tuo controlla
  in basso a destra «Tab Size» e che non ci sia «Spaces».
- **«Nothing to be done»** e il programma non cambia: `make` rifà solo ciò che è più vecchio
  del sorgente; dopo `make asan` l'eseguibile è quello strumentato finché non si rilancia
  `make clean` e `make`.
- **IntelliSense sottolinea ma `make` compila** (o viceversa): IntelliSense è un'analisi
  approssimata; fa fede `make`. Se le sottolineature sono sballate, `⌘⇧P` → «C/C++: Reset
  IntelliSense Database».
- **Il debugger non parte** («program not found»): l'eseguibile non c'è perché la
  compilazione è fallita (guarda il terminale) o perché il file aperto non è un `.c` della
  cartella giusta. F5 compila il file che si ha davanti.
- **Lento in WSL**: il repository sta su `/mnt/c`. Spostalo nella home di Linux (`~`).
- **Java e C nella stessa cartella**: VS Code non serve per i progetti Java, che si aprono
  in Eclipse con `pajc-lab27` come workspace; le estensioni Java non sono necessarie e,
  se ci sono, si possono disabilitare per questa cartella.
