package br.edu.ifsp.ifitness.model.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceSearcher {

    private static final DataSourceSearcher instance =
            new DataSourceSearcher();
    private final DataSource dataSource;

    private DataSourceSearcher() {
        try {
            Context context = new InitialContext();
            context = (Context)context.lookup("java:comp/env");
            dataSource = (DataSource)context.lookup("jdbc/IFitness");
        }catch (NamingException e) {
            throw new RuntimeException("Erro durante o lookup", e);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public static DataSourceSearcher getInstance() {
        return instance;
    }
}