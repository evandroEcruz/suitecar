package com.evandro.tcc.suitecar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.database.sqlite.*;
import android.widget.TextView;

import com.evandro.tcc.suitecar.database.DataBase;
import com.evandro.tcc.suitecar.database.TableCombustivel;
import com.evandro.tcc.suitecar.database.TableDadosRelat;
import com.evandro.tcc.suitecar.database.TablePreventiva;
import com.evandro.tcc.suitecar.database.TableVeiculo;
import com.evandro.tcc.suitecar.database.databaseDao.VeiculoDao;
import com.evandro.tcc.suitecar.database.databaseDao.CombustivelDao;
import com.evandro.tcc.suitecar.database.databaseDao.PreventivaDao;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.List;

public class TelaInicial extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public DataBase db;
    public TableDadosRelat relat;
    private VeiculoDao veiculoDao;
    private CombustivelDao combustivelDao;
    private PreventivaDao preventivaDao;
    private SQLiteDatabase conn;
    private DateTime date;
    private DateTime date2;
    private Period periodo;
    private Duration duration;
    private TableVeiculo veiculo;

    private String nextRevisionName = "";
    private float nextRevisionValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        db = DataBase.getDatabase(this);

        Bundle args = getIntent().getBundleExtra("veiculo");
        veiculo = (TableVeiculo) args.getSerializable("veiculo");


        try {
            TableVeiculo veiculoCarregado = db.getVeiculoDao().queryForId((int) veiculo.getId_veiculo());
            this.getNextRevision(veiculoCarregado);

            if (veiculoCarregado.getRelat() != null){
                TableDadosRelat relat = veiculo.getRelat();
                db.getDadosRelatDao().refresh(relat);
                TextView autonomia = (TextView)findViewById(R.id.autonomia);
                TextView quantLitros = (TextView)findViewById(R.id.quantLitros);
                autonomia.setText(String.valueOf("R$ "+relat.getUltimo_valor()+" "));
                quantLitros.setText(String.valueOf(" "+relat.getLitroAbastecimento()+"L "));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        TextView kmRestProxManutencao = (TextView)findViewById(R.id.kmRestProxManutencao);
        TextView itemProxManutencao = (TextView)findViewById(R.id.itemProxManutencao);
        kmRestProxManutencao.setText(String.valueOf(this.nextRevisionValue+"KM"));
        itemProxManutencao.setText(this.nextRevisionName);



        TextView totHodometro = (TextView)findViewById(R.id.totHodometro);
        totHodometro.setText(String.valueOf(veiculo.getHodometro()));
    }

    private void getNextRevision(TableVeiculo veiculo) {
        TablePreventiva preventiva = veiculo.getPreventiva();
        this.calculateValue("Filtro Óleo", veiculo, preventiva.getFiltro_oleo());
        this.calculateValue("Filtro de Ar", veiculo, preventiva.getFiltro_ar());
        this.calculateValue("Troca de Óleo", veiculo, preventiva.getTroca_oleo());
        this.calculateValue("Fluído de Arrefecimento", veiculo, preventiva.getFluido_arref());
        this.calculateValue("Pastilha de Freio", veiculo, preventiva.getPastilha_freio());
        this.calculateValue("Alin. & Balanc.", veiculo, preventiva.getBalanceamento());
        this.calculateValue("Troca de Velas", veiculo, preventiva.getVelas());
    }

    private void calculateValue(String fieldName, TableVeiculo veiculo, float preventiveValue) {
        float result = 0;
        if (preventiveValue > veiculo.getHodometro()) {
            result = preventiveValue - veiculo.getHodometro();
        } else if (veiculo.getHodometro() % preventiveValue == 0) {
            result = preventiveValue;
        } else {
            result = veiculo.getHodometro() % preventiveValue;
        }

        if (fieldName == "Filtro Óleo") {
            this.nextRevisionValue = result;
            this.nextRevisionName = fieldName;
        } else {
            if (result < this.nextRevisionValue) {
                this.nextRevisionName = fieldName;
                this.nextRevisionValue = result;
            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_inicial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bBarcadVeiculo) {
            // Handle the camera action
            Intent listaVeiculos = new Intent(this, ListaVeiculos.class);
            startActivity(listaVeiculos);
        } else if (id == R.id.bBarcombustivel) {
            Intent combustivel = new Intent(this, Combustivel.class);
            Bundle args = new Bundle();
            args.putSerializable("veiculo", veiculo);
            combustivel.putExtra ("veiculo", args);
            startActivity(combustivel);
        } else if (id == R.id.bBarpreventiva) {
            Intent preventiva = new Intent(this, Preventiva.class);
            Bundle args = new Bundle();
            args.putSerializable("veiculo", veiculo);
            preventiva.putExtra ("veiculo", args);
            startActivity(preventiva);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
