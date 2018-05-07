package database;

import pojo.Constant;
import pojo.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProcedureType implements Procedure<Integer,Type>
{
    private Map<Integer, Type> typeMap;

    public ProcedureType() throws SQLException
    {
        typeMap = selectAllRecords();
    }

    public Map<Integer, Type> getMap()
    {
        return typeMap;
    }

    public Map<Integer,Type> selectRecords(Integer type) throws SQLException
    {
        Map<Integer, Type> typeMap = new HashMap<Integer, Type>();

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT NAME_TYPE FROM \"Types\" WHERE ID_TYPE = ?";

        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, type);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Integer id = Integer.valueOf(rs.getString("ID_TYPE"));
                String name = rs.getString("NAME_TYPE");

                typeMap.put(id, new Type(id,name));

                System.out.println("id : " + id);
                System.out.println("name : " + name);
            }
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
        return typeMap;
    }

    public Map<Integer,Type> selectAllRecords() throws SQLException
    {
        Map<Integer, Type> typeMap = new HashMap<Integer, Type>();

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT ID_TYPE, NAME_TYPE FROM \"Types\"";

        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            //preparedStatement.setInt(1, 1001);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Integer id = Integer.valueOf(rs.getString("ID_TYPE"));
                String name = rs.getString("NAME_TYPE");

                Type type = new Type(id,name);

                typeMap.put(id, type);

                System.out.println("id : " + id);
                System.out.println("name : " + name);
            }
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
        return typeMap;
    }

    public void deleteRecord(Type type) throws SQLException
    {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String deleteTableSQL = "DELETE FROM \"Types\" WHERE ID_TYPE = ?";

        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(deleteTableSQL);

            preparedStatement.setInt(1, type.getId());

            //execute update SQL stetement
            preparedStatement.executeUpdate();

            typeMap.remove(type);

            System.out.println("Record is delete");
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
    }

    public void updateRecord(Type type) throws SQLException
    {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE \"Type\" SET NAME_TYPE = ? "
                + " WHERE ID_TYPE = ?";

        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, type.getName());
            preparedStatement.setInt(2, type.getId());

            //execute update SQL stetement
            preparedStatement.executeUpdate();


            typeMap.put(type.getId(), type);

            System.out.println("Record is updated to DBUSER table!");
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
    }

    public void insertRecord(Type type) throws SQLException
    {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO \"Types\""
                + "(ID_TYPE, NAME_TYPE) VALUES"
                + "(?,?)";
        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, type.getId());
            preparedStatement.setString(2, type.getName());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();


            typeMap.put(type.getId(), type);

            System.out.println("Record is inserted into DBUSER table!");

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
    }
}
