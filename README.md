# Kata : Carte aux trésors
> Réalisé par : Henri MENG
 
> Voici une V1 sans CLI intéractif avec des fichiers en fixe:
> - `resources/input.txt`
> - `resources/output.txt`
 
## Getting started
Dans le `Main.java` veuillez changer les chemins manuellement

- `inputPath` correspond au chemin vers le fichier d'entrée
- `outputPath` vers le fichier de sortie


```
public class Main {

	public static void main(String[] args) {
		...
		String inputPath = "/Users/henrimeng/PERSO/CarteAuxTresors/src/main/resources/input.txt";
		String outputPath = "/Users/henrimeng/PERSO/CarteAuxTresors/src/main/resources/output.txt";
		...
	}
}
```


🚀 **vous pouvez à présent, lancer le `Main`**


## Le projet a été réalisé en s'inspirant du DDD

<hr>

- On y retrouve une partie model contenant toutes les classes riches, **càd la logique métier aussi appelé Domain**
  - Carte
  - Jeu (Aggregate Root)
  - Element
    - Aventurier
    - Montagne
    - Tresor
  - MaitreJeu (Domain Service)
  - Exception Custom
  - Value Object

<hr>

- un packet `use_case` est aussi présent, ça serait la représentation de nos cas d'utilisation ici : 
  - JouerCarteAuxTresor
  - on peut le voir couche étant la **couche Applicative** de notre application

<hr>

- Le Main est la partie incomplète censé représenter notre point d'entrée, **la couche d'exposition**


## Tests

> Seul la couverture de test nominale a été faite, **càd la partie model**


## Exemples 

- Input
```
C - 3 - 4
M - 1 - 0
M - 2 - 1
T - 0 - 3 - 2
T - 1 - 3 - 3
#T - 1 - 3 - 3
A - Lara - 1 - 1 - S - AADADAGGA
  ```

- Output
```
C - 3 - 4
M - 2 - 1
M - 1 - 0
T - 1 - 3 - 2
A - Lara - 0 - 3 - S - AADADAGGA
  ```

> Remarque, l'ordre de retour des montagnes sembles être incorrect