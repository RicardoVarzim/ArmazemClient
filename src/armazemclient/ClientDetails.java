/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package armazemclient;

/**
 *
 * @author Ricardo
 */
public class ClientDetails {
    
    private String _nome;
    private String _password;
    
    public String getCliente(){
       return _nome;
    }
    
    public void setCliente(String c){
       _nome = c;
    }
    
    public String getPassword(){
       return _password;
    }
    
    public void setPassword(String p){
       _password = p;
    }
}
