package com.henrimeng.model.jeu;

import com.henrimeng.model.MockData;
import com.henrimeng.model.jeu.Carte;
import com.henrimeng.model.jeu.CarteIncorrectException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.henrimeng.model.MockData.fakeCarte;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarteTest {

	@ParameterizedTest
	@DisplayName("La création d'une carte doit retourner CarteIncorrectException.class s'il a des valeurs négatives")
	@CsvSource({"-1, 0", "-10, 0", "-20, 0", "0, -1", "0, -10", "0, -20"})
	void carte_of_doit_retourner_PositionIncorrectException_quand_valeur_negative(int colonne, int ligne) {
		assertThatThrownBy(
			() -> Carte.of(colonne, ligne)
		).isInstanceOf(CarteIncorrectException.class);
	}

	@ParameterizedTest
	@DisplayName("La création d'une carte doit correctement se réaliser")
	@CsvSource({"0, 0", "1, 1", "10, 10", "20, 20"})
	void carte_of_doit_correctement_se_realiser(int colonne, int ligne) {
		Carte carte = Carte.of(colonne, ligne);
		assertThat(carte.getColonne()).isEqualTo(colonne);
		assertThat(carte.getLigne()).isEqualTo(ligne);
	}

	@Test
	@DisplayName("La création d'une carte à partir d'un text doit correctement se réaliser")
	void carte_fromText_doit_correctement_se_realiser() {
		String input = "C - 3 - 4";
		Carte carte = Carte.fromText(input);
		assertThat(carte).extracting(Carte::getColonne).isEqualTo(3);
		assertThat(carte).extracting(Carte::getLigne).isEqualTo(4);
	}

	@Test
	@DisplayName("Le formatage d'une carte en text doit correctement se réaliser")
	void carte_toText_doit_correctement_se_realiser() {
		String expected = "C - 3 - 4";
		Carte carte = fakeCarte();
		assertThat(carte.toText()).isEqualTo(expected);
	}
}