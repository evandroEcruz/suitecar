package com.evandro.tcc.suitecar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.evandro.tcc.suitecar.database.DataBase;
import com.evandro.tcc.suitecar.database.TableVeiculo;
import com.evandro.tcc.suitecar.database.databaseDao.VeiculoDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListaVeiculos extends AppCompatActivity {

    private ArrayList<TableVeiculo> arrayVeiculos;
    private ArrayList<String> arrayList;
    private DataBase db;
    private TableVeiculo veiculo;
    private VeiculoDao veiculoDao;
    private int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veiculos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = DataBase.getDatabase(this);
        veiculo = new TableVeiculo();


        ListView lista = (ListView) findViewById(R.id.lista);
        arrayVeiculos = new ArrayList<TableVeiculo>();
        arrayList = new ArrayList<String>();

        try {
            arrayVeiculos = (ArrayList<TableVeiculo>) db.getVeiculoDao().queryForAll();

            if (arrayVeiculos.size() == 0){

                    Intent cadastroCarro = new Intent(this, CadastroVeiculo.class);
                    startActivity(cadastroCarro);
                } else{

                    for (TableVeiculo veiculo : arrayVeiculos) {
                        arrayList.add(veiculo.getNome());
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        lista.setAdapter(array);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map <String, Object> pesquisa = new HashMap<String, Object>();
                pesquisa.put("nome",arrayList.get(i).toString());
                try {
                    arrayVeiculos = (ArrayList<TableVeiculo>) db.getVeiculoDao().queryForFieldValues(pesquisa);
                    for (TableVeiculo veiculoCash : arrayVeiculos) {
                        veiculo = veiculoCash;
                    }

                    Intent intent = new Intent(view.getContext(), TelaInicial.class);
                    Bundle args = new Bundle();
                    args.putSerializable("veiculo", veiculo);
                    intent.putExtra ("veiculo", args);
                    startActivity(intent);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastroVeiculo = new Intent(view.getContext(), CadastroVeiculo.class);
                startActivity(cadastroVeiculo);

            }
        });
    }

}