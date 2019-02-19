package comw.example.user.szakdolgozat;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_eddigi_jatekok extends AppCompatActivity
{
    private TextView MainActivity_6_Adatbazis_avatar_cimek;
    private TextView MainActivity_6_Adatbazis_felhasznalo_nevek;
    private TextView MainActivity_6_Adatbazis_eredmenyek;
    
    private Adatbazis_letrehozo adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eddigi_jatekok);

        Inicializalas();
        Adat_lekerdezes_szoveg_fuzes(); // adatbazis select
    }
    public void Inicializalas()
    {
        adatbazis = new Adatbazis_letrehozo(this);

        MainActivity_6_Adatbazis_avatar_cimek = (TextView) findViewById(R.id.Activity_6_Tablelayout_avatar_nev);
        MainActivity_6_Adatbazis_felhasznalo_nevek = (TextView) findViewById(R.id.Activity_6_Tablelayout_felhasznalo_nev);
        MainActivity_6_Adatbazis_eredmenyek = (TextView) findViewById(R.id.Activity_6_Tablelayout_eredmenyek);
    }
    public void Adat_lekerdezes_szoveg_fuzes()
    {
        Cursor Eredmeny = adatbazis.Adat_lekerdezes();
        StringBuffer Stringfuzes_avatar_nev = new StringBuffer();
        StringBuffer Stringfuzes_felhasznalo_nev = new StringBuffer();
        StringBuffer Stringfuzes_eredmeny = new StringBuffer();
        if (Eredmeny != null && Eredmeny.getCount() > 0)
        {
            while (Eredmeny.moveToNext())
            {
                Stringfuzes_avatar_nev.append(Eredmeny.getString(1) + "\n");
                Stringfuzes_felhasznalo_nev.append(Eredmeny.getString(2) + "\n");
                Stringfuzes_eredmeny.append("13/" + Eredmeny.getInt(3) + "\n");
            }
            Toast.makeText(MainActivity_eddigi_jatekok.this,"Sikeres adatlekérdezés!",Toast.LENGTH_SHORT).show();
            MainActivity_6_Adatbazis_avatar_cimek.setText(Stringfuzes_avatar_nev.toString());
            MainActivity_6_Adatbazis_felhasznalo_nevek.setText(Stringfuzes_felhasznalo_nev.toString());
            MainActivity_6_Adatbazis_eredmenyek.setText(Stringfuzes_eredmeny.toString());
        }
        else
        {
            Toast.makeText(MainActivity_eddigi_jatekok.this,"Nincsen az adatbázisban befejezett játék!",Toast.LENGTH_SHORT).show();
        }
    }
}