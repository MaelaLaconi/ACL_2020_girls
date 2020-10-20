# ACL_2020-girls
Groupe :
Linda Battikh
Nada Madad
Maela Laconi

Backlog :

Les fonctionnalités réalisées dans ce sprint0 sont :
Affichage dans le terminal Ecrire Command(L/R/U/D/S) et attendre que l'utilisateur rentre l'une de ces 5 lettres.
La lettre 'L' correspond à faire bouger le Pacman à gauche.
La lettre 'R' correspond à faire bouger le Pacman à droite.
La lettre 'U' correspond à faire bouger le Pacman en haut.
La lettre 'D' correspond à faire bouger le Pacman en bas.
La lettre 'S' correspond à ne pas faire bouger le Pacman.
On affiche ensuite la localisation du Pacman aprés chaque lettre tapée par l'utilisateur.
Si l'utilisateur entre une autre lettre ou caractère que ceux demandés, on change rien et on lui redemande à nouveau d'entrée l'une des 5 lettres.

Les décisions pour le sprint suivant:
On a ajouté les contraintes de deplacement comme par exemple x et y doivent rester plus grand que zero quand on deplace le Pacman à gauche et en haut respectivement.
Ainsi que pour la droite x doit etre inférieur à la largeur du PacmanPainter et pour le bas  y doit etre inferieur à la hauteur du PacmanPainter.
On a aussi reussi à le faire bouger à travers les commandes entrées dans le terminal pour le moment.
On prévoit donc de faire les collisions.