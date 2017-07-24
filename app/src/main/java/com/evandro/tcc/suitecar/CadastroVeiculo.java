package com.evandro.tcc.suitecar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.evandro.tcc.suitecar.database.DataBase;
import com.evandro.tcc.suitecar.database.TableVeiculo;

import java.sql.SQLException;

public class CadastroVeiculo extends AppCompatActivity {

    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = DataBase.getDatabase(this);

        Button botaoSalvar = (Button) findViewById(R.id.salvarVeiculo);
                botaoSalvar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        EditText placa = (EditText) findViewById(R.id.placa);
                        EditText nome = (EditText) findViewById(R.id.nome);
                        EditText hodometro = (EditText) findViewById(R.id.hodometro);
                        EditText volTanque = (EditText) findViewById(R.id.volTanque);

                        TableVeiculo veiculo = new TableVeiculo();
                        veiculo.setPlaca(placa.getText().toString());
                        veiculo.setNome(nome.getText().toString());
                        veiculo.setHodometro(Float.parseFloat(hodometro.getText().toString().trim()));
                        veiculo.setVolume_tanque(Float.parseFloat(volTanque.getText().toString().trim()));

                        try {

                           db.getVeiculoDao().createOrUpdate(veiculo);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        Intent telaPreventiva = new Intent(v.getContext(), CadPreventiva.class);
                        Bundle args = new Bundle();
                        args.putSerializable("veiculo", veiculo);
                        telaPreventiva.putExtra ("veiculo", args);
                        startActivity(telaPreventiva);

                    }
        });

    }

}
