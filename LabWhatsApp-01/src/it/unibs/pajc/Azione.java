package it.unibs.pajc;

import java.util.function.BiConsumer;

public record Azione(String comando, int nargs, BiConsumer<Sessione, String[]> esegui) {

}
