package serveur;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import javax.imageio.ImageIO;

public class main {

	public static void main(String[] args) throws IOException {
		try{
			ClientSocket socket = new ClientSocket("193.48.125.71",1933,"ClientJava");
			socket.connect();
			Thread.sleep(1000);
			System.out.println("lg");

			String cheminImage = "E:/Cours/S9/PROJ942/tampon.jpg"; //Chemin vers l'image
			File file=new File(cheminImage);
			byte[] bytes = new byte[0];
			
			DataInputStream reader = new DataInputStream(new FileInputStream(cheminImage));
			int nBytesToRead = reader.available();
			
			if (nBytesToRead>0){
				bytes=new byte[nBytesToRead];
				reader.read(bytes);
			}
			System.out.println("image lue");

			//On récupère la taille du fichier image en octets que l'on convertit en chaine de caractères
			String tailleImage = Long.toString(file.length());
			System.out.println("taille image = "+ tailleImage);

			//On rajoute des 0 devant la taille jusqu'à que la chaine fasse 8 caractères
			for (int i=0;i<=((8-tailleImage.length())+1);i++){
				tailleImage = "0"+ tailleImage;
			}
			System.out.println("0 rajoutés :\n"+tailleImage);

			//On a la taille de l'image, on l'envoie au client
			socket.send(tailleImage);
			System.out.println("taille envoyée : " + tailleImage);

			//On envoit le contenu du fichier
			//Pb ici, il faut envoyer l'image en String (surrement en 'Base64')
			String str=new String(bytes);
			System.out.println(str.getBytes("UTF-8"));
//			String encodedString = Base64.getEncoder().encodeToString(bytes);	
//			System.out.println(encodedString);
			socket.send(str);
			/*System.out.println(new String(bytes,"UTF-8"));
			byte[] encodedString = Base64.getEncoder().encode(bytes.toString().getBytes("UTF8"));	
			System.out.println(new String(encodedString,"UTF-8"));
			socket.send(new String(bytes,"UTF-8"));*/
			
			socket.close();
			
			System.out.println("image envoyée");
		}

		catch(Exception e){
			e.printStackTrace();
		}

	}

}
