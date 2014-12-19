/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package armazemclient;

import comands.Command;


/**
 *
 * @author Ricardo
 */
public class ClientMain {
    
    public static void main(String[] args)
    {
        String[] args1 = new String[]{"Mohinder","123456"};
        Command cmd1 = new Command("registar_cliente","1",args1);
        
        try {
            Client cliente = new Client();
            Thread t = new Thread(cliente);
            t.start();
            cliente.send(cmd1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
