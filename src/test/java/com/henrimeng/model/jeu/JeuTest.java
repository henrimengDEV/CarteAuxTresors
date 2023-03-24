package com.henrimeng.model.jeu;

import com.henrimeng.model.jeu.element.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.henrimeng.model.MockData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JeuTest {

	private Jeu jeu;

	@BeforeEach
	void setUp() {
		jeu = fakeJeu();
	}

	@Test
	@DisplayName("Lors de la création d'un Jeu les cellules doient bien être créée")
	void constructeur_doit_correctement_creer_cellules() {
		assertThat(jeu.getCellules()).hasSize(12);
		assertThat(jeu.getCellules().get(Position.of(1, 0))).hasAtLeastOneElementOfType(Montagne.class);
		assertThat(jeu.getCellules().get(Position.of(2, 1))).hasAtLeastOneElementOfType(Montagne.class);
		assertThat(jeu.getCellules().get(Position.of(0, 3))).hasAtLeastOneElementOfType(Tresor.class);
		assertThat(jeu.getCellules().get(Position.of(1, 3))).hasAtLeastOneElementOfType(Tresor.class);
		assertThat(jeu.getCellules().get(Position.of(0, 0))).isEmpty();
	}

	@Test
	@DisplayName("Le jeu doit répondre exactement au comportement désiré")
	void lancer_doit_correctement_reagir() {
		Tresor tresor = (Tresor) jeu.getCellules().get(Position.of(0, 3)).get(0);
		Tresor tresor2 = (Tresor) jeu.getCellules().get(Position.of(1, 3)).get(0);

		jeu.lancer();

		assertThat(jeu.getCellules()).hasSize(12);
		assertThat(tresor).extracting(Tresor::getNombreRestant).isEqualTo(0);
		assertThat(tresor2).extracting(Tresor::getNombreRestant).isEqualTo(2);
		assertThat(jeu.getAventurier()).extracting(Element::getPosition).isEqualTo(Position.of(0,3));
		assertThat(jeu.getAventurier()).extracting(Aventurier::getNombreTresorRamasses).isEqualTo(3);
	}

	@Test
	@DisplayName("Le jeu doit être retranscrit correctement en text")
	void resultat_doit_correctement_formatter() {
		assertThat(jeu.resultat()).isEqualTo(
				"C - 3 - 4\n" +
				"M - 2 - 1\n" +
				"M - 1 - 0\n" +
				"T - 1 - 3 - 3\n" +
				"T - 0 - 3 - 2\n" +
				"A - Lara - 1 - 1 - S - AADADAGGA"
		);
	}
}