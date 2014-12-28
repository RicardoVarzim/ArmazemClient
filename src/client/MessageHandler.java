/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import comands.Command;
import gui.MainFrame;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public class MessageHandler {
    
    private Command cmd;
    public MainFrame ui;
    
    public MessageHandler(MainFrame ui){
        this.ui = ui;
    }
    
    public MessageHandler(Command cmd){
        this.cmd = cmd;
    }
    
    public void ResolveMessage(Command c) {
        this.cmd = c;
        
        if(cmd!=null){
            switch(cmd.type){
                case "registar_cliente":{
                    if((boolean)cmd.result)
                        ui.jTextArea.append("[Application > Me] : Utilizador "+(String)cmd.args.listArgs.get(0)+" registado \n");
                    break;
                }
                case "cliente_login":{
                    System.out.println((boolean)cmd.result);
                    if((boolean)cmd.result){
                        ui.jTextArea.append("[Application > Me] : Login "+(String)cmd.args.listArgs.get(0)+" efectuado \n");
                        ui.LoginSucess();
                    }
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