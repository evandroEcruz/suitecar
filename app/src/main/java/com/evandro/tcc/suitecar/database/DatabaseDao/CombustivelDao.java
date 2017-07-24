package com.evandro.tcc.suitecar.database.databaseDao;

import com.evandro.tcc.suitecar.database.TableCombustivel;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by evand on 03/11/2016.
 */

public class CombustivelDao extends BaseDaoImpl<TableCombustivel, Integer> {
    public CombustivelDao (ConnectionSource conn, Class<TableCombustivel> dataClass) throws SQLException {
        super(conn, dataClass);
        setConnectionSource(conn);
        initialize();

    }
}
