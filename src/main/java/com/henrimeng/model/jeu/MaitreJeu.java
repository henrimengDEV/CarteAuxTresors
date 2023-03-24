package com.henrimeng.model.jeu;

import com.henrimeng.model.jeu.element.*;

import java.util.List;
import java.util.Map;

public final class MaitreJeu {

	public boolean autoriseMouvement(List<Element> cellule) {
		return cellule.stream().noneMatch(Montagne.class::isInstance);
	}

	public void appliqueMouvement(Aventurier aventurier, List<Element> cellule) {
		cellule.stream()
			.filter(Tresor.class::isInstance)
			.filter(element -> 0 < ((Tresor) element).getNombreRestant())
			.forEach(element -> {
				aventurier.ouvreTresor();
				((Tresor) element).estOuvert();
			});
	}
}
