package serveur;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {
	Socket soc;
a
	private BufferedOutputStream bos;
	private BufferedInputStream bis;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	private byte[] b;
	public boolean open;
	private OutputStream outStreamToServer;
	
	private String name;
	private String ip;
	private int port;

	public ClientSocket(String ip, int port, String name) {
		this.name = name;
		this.ip = ip;
		this.port = port;
	}


	public void connect(){
		try {
			this.soc = new Socket(this.ip, this.port); 
			
			// init des buffers pour la comm serveur
			this.bis = new BufferedInputStream(soc.getInputStream());
			this.bos = new BufferedOutputStream(soc.getOutputStream());
			this.inFromServer = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
			this.outToServer = new DataOutputStream(soc.getOutputStream());
			this.b = b;

			byte[] b = this.name.getBytes();
			outToServer.write(b, 0, this.name.length()); // utiliser writeBytes
			outToServer.flush();

			this.open = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Erreur de connexion");
			// rajouter une popup d'erreur
		}
	} 
	
	public void disconnect() {
		try {
			soc.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		// this.sentence = inFromServer.readLine();
		System.err.println("vous avez bien été déconnecté");
		this.open = false;
	}
	


	public void send(String msg){
		System.out.println("Send String");
		
		try {
			b=msg.getBytes();
			outToServer.write(b,0,msg.length());
			//this.outStreamToServer.flush();
			outToServer.flush();
		} catch (IOException e) {
			System.err.println("echec d'envoie");
		}
	}
	
	public void send(byte[] msg){
		System.out.println("Send bytes");
		try {
			outToServer.write(msg,0,msg.length);
			//this.outStreamToServer.flush();
			outToServer.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String reciev(){

		try{
			String sentence = inFromServer.readLine();
			return sentence;
		}
		catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	// getters
	public boolean isOpen() {
		return open;
	}

	public String getIp() {
		return this.ip;
	}

	public int getPort() {
		return this.port;
	}

	// setter pour la comm serveur
	public void setIp(String ip) throws Exception {
		this.ip = ip;
		if (this.open) {
			this.disconnect();
		}
	}

	public void setPort(int port) throws Exception {
		this.port = port;
		if(this.open) {
			this.disconnect();
		}

	}
	
	public void close() throws IOException{
		this.soc.close();
	}
}
