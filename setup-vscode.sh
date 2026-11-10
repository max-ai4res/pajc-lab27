#!/usr/bin/env bash
# setup-vscode.sh: prepara il computer per la parte C del corso PAJC con Visual Studio Code.
#
#   ./setup-vscode.sh        controlla, e per ogni cosa che manca chiede se installarla
#   ./setup-vscode.sh -y     installa senza chiedere
#   ./setup-vscode.sh -n     solo il controllo, non cambia niente
#
# Che cosa fa, in ordine:
#   1. toolchain: gcc, make, git (macOS: Command Line Tools; Linux e WSL: apt/dnf/pacman;
#      MSYS2 su Windows: pacman) e una compilazione di prova con i flag del corso
#   2. VS Code: trova il comando `code` e installa le estensioni C/C++ e CodeLLDB
#      (su Windows anche WSL)
#   3. impostazioni utente di VS Code: aggiunge le poche chiavi della parte C (standard C17,
#      -Wall -Wextra per IntelliSense, TAB nei Makefile, fine riga LF) senza toccare il resto,
#      dopo una copia di sicurezza del file
#   4. su Windows: git senza conversione dei fine riga (core.autocrlf false)
# Funziona su macOS, Linux, Windows con WSL (si lancia dentro Ubuntu) e Windows con MSYS2.
# Si può rilanciare quante volte si vuole: ciò che c'è già lo lascia com'è.
set -u

MODO=chiedi
case "${1:-}" in
  -y) MODO=si ;;
  -n) MODO=no ;;
  -h|--help) sed -n '2,20p' "$0"; exit 0 ;;
  "") ;;
  *) echo "uso: $0 [-y | -n]"; exit 2 ;;
esac

ESTENSIONI="ms-vscode.cpptools vadimcn.vscode-lldb"
ERRORI=0

ok()    { printf '  \033[32m✓\033[0m %s\n' "$*"; }
manca() { printf '  \033[31m✗\033[0m %s\n' "$*"; ERRORI=$((ERRORI + 1)); }
nota()  { printf '  \033[33m!\033[0m %s\n' "$*"; }
passo() { printf '\n\033[1m%s\033[0m\n' "$*"; }

# chiedi "domanda": vero se si deve procedere (-y: sempre; -n: mai)
chiedi() {
  case $MODO in
    si) return 0 ;;
    no) nota "$1: da fare (lanciato con -n, non cambio niente)"; return 1 ;;
  esac
  printf '  %s [s/N] ' "$1"
  read -r risposta
  case "$risposta" in s|S|si|SI|sì|y|Y) return 0 ;; *) return 1 ;; esac
}

# --- il sistema -----------------------------------------------------------------------
SO=altro
case "$(uname -s)" in
  Darwin) SO=macos ;;
  Linux)  SO=linux; grep -qi microsoft /proc/version 2>/dev/null && SO=wsl ;;
  MINGW*|MSYS*) SO=msys2 ;;
esac
DISTRO=""
if [ -r /etc/os-release ]; then
  DISTRO="$(. /etc/os-release; echo "${ID:-} ${ID_LIKE:-}")"
fi

passo "Sistema: $SO${DISTRO:+ ($DISTRO)}"
case $SO in
  wsl)   ok "Windows con WSL: la via consigliata" ;;
  msys2) nota "MSYS2: funziona, ma AddressSanitizer e il debugger sono meno affidabili che in WSL (vedi VSCODE.md)" ;;
  altro) manca "sistema non riconosciuto ($(uname -s)): segui VSCODE.md a mano"; exit 1 ;;
esac

# --- 1. toolchain ---------------------------------------------------------------------
passo "1. Compilatore, make e git"

installa_toolchain() {
  case $SO in
    macos)
      nota "parte l'installazione dei Command Line Tools di Xcode (finestra di sistema, qualche minuto)"
      xcode-select --install 2>/dev/null || true
      echo "  Quando ha finito, rilancia ./setup-vscode.sh"
      exit 1 ;;
    linux|wsl)
      if command -v apt-get >/dev/null; then
        sudo apt-get update && sudo apt-get install -y build-essential gdb git
      elif command -v dnf >/dev/null; then
        sudo dnf install -y gcc make gdb git
      elif command -v pacman >/dev/null; then
        sudo pacman -S --needed --noconfirm gcc make gdb git
      else
        manca "non so quale gestore di pacchetti usare: installa gcc, make, gdb e git a mano"
      fi ;;
    msys2)
      pacman -S --needed --noconfirm mingw-w64-ucrt-x86_64-gcc make git ;;
  esac
}

if [ $SO = macos ] && ! xcode-select -p >/dev/null 2>&1; then
  manca "Command Line Tools di Xcode non installati (gcc, make, lldb)"
  chiedi "Installarli ora?" && installa_toolchain
fi

MANCANTI=""
for cmd in gcc make git; do
  if command -v $cmd >/dev/null 2>&1; then
    ok "$cmd: $($cmd --version 2>/dev/null | head -1)"
  else
    manca "$cmd non trovato"
    MANCANTI="$MANCANTI $cmd"
  fi
done
if [ -n "$MANCANTI" ]; then
  chiedi "Installare$MANCANTI?" && installa_toolchain
  for cmd in $MANCANTI; do
    command -v $cmd >/dev/null 2>&1 && ok "$cmd installato" || manca "$cmd manca ancora"
  done
fi
case $SO in
  linux|wsl)
    command -v gdb >/dev/null 2>&1 && ok "gdb: $(gdb --version | head -1)" \
      || nota "gdb non c'è: non serve con CodeLLDB, ma è comodo da terminale (apt install gdb)" ;;
esac

# la compilazione di prova con i flag del corso
if command -v gcc >/dev/null 2>&1 && command -v make >/dev/null 2>&1; then
  PROVA="$(mktemp -d 2>/dev/null || mktemp -d -t pajc)"
  cat > "$PROVA/main.c" <<'EOC'
#include <stdio.h>
#include <math.h>
int main(void) { printf("ok %.0f\n", sqrt(16.0)); return 0; }
EOC
  printf 'bin/%%: %%.c | bin\n\tgcc -std=c17 -Wall -Wextra -Werror -g -o $@ $< -lm\nbin:\n\tmkdir -p bin\n' > "$PROVA/Makefile"
  if (cd "$PROVA" && make bin/main >/dev/null 2>&1 && ./bin/main 2>/dev/null | grep -q 'ok 4') \
     || (cd "$PROVA" && [ $SO = msys2 ] && ./bin/main.exe 2>/dev/null | grep -q 'ok 4'); then
    ok "compilazione di prova: gcc -std=c17 -Wall -Wextra -Werror con make, funziona"
  else
    manca "la compilazione di prova non è riuscita: prova a mano in $PROVA con 'make bin/main'"
  fi
  [ $ERRORI -eq 0 ] && rm -rf "$PROVA"
fi

# --- 2. VS Code e le estensioni ---------------------------------------------------------
passo "2. Visual Studio Code"

CODE=""
if command -v code >/dev/null 2>&1; then
  CODE="$(command -v code)"
else
  for c in "/Applications/Visual Studio Code.app/Contents/Resources/app/bin/code" \
           "$HOME/Applications/Visual Studio Code.app/Contents/Resources/app/bin/code" \
           /usr/share/code/bin/code /snap/bin/code /usr/bin/code \
           /mnt/c/Users/*/AppData/Local/Programs/"Microsoft VS Code"/bin/code \
           "/mnt/c/Program Files/Microsoft VS Code/bin/code" \
           "/c/Users/$USER/AppData/Local/Programs/Microsoft VS Code/bin/code"; do
    [ -x "$c" ] && { CODE="$c"; break; }
  done
fi

if [ -z "$CODE" ]; then
  manca "non trovo il comando 'code' di VS Code"
  case $SO in
    macos) echo "    Scarica VS Code da https://code.visualstudio.com, spostalo in Applicazioni, aprilo e da"
           echo "    ⌘⇧P lancia «Shell Command: Install 'code' command in PATH». Poi rilancia lo script." ;;
    linux) echo "    Installa VS Code (pacchetto .deb/.rpm da https://code.visualstudio.com, o snap) e rilancia." ;;
    wsl)   echo "    VS Code va installato su Windows (https://code.visualstudio.com), non dentro WSL;"
           echo "    chiudi e riapri il terminale Ubuntu, poi rilancia lo script." ;;
    msys2) echo "    Installa VS Code su Windows con l'opzione «Add to PATH» e riapri il terminale MSYS2." ;;
  esac
else
  ok "VS Code $("$CODE" --version 2>/dev/null | head -1) ($CODE)"
  INSTALLATE="$("$CODE" --list-extensions 2>/dev/null)"
  [ $SO = wsl ] && ESTENSIONI="ms-vscode-remote.remote-wsl $ESTENSIONI"
  for e in $ESTENSIONI; do
    if printf '%s\n' "$INSTALLATE" | grep -qix "$e"; then
      ok "estensione $e"
    else
      manca "estensione $e non installata"
      if chiedi "Installare $e?"; then
        "$CODE" --install-extension "$e" >/dev/null 2>&1 && { ok "$e installata"; ERRORI=$((ERRORI - 1)); } \
          || manca "installazione di $e fallita: dalla vista Estensioni di VS Code, cerca '$e'"
      fi
    fi
  done
  if [ $SO = wsl ]; then
    nota "in WSL le estensioni C/C++ e CodeLLDB devono stare nella sezione «WSL: Ubuntu» della vista"
    nota "Estensioni: se non ci sono, aprile da lì con «Install in WSL» (VS Code le propone da solo)"
  fi
fi

# --- 3. le impostazioni utente di VS Code -------------------------------------------------
passo "3. Impostazioni utente di VS Code"

SETTINGS=""
case $SO in
  macos) SETTINGS="$HOME/Library/Application Support/Code/User/settings.json" ;;
  linux) SETTINGS="$HOME/.config/Code/User/settings.json" ;;
  wsl)
    APPDATA_WIN="$(cmd.exe /C 'echo %APPDATA%' 2>/dev/null | tr -d '\r')"
    [ -n "$APPDATA_WIN" ] && command -v wslpath >/dev/null && SETTINGS="$(wslpath "$APPDATA_WIN")/Code/User/settings.json" ;;
  msys2) SETTINGS="${APPDATA:-}/Code/User/settings.json" ;;
esac

# le chiavi da aggiungere (solo se mancano): poche e tutte per il C
CHIAVI='{
  "C_Cpp.default.cStandard": "c17",
  "C_Cpp.default.compilerArgs": ["-Wall", "-Wextra"],
  "C_Cpp.debugShortcut": false,
  "[c]": {"editor.tabSize": 4, "editor.insertSpaces": true, "editor.formatOnSave": false},
  "[makefile]": {"editor.insertSpaces": false},
  "files.eol": "\n"
}'

if [ -z "$SETTINGS" ]; then
  nota "non so dove sta settings.json su questo sistema: aggiungi a mano le chiavi elencate in VSCODE.md"
elif ! command -v python3 >/dev/null 2>&1; then
  nota "python3 non c'è: aggiungi a mano a $SETTINGS le chiavi elencate in VSCODE.md"
else
  echo "  file: $SETTINGS"
  if [ ! -d "$(dirname "$SETTINGS")" ] && [ $SO != wsl ]; then
    nota "VS Code non è mai stato aperto (la cartella non esiste): aprilo una volta e rilancia"
  elif [ $MODO = no ]; then
    python3 - "$SETTINGS" "$CHIAVI" <<'EOP'
import json, re, sys, pathlib
p = pathlib.Path(sys.argv[1]); txt = p.read_text() if p.exists() else "{}"
for k in json.loads(sys.argv[2]):
    c = "già impostata" if re.search(r'^\s*"' + re.escape(k) + r'"\s*:', txt, re.M) else "da aggiungere"
    print(f"    {k}: {c}")
EOP
  else
    python3 - "$SETTINGS" "$CHIAVI" <<'EOP'
import json, re, sys, pathlib, shutil, datetime
p = pathlib.Path(sys.argv[1]); nuove = json.loads(sys.argv[2])
txt = p.read_text() if p.exists() else ""
if not txt.strip():
    txt = "{\n}\n"
presente = lambda k: re.search(r'^\s*"' + re.escape(k) + r'"\s*:', txt, re.M)
da_fare = {k: v for k, v in nuove.items() if not presente(k)}
for k in nuove:
    print(f"    {k}: {'già impostata' if k not in da_fare else 'aggiunta'}")
if not da_fare:
    sys.exit(0)
# inserimento testuale prima dell'ultima graffa: i commenti del file restano dove sono
i = txt.rstrip().rfind('}')
if i < 0:
    print("    il file non sembra JSON: non lo tocco"); sys.exit(1)
testa, coda = txt[:i].rstrip(), txt[i:]
sep = '' if testa.endswith(('{', ',')) else ','
righe = ',\n'.join(f'    "{k}": {json.dumps(v, ensure_ascii=False)}' for k, v in da_fare.items())
nuovo = f"{testa}{sep}\n{righe}\n{coda}"
# verifica: il risultato, senza commenti e virgole finali, deve essere JSON valido
senza = re.sub(r'(?m)^\s*//.*$', '', nuovo)
senza = re.sub(r'/\*.*?\*/', '', senza, flags=re.S)
senza = re.sub(r'("(?:[^"\\]|\\.)*")|//[^\n]*', lambda m: m.group(1) or '', senza)
senza = re.sub(r',(\s*[}\]])', r'\1', senza)
try:
    json.loads(senza)
except ValueError as e:
    print(f"    il risultato non sarebbe JSON valido ({e}): non lo tocco"); sys.exit(1)
if p.exists():
    bak = p.with_name(p.name + ".bak-" + datetime.datetime.now().strftime("%Y%m%d-%H%M%S"))
    shutil.copy2(p, bak); print(f"    copia di sicurezza: {bak}")
p.parent.mkdir(parents=True, exist_ok=True)
p.write_text(nuovo)
EOP
    [ $? -eq 0 ] && ok "impostazioni utente aggiornate" || manca "impostazioni utente non aggiornate"
  fi
fi

# --- 4. git su Windows: fine riga -------------------------------------------------------
if [ $SO = wsl ] || [ $SO = msys2 ]; then
  passo "4. git e i fine riga"
  if [ "$(git config --global core.autocrlf 2>/dev/null)" = false ]; then
    ok "core.autocrlf = false"
  else
    manca "git converte i fine riga (core.autocrlf non è false): make e gcc non vogliono il CR"
    chiedi "Impostare core.autocrlf=false per il tuo utente?" && git config --global core.autocrlf false && ok "fatto" && ERRORI=$((ERRORI - 1))
  fi
  if [ $SO = wsl ] && pwd | grep -q '^/mnt/'; then
    nota "il repository sta sul disco di Windows (/mnt/...): clonalo dentro Linux (cd ~ && git clone ...), è molto più veloce"
  fi
fi

# --- riepilogo ------------------------------------------------------------------------
passo "Riepilogo"
if [ $ERRORI -le 0 ]; then
  ok "tutto pronto. Apri la cartella con:  code ."
  echo "    poi un main.c, ⌘⇧B / Ctrl⇧B per compilare, F5 per il debugger (vedi VSCODE.md)."
else
  manca "$ERRORI cose da sistemare (sopra, con ✗). Rilancia lo script quando le hai fatte."
  exit 1
fi
