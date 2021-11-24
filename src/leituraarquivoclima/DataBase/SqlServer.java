/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leituraarquivoclima.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Eduardo Henrique Marques Ferreira eduardohmferreira@gmail.com
 */
public class SqlServer {
    private String host;
    private String user;
    private String pass;
    private String database;
    private String trusted_conn;
    private int porta;
    
    public String mensagem;
    
    public Connection c;
    
    public SqlServer(ConexaoBD conexaoBD){
        this.pass = conexaoBD.getPWD_Conn();//  pass;
        this.user = conexaoBD.getUID_Conn();// user;
        this.host = conexaoBD.getNomeServidor();// host;
        this.database = conexaoBD.getBD(); // database;        
        this.porta = conexaoBD.getPorta();// porta;
        this.trusted_conn = conexaoBD.getTrusted_Conn();// trusted_conn;
    }

    SqlServer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean connect() throws ClassNotFoundException{
        boolean isConnected = false;
        
        String url;
        String portNumber;
        if (this.porta != 0)
            portNumber = String.valueOf(this.porta);
        else //
            portNumber = "";
        String userName = this.user;
        String passName = this.pass;
        String textPorta = "";
        String servidor = "";
        String instancia = "";        
        if(portNumber.trim().length()>0) textPorta= ":" + portNumber;        
        //verificar se a conexão é uma instância:        
        if(this.host.indexOf("\\") > 0){                
            servidor = this.host.substring(0, this.host.indexOf("\\"));
            instancia = ";instance=" + this.host.substring(this.host.indexOf("\\")+1).trim();
        }else{
            servidor = this.host;
        }
        url = "jdbc:sqlserver://" + servidor + textPorta + ";"
                + "database=" + this.database + ";"
                + "user=" + userName + ";"
                + "password=" + passName + ";"                
                + "trustServerCertificate=" + (trusted_conn.trim().toUpperCase().equals("NO") ? "false" : "true") + ";"
                + "loginTimeout=33" + instancia + ";" ;
        try{            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.c = DriverManager.getConnection(url);
            isConnected = true;            
        }catch(SQLException  ex){
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Erro ao conectar no banco de dados.\n" + ex.getMessage(),
                    "Controle de Dados",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            isConnected = false;
        } 
        return isConnected;
    }
    
    public boolean disconnect(){
        boolean isConnected = false;
        String url;
        String portNumber;
        if (this.porta != 0)
            portNumber = String.valueOf(this.porta);
        else
            portNumber = "";
        String userName = this.user;
        String passName = this.pass;
        String textPorta = "";
        String servidor = "";
        String instancia = "";        
        if(portNumber.trim().length()>0) textPorta= ":" + portNumber;        
        //verificar se a conexão é uma instância:        
        if(this.host.indexOf("\\") > 0){                
            servidor = this.host.substring(0, this.host.indexOf("\\"));
            instancia = ";instance=" + this.host.substring(this.host.indexOf("\\")+1).trim();
        }else{
            servidor = this.host;
        }
        url = "jdbc:sqlserver://" + servidor + textPorta + ";"
                + "database=" + this.database + ";"
                + "user=" + userName + ";"
                + "password=" + passName + ";"                
                + "trustServerCertificate=" + (trusted_conn.trim().toUpperCase().equals("NO") ? "false" : "true") + ";"
                + "loginTimeout=33" + instancia + ";" ;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.c = DriverManager.getConnection(url);
            this.c.close();
            isConnected = true;            
        }catch(SQLException | ClassNotFoundException ex){
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Erro ao desconectar no banco de dados.\n" + ex.getMessage(),
                    "Controle de Dados",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            isConnected = false;
        } 
        return isConnected;
    }
    
    public ResultSet executar(String query){
        Statement st;
        ResultSet rs;
        try{
            st = this.c.createStatement();
            rs = st.executeQuery(query);
            return rs;
        }catch(SQLException ex){
            mensagem = ex.getMessage();
            return null;
        }
    }
    
    public int inserir(String query){
        Statement st;
        int result = -1;
        try{
            st = this.c.createStatement();
            result = st.executeUpdate(query);
        }catch(SQLException ex){
            mensagem = ex.getMessage();
            result = 0;
        }
        return result;
    }
}
