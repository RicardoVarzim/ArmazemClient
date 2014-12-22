/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client_interface.Login;
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
    private Login login;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    public boolean keepRunning;
    
    //TODO:
    public ClientDetails cliente;
    
    public Client() throws IOException{
        login = null;
        socket = new Socket(host,PORT);
        
        Out = new ObjectOutputStream(socket.getOutputStream());
        //Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        
    }
    public Client(Login l) throws IOException{
        login = l;
        socket = new Socket(host,PORT);
        
        Out = new ObjectOutputStream(socket.getOutputStream());
        //Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        
    }
    
    @Override
    public void run() {
        keepRunning = true;
        while(keepRunning){
            try{
                Command cmd = (Command) In.readObject();
                System.out.println("Incoming : "+cmd.toString());
                
                //Metodo MessageHandler(
                MessageHandler msgHandle = new MessageHandler(login);
                
                
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
