package pojo;

public class Constant
{
    private int id;
    private String name;
    private double value;
    private int type;
    private String typeName;


    public Constant(int id, String name, double value, int type, String typeName)
    {
        this.id = id;
        this.name = name;
        this.value = value;
        this.type = type;
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
