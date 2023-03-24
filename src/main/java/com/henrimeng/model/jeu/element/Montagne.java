package com.henrimeng.model.jeu.element;

import lombok.Getter;

import static java.lang.String.format;

@Getter
public final class Montagne extends Element {

	private static final int NB_ATTRIBUTES = 3;

	public Montagne(int x, int y) {
        super(Position.of(x, y));
    }

	public static Montagne fromText(String line) {
		String[] attributes = line.split(" - ");

		if (attributes.length != NB_ATTRIBUTES) {
			throw new MontagneIncorrectException(
				format("[%s] semble être incorrect pour créer une montagne", line)
			);
		}

		return new Montagne(Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]));
	}

	public String toText() {
		return String.join(
				" - ",
				"M",
				String.valueOf(getPosition().getX()),
				String.valueOf(getPosition().getY())
		);
	}
}
