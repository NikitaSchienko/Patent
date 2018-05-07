package database;

import pojo.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProcedureConstant implements Procedure<Integer,Constant>
{
    private Map<Integer, Constant> constantMap;

    public ProcedureConstant() throws SQLException
    {
        constantMap = selectAllRecords();
    }

    public Map<Integer, Constant> getMap()
    {
        return constantMap;
    }




    public Map<Integer,Constant> selectRecords(Integer type) throws SQLException
    {
        Map<Integer, Constant> typeMap = new HashMap<Integer, Constant>();

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT ID_CONSTANT, NAME_CONSTANT, VALUE_CONSTANT, NAME_TYPE FROM \"Constants\",\"Types\" WHERE TYPE_CONSTANT = ?";

        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, type);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Integer id = Integer.valueOf(rs.getString("ID_CONSTANT"));
                String name = rs.getString("NAME_CONSTANT");
                Double value = rs.getDouble("VALUE_CONSTANT");
                String nameType = rs.getString("NAME_TYPE");

                Constant constant = new Constant(id, name, value, type, nameType);

                typeMap.put(id, constant);


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

    public Map<Integer,Constant> selectAllRecords() throws SQLException
    {
        Map<Integer, Constant> typeMap = new HashMap<Integer, Constant>();

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT ID_CONSTANT, NAME_CONSTANT, VALUE_CONSTANT,TYPE_CONSTANT, NAME_TYPE " +
                "FROM \"Constants\",\"Types\" WHERE ID_TYPE = TYPE_CONSTANT";

        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            //preparedStatement.setInt(1, 1001);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Integer id = Integer.valueOf(rs.getString("ID_CONSTANT"));
                String name = rs.getString("NAME_CONSTANT");
                Double value = rs.getDouble("VALUE_CONSTANT");
                Integer type = rs.getInt("TYPE_CONSTANT");
                String typeName = rs.getString("NAME_TYPE");

                Constant constant = new Constant(id, name, value, type,typeName);

                typeMap.put(id, constant);

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



    public void updateRecord(Constant constant) throws SQLException
    {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE \"Constants\" SET NAME_CONSTANT = ?, VALUE_CONSTANT = ?, TYPE_CONSTANT = ?"
                + " WHERE ID_CONSTANT = ?";

        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, constant.getName());
            preparedStatement.setDouble(2, constant.getValue());
            preparedStatement.setInt(3, constant.getType());
            preparedStatement.setInt(4, constant.getId());

            //execute update SQL stetement
            preparedStatement.executeUpdate();

            constantMap.put(constant.getId(), constant);

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

    public void deleteRecord(Constant constant) throws SQLException
    {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String deleteTableSQL = "DELETE FROM \"Constants\" WHERE ID_CONSTANT = ?";

        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(deleteTableSQL);

            preparedStatement.setInt(1, constant.getId());

            //execute update SQL stetement
            preparedStatement.executeUpdate();

            constantMap.remove(constant.getId());

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

    public void insertRecord(Constant constant) throws SQLException
    {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO \"Constants\""
                + "(NAME_CONSTANT, VALUE_CONSTANT, TYPE_CONSTANT, ID_CONSTANT) VALUES"
                + "(?,?,?,SEQUENCE.NEXTVAL)";
        try
        {
            dbConnection = Database.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, constant.getName());
            preparedStatement.setDouble(2, constant.getValue());
            preparedStatement.setInt(3, constant.getType());
            //preparedStatement.setInt(4, constant.getId());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();


            constantMap.put(constant.getId(), constant);

            System.out.println("Record is inserted into table! "+constant.toString());

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
