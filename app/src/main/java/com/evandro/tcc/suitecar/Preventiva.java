package com.evandro.tcc.suitecar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.evandro.tcc.suitecar.database.DataBase;
import com.evandro.tcc.suitecar.database.TablePreventiva;
import com.evandro.tcc.suitecar.database.TableVeiculo;

import java.sql.SQLException;

public class Preventiva extends AppCompatActivity {

    private TablePreventiva preventiva;
    private TableVeiculo veiculo;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preventiva);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    */

    }

    @Override
    protected void onResume() {
        super.onResume();

        veiculo = new TableVeiculo();
        preventiva = new TablePreventiva();

        db = DataBase.getDatabase(this);

        TextView restFiltroOleo = (TextView)findViewById(R.id.restFiltroOleo);
        TextView restFiltroAr = (TextView)findViewById(R.id.restFiltroAr);
        TextView restTrocaOleo = (TextView)findViewById(R.id.restTrocaOleo);
        TextView restFluidoArref = (TextView)findViewById(R.id.restFluidoArref);
        TextView restPastFreio = (TextView)findViewById(R.id.restPastFreio);
        TextView restAlinhamento = (TextView)findViewById(R.id.restAlinhamento);
        TextView restVelas = (TextView)findViewById(R.id.restVelas);

        try {
            // lê a passagem de parametro da activity CadastroVeiculo
            Bundle args = getIntent().getBundleExtra("veiculo");
            veiculo = (TableVeiculo) args.getSerializable("veiculo");
            TableVeiculo veiculoCarregado = db.getVeiculoDao().queryForId((int) veiculo.getId_veiculo());
            preventiva = veiculoCarregado.getPreventiva();

            restFiltroOleo.setText(String.valueOf(this.calculateValue(
                    veiculoCarregado, preventiva.getFiltro_oleo())));

            restFiltroAr.setText(String.valueOf(
                    this.calculateValue(veiculoCarregado, preventiva.getFiltro_ar())));

            restTrocaOleo.setText(String.valueOf(
                    this.calculateValue(veiculoCarregado, preventiva.getTroca_oleo())));

            restFluidoArref.setText(String.valueOf(
                    this.calculateValue(veiculoCarregado, preventiva.getFluido_arref())));

            restPastFreio.setText(String.valueOf(
                    this.calculateValue(veiculoCarregado, preventiva.getPastilha_freio())));

            restAlinhamento.setText(String.valueOf(
                    this.calculateValue(veiculoCarregado, preventiva.getBalanceamento())));

            restVelas.setText(String.valueOf(
                    this.calculateValue(veiculoCarregado, preventiva.getVelas())));
//            db.getPreventivaDao().refresh(preventiva);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private float calculateValue(TableVeiculo veiculo, float preventiveValue) {
        if (preventiveValue > veiculo.getHodometro()) {
            return preventiveValue - veiculo.getHodometro();
        } else if (veiculo.getHodometro() % preventiveValue == 0) {
            return preventiveValue;
        } else {
            return veiculo.getHodometro() % preventiveValue;
        }
    }
}
