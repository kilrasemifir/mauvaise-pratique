# Projet Compétences

## Description

Ce projet a pour but de gérer les compétences d'une équipe.

## Objectifs
Pouvoir voir les compétences d'une équipe, les ajouter, les modifier et les supprimer.
Pouvoir suivre l'évolution des compétences d'une équipe.
Proposer de nouvelles compétences à apprendre.

## Besoins fonctionnels

1. Une équipe est composée de plusieurs personnes.
2. Une compétence est decrite par un nom, une description.
3. Chaque personne posséde un certain niveau de compétence pour chaque compétence.
4. Un niveau de compétence est décrit par une valeur allant de 0 à 10:
    * 0: Aucune connaissance
    * 1: Connait le nom de la compétence
    * 2: Connait le nom de la compétence et sa définition
    * 3: Connait le nom de la compétence, sa définition et ce qu'elle fait
    * 4: Connait le nom de la compétence, sa définition, ce qu'elle fait et comment elle fonctionne
    * 5: Sait utiliser de manière basique la compétence
    * 6: Sait utiliser de manière intermédiaire la compétence
    * 7: Sait utiliser de manière avancée la compétence
    * 8: Sait utiliser de manière experte la compétence
    * 9: Maitrise la compétence
    * 10: Sait enseigner la compétence a la personne qui la créé.
5. Les compétences possèdent des prérequis.
    1. Pour avoir un niveau de compétence, il faut avoir un certain niveau de compétence dans une autre compétence.
        * Exemple: Pour Avoir un niveau de compétence de 5 en Angular, il faut avoir un niveau de compétence de 6 en Typescript.
        * Exemple: Pour Avoir un niveau de compétence de 5 en Angular, il faut avoir un niveau de compétence de 5 en HTML.
6. Les personnes peuvent voir les compétences dont ils ont les prérequis.
7. Les personnes peuvent voir l'ensemble des compétences avec leurs prérequis.
8. Les personnes peuvent voir les personnes qui ont un niveau de compétence supérieur à 5 dans une compétence.
9. Les Managers peuvent ajouter des compétences.
10. Les Managers peuvent ajouter des personnes.
11. Les Managers peuvent ajouter le prérequis "Validation par test" à une compétence.
    1. Lors ce qu'une personne veut avoir un niveau de compétence supérieur à 5 dans une compétence avec un prérequis "Validation par test", il faut qu'elle passe un test.
    2. Le manager peut donné un lien url vers un test.
    3. Il valide manuellement le test et augmente le niveau de compétence de la personne.
12. Les Managers peuvent ajouter le prérequis "Validation par pair" à une compétence.
    1. Lors ce qu'une personne veut avoir un niveau supperieur à 5 dans une compétence avec un prérequis "Validation par pair", il faut qu'une autre personne ayant un meilleur niveau de compétence que la personne valide la compétence.
13. S'il y a un prérequis sur une compétence qui implique une autre personne (Managers ou non), quand la compétence est validée, la personne qui a validé la compétence est afficher sur la compétence du demandeur.
14. Les Managers peuvent ajouter le prérequis "Validation par un manager" à une compétence.
    1. Lors ce qu'une personne veut avoir un niveau supperieur à 5 dans une compétence avec un prérequis "Validation par un manager", il faut qu'un manager valide la compétence.
15. Les personnes peuvent voir les validations de compétences qu'elles peuvent valider.
16. Les managers peuvent créer un Role.
    1. Un role est décrit par un nom et une description.
    2. Un role posséde des prérequis.
        1. Il peut demandé d'avoir un certain niveau de compétence dans une ou plusieurs compétences.
        2. Il peut demandé d'avoir un certain role.
        3. Il peut demandé d'avoir un certain nombre de points de compétences.
17. Les personnes posséde un niveau global de compétence.
    1. Le niveau global de compétence est la somme des niveaux de compétences de chaque compétence.
18. Les personnes peuvent voir les personnes qui ont un niveau global de compétence le plus haut dans l'équipe.
19. Les managers peuvent suivrent l'évolution des compétences des personnes dans l'équipe.
    1. En donnant deux dates, il peut voir:
        1. Les nouvelles compétences acquises (supérieur à 5) par les personnes.
        2. Les personnes ayant gagné le plus de niveau de compétence.
        3. pour chaque compétence, les personnes ayant gagné le plus de niveau de compétence.
    2. La répartition des niveaux de compétences par compétence.
    3. La répartition des niveaux de compétences par rôle.
    4. La répartition des niveaux de compétences par personne.
20. Les personnes peuvent demander de gagner un niveau de compétence.
