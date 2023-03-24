package com.henrimeng.model.jeu.element;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Getter
@EqualsAndHashCode
public final class Aventurier extends Element {
    private static final int NB_ATTRIBUTES = 6;
    private final String nom;
    private Orientation orientation;
    private final List<Deplacement> deplacements;
    private int nombreTresorRamasses;

    private Aventurier(String nom, int x, int y, String orientation, String deplacements, int nombreTresorRamasses) {
        super(Position.of(x, y));
        this.nom = nom;
        this.orientation = Orientation.valueOf(orientation);
        this.deplacements = Arrays
            .stream(deplacements.replace(" ", "")
            .split(""))
            .map(Deplacement::valueOf)
            .toList();
        this.nombreTresorRamasses = nombreTresorRamasses;
    }

    public static Aventurier of(String nom, int x, int y, String orientation, String deplacements) {
        return new Aventurier(nom, x, y, orientation, deplacements, 0);
    }

    public static Aventurier fromText(String line) {
        String[] attributes = line.split(" - ");

        if (attributes.length != NB_ATTRIBUTES) {
            throw new AventurierIncorrectException(
                format("[%s] semble être incorrect pour créer un aventurier", line)
            );
        }

        return of(
            attributes[1],
            Integer.parseInt(attributes[2]),
            Integer.parseInt(attributes[3]),
            attributes[4],
            attributes[5]
        );
    }

    public void ouvreTresor() {
        nombreTresorRamasses++;
    }

    public void seDeplace(Deplacement deplacement) {
        switch (deplacement) {
            case A -> avance();
            case D -> orientation = Orientation.suivant(orientation);
            case G -> orientation = Orientation.precedent(orientation);
        }
    }

    public Position prochainePosition(Deplacement deplacement) {
        if (deplacement != Deplacement.A) {
            return getPosition();
        }
        return switch (orientation) {
            case N -> Position.of(getPosition().getX(), getPosition().getY() - 1);
            case S -> Position.of(getPosition().getX(), getPosition().getY() + 1);
            case O -> Position.of(getPosition().getX() - 1, getPosition().getY());
            case E -> Position.of(getPosition().getX() + 1, getPosition().getY());
        };
    }

    private void avance() {
        setPosition(prochainePosition(Deplacement.A));
    }

    public String toText() {
        return String.join(
                " - ",
                "A",
                nom,
                String.valueOf(getPosition().getX()),
                String.valueOf(getPosition().getY()),
                orientation.name(),
                deplacements.stream().map(Deplacement::name).collect(Collectors.joining(""))
        );
    }
}
