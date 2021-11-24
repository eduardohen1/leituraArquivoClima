/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leituraarquivoclima;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import leituraarquivoclima.DataBase.ConexaoBD;
import leituraarquivoclima.DataBase.Model;
import leituraarquivoclima.model.DataSetDisponiveis;
import leituraarquivoclima.model.InmetGeral;

/**
 *
 * @author Eduardo Henrique Marques Ferreira eduardohmferreira@gmail.com
 */
public class LeituraArquivoClima {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        ConexaoBD conexaoBD = new ConexaoBD();
        conexaoBD.setNomeServidor("SQLEXPRESS2019");
        conexaoBD.setBD("visualizacao");
        conexaoBD.setUID_Conn("sa");
        conexaoBD.setPWD_Conn("*****");
        conexaoBD.setPorta(1433);
        conexaoBD.setTrusted_Conn("No");
        
        Model model = new Model(conexaoBD);
        List<InmetGeral> lstInmetGeral = new ArrayList<>();
        
        //buscar os arquivos da pasta
        int count = 0;
        List<DataSetDisponiveis> lstDataSetDisponiveis = model.buscaDataSetDisponiveis();
        if(lstDataSetDisponiveis != null){
            for(DataSetDisponiveis dataSetDisponiveis : lstDataSetDisponiveis){
                //Abrir os arquivos, preparar objeto e gravar no banco de dados.
                count++;
                System.out.println("Arquivo " + count + "/" + lstDataSetDisponiveis.size());
                lstInmetGeral = buscaDadosArquivo(dataSetDisponiveis.getPath(), dataSetDisponiveis.getArquivo(), dataSetDisponiveis.getId_cidade());                            
                if(!model.inserirRegistrosInmetGeral(lstInmetGeral)){
                    String erro = "";
                }            
            }
        }
    }
    
    private static List<InmetGeral> buscaDadosArquivo(String arquivoCSV, String nomeArquivo, int Id_cidade){
        List<InmetGeral> lstInmetGeral = new ArrayList<>();
        InmetGeral inmetGeral = null;
        int passo = 0;
        String sCampo = "";
        long nLinha = 0;
        try{
            BufferedReader bufferedReader = null;
            String linha = "";
            String divisor = ";";
            String Latitude = "";
            String Longitude = "";            
            boolean prima = true;
            boolean passouCabecalho = false;
            bufferedReader = new BufferedReader(new FileReader(arquivoCSV));
            while((linha = bufferedReader.readLine()) != null){                
                nLinha++;
                linha = linha.replace(";", " ;");
                String[] dados = linha.split(divisor);
                if(dados[0].trim().length() > 0){                    
                    if(!passouCabecalho){
                        switch(dados[0].trim().toUpperCase()){
                            case "LATITUDE:":  Latitude = dados[1].trim();  break;
                            case "LONGITUDE:": Longitude = dados[1].trim(); break;
                            case "DATA":       passouCabecalho = true;      break;
                        }
                    }else{
                        inmetGeral = new InmetGeral();
                        inmetGeral.setId_cidade(Id_cidade);
                        inmetGeral.setLatitude(Latitude);
                        inmetGeral.setLongitude(Longitude);
                        
                        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateH = dtf.parse(dados[0].replace("/","-"));
                        inmetGeral.setDataRegistro(dateH);
                        inmetGeral.setHora_UTC(dados[1]);
                        
                        if(dados.length > 5) {
                            passo = 2; sCampo = dados[2];
                            if(dados[2].trim().length() != 0){
                                if(dados[2].contains(","))
                                    inmetGeral.setPTH(Float.parseFloat(dados[2].replace(",",".")));
                                else
                                    inmetGeral.setPTH(Float.parseFloat(dados[2]));
                            }
                            else{
                                inmetGeral.setPTH(0);
                            }

                            passo = 3; sCampo = dados[3];
                            if(dados[3].trim().length() != 0){
                                if(dados[3].contains(","))
                                    inmetGeral.setPANEH(Float.parseFloat(dados[3].replace(",", ".")));
                                else
                                    inmetGeral.setPANEH(Float.parseFloat(dados[3]));
                            }else{
                                inmetGeral.setPANEH(0);
                            }

                            passo = 4; sCampo = dados[4];
                            if(dados[4].trim().length() != 0){
                                if(dados[4].contains(","))
                                    inmetGeral.setPAMAXHA(Float.parseFloat(dados[4].replace(",", ".")));
                                else
                                    inmetGeral.setPAMAXHA(Float.parseFloat(dados[4]));
                            }else{
                                inmetGeral.setPAMAXHA(0);
                            }

                            passo = 5; sCampo = dados[5];
                            inmetGeral.setRADIACAO(dados[5].trim());

                            passo = 6; sCampo = dados[6];
                            if(dados[6].trim().length() != 0){
                                if(dados[6].contains(","))
                                    inmetGeral.setTAH(Float.parseFloat(dados[6].replace(",",".")));
                                else
                                    inmetGeral.setTAH(Float.parseFloat(dados[6]));
                            }else{
                                inmetGeral.setTAH(0);
                            }

                            passo = 7; sCampo = dados[7];
                            if(dados[7].trim().length() != 0){
                                if(dados[7].contains(","))
                                    inmetGeral.setTPO(Float.parseFloat(dados[7].replace(",",".")));
                                else
                                    inmetGeral.setTPO(Float.parseFloat(dados[7]));
                            }else{
                                inmetGeral.setTPO(0);
                            }

                            passo = 8; sCampo = dados[8];
                            if(dados[8].trim().length() != 0){
                                if(dados[8].contains(","))
                                    inmetGeral.setTMAXHA(Float.parseFloat(dados[8].replace(",", ".")));
                                else
                                    inmetGeral.setTMAXHA(Float.parseFloat(dados[8]));
                            }else{
                                inmetGeral.setTMAXHA(0);
                            }

                            passo = 9; sCampo = dados[9];
                            if(dados[9].trim().length() != 0){
                                if(dados[9].contains(","))
                                    inmetGeral.setTMINHA(Float.parseFloat(dados[9].replace(",", ".")));
                                else
                                    inmetGeral.setTMINHA(Float.parseFloat(dados[9]));
                            }else{
                                inmetGeral.setTMINHA(0);
                            }

                            passo = 10; sCampo = dados[10];
                            if(dados[10].trim().length() != 0){
                                if(dados[10].contains(","))
                                    inmetGeral.setTOMAX(Float.parseFloat(dados[10].replace(",", ".")));
                                else
                                    inmetGeral.setTOMAX(Float.parseFloat(dados[10]));
                            }else{
                                inmetGeral.setTOMAX(0);
                            }

                            passo = 11; sCampo = dados[11];
                            if(dados[11].trim().length() != 0){
                                if(dados[11].contains(","))
                                    inmetGeral.setTOMIN(Float.parseFloat(dados[11].replace(",", ".")));
                                else
                                    inmetGeral.setTOMIN(Float.parseFloat(dados[11]));
                            }else{
                                inmetGeral.setTOMIN(0);
                            }

                            passo = 12; sCampo = dados[12];
                            if(dados[12].trim().length() != 0){
                                if(dados[12].contains(","))
                                    inmetGeral.setURMAXH(Float.parseFloat(dados[12].replace(",", ".")));
                                else
                                    inmetGeral.setURMAXH(Float.parseFloat(dados[12]));
                            }else{
                                inmetGeral.setURMAXH(0);
                            }

                            passo = 13; sCampo = dados[13];
                            if(dados[13].trim().length() != 0){
                                if(dados[13].contains(","))
                                    inmetGeral.setURMINH(Float.parseFloat(dados[13].replace(",", ".")));
                                else
                                    inmetGeral.setURMINH(Float.parseFloat(dados[13]));
                            }else{
                                inmetGeral.setURMINH(0);
                            }

                            passo = 14; sCampo = dados[14];
                            if(dados[14].trim().length() != 0){
                                if(dados[14].contains(","))
                                    inmetGeral.setURH(Float.parseFloat(dados[14].replace(",", ".")));
                                else
                                    inmetGeral.setURH(Float.parseFloat(dados[14]));
                            }else{
                                inmetGeral.setURH(0);
                            }

                            passo = 15; sCampo = dados[15];
                            if(dados[15].trim().length() != 0){
                                if(dados[15].contains(","))
                                    inmetGeral.setVDH(Float.parseFloat(dados[15].replace(",",".")));
                                else
                                    inmetGeral.setVDH(Float.parseFloat(dados[15]));
                            }else{
                                inmetGeral.setVDH(0);
                            }

                            passo = 16; sCampo = dados[16];
                            if(dados[16].trim().length() != 0){
                                if(dados[16].contains(","))
                                    inmetGeral.setVRM(Float.parseFloat(dados[16].replace(",", ".")));
                                else
                                    inmetGeral.setVRM(Float.parseFloat(dados[16]));
                            }else{
                                inmetGeral.setVRM(0);
                            }

                            passo = 17; sCampo = dados[17];
                            if(dados[17].trim().length() != 0){
                                if(dados[17].contains(","))
                                    inmetGeral.setVVH(Float.parseFloat(dados[17].replace(",", ".")));
                                else
                                    inmetGeral.setVVH(Float.parseFloat(dados[17]));
                            }else{
                                inmetGeral.setVVH(0);      
                            }     
                        }else{
                            inmetGeral.setPTH(0); 
                            inmetGeral.setPANEH(0);
                            inmetGeral.setPAMAXHA(0);
                            inmetGeral.setRADIACAO(""); 
                            inmetGeral.setTAH(0);
                            inmetGeral.setTPO(0); 
                            inmetGeral.setTPO(0); 
                            inmetGeral.setTMINHA(0); 
                            inmetGeral.setTOMAX(0); 
                            inmetGeral.setTOMIN(0);
                            inmetGeral.setURMAXH(0); 
                            inmetGeral.setURMINH(0); 
                            inmetGeral.setURH(0);
                            inmetGeral.setVDH(0); 
                            inmetGeral.setVRM(0);
                            inmetGeral.setVVH(0);
                        }
                        lstInmetGeral.add(inmetGeral);
                    }                                        
                }
            }            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo: " + ex.getMessage() + "\nPasso: " + passo + "\nDado: " + sCampo + "\nLinha: " + nLinha, "Erro ao buscar dados no arquivo (" + nomeArquivo + ").", JOptionPane.ERROR_MESSAGE);            
        }
        return lstInmetGeral;
    }
    
}
