/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leituraarquivoclima.DataBase;

/**
 *
 * @author Eduardo Henrique Marques Ferreira eduardohmferreira@gmail.com
 */
public class ConexaoBD {
    private String NomeServidor="?";    
    private String UID_Conn="?";
    private String PWD_Conn="?";
    private String Trusted_Conn="?";
    private String BD="?";    
    private int Porta=0;

    public ConexaoBD(
            String NomeServidor,
            String UID_Conn,
            String PWD_Conn,
            String Trusted_Conn,
            String BD,
            int Porta
    ) {
        this.NomeServidor = NomeServidor;
        this.UID_Conn = UID_Conn;
        this.PWD_Conn = PWD_Conn;
        this.Trusted_Conn = Trusted_Conn;
        this.BD = BD;
        this.Porta = Porta;
    }
    public ConexaoBD(){}
    
    public String getNomeServidor() {
        return NomeServidor;
    }

    public void setNomeServidor(String NomeServidor) {
        this.NomeServidor = NomeServidor;
    }

    public String getUID_Conn() {
        return UID_Conn;
    }

    public void setUID_Conn(String UID_Conn) {
        this.UID_Conn = UID_Conn;
    }

    public String getPWD_Conn() {
        return PWD_Conn;
    }

    public void setPWD_Conn(String PWD_Conn) {
        this.PWD_Conn = PWD_Conn;
    }

    public String getTrusted_Conn() {
        return Trusted_Conn;
    }

    public void setTrusted_Conn(String Trusted_Conn) {
        this.Trusted_Conn = Trusted_Conn;
    }

    public String getBD() {
        return BD;
    }

    public void setBD(String BD) {
        this.BD = BD;
    }

    public int getPorta() {
        return Porta;
    }

    public void setPorta(int Porta) {
        this.Porta = Porta;
    }
    
    
    
}
