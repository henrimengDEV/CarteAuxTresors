package com.henrimeng.model.jeu;

import com.henrimeng.model.jeu.element.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Jeu {

    private final Carte carte;
    private final Map<Position, List<Element>> cellules;
    private final Aventurier aventurier;
    private final MaitreJeu maitreJeu;

    public Jeu(Carte carte, List<Montagne> montagnes, List<Tresor> tresors, Aventurier aventurier) {
        this.carte = carte;
        this.aventurier = aventurier;
        this.maitreJeu = new MaitreJeu();

        this.cellules = new HashMap<>();
        for (int i = 0; i < carte.getColonne(); i++) {
            for (int j = 0; j < carte.getLigne(); j++) {
                this.cellules.put(Position.of(i, j), new ArrayList<>());
            }
        }

        montagnes.forEach(montagne -> cellules.get(montagne.getPosition()).add(montagne));
        tresors.forEach(tresor -> cellules.get(tresor.getPosition()).add(tresor));
    }

    public void lancer() {
        aventurier.getDeplacements().forEach(deplacement -> {
            boolean estMouvementValid = maitreJeu.autoriseMouvement(cellules.get(aventurier.prochainePosition(deplacement)));

            if (!estMouvementValid) return;

            aventurier.seDeplace(deplacement);

            if (deplacement != Deplacement.A) return;

            maitreJeu.appliqueMouvement(aventurier, cellules.get(aventurier.getPosition()));
        });
    }

    public Map<Position, List<Element>> getCellules() {
        return cellules;
    }

    public Aventurier getAventurier() {
        return aventurier;
    }

    public Carte getCarte() {
        return carte;
    }

    public String resultat() {
        List<String> result = new ArrayList<>();
        List<Element> reduce = cellules.values()
                .stream()
                .reduce((elements, elements2) -> Stream.concat(elements.stream(), elements2.stream()).toList())
                .orElse(List.of());

        List<Montagne> montagnes = reduce.stream()
                .filter(Montagne.class::isInstance)
                .map(element -> (Montagne) element)
                .toList();

        List<Tresor> tresors = reduce.stream()
                .filter(Tresor.class::isInstance)
                .map(element -> (Tresor) element)
                .toList();


        result.add(carte.toText());
        for (Montagne montagne : montagnes) {
            result.add(montagne.toText());
        }
        for (Tresor tresor : tresors) {
            if (tresor.getNombreRestant() < 1) continue;
            result.add(tresor.toText());
        }
        result.add(aventurier.toText());

        return String.join("\n", result);
    }
}
