package database;

import pojo.Type;

import java.sql.SQLException;
import java.util.Map;

public interface Procedure <K,V>
{
    Map<K,V> selectAllRecords() throws SQLException;
    Map<K,V> selectRecords(Integer type) throws SQLException;
    void updateRecord(V value) throws SQLException;
    void insertRecord(V value) throws SQLException;
    void deleteRecord(V value) throws SQLException;
    Map<K, V> getMap();
}
