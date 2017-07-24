package com.evandro.tcc.suitecar.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;

/**
 * Created by evand on 03/11/2016.
 */
@DatabaseTable(tableName = "COMBUSTIVEL")
public class TableCombustivel implements Serializable {


    public TableCombustivel(int id_combustivel) {
        this.id_combustivel = id_combustivel;
    }

    public TableCombustivel() { }

    @DatabaseField (generatedId = true, columnName = "id")
    private int id_combustivel;
    @DatabaseField (columnName = "litroAtual")
    float atual_litro;
    @DatabaseField (columnName = "valorAtual")
    float atual_valor;
    @DatabaseField (columnName = "atualKmrodado")
    float atual_kmrodado;
    @DatabaseField(foreign = true, columnName = "veiculo")
    TableVeiculo veiculo;

    public int getId_combustivel() {
        return id_combustivel;
    }

    public void setId_combustivel(int id_combustivel) {
        this.id_combustivel = id_combustivel;
    }

    public float getAtual_litro() {
        return atual_litro;
    }

    public void setAtual_litro(float atual_litro) {
        this.atual_litro = atual_litro;
    }

    public float getAtual_valor() {
        return atual_valor;
    }

    public void setAtual_valor(float atual_valor) {
        this.atual_valor = atual_valor;
    }

    public float getAtual_kmrodado() {
        return atual_kmrodado;
    }

    public void setAtual_kmrodado(float atual_kmrodado) {
        this.atual_kmrodado = atual_kmrodado;
    }

    public TableVeiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(TableVeiculo carro) {
        this.veiculo = veiculo;
    }
}
