package com.evandro.tcc.suitecar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.evandro.tcc.suitecar.database.DataBase;
import com.evandro.tcc.suitecar.database.TablePreventiva;
import com.evandro.tcc.suitecar.database.TableVeiculo;

import org.w3c.dom.Text;

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

        // lê a passagem de parametro da activity CadastroVeiculo
        Bundle args = getIntent().getBundleExtra("veiculo");
        veiculo = (TableVeiculo) args.getSerializable("veiculo");

        preventiva = veiculo.getPreventiva();
        try {
            db.getPreventivaDao().refresh(preventiva);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TextView restFiltroOleo = (TextView)findViewById(R.id.restFiltroOleo);
        TextView restFiltroAr = (TextView)findViewById(R.id.restFiltroAr);
        TextView restTrocaOleo = (TextView)findViewById(R.id.restTrocaOleo);
        TextView restFluidoArref = (TextView)findViewById(R.id.restFluidoArref);
        TextView restPastFreio = (TextView)findViewById(R.id.restPastFreio);
        TextView restAlinhamento = (TextView)findViewById(R.id.restAlinhamento);
        TextView restVelas = (TextView)findViewById(R.id.restVelas);
        TextView restFiltroCombust = (TextView)findViewById(R.id.restFiltroCombust);

        if (veiculo.getHodometro() < preventiva.getFiltro_oleo()){
            restFiltroOleo.setText(String.valueOf(preventiva.getFiltro_oleo() - veiculo.getHodometro()));
        } else {
            restFiltroOleo.setText(String.valueOf(veiculo.getHodometro() % preventiva.getFiltro_oleo()));
        }

        if (veiculo.getHodometro() < preventiva.getFiltro_ar()){
            restFiltroAr.setText(String.valueOf(preventiva.getFiltro_ar() - veiculo.getHodometro()));
        } else {
            restFiltroAr.setText(String.valueOf(veiculo.getHodometro() % preventiva.getFiltro_ar()));
        }

        if (veiculo.getHodometro() < preventiva.getTroca_oleo()){
            restTrocaOleo.setText(String.valueOf(preventiva.getTroca_oleo() - veiculo.getHodometro()));
        } else {
            restTrocaOleo.setText(String.valueOf(veiculo.getHodometro() % preventiva.getTroca_oleo()));
        }

        if (veiculo.getHodometro() < preventiva.getFluido_arref()){
            restFluidoArref.setText(String.valueOf(preventiva.getFluido_arref() - veiculo.getHodometro()));
        } else {
            restFluidoArref.setText(String.valueOf(veiculo.getHodometro() % preventiva.getFluido_arref()));
        }

        if (veiculo.getHodometro() < preventiva.getPastilha_freio()){
            restPastFreio.setText(String.valueOf(preventiva.getPastilha_freio() - veiculo.getHodometro()));
        } else {
            restPastFreio.setText(String.valueOf(veiculo.getHodometro() % preventiva.getPastilha_freio()));
        }

        if (veiculo.getHodometro() < preventiva.getBalanceamento()){
            restAlinhamento.setText(String.valueOf(preventiva.getBalanceamento() - veiculo.getHodometro()));
        } else {
            restAlinhamento.setText(String.valueOf(veiculo.getHodometro() % preventiva.getBalanceamento()));
        }

        if (veiculo.getHodometro() < preventiva.getVelas()){
            restVelas.setText(String.valueOf(preventiva.getVelas() - veiculo.getHodometro()));
        } else {
            restVelas.setText(String.valueOf(veiculo.getHodometro() % preventiva.getVelas()));
        }

        if (veiculo.getHodometro() < preventiva.getFiltro_comustivel()){
            restFiltroCombust.setText(String.valueOf(preventiva.getFiltro_comustivel() - veiculo.getHodometro()));
        } else {
            restFiltroCombust.setText(String.valueOf(veiculo.getHodometro() % preventiva.getFiltro_comustivel()));
        }

    }
}
