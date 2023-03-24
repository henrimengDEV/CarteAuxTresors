package com.henrimeng.model.jeu.element;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.lang.String.format;

@Getter
@EqualsAndHashCode
public final class Tresor extends Element {

	private static final int NB_ATTRIBUTES = 4;
	private int nombreRestant;

	private Tresor(int x, int y, int nombreRestant) {
		super(Position.of(x, y));
		this.nombreRestant = nombreRestant;
	}

	public static Tresor of(int x, int y, int nombreRestant) {
		return new Tresor(x, y, nombreRestant);
	}

	public static Tresor fromText(String line) {
		String[] attributes = line.split(" - ");

		if (attributes.length != NB_ATTRIBUTES) {
			throw new TresorIncorrectException(
				format("[%s] semble être incorrect pour créer un trésor", line)
			);
		}

		return of(
			Integer.parseInt(attributes[1]),
			Integer.parseInt(attributes[2]),
			Integer.parseInt(attributes[3])
		);

	}

	public void estOuvert() {
		nombreRestant--;
	}

	public String toText() {
		return String.join(
				" - ",
				"T",
				String.valueOf(getPosition().getX()),
				String.valueOf(getPosition().getY()),
				String.valueOf(nombreRestant)
		);
	}
}
