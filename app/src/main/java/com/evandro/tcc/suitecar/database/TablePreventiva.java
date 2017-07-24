package com.evandro.tcc.suitecar.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;

/**
 * Created by evand on 03/11/2016.
 */
@DatabaseTable(tableName = "PREVENTIVA")
public class TablePreventiva implements Serializable {

    @DatabaseField (generatedId = true, columnName = "id")
    private int id_preventiva;
    @DatabaseField (columnName = "filtroOle")
    float filtro_oleo;
    @DatabaseField (columnName = "filtroAr")
    float filtro_ar;
    @DatabaseField (columnName = "trocaOleo")
    float troca_oleo;
    @DatabaseField (columnName = "filtroArref")
    float fluido_arref;
    @DatabaseField (columnName = "pastilhaFreio")
    float pastilha_freio;
    @DatabaseField (columnName = "balanceamento")
    float balanceamento;
    @DatabaseField (columnName = "velas")
    float velas;
    @DatabaseField (columnName = "filtroCombustivel")
    float filtro_comustivel;

    public int getId_preventiva() {
        return id_preventiva;
    }

    public void setId_preventiva(int id_preventiva) {
        this.id_preventiva = id_preventiva;
    }

    @DatabaseField (columnName = "veiculo", foreign = true)

    TableVeiculo veiculo;

    public float getFiltro_oleo() {
        return filtro_oleo;
    }

    public void setFiltro_oleo(float filtro_oleo) {
        this.filtro_oleo = filtro_oleo;
    }

    public float getFiltro_ar() {
        return filtro_ar;
    }

    public void setFiltro_ar(float filtro_ar) {
        this.filtro_ar = filtro_ar;
    }

    public float getTroca_oleo() {
        return troca_oleo;
    }

    public void setTroca_oleo(float troca_oleo) {
        this.troca_oleo = troca_oleo;
    }

    public float getFluido_arref() {
        return fluido_arref;
    }

    public void setFluido_arref(float fluido_arref) {
        this.fluido_arref = fluido_arref;
    }

    public float getPastilha_freio() {
        return pastilha_freio;
    }

    public void setPastilha_freio(float pastilha_freio) {
        this.pastilha_freio = pastilha_freio;
    }

    public float getBalanceamento() {
        return balanceamento;
    }

    public void setBalanceamento(float balanceamento) {
        this.balanceamento = balanceamento;
    }

    public float getVelas() {
        return velas;
    }

    public void setVelas(float velas) {
        this.velas = velas;
    }

    public float getFiltro_comustivel() {
        return filtro_comustivel;
    }

    public void setFiltro_comustivel(float filtro_comustivel) {
        this.filtro_comustivel = filtro_comustivel;
    }

    public TableVeiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(TableVeiculo veiculo) {
        this.veiculo = veiculo;
    }
}
