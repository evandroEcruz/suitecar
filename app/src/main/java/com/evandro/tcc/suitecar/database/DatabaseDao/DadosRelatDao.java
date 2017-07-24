package com.evandro.tcc.suitecar.database.databaseDao;

import com.evandro.tcc.suitecar.database.TableDadosRelat;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by evand on 11/11/2016.
 */

public class DadosRelatDao extends BaseDaoImpl<TableDadosRelat, Integer> {
    public DadosRelatDao (ConnectionSource conn, Class<TableDadosRelat> dataClass) throws SQLException {
        super(conn, dataClass);
        setConnectionSource(conn);
        initialize();

    }


}
