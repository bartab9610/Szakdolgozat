package comw.example.user.szakdolgozat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Adatbazis_letrehozo extends SQLiteOpenHelper
{
    // Adatbázis név - Tábla név - Oszlopok létrehozása
    private static String Adatbazis_nev = "Szakdolgozat"; // Adatbázis név
    private static String Tabla_nev = "Jatekosok_eredmenyei"; // Tábla név

    private static String COL_1_Eredmeny_ID = "Eredmeny_ID";
    private static String COL_2_Eredmeny_profilkep = "Eredmeny_profilkep"; // KÉP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static String COL_3_Eredmeny_felhasznalo_nev = "Eredmeny_felhasznalo_nev";
    private static String COL_4_Eredmeny_kerdes_szam = "Eredmeny_kerdes_szam";

    // Konstruktor
    public Adatbazis_letrehozo(Context context)
    {
        super(context,Adatbazis_nev,null,1);
    }
    // Adatbázis és oszlopok létrehozása
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE " + Tabla_nev + "(Eredmeny_ID INTEGER PRIMARY KEY AUTOINCREMENT," +" Eredmeny_profilkep TEXT, Eredmeny_felhasznalo_nev TEXT, Eredmeny_kerdes_szam INTEGER)");
    }
    // Ha a tábla létezik már akkor eldobja
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tabla_nev);
    }
    // Adatfelvétel
    public boolean Adat_felvetel(String profilkep, String felhasznalo_nev, int kerdes_szam)
    {
        SQLiteDatabase Adatbazis = this.getWritableDatabase();
        ContentValues Ertekek = new ContentValues();

        Ertekek.put(COL_2_Eredmeny_profilkep, profilkep);
        Ertekek.put(COL_3_Eredmeny_felhasznalo_nev, felhasznalo_nev);
        Ertekek.put(COL_4_Eredmeny_kerdes_szam, kerdes_szam);

        long eredmeny = Adatbazis.insert(Tabla_nev,null,Ertekek);
        if (eredmeny == -1)
        {
            return false; // Sikertelen adatfelvétel
        }
        else
        {
            return true; // Sikeres adatfelvétel
        }
    }
    public Cursor Adat_lekerdezes() // Itt csak lekérdezzük az adatbázisból a dolgokat de nem fűzzük hozzá egy Textview-hoz
    {
        SQLiteDatabase Adatbazis_select = this.getWritableDatabase();
        Cursor sqlparancs = Adatbazis_select.rawQuery("SELECT * FROM " + Tabla_nev,null);
        return sqlparancs;
    }
    public int Tabla_torles()
    {
        SQLiteDatabase Adatbazis_torles = this.getWritableDatabase();
        return Adatbazis_torles.delete(Tabla_nev,"1 = 1",null);
    }
    public int Adat_modositas(String felhasznalo_nev, int jo_valaszok_szama)
    {
        ContentValues cv = new ContentValues();
        cv.put(COL_4_Eredmeny_kerdes_szam,jo_valaszok_szama); // belerakom a Concentvalues-be a jó válaszok számát
        String Where_felhasznalo_nev = COL_3_Eredmeny_felhasznalo_nev + " = ?"; // lekérem az új felhasználó név értékét
        SQLiteDatabase Adatbazis_update = this.getWritableDatabase();
        return Adatbazis_update.update(Tabla_nev,cv,Where_felhasznalo_nev,new String[] {felhasznalo_nev}); // vissza adom a tábla nevet, a Stringet amiben lekértem a felhasználó nevet, és a tömböt amibe benne van a felhasznló név
    }
    public Cursor Felhasznalo_nev(String felhasznalo_nev)
    {
        SQLiteDatabase Felhasznalo_nev = this.getWritableDatabase();
        Cursor felhasznalo_nev_lekeres = Felhasznalo_nev.rawQuery("SELECT " + COL_3_Eredmeny_felhasznalo_nev + " FROM " + Tabla_nev + " WHERE " + COL_3_Eredmeny_felhasznalo_nev + " = ?",new String[]{felhasznalo_nev});
        return felhasznalo_nev_lekeres;
    }
}