package com.evandro.tcc.suitecar.database.databaseDao;

import com.evandro.tcc.suitecar.database.TablePreventiva;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by evand on 03/11/2016.
 */

public class PreventivaDao extends BaseDaoImpl<TablePreventiva, Integer> {
    public PreventivaDao (ConnectionSource conn, Class<TablePreventiva> dataClass) throws SQLException {
        super(conn, dataClass);
        setConnectionSource(conn);
        initialize();

    }
}
