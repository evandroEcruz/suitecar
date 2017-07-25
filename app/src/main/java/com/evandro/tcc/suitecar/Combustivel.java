package com.evandro.tcc.suitecar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.evandro.tcc.suitecar.database.DataBase;
import com.evandro.tcc.suitecar.database.TableDadosRelat;
import com.evandro.tcc.suitecar.database.TableVeiculo;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.text.DecimalFormat;

public class Combustivel extends AppCompatActivity {

    private TableVeiculo veiculo;
    private TableVeiculo veiculoCash;
    private TableDadosRelat relat;
    private TableDadosRelat relatCash;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combustivel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = DataBase.getDatabase(this);

        TextView custoDia = (TextView) findViewById(R.id.custoDia);
        TextView custoMes = (TextView) findViewById(R.id.custoMes);
        TextView custoAno = (TextView) findViewById(R.id.custoAno);
        TextView totConsumo = (TextView) findViewById(R.id.totConsumo);
        TextView kmValorDia = (TextView) findViewById(R.id.kmValorDia);
        TextView kmValorMes = (TextView) findViewById(R.id.kmValorMes);
        TextView kmValorAno = (TextView) findViewById(R.id.kmValorAno);
        TextView menorConsumo = (TextView) findViewById(R.id.menorConsumo);
        TextView consumoMedio = (TextView) findViewById(R.id.consumoMedio);
        TextView maiorConsumo = (TextView) findViewById(R.id.maiorConsumo);

        try {
            Bundle args = getIntent().getBundleExtra("veiculo");
            veiculo = (TableVeiculo) args.getSerializable("veiculo");
            TableVeiculo veiculoCarregado = db.getVeiculoDao().queryForId((int) veiculo.getId_veiculo());

            TableDadosRelat relat = veiculoCarregado.getRelat();

            if (relat != null){

                DecimalFormat df = new DecimalFormat("0.0");

                custoDia.setText(String.valueOf(df.format(relat.getValorDia())));
                custoMes.setText(String.valueOf(df.format(relat.getValorMes())));
                custoAno.setText(String.valueOf(df.format(relat.getValorAno())));
                totConsumo.setText(String.valueOf(relat.getTot_gasto()));
                kmValorDia.setText(String.valueOf(df.format(relat.getKmDia())));
                kmValorMes.setText(String.valueOf(df.format(relat.getKmMes())));
                kmValorAno.setText(String.valueOf(df.format(relat.getKmAno())));
                menorConsumo.setText(String.valueOf(df.format(relat.getBaixa_efi())));
                consumoMedio.setText(String.valueOf(df.format(relat.getMedia_efi())));
                maiorConsumo.setText(String.valueOf(df.format(relat.getAlta_efi())));
            } else {
                Toast.makeText(getApplicationContext(), "Registre um abastecimento para gerar relatórios!", Toast.LENGTH_LONG).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.botaoAddAbast);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), CadCombustivel.class);
                Bundle args1 = new Bundle();
                args1.putSerializable("veiculo", veiculo);
                intent.putExtra ("veiculo", args1);
                startActivity(intent);

            }
        });
    }
}
