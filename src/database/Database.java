package database;

import pojo.Type;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database
{
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "Patent";
    private static final String DB_PASSWORD = "patent";




    public static Connection getDBConnection()
    {
        Connection dbConnection = null;
        try
        {
            Class.forName(DB_DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
        try
        {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return dbConnection;
    }

    public static Integer newId() throws SQLException
    {
        Integer id = null;

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT SEQUENCE.NEXTVAL FROM DUAL ";

        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            id = Integer.valueOf(rs.getString("NEXTVAL"));
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (preparedStatement != null)
            {
                preparedStatement.close();
            }
            if (dbConnection != null)
            {
                dbConnection.close();
            }
        }
        return id;
    }
}
