package com.evandro.tcc.suitecar.database;

/**
 * Created by evand on 23/10/2016.
 */

import android.content.Context;
import android.database.sqlite.*;

import com.evandro.tcc.suitecar.database.databaseDao.CombustivelDao;
import com.evandro.tcc.suitecar.database.databaseDao.DadosRelatDao;
import com.evandro.tcc.suitecar.database.databaseDao.PreventivaDao;
import com.evandro.tcc.suitecar.database.databaseDao.VeiculoDao;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class DataBase extends OrmLiteSqliteOpenHelper {

    public static final String TAG = "sql";
    private static final String databaseName = "suiteCar.db";
    private static DataBase db;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);
    private static final int VersaoBanco = 17;

    private VeiculoDao veiculoDao = null;
    private PreventivaDao preventivaDao = null;
    private CombustivelDao combustivelDao = null;
    private DadosRelatDao dadosRelatDao = null;

    public DataBase (Context context){

        super (context, databaseName,null, VersaoBanco);
    }

    public static synchronized DataBase getDatabase(Context context) {
        if (db == null) {
            db = new DataBase(context);
        }
        usageCounter.incrementAndGet();
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase sd, ConnectionSource conn) {

        try {
            TableUtils.createTableIfNotExists(conn, TableVeiculo.class);
            TableUtils.createTableIfNotExists(conn, TableCombustivel.class);
            TableUtils.createTableIfNotExists(conn, TablePreventiva.class);
            TableUtils.createTableIfNotExists(conn, TableDadosRelat.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sd, ConnectionSource conn, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(conn, TableVeiculo.class, false);
            TableUtils.dropTable(conn, TableCombustivel.class, false);
            TableUtils.dropTable(conn, TablePreventiva.class, false);

            onCreate(sd, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close(){
        super.close();
    }

    public VeiculoDao getVeiculoDao(){
        try{
            if (veiculoDao == null){
                veiculoDao = new VeiculoDao(connectionSource, TableVeiculo.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculoDao;
    }

    public PreventivaDao getPreventivaDao(){
        try{
            if (preventivaDao == null){
                preventivaDao = new PreventivaDao(connectionSource, TablePreventiva.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preventivaDao;
    }

    public CombustivelDao getCombustivelDao(){
        try{
            if (combustivelDao == null){
                combustivelDao = new CombustivelDao(connectionSource, TableCombustivel.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return combustivelDao;
    }

    public DadosRelatDao getDadosRelatDao(){
        try{
            if (dadosRelatDao == null){
                dadosRelatDao = new DadosRelatDao(connectionSource, TableDadosRelat.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dadosRelatDao;
    }


}
