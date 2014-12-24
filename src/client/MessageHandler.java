/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import gui.Login;
import comands.Command;
import java.util.ArrayList;

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
    
    public void ResolveMessage(Command c) {
        this.cmd = c;
        
        if(cmd!=null){
            switch(cmd.type){
                case "registar_cliente":{
                    System.out.println((boolean)cmd.result);
                    break;
                }
                case "cliente_login":{
                    System.out.println((boolean)cmd.result);
                    if((boolean)cmd.result)
                        ui.switchToMainWindow();
                    break;
                }
                case "listar_clientes":{
                    System.out.println((String)cmd.result);
                    break;
                }
                case "definir_tarefa":{
                    System.out.println((boolean)cmd.result);
                    break;
                }
                case "iniciar_tarefa":{
                    System.out.println((long)cmd.result);
                    break;
                }
                case "concluir_tarefa":{
                    System.out.println((String)cmd.result);
                    break;
                }
                case "pedido_notificacao":{
                    System.out.println((boolean)cmd.result);
                    break;
                }
                case "listar_notificacoes":{
                    System.out.println((ArrayList<String>)cmd.result);
                    break;
                }
                case "listar_items":{
                    System.out.println((String)cmd.result);
                    break;
                }
                case "listar_tarefas":{
                    System.out.println((String)cmd.result);
                    break;
                }
                case "listar_tarefas_activas":{
                    System.out.println((String)cmd.result);
                    break;
                }
                case "listar_tarefas_concluidas":{
                    System.out.println((String)cmd.result);
                    break;
                }
                default:
                    System.out.println((String)cmd.result);
            }
        }else{
            System.out.println("Invalid Command!\n");
        }
        
    }
}
