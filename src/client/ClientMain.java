/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import comands.Command;


/**
 *
 * @author Ricardo
 */
public class ClientMain {
    
    public static void main(String[] args)
    {
        Command cmd1 = new Command("registar_cliente","1",new Object[]{"Mohinder2","123456"});
        Command cmd2 = new Command("cliente_login","1",new Object[]{"Mohinder2","123456"});
        Command cmd3 = new Command("abastecer","1",new Object[]{"parafuso",10});
        Command cmd4 = new Command("listar_clientes","1", new Object[]{});
        
        try {
            Client cliente = new Client();
            Thread t = new Thread(cliente);
            t.start();
            cliente.send(cmd1);
            cliente.send(cmd2);
            cliente.send(cmd3);
            cliente.send(cmd4);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
