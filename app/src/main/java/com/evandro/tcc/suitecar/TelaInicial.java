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
import android.widget.EditText;
import android.widget.TextView;

import com.evandro.tcc.suitecar.database.DataBase;
import com.evandro.tcc.suitecar.database.TableDadosRelat;
import com.evandro.tcc.suitecar.database.TableVeiculo;
import com.evandro.tcc.suitecar.database.databaseDao.VeiculoDao;
import com.evandro.tcc.suitecar.database.databaseDao.CombustivelDao;
import com.evandro.tcc.suitecar.database.databaseDao.PreventivaDao;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.w3c.dom.Text;

import java.sql.SQLException;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



     /*   FloatingActionButton add = (FloatingActionButton) findViewById(R.id.botaoAddAbast);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addCombustivel = new Intent(view.getContext(), CadCombustivel.class);
                startActivity(addCombustivel);
            }
        });
    */

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

        // lê a passagem de parametro da activity CadastroVeiculo
        Bundle args = getIntent().getBundleExtra("veiculo");
        veiculo = (TableVeiculo) args.getSerializable("veiculo");


        try {
            if (veiculo.getRelat() != null){
                relat = new TableDadosRelat();
                relat = veiculo.getRelat();
                db.getDadosRelatDao().refresh(relat);
                TextView autonomia = (TextView)findViewById(R.id.autonomia);
                TextView quantLitros = (TextView)findViewById(R.id.quantLitros);
                autonomia.setText(String.valueOf(" "+relat.getKmAbastecimento()+"KM "));
                quantLitros.setText(String.valueOf(" "+relat.getLitroAbastecimento()+"L "));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        TextView totHodometro = (TextView)findViewById(R.id.totHodometro);
        totHodometro.setText(String.valueOf(veiculo.getHodometro()));
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
