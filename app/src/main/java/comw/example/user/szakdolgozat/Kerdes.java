package comw.example.user.szakdolgozat;

public class Kerdes
{
    private String Kerdes;
    private String Valasz_A;
    private String Valasz_B;
    private String Valasz_C;
    private String Valasz_D;
    private char Helyes_valasz;

    public Kerdes(String kerdes, String valasz_A, String valasz_B, String valasz_C, String valasz_D, char helyes_valasz)
    {
        this.Kerdes = kerdes;
        this.Valasz_A = valasz_A;
        this.Valasz_B = valasz_B;
        this.Valasz_C = valasz_C;
        this.Valasz_D = valasz_D;
        this.Helyes_valasz = helyes_valasz;
    }
    public String getKerdes()
    {
        return Kerdes;
    }
    public String getValasz_A()
    {
        return Valasz_A;
    }
    public String getValasz_B()
    {
        return Valasz_B;
    }
    public String getValasz_C()
    {
        return Valasz_C;
    }
    public String getValasz_D()
    {
        return Valasz_D;
    }
    public char getHelyes_valasz()
    {
        return Helyes_valasz;
    }
}