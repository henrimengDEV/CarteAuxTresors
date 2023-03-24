package com.henrimeng.model.jeu.element;

import com.henrimeng.model.jeu.Carte;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.henrimeng.model.MockData.fakeCarte;
import static com.henrimeng.model.MockData.fakeMontagne;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MontagneTest {

    @Test
    @DisplayName("La création d'une montagne à partir d'un text doit correctement se réaliser")
    void montagne_fromText_doit_correctement_se_realiser() {
        String input = "M - 1 - 0";
        Montagne montagne = Montagne.fromText(input);
        assertThat(montagne).extracting(Montagne::getPosition).isEqualTo(Position.of(1, 0));
    }

    @Test
    @DisplayName("Le formatage d'une montagne en text doit correctement se réaliser")
    void montagne_toText_doit_correctement_se_realiser() {
        String expected = "M - 2 - 2";
        Montagne montagne = fakeMontagne();
        assertThat(montagne.toText()).isEqualTo(expected);
    }
}