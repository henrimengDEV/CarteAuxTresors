package com.henrimeng.model.jeu.element;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Element {

	private Position position;

	public Element(Position position) {
		this.position = position;
	}
}
