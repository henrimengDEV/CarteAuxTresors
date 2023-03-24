package com.henrimeng.model.jeu;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.lang.String.format;

@Getter
@EqualsAndHashCode
public final class Carte {

    private static final int NB_ATTRIBUTES = 3;
    private final int colonne;
    private final int ligne;

    private Carte(int colonne, int ligne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public static Carte of(int colonne, int ligne) {
        if (isNegative(colonne) || isNegative(ligne)) {
            throw new CarteIncorrectException(
                format("Carte ne peut pas avoir de nombre de colonne ou ligne négative[%d, %d]", colonne, ligne)
            );
        }
        return new Carte(colonne, ligne);
    }

    public static Carte fromText(String line) {
        String[] attributes = line.split(" - ");

        if (attributes.length != NB_ATTRIBUTES) {
            throw new CarteIncorrectException(
                format("[%s] semble être incorrect pour créer une carte", line)
            );
        }

        return of(Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]));
    }

    public String toText() {
        return String.join(" - ", "C", String.valueOf(colonne), String.valueOf(ligne));
    }

    private static boolean isNegative(int source) {
        return source < 0;
    }
}
