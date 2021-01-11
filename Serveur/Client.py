# -*- coding: utf-8 -*-
"""
Created on Fri oct 9 09:39:41 2020
@author: anthoicl
"""



import socket
import select
import sys
import matplotlib.image as mpimg
import time
import os

from threading import Thread
global test
test=True

class Msgrecu(Thread):
    
    def __init__(self):
        Thread.__init__(self)
    def run(self):
        global test

        while test:
            try:
                msg_recu = client.recv(1024)
                msg_recu=msg_recu.decode()
                print(msg_recu)

            except:
                pass

    

#        client.close()
#
#        self.terminate()
    
class Msgenvoyer(Thread):
    
    def __init__(self):
        Thread.__init__(self)
    
    def run(self):
#        msg=b""
#        msg=input("> ")
#        msg=msg.encode()
#        client.send(msg)
        global test
        while test:
            
            time.sleep(1)
            text=input("Go?")
            if text=="oui":
                #try:
                    cheminImage = "test.jpg"#Chemin vers l'image
                    fichierImage = open(cheminImage, "rb")
                     
                    #On récupère la taille du fichier image en octets que l'on convertit en chaine de caractères
                    tailleImage = str(os.path.getsize(cheminImage))
                    #On rajoute des 0 devant la taille jusqu'à que la chaine fasse 8 caractères
                    for i in range(8-len(tailleImage)):
                        tailleImage = "0"+ tailleImage
                     
                    #On a la taille de l'image, on l'envoie au client
                    client.send(tailleImage.encode())
                     
                    #On envoit le contenu du fichier
                    data = fichierImage.read()
                    #print("data",data)
                    client.send(data)
                #except :
                #    print("pas marché")
                #    test=False
            if text=="non":
                test=False
                
#        def fermer
#        try:
#            client.close()
#
#
#        except:
#            print("deja deco")
#            test=False

class MsgRE(Thread):
    def __init__(self):
        Thread.__init__(self)
    def run (self):
        global test
        thread_test1=Msgrecu()
        thread_test2=Msgenvoyer()
        thread_test1.start()
        thread_test2.start()
        while test:
            pass
        
        

    
    
    
hote='193.48.125.71'
port=1933
client=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    while test==True:
        client.connect((hote,port))
        print("connecte")
        time.sleep(1)
        client.send("Pepper".encode())
        thread_1=MsgRE()
    
    
        thread_1.start()
        while test:
            time.sleep(1)
            """
            text=input("Go?")
            if text=="oui":
                try:
                    cheminImage = "tampon.jpg" #Chemin vers l'image
                    fichierImage = open(cheminImage, "rb")
                    
                    #On récupère la taille du fichier image en octets que l'on convertit en chaine de caractères
                    tailleImage = str(os.path.getsize(cheminImage))
                    print(tailleImage)
                    #On rajoute des 0 devant la taille jusqu'à que la chaine fasse 8 caractères
                    for i in range(8-len(tailleImage)):
                        tailleImage = "0"+ tailleImage
                        print(tailleImage)
                        #On a la taille de l'image, on l'envoie au client
                        client.send(tailleImage.encode())
                        
                        #On envoit le contenu du fichier
                        print(fichierImage.read())
                        client.send(fichierImage.read())
                    print("terminé")
                except :
                    print("pas marché")
                            #test=False
            if text=="non":
                test=False
                """
        thread_1.join()
    if test==False:
        client.close()
        print("deco reussi")
except:
    print ("Error")


#
#msg_a_envoyer = b""
#while msg_a_envoyer != b"fin":
#    msg_a_envoyer = input("> ")
#    # Peut planter si vous tapez des caractères spéciaux
#    msg_a_envoyer = msg_a_envoyer.encode()
#    # On envoie le message
#    client.send(msg_a_envoyer)
#    msg_recu = client.recv(1024)
#    print(msg_recu.decode()) # Là encore, peut planter s'il y a des accents
#
#print("Fermeture de la connexion")
#client.close()
