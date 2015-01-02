/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import comands.Command;
import gui.MainFrame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class Client implements Runnable {
    
    public String host = "localhost";
    public int port = 1200;
    public String mac;
    private final Socket socket;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    
    public MainFrame ui;
    public boolean keepRunning;
    
    //TODO:
    public ClientDetails cliente;
    
    public Client()throws IOException{
        socket = new Socket(host,port);
        mac = getMacAddress();
        
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
    }
    
    public Client(MainFrame ui) throws IOException{
        this.ui = ui;
        socket = new Socket(host,port);
        mac = getMacAddress();
        
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
    }
    
    public Client(String host, int port, MainFrame ui) throws IOException{
        this.host = host;
        this.port = port;
        this.ui = ui;
        mac = getMacAddress();
        
        socket = new Socket(host,port);
        
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        
    }
    
    @Override
    public void run() {
        keepRunning = true;
        Command cmd;
        MessageHandler messageHandle = new MessageHandler(ui);
        
        while(keepRunning){
            try{
                cmd = (Command) In.readObject();
                messageHandle.ResolveMessage(cmd);
                
            } catch (Exception ex) {
                keepRunning = false;
                System.out.println("Connection Failure");
            } 
        }
    }
    
    public void send(Command cmd){
        try {
            if(cmd.type == "close")
                this.keepRunning = false;
            else{
                Out.writeObject(cmd);
                Out.flush();
                //System.out.println("Outgoing : "+cmd.toString());
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
    public String getMacAddress() throws UnknownHostException{
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            
            byte[] mac = network.getHardwareAddress();
            
            StringBuilder sb = new StringBuilder();
            for(int i= 0;i<mac.length;i++){
                sb.append(String.format("%02X%s", mac[i],(i<mac.length)?"-":""));
            }
            return sb.toString();
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
}
