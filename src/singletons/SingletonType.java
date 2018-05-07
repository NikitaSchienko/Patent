package singletons;

import database.Procedure;
import database.ProcedureType;

import java.sql.SQLException;

public class SingletonType
{
    private static SingletonType instance = null;
    private Procedure procedure;

    private SingletonType()
    {
        try
        {
            procedure = new ProcedureType();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static synchronized SingletonType getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonType();
        }
        return instance;
    }

    public Procedure getProcedureType()
    {
        return procedure;
    }
}
