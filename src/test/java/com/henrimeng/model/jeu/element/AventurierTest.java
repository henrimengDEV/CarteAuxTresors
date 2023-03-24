package com.henrimeng.model.jeu.element;

import com.henrimeng.model.MockData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.henrimeng.model.MockData.fakeAventurier;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AventurierTest {

    @ParameterizedTest
    @DisplayName("La création d'un Aventurier doit retourner une Exception quand les déplacements sont incorrects")
    @ValueSource(strings = {"XXXXX", "ADGX", "xAAAAAAAA", "12321", ""})
    void new_aventurier_doit_retourner_IllegalArgumentException_quand_deplacements_incorrects(String deplacements) {
        assertThatThrownBy(
            () -> Aventurier.of("Indiana", 1, 1, "S", deplacements)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("La création d'un Aventurier doit retourner une Exception quand l'orientation est incorrect")
    @ValueSource(strings = {"North", "A", "1"})
    void new_aventurier_doit_retourner_IllegalArgumentException_quand_orientation_incorrect(String orientation) {
        assertThatThrownBy(
            () -> Aventurier.of("Indiana", 1, 1, orientation, "AADADA")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("Lors de la création d'un Aventurier déplacements doit être correctement formatté")
    @CsvSource({"AADADA, 6", "GGGGAADADA, 10", " AADADA , 6", "A A D A DA G, 7"})
    void new_aventurier_doit_correctement_formatte_deplacements(String deplacements, int expectedSize) {
        Aventurier aventurier = Aventurier.of("Indiana", 1, 1, "S", deplacements);
        assertThat(aventurier.getDeplacements().size()).isEqualTo(expectedSize);
    }

    @ParameterizedTest
    @DisplayName("La création d'un Aventurier doit correctement se réaliser")
    @CsvSource({
        "Indiana, 1, 1, N, AADADA",
        "Quentin, 2, 1, E, AADADA",
        "Vincent, 5, 3, S, AADADA",
        "Dorian, 0, 0, O, AADADA",
    })
    void new_aventurier_doit_correctement_se_realiser(
        String nom,
        int x,
        int y,
        String orientation,
        String deplacements
    ) {
        Aventurier aventurier = Aventurier.of(nom, x, y, orientation, deplacements);
        assertThat(aventurier.getNom()).isEqualTo(nom);
        assertThat(aventurier.getNombreTresorRamasses()).isZero();
        assertThat(aventurier.getOrientation()).isEqualTo(Orientation.valueOf(orientation));
    }

    @Test
    void ouvreTresor() {
        Aventurier aventurier = MockData.fakeAventurier();
        aventurier.ouvreTresor();
        assertThat(aventurier.getNombreTresorRamasses()).isEqualTo(1);
    }

    @Test
    @DisplayName("L'aventurier se déplace plusieurs fois et doit arriver sur [0,1] depuis [1,1]")
    void seDeplace_doit_arriver_a_la_bonne_position() {
        Aventurier aventurier = MockData.fakeAventurier();
        aventurier.seDeplace(Deplacement.A);
        assertThat(aventurier.getPosition()).isEqualTo(Position.of(1, 0));
        aventurier.seDeplace(Deplacement.D);
        assertThat(aventurier.getPosition()).isEqualTo(Position.of(1, 0));
        aventurier.seDeplace(Deplacement.D);
        aventurier.seDeplace(Deplacement.A);
        assertThat(aventurier.getPosition()).isEqualTo(Position.of(1, 1));
        aventurier.seDeplace(Deplacement.D);
        aventurier.seDeplace(Deplacement.A);
        assertThat(aventurier.getPosition()).isEqualTo(Position.of(0, 1));
    }

    @Test
    @DisplayName("La prochaine position de l'aventurier doit être bien anticipé")
    void prochainePosition_doit_correctement_marcher() {
        Aventurier aventurier = MockData.fakeAventurier();
        assertThat(aventurier.prochainePosition(Deplacement.A)).isEqualTo(Position.of(1, 0));
        assertThat(aventurier.prochainePosition(Deplacement.D)).isEqualTo(Position.of(1, 1));
        assertThat(aventurier.prochainePosition(Deplacement.G)).isEqualTo(Position.of(1, 1));
    }

    @Test
    @DisplayName("La création d'un aventurier à partir d'un text doit correctement se réaliser")
    void aventurier_fromText_doit_correctement_se_realiser() {
        String input = "A - Indiana - 1 - 1 - N - AADADA";
        Aventurier aventurier = Aventurier.fromText(input);
        assertThat(aventurier.getNom()).isEqualTo("Indiana");
        assertThat(aventurier.getNombreTresorRamasses()).isZero();
        assertThat(aventurier.getOrientation()).isEqualTo(Orientation.N);
    }

    @ParameterizedTest
    @DisplayName("La création d'un aventurier à partir d'un text doit renvoyer une MontagneIncorrectException en cas d'un mauvais argument")
    @ValueSource(strings = {
            "- Indiana - 1 - 1 - N - AADADA",
            "A - 1 - 1 - N - AADADA",
            "A - Indiana  1 - 1 - N - AADADA"
    })
    void aventurier_fromText_doit_retourner_AventurierIncorrectException_quand_mauvais_input(String line) {
        assertThatThrownBy(
                () -> Aventurier.fromText(line)
        ).isInstanceOf(AventurierIncorrectException.class);
    }

    @Test
    @DisplayName("Le formatage d'un aventurier en text doit correctement se réaliser")
    void aventurier_toText_doit_correctement_se_realiser() {
        String expected = "A - Indiana - 1 - 1 - N - AADADA";
        Aventurier aventurier = fakeAventurier();
        assertThat(aventurier.toText()).isEqualTo(expected);
    }
}