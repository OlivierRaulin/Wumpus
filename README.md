Wumpus
======

Wumpus - projet de DUT

Projet réalisé lors de mon DUT Informatique, en seconde année.

Principe
======
Un monde est généré, composé de 16 cases (4*4)
Un Wumpus (monstre), des trous, et un trésor.

Le joueur apparaît en bas à gauche du monde (coordonnées 0;0)
Il peut tourner (gauche, droite) et il fait donc face à une nouvelle direction (Nord, Sud, Est, Ouest)

Il peut enfin avancer dans la case qui est devant lui.

Il a des sensations :
- Stench : le Wumpus est dans une case adjacente
- Glitter : l'Or se trouve dans cette case, il peut le ramasser
- Breeze : un trou se trouve dans une case adjacente


Le but ? Récupérer l'or, éventuellement tuer le Wumpus, et ressortir.

Le jeu est actuellement bugué :-(

Installation
======
1- Compiler les classes Java
 javac *.java

2- Lancer le serveur
 java Serveur

3- Lancer le client
 java Client

Débuguer :-)
