package com.henrimeng.use_case;

import com.henrimeng.model.jeu.Carte;
import com.henrimeng.model.jeu.Jeu;
import com.henrimeng.model.jeu.element.Aventurier;
import com.henrimeng.model.jeu.element.Montagne;
import com.henrimeng.model.jeu.element.Tresor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public final class JouerCarteAuxTresors {

	public Jeu commencer(List<String> lines) {
		Carte carte = null;
		List<Montagne> montagnes = new ArrayList<>();
		List<Tresor> tresors = new ArrayList<>();
		Aventurier aventurier = null;

		for (String line : lines) {
			if (line.startsWith("C")) carte = Carte.fromText(line);
			if (line.startsWith("M")) montagnes.add(Montagne.fromText(line));
			if (line.startsWith("T")) tresors.add(Tresor.fromText(line));
			if (line.startsWith("A")) aventurier = Aventurier.fromText(line);
		}

		if (isNull(carte) || isNull(aventurier)) {
			throw new RuntimeException("Fichier incomplet pour créer jouer au jeu");
		}

		Jeu jeu = new Jeu(
			carte,
			montagnes,
			tresors,
			aventurier
		);

		jeu.lancer();

		return jeu;
	}
}
