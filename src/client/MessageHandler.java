/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import client_interface.Login;
import comands.Command;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author Ricardo
 */
public class MessageHandler {
    
    private Login ui;
    private Command cmd;
    
    public MessageHandler(Login f){
        ui = f;
    }
    
    public MessageHandler(Login f, Command cmd){
        this.ui = f;
        this.cmd = cmd;
    }
    
    public Command ResolveMessage(Command c) {
        this.cmd = c;
        
        if(cmd!=null){
            switch(cmd.type){
                case "registar_cliente":{
                    System.out.println((boolean)cmd.result);
                    return cmd;
                }
                case "cliente_login":{
                    System.out.println((boolean)cmd.result);
                    return cmd;
                }
                case "listar_clientes":{
                    return cmd;
                }
                case "abastecer":{
                    return cmd;
                }
                case "definir_tarefa":{
                    return cmd;
                }
                case "iniciar_tarefa":{
                    return cmd;
                }
                case "concluir_tarefa":{
                    return cmd;
                }
                case "pedido_notificacao":{
                    return cmd;
                }
                case "listar_notificacoes":{
                    return cmd;
                }
                case "listar_items":{
                    return cmd;
                }
                case "listar_tarefas":{
                    return cmd;
                }
                case "listar_tarefas_activas":{
                    return cmd;
                }
                case "listar_tarefas_concluidas":{
                    return cmd;
                }
                default:
                    return cmd;
            }
        }else{
            cmd.result ="Invalid Command!\n";
            return cmd;
        }
        
    }
}
