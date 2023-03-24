package com.henrimeng.model;

import com.henrimeng.model.jeu.Carte;
import com.henrimeng.model.jeu.Jeu;
import com.henrimeng.model.jeu.element.Aventurier;
import com.henrimeng.model.jeu.element.Montagne;
import com.henrimeng.model.jeu.element.Tresor;

import java.util.List;

public class MockData {

    public static Aventurier fakeAventurier() {
        return Aventurier.of("Indiana", 1, 1, "N", "AADADA");
    }

    public static Tresor fakeTresor() {
        return Tresor.of( 1, 1, 1);
    }

    public static Carte fakeCarte() {
        return Carte.of(3, 4);
    }

    public static Montagne fakeMontagne() {
        return new Montagne(2, 2);
    }

    public static Jeu fakeJeu() {
        //    C - 3 - 4
        //    M - 1 - 0
        //    M - 2 - 1
        //    T - 0 - 3 - 2
        //    T - 1 - 3 - 3
        //    A - Lara - 1 - 1 - S - AADADAGGA
        return new Jeu(
            Carte.of(3, 4),
            List.of(new Montagne(1, 0), new Montagne(2, 1)),
            List.of(Tresor.of(0, 3, 2), Tresor.of(1, 3, 3)),
            Aventurier.of("Lara", 1, 1 ,"S", "AADADAGGA")
        );
    }
}
