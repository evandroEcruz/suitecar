package com.evandro.tcc.suitecar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.evandro.tcc.suitecar.database.DataBase;
import com.evandro.tcc.suitecar.database.TablePreventiva;
import com.evandro.tcc.suitecar.database.TableVeiculo;

import java.sql.SQLException;

public class CadPreventiva extends AppCompatActivity {

    private DataBase db;
    private TablePreventiva preventiva;
    private TableVeiculo veiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_preventiva);

        db = DataBase.getDatabase(this);


        Button botaoSalvar = (Button) findViewById(R.id.botaoSalvarPrev);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preventiva = new TablePreventiva();

                EditText filtroOleo = (EditText) findViewById(R.id.filtroOleo);
                EditText filtroAr = (EditText) findViewById(R.id.filtroAr);
                EditText trocaOleo = (EditText) findViewById(R.id.trocaOleo);
                EditText fluidoArref = (EditText) findViewById(R.id.fluidoArref);
                EditText pastFreio = (EditText) findViewById(R.id.pastFreio);
                EditText balanciamento = (EditText) findViewById(R.id.balanciamento);
                EditText trocaVela = (EditText) findViewById(R.id.trocaVela);


                try {
                    Bundle args = getIntent().getBundleExtra("veiculo");
                    veiculo = (TableVeiculo) args.getSerializable("veiculo");
                    TableVeiculo veiculoCarregado = db.getVeiculoDao().queryForId((int) veiculo.getId_veiculo());

                    preventiva.setVeiculo(veiculoCarregado);

                    preventiva.setFiltro_oleo(Float.parseFloat(filtroOleo.getText().toString().trim()));
                    preventiva.setFiltro_ar(Float.parseFloat(filtroAr.getText().toString().trim()));
                    preventiva.setTroca_oleo(Float.parseFloat(trocaOleo.getText().toString().trim()));
                    preventiva.setFluido_arref(Float.parseFloat(fluidoArref.getText().toString().trim()));
                    preventiva.setPastilha_freio(Float.parseFloat(pastFreio.getText().toString().trim()));
                    preventiva.setBalanceamento(Float.parseFloat(balanciamento.getText().toString().trim()));
                    preventiva.setVelas(Float.parseFloat(trocaVela.getText().toString()));

                    db.getPreventivaDao().create(preventiva);
                    veiculoCarregado.setPreventiva(preventiva);
                    db.getVeiculoDao().update(veiculoCarregado);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // lê a passagem de parametro da activity CadastroVeiculo
                Intent intent = new Intent(view.getContext(), ListaVeiculos.class);
                startActivity(intent);

            }
        });
    }
}
