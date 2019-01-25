package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface DAO_interface {

    void connect();

    int insert(String table, ArrayList<String> columns, ArrayList<String> values);

    ArrayList<ArrayList<String>> select(String table);

    ArrayList<ArrayList<String>> select(String table, ArrayList<String> columns, String limit);

    int update(String table, String column, String value, String limit);

    int delete(String table, String limit);

    void close();

}
