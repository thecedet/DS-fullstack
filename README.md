Concernant la possibilité de modifier une image d'illustration, avez-vous fait une route dédiée au fait de remplacer la photo ou bien considérez-vous qu'il faille simplement appeler la route de suppression puis la route d'ajout ? 

Réponse : Il faut faire une route uniquement pour ça, route pour changer le boolean et nettoyer minio. Toujours nettoyer derrière

Concernant le stockage des images, est-il préférable de mettre d'avoir une seule table contenant tous les IDs de toutes les images (et faire une jointure systématique donc) ou bien vaut-il mieux mettre l'ID de la photo d'illustration dans la table restaurant et ne pas mettre d'ID en DB pour l'image d'illustration d'un restaurant ?

Réponse : Mettre l'ID est suffisant, on ne va pas surcharger pour rien notre back

Lorsque vous retournez un restaurant, est-ce une bonne idée de retourner la photo systématiquement (via un lien bien sûr) ?

Réponse : Non c'est pas utile, il y a du cache côté client. On ne donne que les informations vraiment utile (c'est pour ça que j'ai un RestaurantSummaryDto et RestaurantDto)

Lorsque vous retournez un restaurant, est-ce une bonne idée de retourner l'évaluation finale systématiquement ?

Réponse : Même réponse que ci-dessus

Expliquez l'implémentation que vous avez fait des tags (quelles solutions avez-vous envisagées et pourquoi avoir retenu la vôtre en particulier)

J'ai utilisé un enum. Je ne voulais pas surcharger ma BDD (de requete et de text non dynamique)