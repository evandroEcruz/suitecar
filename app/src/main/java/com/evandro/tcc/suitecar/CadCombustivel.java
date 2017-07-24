package com.evandro.tcc.suitecar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.evandro.tcc.suitecar.database.DataBase;
import com.evandro.tcc.suitecar.database.TableCombustivel;
import com.evandro.tcc.suitecar.database.TableDadosRelat;
import com.evandro.tcc.suitecar.database.TableVeiculo;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CadCombustivel extends AppCompatActivity {

    private DataBase db;
    private TableVeiculo veiculo;
    private TableCombustivel combustivel;
    private TableDadosRelat relat;
    private List<TableCombustivel> combustivelList;
    private Duration periodo;
    private float eficiencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_combustivel);
    }

    @Override
    protected void onResume (){
        super.onResume();
        db = DataBase.getDatabase(this);

        combustivel = new TableCombustivel();
        veiculo = new TableVeiculo();
        relat = new TableDadosRelat();


        // lê a passagem de parametro da activity CadastroVeiculo
        Bundle args = getIntent().getBundleExtra("veiculo");
        combustivel.setVeiculo((TableVeiculo)args.getSerializable("veiculo"));
        veiculo = (TableVeiculo) args.getSerializable("veiculo");
        Toast.makeText(getApplicationContext(), veiculo.getNome(), Toast.LENGTH_SHORT).show();

        Button botaoSalvar = (Button) findViewById(R.id.botaoSalvarAbast);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText combHodometro = (EditText) findViewById(R.id.ComHodometro);
                EditText quantLitros = (EditText) findViewById(R.id.quantLitros);
                EditText valorAbast = (EditText) findViewById(R.id.valorAbast);

                veiculo.setHodometro(Float.parseFloat(combHodometro.getText().toString().trim()));
                combustivel.setAtual_litro(Float.parseFloat(quantLitros.getText().toString().trim()));
                combustivel.setAtual_valor(Float.parseFloat(valorAbast.getText().toString().trim()));



                veiculo.setCombustivel(Arrays.asList(new TableCombustivel(combustivel.getId_combustivel())));

                try {
                    db.getCombustivelDao().create(combustivel);
                    db.getVeiculoDao().update(veiculo);
                } catch (SQLException e) {

                    e.printStackTrace();
                }



                if (veiculo.getRelat() == null){
                    relat.setprimeiraData(DateTime.parse("2016-11-01T17:16:17.297-03:00"));
                    relat.setUltimoHodometro(veiculo.getHodometro());
                    relat.setTot_gasto(relat.getTot_gasto() + combustivel.getAtual_valor());
                    relat.setKmAbastecimento(veiculo.getHodometro() - relat.getUltimoHodometro());
                    relat.setLitroAbastecimento(combustivel.getAtual_litro());
                    veiculo.setRelat(relat);
                } else {

                    relat = veiculo.getRelat();
                    try {
                        db.getDadosRelatDao().refresh(relat);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    relat.setprimeiraData(DateTime.parse("2015-11-01T17:16:17.297-03:00"));
                    relat.setTot_gasto(relat.getTot_gasto() + combustivel.getAtual_valor());
                    periodo = new Duration(relat.getprimeiraData(), new DateTime());
                    relat.setValorDia(relat.getTot_gasto()/periodo.getStandardDays());

                    relat.setKmDia(veiculo.getHodometro() / periodo.getStandardDays());
                    if (((int)periodo.getStandardDays()/30) >= 1){
                        relat.setValorMes(relat.getTot_gasto()/((int)periodo.getStandardDays()/30));
                        relat.setKmMes(veiculo.getHodometro()/((int)periodo.getStandardDays()/30));
                    }

                    if (((int)(periodo.getStandardDays()/30)/12) >= 1){
                        relat.setValorAno(relat.getTot_gasto()/((int)(periodo.getStandardDays()/30)/12));
                        relat.setKmAno(veiculo.getHodometro()/((int)(periodo.getStandardDays()/30)/12));
                    }

                    eficiencia = (veiculo.getHodometro() - relat.getUltimoHodometro())/combustivel.getAtual_litro();

                    if (relat.getBaixa_efi() == 0){
                        relat.setBaixa_efi(eficiencia);
                    }

                    if (eficiencia < relat.getBaixa_efi()){
                        relat.setBaixa_efi(eficiencia);
                    }

                    if (eficiencia > relat.getAlta_efi()){
                        relat.setAlta_efi(eficiencia);
                    }

                        relat.setContMedia_efi((int) (relat.getContMedia_efi()+1));
                        relat.setMedia_efi(relat.getMedia_efi() + eficiencia);
                        relat.setMedia_efi(relat.getMedia_efi()/relat.getContMedia_efi());

                    relat.setKmAbastecimento(veiculo.getHodometro() - relat.getUltimoHodometro());
                    relat.setLitroAbastecimento(combustivel.getAtual_litro());
                    relat.setUltimoHodometro(veiculo.getHodometro());
                }


                try {
                    db.getDadosRelatDao().createOrUpdate(relat);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(view.getContext(), TelaInicial.class);
                Bundle args1 = new Bundle();
                args1.putSerializable("veiculo", veiculo);
                intent.putExtra ("veiculo", args1);
                startActivity(intent);
            }
        });
    }
}
