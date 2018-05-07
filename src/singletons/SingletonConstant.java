package singletons;

import database.Procedure;
import database.ProcedureConstant;

import java.sql.SQLException;

public class SingletonConstant
{
    private static SingletonConstant instance = null;
    private Procedure procedure;

    private SingletonConstant()
    {
        try
        {
            procedure = new ProcedureConstant();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static synchronized SingletonConstant getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonConstant();
        }
        return instance;
    }

    public Procedure getProcedureConstant()
    {
        return procedure;
    }
}
