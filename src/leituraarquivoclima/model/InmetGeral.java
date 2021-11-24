/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leituraarquivoclima.model;

import java.util.Date;

/**
 *
 * @author Eduardo Henrique Marques Ferreira eduardohmferreira@gmail.com
 */
public class InmetGeral {
    private Date DataRegistro;
    private String Hora_UTC;
    private float PTH;
    private float PANEH;
    private float PAMAXHA;
    private float PAMINHA;
    private String RADIACAO;
    private float TAH;
    private float TPO;
    private float TMAXHA;
    private float TMINHA;
    private float TOMAX;
    private float TOMIN;
    private float URMAXH;
    private float URMINH;
    private float URH;
    private float VDH;
    private float VRM;
    private float VVH;
    private int id_cidade;
    private String latitude;
    private String longitude;

    public InmetGeral(Date DataRegistro, String Hora_UTC, float PTH, float PANEH, float PAMAXHA, float PAMINHA, String RADIACAO, float TAH, float TPO, float TMAXHA, float TMINHA, float TOMAX, float TOMIN, float URMAXH, float URMINH, float URH, float VDH, float VRM, float VVH, int id_cidade, String latitude, String longitude) {
        this.DataRegistro = DataRegistro;
        this.Hora_UTC = Hora_UTC;
        this.PTH = PTH;
        this.PANEH = PANEH;
        this.PAMAXHA = PAMAXHA;
        this.PAMINHA = PAMINHA;
        this.RADIACAO = RADIACAO;
        this.TAH = TAH;
        this.TPO = TPO;
        this.TMAXHA = TMAXHA;
        this.TMINHA = TMINHA;
        this.TOMAX = TOMAX;
        this.TOMIN = TOMIN;
        this.URMAXH = URMAXH;
        this.URMINH = URMINH;
        this.URH = URH;
        this.VDH = VDH;
        this.VRM = VRM;
        this.VVH = VVH;
        this.id_cidade = id_cidade;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public InmetGeral(){}

    public Date getDataRegistro() {
        return DataRegistro;
    }

    public void setDataRegistro(Date DataRegistro) {
        this.DataRegistro = DataRegistro;
    }

    public String getHora_UTC() {
        return Hora_UTC;
    }

    public void setHora_UTC(String Hora_UTC) {
        this.Hora_UTC = Hora_UTC;
    }

    public float getPTH() {
        return PTH;
    }

    public void setPTH(float PTH) {
        this.PTH = PTH;
    }

    public float getPANEH() {
        return PANEH;
    }

    public void setPANEH(float PANEH) {
        this.PANEH = PANEH;
    }

    public float getPAMAXHA() {
        return PAMAXHA;
    }

    public void setPAMAXHA(float PAMAXHA) {
        this.PAMAXHA = PAMAXHA;
    }

    public float getPAMINHA() {
        return PAMINHA;
    }

    public void setPAMINHA(float PAMINHA) {
        this.PAMINHA = PAMINHA;
    }

    public String getRADIACAO() {
        return RADIACAO;
    }

    public void setRADIACAO(String RADIACAO) {
        this.RADIACAO = RADIACAO;
    }

    public float getTAH() {
        return TAH;
    }

    public void setTAH(float TAH) {
        this.TAH = TAH;
    }

    public float getTPO() {
        return TPO;
    }

    public void setTPO(float TPO) {
        this.TPO = TPO;
    }

    public float getTMAXHA() {
        return TMAXHA;
    }

    public void setTMAXHA(float TMAXHA) {
        this.TMAXHA = TMAXHA;
    }

    public float getTMINHA() {
        return TMINHA;
    }

    public void setTMINHA(float TMINHA) {
        this.TMINHA = TMINHA;
    }

    public float getTOMAX() {
        return TOMAX;
    }

    public void setTOMAX(float TOMAX) {
        this.TOMAX = TOMAX;
    }

    public float getTOMIN() {
        return TOMIN;
    }

    public void setTOMIN(float TOMIN) {
        this.TOMIN = TOMIN;
    }

    public float getURMAXH() {
        return URMAXH;
    }

    public void setURMAXH(float URMAXH) {
        this.URMAXH = URMAXH;
    }

    public float getURMINH() {
        return URMINH;
    }

    public void setURMINH(float URMINH) {
        this.URMINH = URMINH;
    }

    public float getURH() {
        return URH;
    }

    public void setURH(float URH) {
        this.URH = URH;
    }

    public float getVDH() {
        return VDH;
    }

    public void setVDH(float VDH) {
        this.VDH = VDH;
    }

    public float getVRM() {
        return VRM;
    }

    public void setVRM(float VRM) {
        this.VRM = VRM;
    }

    public float getVVH() {
        return VVH;
    }

    public void setVVH(float VVH) {
        this.VVH = VVH;
    }

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    
}
