# Application de recette de cocktail - ShakeItUp
## Projet de développment mobile
Réalisée par Marine Vovard et Lisa Veillat
(Avec designs des catégories et du logo par Emma Veillat)



Nous avons développé les fonctionnalités suivantes :
- Onglet de recherche : pour rechercher des cocktails par nom. On a fait le choix de dire que l'ensemble des cocktails était représenté par tous les cocktails qu'on pouvait obtenir requêtant toutes les catégories car l'API ne fournissait les cocktails que par lettre et le risque était d'obtenir des cocktails différents entre les catégories. Le filtrage des coctails se fait au niveau du code de l'application (et non de l'api). 
- Onglet de catégories : pour avoir la liste des catégories et choisir son cocktail en fonction. Cet onglet mène à la liste des cocktails par catégories ainsi que la recette du cocktail sélectionné.  Il est également possible d'ajouter les ingrédients nécessaires à la préparation de ce cocktail dans une liste de courses.
- Onglet d'ingrédients : qui focntionne de la même manière que celui pour les catégories.
- Onglet liste de courses : Qui permet d'avoir accès à la liste des ingrédients ajoutés préalablement, accompagnés de checkbox pour se repérer lors des courses ou de ce qu'on possède déjà, ainsi qu'un bouton pour vider cette liste. Cette liste est enregistrée dans les sharedPreferences.
- Onglet random : Il permet d'obtenir un cocktail aléatoire (et donc sa recette) lorsqu'on secoue le téléphone (d'où le nom de l'application)

Difficultés rencontrées :
- Répartir le code comme il faut entre les différents fichiers.
- Essayer de factoriser les fonctionnalités/parties du code (ça a été fait pour le Fetcher mais on aurait aussi aimé le faire pour les fragment/adapter/viewholder qui ont pour la plupart un fonctionnement similaire)
- Essayer de faire fonctionner la searchBar de Material Design 3 (on s'est tournée vers une solution plus simple au final avec la SearchView)
