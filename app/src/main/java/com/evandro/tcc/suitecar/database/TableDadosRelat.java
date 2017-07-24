package com.evandro.tcc.suitecar.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.joda.time.DateTime;
import java.io.Serializable;

/**
 * Created by evand on 10/11/2016.
 */
@DatabaseTable(tableName = "DADOS_RELAT")
public class TableDadosRelat implements Serializable {



    @DatabaseField (generatedId = true, columnName = "id")
    private int id_relat;
    @DatabaseField(columnName = "primeiraData")
    DateTime primeiraData;
    @DatabaseField (columnName = "ultimoValor")
    float ultimo_valor;
    @DatabaseField (columnName = "valorDia")
    float valorDia;
    @DatabaseField (columnName = "valorMes")
    float valorMes;
    @DatabaseField (columnName = "valorAno")
    float valorAno;
    @DatabaseField (columnName = "kmDia")
    float kmDia;
    @DatabaseField (columnName = "kmMes")
    float kmMes;
    @DatabaseField (columnName = "kmAno")
    float kmAno;
    @DatabaseField (columnName = "ultimoHodometro")
    float ultimoHodometro;
    @DatabaseField (columnName = "totGasto")
    float tot_gasto = 0;
    @DatabaseField (columnName = "kmAbastecimento")
    float kmAbastecimento = 0;
    @DatabaseField (columnName = "litroAbastecimento")
    float litroAbastecimento = 0;
    @DatabaseField (columnName = "baixaEfi")
    float baixa_efi = 0;
    @DatabaseField (columnName = "mediaEfi")
    float media_efi = 0;
    @DatabaseField (columnName = "contMedia_efi")
    int contMedia_efi = 0;
    @DatabaseField (columnName = "altaefi")
    float alta_efi;
    @DatabaseField(foreign = true, columnName = "veiculo")
    TableVeiculo veiculo;

    public int getId_relat() {
        return id_relat;
    }

    public void setId_relat(int id_relat) {
        this.id_relat = id_relat;
    }

    public float getContMedia_efi() {
        return contMedia_efi;
    }

    public void setContMedia_efi(int contMedia_efi) {
        this.contMedia_efi = contMedia_efi;
    }

    public DateTime getprimeiraData() {
        return primeiraData;
    }

    public void setprimeiraData(DateTime primeiraData) {
        this.primeiraData = primeiraData;
    }

    public float getUltimo_valor() {
        return ultimo_valor;
    }

    public void setUltimo_valor(float ultimo_valor) {
        this.ultimo_valor = ultimo_valor;
    }

    public float getValorDia() {
        return valorDia;
    }

    public void setValorDia(float valorDia) {
        this.valorDia = valorDia;
    }

    public float getValorMes() {
        return valorMes;
    }

    public void setValorMes(float valorMes) {
        this.valorMes = valorMes;
    }

    public float getValorAno() {
        return valorAno;
    }

    public void setValorAno(float valorAno) {
        this.valorAno = valorAno;
    }

    public float getKmDia() {
        return kmDia;
    }

    public void setKmDia(float kmDia) {
        this.kmDia = kmDia;
    }

    public float getKmMes() {
        return kmMes;
    }

    public void setKmMes(float kmMes) {
        this.kmMes = kmMes;
    }

    public float getKmAno() {
        return kmAno;
    }

    public void setKmAno(float kmAno) {
        this.kmAno = kmAno;
    }

    public float getUltimoHodometro() {
        return ultimoHodometro;
    }

    public void setUltimoHodometro(float ultimoHodometro) {
        this.ultimoHodometro = ultimoHodometro;
    }

    public float getLitroAbastecimento() {
        return litroAbastecimento;
    }

    public void setLitroAbastecimento(float litroAbastecimento) {
        this.litroAbastecimento = litroAbastecimento;
    }

    public float getKmAbastecimento() {
        return kmAbastecimento;
    }

    public void setKmAbastecimento(float kmAbastecimento) {
        this.kmAbastecimento = kmAbastecimento;
    }

    public float getTot_gasto() {
        return tot_gasto;
    }

    public void setTot_gasto(float tot_gasto) {
        this.tot_gasto = tot_gasto;
    }

    public float getBaixa_efi() {
        return baixa_efi;
    }

    public void setBaixa_efi(float baixa_efi) {
        this.baixa_efi = baixa_efi;
    }

    public float getMedia_efi() {
        return media_efi;
    }

    public void setMedia_efi(float media_efi) {
        this.media_efi = media_efi;
    }

    public float getAlta_efi() {
        return alta_efi;
    }

    public void setAlta_efi(float alta_efi) {
        this.alta_efi = alta_efi;
    }

    public TableVeiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(TableVeiculo veiculo) {
        this.veiculo = veiculo;
    }
}
