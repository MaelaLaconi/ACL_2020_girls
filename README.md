# ACL girls PacWomen
Groupe :
Linda Battikh
Nada Madad
Maela Laconi

## Necessaire
Afin de pouvoir compliler le jeu, il est nécessaire que vous ayez Maven installer sur votre machine.
### Installer Maven sur Linux :
>sudo apt update

>sudo apt install maven


### Installer Maven sur Windows :
* Aller sur le site officiel de Maven et télécharger le fichier zip Maven.

* Décompreser le fichier zip.

* Appuyez sur la touche Windows, tapez «adva» et cliquez sur «Afficher les paramètres système avancés». 

* Dans la boîte de dialogue Propriétés système, sélectionnez l’onglet "Avancé" et cliquez sur le bouton "Variables d’environnement".

* Dans la boîte de dialogue "Variables d’environnement", Variables système , Cliquez sur le bouton "Nouveau..." et ajoutez une variable MAVEN_HOME et pointez la sur le fichier que vous avez décompressé précedemment.

* Dans les variables système, rechercher PATH, cliquez sur le bouton "Editer...". Dans la boîte de dialogue "Modifier la varibale d'environnement", cliquez sur le bouton "Nouveau" et ajoutez MAVEN_HOME (crée précedement).


### Installer Maven sur Mac :

* Aller sur le site officiel de Maven et télécharger le fichier Maven.

* Décompreser le fichier.

* Fixer les permissions :
    >chown -R root:wheel Downloads/apache-maven*

* Changer de place le contenu de Maven :
    >mv Downloads/apache-maven* /opt/apache-maven

* Archiver la session :
    >exit

* Ajouter Maven au PATH :
    >nano $HOME/.profile
    
    >export PATH=$PATH:/opt/apache-maven/bin

* Pour charger le nouveau setup :
    >bash

### Pour verifier que vous avez bien maven d'installé :
>mvn -version

## Compilation :
Ce placer au dessus de src.
>mvn package

## Execution :
>cd target

>java -jar ACL_2020_girls-1.0-SNAPSHOT.jar 

## But et règles du jeu :


<img src="https://github.com/MaelaLaconi/ACL_2020_girls/blob/main/resources/images/readMe/welcome.png" width="500">


Au lancement du jeu, on arrive sur la plage d'acceuil. Sur cette page, nous pouvons soit commencer la partie,
quitter ou bien aller dans le menu option pour choisir le personnage que l'on veut incarner (soit Belle ou Bulle). Dans cette même fenêtre, on peut choisir le 
niveau de difficulté que l'on veut pour la partie.


<img src="https://github.com/MaelaLaconi/ACL_2020_girls/blob/main/resources/images/readMe/mainLaby.jpeg" width="500">


Lorsque que l'on clique sur "Start Game", la partie se lance. Le héro a un chrono de 60 secondes et de 3 vies, une fois ce temps épuisé ou bien l'intégralité de ses vies, il meurt (fin de la partie).
Le but du jeu est de se déplacer dans le labyrinthe en évitant les différents monstres (les fantômes qui peuvent passer au travers des mûrs, les monstres normaux qui sont stoppés par les mûrs et le gardien du trésor qui
se dirige vers le héro pour le tuer). Lorsqu'il y a une collision avec un monstre, le héro perd de sa jauge de vie. Une fois sa jauge de vie vide, les vies du héro ne sont plus protégées.
La prochaine fois que le héro touche un montre, il perdra donc une vie (il en a trois au départ). Une fois ses trois vies épuisées, le héro perd. 
Cependant, si le héro collecte une potion, sa jauge de vie augmente. S'il va sur un magic step (box avec un ? sur la map), il gagne un bonus. Il peut gagner soit du temps supplémentaire ou une vie supplémentaire. Une suspension du monde (pendant un
certain temps, tous les monstres du labyrinthe sont figés) peut aussi être gagnée ou bien encore un mode saiyan qui rend le héro invincible durant tout ce niveau. Pour passer au niveau suivant, le héro doit d'abord récupérer
le trésor pour que la porte de sortie s'ouvre. Une fois la porte ouverte, il peut l'emprunter pour aller dans le niveau suivant. Différents diamants sont disposés dans le labyrinthe.
Chacun rapport des points qui s'ajoute au score du héro. Deux portails de téléportations sont dans le labyrinthe, lorsque le héro va dessus, il apparaît au niveau du deuxième portail.
Mais attention ! Si le héro se déplace sur un trap step (box avec une tête de mort dessus), il gagne un malus. Il peut perdre soit une vie, soit du temps ou bien des points.

## Construction d'un labyrinthe

Les labythintes sont générés à partir d'un fichier .txt. Ce fichier est constitué de plusieurs lettres, chacune d'elle représentant un objet différent :

w : mûr (wall)
n : sol basique (normal stap)
m : case bonus (magic step)
t : trésor
s : case malus (trap step)
c : porte
d : potion
p : portail de téléportation
b : diamant bleu
r : diamant rouge
