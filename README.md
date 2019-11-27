# IUT_JeuDeLaVie_3D
Simultation of cells propagation in a 3d universe. The cells color is related by the number of cells nearby (alive).
At each generation the cell satate changes:
 - If a dead cell a 3 nearby cell that are alive she lives
 - If an alive cell doesn't have 2 or 3 nearby cells alive she dies

# Instructions

## Settings
You can change the setttings of the universe by changing theses variables in the `main()` 
```java
final int n = 70;
final double PROBA = 0.025 ;
```

## Build and launch the app
Build status:

![Actions Status](https://github.com/TurtleForGaming/IUT_JeuDeLaVie_3D/workflows/Javac%20build/badge.svg](https://github.com/TurtleForGaming/IUT_JeuDeLaVie_3D/actions)
```bash
stugler@912e007-12:~/TP/Algo/IUT_JeuDeLaVie_3D$ ./clean.sh 
stugler@912e007-12:~/TP/Algo/IUT_JeuDeLaVie_3D$ ./build.sh 
stugler@912e007-12:~/TP/Algo/IUT_JeuDeLaVie_3D$ ./launch.sh 
```

# Screenshots

![Jeu de la vie](https://i.ibb.co/5YjMy3J/image0.png)
![Jeu de la vie](https://i.ibb.co/svLwzXc/image1.png)
