package comw.example.user.szakdolgozat;

import android.database.Cursor;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_eddigi_jatekok extends AppCompatActivity
{
    private SwipeRefreshLayout MainActivity_6_Refreshlayout;
    private TextView MainActivity_6_Adatbazis_avatar_cimek;
    private TextView MainActivity_6_Adatbazis_felhasznalo_nevek;
    private TextView MainActivity_6_Adatbazis_eredmenyek;

    private Adatbazis_letrehozo adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eddigi_jatekok);

        Inicializalas();
        Adatbazis_frissites(); // Refreshlayout - adatbázis select
    }
    public void Inicializalas()
    {
        adatbazis = new Adatbazis_letrehozo(this);

        MainActivity_6_Refreshlayout = (SwipeRefreshLayout) findViewById(R.id.Activity_6_Refreshlayout);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.adatbazis_torles,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.Adatbazis_torles:
                int Adatbazis_eredmeny = adatbazis.Tabla_torles();
                if (Adatbazis_eredmeny > 0)
                {
                    Toast.makeText(MainActivity_eddigi_jatekok.this,"Sikeres törlés!",Toast.LENGTH_SHORT).show();
                    MainActivity_6_Adatbazis_avatar_cimek.setText("");
                    MainActivity_6_Adatbazis_felhasznalo_nevek.setText("");
                    MainActivity_6_Adatbazis_eredmenyek.setText("");
                    return true;
                }
                else if (Adatbazis_eredmeny == 0)
                {
                    Toast.makeText(MainActivity_eddigi_jatekok.this,"Az adatbázis üres!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity_eddigi_jatekok.this,"Sikertelen törlés!",Toast.LENGTH_SHORT).show();
                    return false;
                }
        }
        return super.onOptionsItemSelected(item);
    }
    public void Adatbazis_frissites()
    {
        MainActivity_6_Refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                MainActivity_6_Refreshlayout.setRefreshing(true); // true értékkel elérjük hogy frissítsen
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Adat_lekerdezes_szoveg_fuzes(); // adatbazis select
                        MainActivity_6_Refreshlayout.setRefreshing(false);
                    }
                },2000); // 2 másodpercig fut
            }
        });
    }
}