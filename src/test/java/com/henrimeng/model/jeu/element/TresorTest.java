package com.henrimeng.model.jeu.element;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.henrimeng.model.MockData.fakeMontagne;
import static com.henrimeng.model.MockData.fakeTresor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class TresorTest {

    @Test
    @DisplayName("La création d'un trésor à partir d'un text doit correctement se réaliser")
    void tresor_fromText_doit_correctement_se_realiser() {
        String input = "T - 1 - 0 - 3";
        Tresor tresor = Tresor.fromText(input);
        assertThat(tresor).extracting(Tresor::getPosition).isEqualTo(Position.of(1, 0));
        assertThat(tresor).extracting(Tresor::getNombreRestant).isEqualTo(3);
    }

    @ParameterizedTest
    @DisplayName("La création d'un trésor à partir d'un text doit renvoyer une MontagneIncorrectException en cas d'un mauvais argument")
    @ValueSource(strings = {
            "- 1 - 0 - 3",
            "T - 1  0 - 3",
            "T - 1 - 3"
    })
    void tresor_fromText_doit_retourner_TresorIncorrectException_quand_mauvais_input(String line) {
        assertThatThrownBy(
                () -> Tresor.fromText(line)
        ).isInstanceOf(TresorIncorrectException.class);
    }

    @Test
    @DisplayName("Le formatage d'un trésor en text doit correctement se réaliser")
    void tresor_toText_doit_correctement_se_realiser() {
        String expected = "T - 1 - 1 - 1";
        Tresor tresor = fakeTresor();
        assertThat(tresor.toText()).isEqualTo(expected);
    }
}