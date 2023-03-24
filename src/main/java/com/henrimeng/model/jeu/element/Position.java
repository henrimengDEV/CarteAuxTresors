package com.henrimeng.model.jeu.element;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.lang.String.format;

@Getter
@EqualsAndHashCode
public final class Position {

	private final int x;
	private final int y;

	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Position of(int x, int y) {
		if (isNegative(x) || isNegative(y)) {
			throw new PositionIncorrectException(format("Un Indice ne peut pas être nul [%d, %d]", x, y));
		}

		return new Position(x, y);
	}

	private static boolean isNegative(int source) {
		return source < 0;
	}
}
