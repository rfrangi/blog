# Gestionnaire de blog

## Introduction
Ce projet est une simple appli REST pour servir un blog. On utilise Spring Boot Spring DATA JPA principalement.
## Enoncé

Actuellement, l'application fourni uniquement le detail d'une publication de blog via une requête GET `/posts/{id}` ou `{id}` est son identifiant.

Le but est d'ajouter une gestion de commentaire sur un post en créant  2 nouveaux types de requêtes:

- `POST` url = `/posts/{id}/comments`:
  - Enregistrer un nouveau commentaire avec la date et l'heure actuelles pour un post ciblé avec `{id}`
  - Renvoie le code 201 si le commentaire est créé avec succès
  - Retourne une 404 Not Found si le post n'existe pas
    

- `GET` url = `/posts/{id}/comments`:
  - Retourne tous les commentaires triés par date de création sur l'ordre décroissant pour un post dont l'identifiant est `{id}`

Vous devez implémenter la class `CommentService`, Chaque méthode contient une javadoc sur le comportement attendu.

Certains test échouent actuellement et votre solution doit satisfaire l'ensemble des tests en respectant les meilleures pratiques.
N'oubliez pas que les tests fournis vérifient l'exactitude fonctionnelle, vous ne devez pas modifier les tests unitaire.

## Astuce
N'hésitez pas à créer de nouveaux fichiers et à modifier des fichiers existants. Vous pouvez ajouter de nouvelles dép si vous le souhaitez en modifiant simplement le pom.xml.

### Bonne chance!
