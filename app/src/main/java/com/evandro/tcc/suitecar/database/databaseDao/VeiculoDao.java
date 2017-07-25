package com.evandro.tcc.suitecar.database.databaseDao;

import com.evandro.tcc.suitecar.database.TableVeiculo;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by evand on 03/11/2016.
 */

public class VeiculoDao extends BaseDaoImpl<TableVeiculo, Integer>{
    public VeiculoDao (ConnectionSource conn, Class<TableVeiculo> dataClass) throws SQLException {
        super(conn, dataClass);
        setConnectionSource(conn);
       initialize();

    }
}
