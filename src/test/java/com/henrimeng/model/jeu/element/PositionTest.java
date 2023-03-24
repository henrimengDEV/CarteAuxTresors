package com.henrimeng.model.jeu.element;

import com.henrimeng.model.jeu.element.Position;
import com.henrimeng.model.jeu.element.PositionIncorrectException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

	@ParameterizedTest
	@DisplayName("La création d'un position doit retourner PositionIncorrectException.class s'il est négatif")
	@CsvSource({"-1, 0", "-10, 0", "-20, 0", "0, -1", "0, -10", "0, -20"})
	void position_of_doit_retourner_PositionIncorrectException_quand_valeur_negative(int x, int y) {
		assertThatThrownBy(
			() -> Position.of(x, y)
		).isInstanceOf(PositionIncorrectException.class);
	}

	@ParameterizedTest
	@DisplayName("La création d'un position doit correctement se réaliser")
	@CsvSource({"0, 0", "1, 1", "10, 10", "20, 20"})
	void position_of_doit_correctement_se_realiser(int x, int y) {
		Position position = Position.of(x, y);
		assertThat(position.getX()).isEqualTo(x);
		assertThat(position.getY()).isEqualTo(y);
	}
}