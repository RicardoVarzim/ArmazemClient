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
import java.util.TreeMap;
import javax.swing.JOptionPane;
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
                    if((long)cmd.result != -1){
                        ui.jTextArea.append("[Application > Me] : Tarefa iníciada com sucesso.\n");
                    }
                    else{
                        ui.jTextArea.append("[Application > Me] : Erro ao iníciar a tarefa! \n");
                    }
                    break;
                }
                case "concluir_tarefa":{
                    if((String)cmd.result != null){
                        ui.jTextArea.append("[Application > Me] : "+(String)cmd.result+"\n");
                        //ui.jTabbedPane1.selectedIndex(2) actualizar a lista de tarefas a s!!!
                    }else{
                        ui.jTextArea.append("[Application > Me] : Erro ao concluir tarefa\n");
                        
                    }
                    break;
                }
                case "pedido_notificacao":{
                    if((boolean)cmd.result){
                        ui.jTextArea.append("[Application > Me] : Tarefa "+(String)cmd.args.listArgs.get(0)+" adicionada às notificações! \n");
                        //ui.jTabbedPane1.selectedIndex(2) actualizar a lista de tarefas a s!!!
                    }
                    else{
                        ui.jTextArea.append("[Application > Me] : Erro ao adicionar tarefa às notificações! Verifique se a mesma já foi concluída.\n");
                    }
                    break;
                }
                case "listar_notificacoes":{
                    System.out.println((ArrayList<String>)cmd.result);
                    break;
                }
                case "listar_items":{
                    ui.jTextArea.append("[Application > Me] : Listar Objectos\n\tDetalhes:"+cmd.toString()+"\n");
                    if(ui.jTabbedPane1.getSelectedIndex()== 1){
                        DefaultTableModel model = (DefaultTableModel) ui.jTableObjectos.getModel();
                        HashMap< String,Integer > temp = (HashMap< String,Integer >)cmd.result;
                        for(Map.Entry<String,Integer> item :temp.entrySet())
                            model.addRow(new Object[]{item.getKey(), item.getValue()});    
                    }
                    else if(ui.uitarefa.jTableCriarTarefaItem.isShowing()){
                        DefaultTableModel model1 = (DefaultTableModel) ui.uitarefa.jTableCriarTarefaItem.getModel();
                        HashMap< String,Integer > temp1 = (HashMap< String,Integer >)cmd.result;
                        for(Map.Entry<String,Integer> item1 :temp1.entrySet())
                            model1.addRow(new Object[]{item1.getKey(), item1.getValue()});
                    }
                    
                    break;
                }
                case "listar_tarefas":{
                    ui.jTextArea.append("[Application > Me] : Listar Tarefas\n\tDetalhes:"+cmd.toString()+"\n");
                    int selectedIndex = ui.jTabbedPane1.getSelectedIndex();

                    if(selectedIndex==2){    
                        DefaultTableModel model = (DefaultTableModel) ui.jTableTiposTarefas.getModel();
                        
                        TreeMap <String,TreeMap <String, Integer>> temp = (TreeMap < String,TreeMap <String, Integer>>)cmd.result;
                        for(Map.Entry<String,TreeMap<String,Integer>> tarefa :temp.entrySet()){
                            model.addRow(new Object[]{ tarefa.getKey()});
                        }
                    }
                    
                    break;
                }
                case "activas":{ // Ver o que é isto!!
                    System.out.println((String)cmd.result);
                    break;
                }
                case "listar_tarefas_concluidas":{ 
                    ui.jTextArea.append("[Application > Me] : Listar Tarefas\n\tDetalhes:"+cmd.toString()+"\n");
                    ArrayList< HashMap< Long,String >> temp = (ArrayList< HashMap< Long,String >>)cmd.result;
                    int selectedIndex = ui.jTabbedPane1.getSelectedIndex();

                    if(selectedIndex==4){   
                        ui.jLabel18.setText(Integer.toString(temp.get(0).size()+temp.get(1).size()));
                        //Integer aux= Integer.parseInt(ui.jLabel18.getText()) + temp.size();
                        //ui.jLabel10.setText(Integer.toString(aux));
                    }
                    else if(selectedIndex==3){
                        DefaultTableModel model = (DefaultTableModel) ui.jTableTarefas.getModel();
                        
//                        int rows = model.getRowCount(); 
//                        for(int i = rows - 1; i >=0; i--)
//                        {
//                           model.removeRow(i); 
//                        }
                        
                        String estado = "Activa";
                        for(HashMap< Long,String > map :temp){
                            for(Map.Entry<Long,String> tarefa: map.entrySet())
                                model.addRow(new Object[]{tarefa.getKey(), tarefa.getValue(),estado});
                            estado = "Executada";
                            
                        }
                    }
                    break;
                }
                case "tipos_tarefas":{
                    ui.jTextArea.append("[Application > Me] : Listar Tarefas\n\tDetalhes:"+cmd.toString()+"\n");
                    int selectedIndex = ui.jTabbedPane1.getSelectedIndex();

                    if(selectedIndex==2){ 
                        DefaultTableModel model = (DefaultTableModel) ui.jTableTiposTarefas.getModel();
                        
                        int rows = model.getRowCount(); 
                        for(int i = rows - 1; i >=0; i--)
                        {
                           model.removeRow(i); 
                        }
                        
                       ArrayList<String> temp = (ArrayList<String>)cmd.result;
                        for(String tarefa :temp){
                            model.addRow(new Object[]{ tarefa});
                            
                        }
                    }
                    break;
                }
                case "items_tarefa":{
                    ui.jTextArea.append("[Application > Me] : Listar Tarefas\n\tDetalhes:"+cmd.toString()+"\n");
                    int selectedIndex = ui.jTabbedPane1.getSelectedIndex();

                    if(selectedIndex==2){ 
                        DefaultTableModel model = (DefaultTableModel) ui.jTableTarefasObjeto.getModel();
                        
                        int rows = model.getRowCount(); 
                        for(int i = rows - 1; i >=0; i--)
                        {
                           model.removeRow(i); 
                        }
                        
                        TreeMap<String, Integer> temp = (TreeMap<String, Integer>)cmd.result;
                        for(Map.Entry<String, Integer> objecto :temp.entrySet()){
                            model.addRow(new Object[]{ objecto.getKey(),objecto.getValue()});
                            
                        }
                    }
                    break;
                }
                case "notify":{
                    ArrayList<String> temp =(ArrayList<String>)cmd.args.listArgs.get(0);
                    if(temp.contains(ui.cliente.cliente.getCliente()))
                        JOptionPane.showMessageDialog(ui,"Tarefas Terminadas.", "Notificação", JOptionPane.WARNING_MESSAGE);
                    break;
                }
                case "listar_real_concluidas":{
                    ui.jTextArea.append("[Application > Me] : Listar Tarefas concluidas\n\tDetalhes:"+cmd.toString()+"\n");
                    HashMap<Long, String> temp;
                    if(cmd.result != null){
                        temp = (HashMap<Long, String>)cmd.result;
                        int selectedIndex = ui.jTabbedPane1.getSelectedIndex();

                        if(selectedIndex==3){
                            DefaultTableModel model = (DefaultTableModel) ui.jTableTarefas.getModel();

                            String estado = "Concluida";
                            for(Map.Entry<Long,String> tarefa: temp.entrySet())
                                model.addRow(new Object[]{tarefa.getKey(), tarefa.getValue(),estado});

                        }
                    }else{
                       ui.jTextArea.append("[Application > Me] : Sem Tarefas Concluidas\n");
                    }
                    
                    break;
                }
                default:{
                    //System.out.println((String)cmd.result);
                    break;
                }
                    
            }
        }else{
            System.out.println("Invalid Command!\n");
        }
        
    }
}
