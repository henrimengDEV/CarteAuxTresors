package com.henrimeng.model.jeu;

import com.henrimeng.model.jeu.element.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.henrimeng.model.MockData.fakeAventurier;
import static com.henrimeng.model.MockData.fakeTresor;
import static org.assertj.core.api.Assertions.assertThat;

class MaitreJeuTest {

    private MaitreJeu maitreJeu;

    @BeforeEach
    void setUp() {
        maitreJeu = new MaitreJeu();
    }

    @Test
    @DisplayName("Le Maitre du jeu doit autoriser le mouvement s'il y a aucun élément gênant")
    void autoriseMouvement_doit_etre_oui() {
        List<Element> cellule = new ArrayList<>();
        assertThat(maitreJeu.autoriseMouvement(cellule)).isTrue();
    }

    @Test
    @DisplayName("Le Maitre du jeu ne doit pas autoriser le mouvement si la position est occupée par une montagne")
    void autoriseMouvement_doit_etre_non_quand_montage() {
        List<Element> cellule = new ArrayList<>();
        Montagne montagne = new Montagne(1, 1);
        cellule.add(montagne);

        assertThat(maitreJeu.autoriseMouvement(cellule)).isFalse();
    }

    @Test
    @DisplayName("Le Maitre du jeu doit autoriser le mouvement si la position est occupée par un trésor")
    void autoriseMouvement_doit_etre_oui_quand_tresor() {
        List<Element> cellule = new ArrayList<>();
        Tresor tresor = Tresor.of(1, 1, 1);
        cellule.add(tresor);

        assertThat(maitreJeu.autoriseMouvement(cellule)).isTrue();
    }

    @Test
    void appliqueMouvement() {
        Aventurier aventurier = fakeAventurier();
        Tresor tresor = fakeTresor();
        List<Element> cellule = new ArrayList<>();

        cellule.add(tresor);
        maitreJeu.appliqueMouvement(aventurier, cellule);

        assertThat(aventurier.getNombreTresorRamasses()).isEqualTo(1);
        assertThat(tresor.getNombreRestant()).isZero();
    }
}