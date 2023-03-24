package com.henrimeng.model.jeu.element;

public enum Orientation {
	N, E, S, O;

	public static Orientation suivant(Orientation orientation) {
		return values()[(orientation.ordinal() + 1) % values().length];
	}

	public static Orientation precedent(Orientation orientation) {
		return values()[(orientation.ordinal() - 1 + values().length) % values().length];
	}
}
