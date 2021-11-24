/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leituraarquivoclima.model;

/**
 *
 * @author Eduardo Henrique Marques Ferreira eduardohmferreira@gmail.com
 */
public class DataSetDisponiveis {
    private String Path;
    private String Arquivo;
    private String Estado;
    private String Cidade;
    private int Id_cidade;

    public DataSetDisponiveis(String Path, String Arquivo, String Estado, String Cidade, int Id_cidade) {
        this.Path = Path;
        this.Arquivo = Arquivo;
        this.Estado = Estado;
        this.Cidade = Cidade;
        this.Id_cidade = Id_cidade;
    }
    
    public DataSetDisponiveis(){}

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    public String getArquivo() {
        return Arquivo;
    }

    public void setArquivo(String Arquivo) {
        this.Arquivo = Arquivo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public int getId_cidade() {
        return Id_cidade;
    }

    public void setId_cidade(int Id_cidade) {
        this.Id_cidade = Id_cidade;
    }
    
    
    
}
