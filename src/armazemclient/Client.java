/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package armazemclient;

import comands.Command;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Ricardo
 */
public class Client implements Runnable {
    
    private static final String host = "localhost";
    private static final int PORT = 1200;
    private final Socket socket;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    
    private ClientDetails cliente;
    
    public Client() throws IOException{
        
        socket = new Socket(host,PORT);
        
        Out = new ObjectOutputStream(socket.getOutputStream());
        //Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        
    }
    
    @Override
    public void run() {
        boolean keepRunning = true;
        while(keepRunning){
            try{
                Command cmd = (Command) In.readObject();
                System.out.println("Incoming : "+cmd.toString());
                
                //Metodo Tratar Command
                
                
            } catch (Exception ex) {
                keepRunning = false;
                System.out.println("Connection Failure");
            } 
        }
    }
    
    public void send(Command cmd){
        try {
            Out.writeObject(cmd);
            Out.flush();
            System.out.println("Outgoing : "+cmd.toString());
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
}
