package com.henrimeng.model.jeu.element;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrientationTest {

	@Test
	@DisplayName("suivant doit retourner l'orientation suivante dans le sens horaire")
	void suivant() {
		assertThat(Orientation.suivant(Orientation.N)).isEqualTo(Orientation.E);
		assertThat(Orientation.suivant(Orientation.E)).isEqualTo(Orientation.S);
		assertThat(Orientation.suivant(Orientation.S)).isEqualTo(Orientation.O);
		assertThat(Orientation.suivant(Orientation.O)).isEqualTo(Orientation.N);
	}

	@Test
	@DisplayName("suivant doit retourner l'orientation suivante dans le sens contre horaire")
	void precedent() {
		assertThat(Orientation.precedent(Orientation.N)).isEqualTo(Orientation.O);
		assertThat(Orientation.precedent(Orientation.O)).isEqualTo(Orientation.S);
		assertThat(Orientation.precedent(Orientation.S)).isEqualTo(Orientation.E);
		assertThat(Orientation.precedent(Orientation.E)).isEqualTo(Orientation.N);
	}
}