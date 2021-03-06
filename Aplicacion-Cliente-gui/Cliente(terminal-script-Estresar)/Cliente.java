import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Thread{
    private final int portNumber;
    private final String hostName;
    private final String mensaje;
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    
    public Cliente(String ip, int puerto){
        hostName = ip;
        portNumber = puerto;
        mensaje = "";
    }

    public Cliente(String ip, int puerto, String mensaje){
        hostName = ip;
        portNumber = puerto;
	this.mensaje = mensaje;
    }    

    public void conectar(){
        try {
            socket = new Socket(hostName, portNumber);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            start();
            //enviarMensaje();
	    enviarMensaje(mensaje);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } 
    }
    
    @Override
    public void run(){
        recibirMensaje();
    }
    
    public void enviarMensaje(){
        String mensaje;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        try {
            while ((mensaje = stdIn.readLine()) != null) {
                salida.println(mensaje); 
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarMensaje(String mensaje){
	try{
	  while(true){
	  	salida.println(mensaje);
		Thread.sleep(1000);
	   }
	} catch(InterruptedException ie){
	}
    }

    public void recibirMensaje(){
        
        String userInput;
        try {
            while ((userInput = entrada.readLine()) != null) {
                System.out.println(userInput);
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
