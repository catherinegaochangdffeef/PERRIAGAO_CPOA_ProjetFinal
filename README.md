# Projet-CPOA-Final

## Trello :https://trello.com/b/YIGfWYZk/travail


### Ce qui fonctionne:  
1.Saisir/modifier/supprimer/visualiser les données pour l'ensemble des produits vendus, des catégories, des clients et des commandes.  
2.Pour chaque élément du logiciel, un formulaire permettra d'ajouter un nouvel élément ou de modifier unélément existant.  
3.La fenêtre de gestion sera séparée en deux parties : une partie affiche les éléments existants dans une table.   
4. Après sélection dans cette table, on peut visualiser, modifier ou supprimer l'élément sélectionné.  
5.Un bouton d'ajout activé lorsqu'aucune sélection n'est en cours permet d'ajouter un nouvel élément.  
6.pas de suppression en cascade !  
7.Un bouton présent sur l'écran d'accueil ou un élément de menu devra permettre de passer de la solution base de données à la solution liste mémoire (ce qui permettra de faire les tests même en absence de connexion au serveur de base de données).  




### Ce qui fonctionne pas:
1.L'affichage des produits ne pourra pas être filtré ou trié par catégorie.  
2.Une recherche par nom de produit ne sera pas également proposée, ainsi qu'une recherche relative au tarif (tous les produits dont le tarif est inférieur à n euros).  
3.Sur la fenêtre des clients, l'affichage n'est pas trié par ordre alphabétique des noms des clients, ou par ville, nom ; il est impossible de faire une recherche par nom et nom, prénom.  
4.La fenêtre des commandes ne pourra pas afficher uniquement les commandes d'un client. Il sera impossible de
trier/filtrer par produit et client.  
5.Il y a des erreurs dans listmémoire donc il ne peut pas fonctionne très bien.   

### La répartition des tâches entre les différents membres du groupe:  
#### GAO Chang  
--TD5: Tous les interfaces, relier avec la base de donnée, les fonctions saisir/modifier/supprimer/visualiser   
--TD4: Constuire l'interface graphique, gérer les erreurs de saisie de l'utilisateur,
réaliser une insertion dans la base de données au clic sur le bouton de création,la classe controlleur  
--TD3:Test SQL  
--TD2: Créer les objets métiers(POJO) pour les tables à gérer, DAO niveau 2(MYSQL, ListeMémoire), DAOFactory, passer de mySQL à ListeMemoire,HashMap  
--TD1: Le menu pour tester les 4 méthodes  



#### Inès PERRIA  
--TD5: interfaces graphique du produit  
--TD4: Constuire l'interface graphique  
--TD3: Test ListeMémoire, Test SQL  
--TD2: POJO  
--TD1: Les 4 méthodes    

### Une  estimation du pourcentage de l'investissement de chacun dans le projet rendu:  
GAO Chang: 60%   
Inès PERRIA:40%
