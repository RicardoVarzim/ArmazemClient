/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import comands.Command;
import gui.MainFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.RowFilter.Entry;
import javax.swing.table.DefaultTableModel;

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
                        ui.jTextArea.append("[Application > Me] : Utilizador "+(String)cmd.args.listArgs.get(0)+" registado! \n");
                     else{
                        ui.jTextArea.append("[Application > Me] : Utilizador "+(String)cmd.args.listArgs.get(0)+" inválido! \n");
                    }
                    break;
                }
                case "cliente_login":{
                    System.out.println((boolean)cmd.result);
                    if((boolean)cmd.result){
                        ui.jTextArea.append("[Application > Me] : Login "+(String)cmd.args.listArgs.get(0)+" efectuado! \n");
                        ui.LoginSucess();
                    }
                    else{
                        ui.jTextArea.append("[Application > Me] : Login "+(String)cmd.args.listArgs.get(0)+" inválido! \n");
                    }
                    break;
                }
                case "definir_tarefa":{
                    if((boolean)cmd.result){
                        ui.jTextArea.append("[Application > Me] : Tarefa "+(String)cmd.args.listArgs.get(0)+" registada \n");
                    }
                    else{
                        ui.jTextArea.append("[Application > Me] : Erro ao definir tarefa "+(String)cmd.args.listArgs.get(0)+"\n");
                    }
                    break;
                }
                case "iniciar_tarefa":{
                     if((boolean)cmd.result){
                        ui.jTextArea.append("[Application > Me] : Tarefa iníciada com o id.: "+(String)cmd.args.listArgs.get(0)+"\n");
                        //ui.jTabbedPane1.selectedIndex(2) actualizar a lista de tarefas!!!
                    }
                    else{
                        ui.jTextArea.append("[Application > Me] : Erro ao iníciar a tarefa! \n");
                    }
                    break;
                }
                case "concluir_tarefa":{
                    if((boolean)cmd.result){
                        ui.jTextArea.append("[Application > Me] : Tarefa "+(String)cmd.args.listArgs.get(0)+"\n");
                        //ui.jTabbedPane1.selectedIndex(2) actualizar a lista de tarefas a s!!!
                    }
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
                    ui.jTextArea.append("[Application > Me] : Listar Tarefas\n\tDetalhes:"+cmd.toString()+"\n");
                        
                    DefaultTableModel model = (DefaultTableModel) ui.jTableObjectos.getModel();
                    HashMap< String,Integer > temp = (HashMap< String,Integer >)cmd.result;
                    for(Map.Entry<String,Integer> item :temp.entrySet())
                        model.addRow(new Object[]{item.getKey(), item.getValue()});
                    break;
                }
                case "listar_tarefas":{
                    
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
