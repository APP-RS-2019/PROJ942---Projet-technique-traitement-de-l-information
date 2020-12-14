# Application Android

## Introduction
Cette partie a pour objectif de faire une application permettant de prendre une photo et de l'envoyer au serveur pour une reconnaissance. 

Pour pouvoir réaliser l'application, nous avons développé 3 parties :
- "AndroidManifest" : nous donnons toutes les permissions pour que l'application fonctionnne : accès à la caméra, lecture du stockage externe et écriture sur le stockage externe.
- "activity_main" : nous représentons ce que nous voulons observer dans notre application. Il s'agit du contenu affiché sur la tablette.
- "MainActivity" : tous notre code est présent dans cette fenêtre

## AndroidManifest
Ces 3 lignes de codes nous permettent de donner les permissions à notre application : 
    <uses-permission android:name="android.permission.CAMERA"></uses-permission>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>

## activity_main
Sur notre application nous avons eu besoin :
- un textView pour afficher le nom de la personne que nous avons reconnu
- un bouton pour pouvoir prendre la photo
- un camera_preview pour voir le flux vidéo de la caméra
- une imageView pour afficher la dernière image prise par l'application et qui est envoyé au serveur

## MainActivity
Dans un premier temps, nous avons créé une classe preview. Cette classe va nous permettre de récupérer le flux vidéo pour pouvoir l'afficher dans l'application.
Nous lui donnons une méthode surfaceCreated qui va créer la surface, faire l'acquisition du flux vidéo et l'afficher.
Nous avons aussi créé une méthode surfaceChanged dans laquelle nous définissons les différents paramètres de notre surface.
Nous faisons aussi la méthode surfaceDestroyed qui va arrêter l'acquisition du flux.

Ainsi, dans le main, nous créons un objet de la classe preview et nous nous connectons à la caméra.
Nous avons ensuite fait le choix d'ajouter un listener au bouton. Celui va venir écouter quand l'utilisateur appuie sur le bouton. A l'appui du bouton nous prenom une photo en 
appelant la méthode takePicture.

La méthode takePicture va dés le départ appeler la méthode getOutputMediaFile qui va créer le lieu de stockage de notre photo et donner un nom à la photo. Ensuite, nous
sauvegardons la dernière image dans un bitmap. Nous pouvons ainsi retrouver notre photo dans la galerie.

Maintenant que nous arrivons à prendre la photo et à la sauvegarder dans le stockage de la tablette, il faut l'envoyer au serveur.






