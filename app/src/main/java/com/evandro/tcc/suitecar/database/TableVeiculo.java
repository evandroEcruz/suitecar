package com.evandro.tcc.suitecar.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by evand on 03/11/2016.
 */
@DatabaseTable (tableName = "VEICULO")
public class TableVeiculo implements Serializable {

    @DatabaseField (generatedId = true, columnName = "id")
    private int id_veiculo;
    @DatabaseField (columnName = "placa", canBeNull = false)
    String placa;
    @DatabaseField (columnName = "nome", canBeNull = false)
    String nome;
    @DatabaseField (columnName = "hodometro", canBeNull = false)
    float hodometro;
    @DatabaseField (columnName = "volTanque", canBeNull = false)
    float volume_tanque;
    @DatabaseField (foreign = true, columnName = "preventiva", foreignAutoRefresh = true)
    TablePreventiva preventiva;
    @DatabaseField (foreign = true, columnName = "relatorio", foreignAutoRefresh = true)
    TableDadosRelat relat;
    @ForeignCollectionField(eager = true)
    private Collection<TableCombustivel> combustivel;

    public TablePreventiva getPreventiva() {
        return preventiva;
    }

    public void setPreventiva(TablePreventiva preventiva) {
        this.preventiva = preventiva;
    }

    public Collection<TableCombustivel> getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Collection<TableCombustivel> combustivel) {
        this.combustivel = combustivel;
    }

    public TableDadosRelat getRelat() {
        return relat;
    }

    public void setRelat(TableDadosRelat relat) {
        this.relat = relat;
    }

    public long getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getHodometro() {
        return hodometro;
    }

    public void setHodometro(float hodometro) {
        this.hodometro = hodometro;
    }

    public float getVolume_tanque() {
        return volume_tanque;
    }

    public void setVolume_tanque(float volume_tanque) {
        this.volume_tanque = volume_tanque;
    }
}
