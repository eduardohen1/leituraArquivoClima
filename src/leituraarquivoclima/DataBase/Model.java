/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leituraarquivoclima.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import leituraarquivoclima.model.DataSetDisponiveis;
import leituraarquivoclima.model.InmetGeral;

/**
 *
 * @author Eduardo Henrique Marques Ferreira eduardohmferreira@gmail.com
 */
public class Model {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Locale ptBR = new Locale("pt", "BR");
    NumberFormat moedaFormat;
    DateFormat dateFormat;
    ConexaoBD conexaoBD;
    
    public Model(ConexaoBD conexaoBD) {
        moedaFormat = NumberFormat.getCurrencyInstance(ptBR);
        dateFormat = DateFormat.getDateInstance(DateFormat.FULL, ptBR);
        this.conexaoBD = conexaoBD;
    }
    
    public boolean inserirRegistrosInmetGeral(List<InmetGeral> lstInmetGeral){
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        PreparedStatement stmt = null;        
        try{
            SqlServer sqlServer = new SqlServer(conexaoBD);
            if (sqlServer.connect()) {            
                for(InmetGeral inmetGeral : lstInmetGeral){
                    String sql = "INSERT INTO INMET_GERAL("
                            + "Data, Hora_UTC, PTH, PANEH, PAMAXHA, PAMINHA, RADIACAO, "
                            + "TAH, TPO, TMAXHA, TMINHA, TOMAX, TOMIN, URMAXH, URMINH, "
                            + "URH, VDH, VRM, VVH, id_cidade, latitude, longitude) VALUES("
                            + "CONVERT(DATETIME, '" + fmt.format(inmetGeral.getDataRegistro()) + "', 103), "
                            + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    stmt = sqlServer.c.prepareStatement(sql);
                    stmt.setString(1, inmetGeral.getHora_UTC());
                    stmt.setFloat(2, inmetGeral.getPTH());
                    stmt.setFloat(3, inmetGeral.getPANEH());
                    stmt.setFloat(4, inmetGeral.getPAMAXHA());
                    stmt.setFloat(5, inmetGeral.getPAMINHA());
                    stmt.setString(6, inmetGeral.getRADIACAO());
                    stmt.setFloat(7, inmetGeral.getTAH());
                    stmt.setFloat(8, inmetGeral.getTPO());
                    stmt.setFloat(9, inmetGeral.getTMAXHA());
                    stmt.setFloat(10, inmetGeral.getTMINHA());
                    stmt.setFloat(11, inmetGeral.getTOMAX());
                    stmt.setFloat(12, inmetGeral.getTOMIN());
                    stmt.setFloat(13, inmetGeral.getURMAXH());
                    stmt.setFloat(14, inmetGeral.getURMINH());
                    stmt.setFloat(15, inmetGeral.getURH());
                    stmt.setFloat(16, inmetGeral.getVDH());
                    stmt.setFloat(17, inmetGeral.getVRM());
                    stmt.setFloat(18, inmetGeral.getVVH());
                    stmt.setInt(19, inmetGeral.getId_cidade());
                    stmt.setString(20, inmetGeral.getLatitude());
                    stmt.setString(21, inmetGeral.getLongitude());                    
                    stmt.executeUpdate();
                    stmt.close();
                }
                sqlServer.disconnect();
                return true;
            }else{
                return false;
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir: " + ex.getMessage(), "Erro ao buscar dados no banco de dados.", JOptionPane.WARNING_MESSAGE);
            return false;
        }        
    }
    
    public List<DataSetDisponiveis> buscaDataSetDisponiveis(){
        List<DataSetDisponiveis> lstResposta = new ArrayList<>();
        String sql = "SELECT ds.* FROM DataSetDisponiveis ds WHERE (SELECT COUNT(*) FROM DesmatamentoCidade dc WHERE  dc.id_cidade = ds.id_cidade) > 0 AND Id_cidade <> 1";
        try {
            SqlServer sqlServer = new SqlServer(conexaoBD);
            if (sqlServer.connect()) {
                ResultSet resposta = sqlServer.executar(sql);
                if (resposta != null) {
                    while (resposta.next()) {
                        DataSetDisponiveis dataSetDisponiveis = new DataSetDisponiveis();
                        dataSetDisponiveis.setPath(resposta.getString("Path"));
                        dataSetDisponiveis.setArquivo(resposta.getString("Arquivo"));
                        dataSetDisponiveis.setEstado(resposta.getString("Estado"));
                        dataSetDisponiveis.setCidade(resposta.getString("Cidade"));
                        dataSetDisponiveis.setId_cidade(Integer.parseInt(resposta.getString("Id_cidade")));
                        lstResposta.add(dataSetDisponiveis);
                    }
                }
            }
            sqlServer.disconnect();
        }catch(NumberFormatException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados: " + ex.getMessage(), "Erro ao buscar dados no banco de dados.", JOptionPane.WARNING_MESSAGE);
            lstResposta = null;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados: " + ex.getMessage(), "Erro ao buscar dados no banco de dados.", JOptionPane.WARNING_MESSAGE);
            lstResposta = null;
        }
        return lstResposta;
    }
    
}
