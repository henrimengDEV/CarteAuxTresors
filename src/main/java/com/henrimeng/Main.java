package com.henrimeng;

import com.henrimeng.model.jeu.Jeu;
import com.henrimeng.use_case.JouerCarteAuxTresors;

import java.io.*;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		JouerCarteAuxTresors jouerCarteAuxTresors = new JouerCarteAuxTresors();
		String inputPath = "/Users/henrimeng/PERSO/CarteAuxTresors/src/main/resources/input.txt";
		String outputPath = "/Users/henrimeng/PERSO/CarteAuxTresors/src/main/resources/output.txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
			List<String> lines = reader.lines().filter(line -> !line.startsWith("#")).toList();
			Jeu jeu = jouerCarteAuxTresors.commencer(lines);
			reader.close();
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
			writer.append(jeu.resultat());
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}