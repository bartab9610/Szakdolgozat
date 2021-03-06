package comw.example.user.szakdolgozat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button Button_uj_jatek;
    private Button Button_jatek_folytatas;
    private Button Button_eredmenyek;
    private Button Button_kilepes;
    private ImageButton Imagebutton_angol;
    private AlertDialog.Builder Alert_kilepes;

    private String filename = "adatok";
    private int Mentett_valaszok_szama;
    private String Mentett_felhasznalo_nev = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializalas();
        Alert_dialogbox();
        Button_uj_jatek.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Uj_jatek_ablak();
            }
        });
        Button_jatek_folytatas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences Lekeres = getSharedPreferences(filename, Context.MODE_PRIVATE);
                Mentett_valaszok_szama = Lekeres.getInt("Mentett válaszok száma",0);
                Mentett_felhasznalo_nev = Lekeres.getString("Mentett felhasználó név","");
                if (Mentett_valaszok_szama > 0 && !Mentett_felhasznalo_nev.equals(""))
                {
                    Jatek_folytatasa();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Nincsen mentett állapotod!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button_eredmenyek.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Elert_eredmenyek_ablak();
            }
        });
        Button_kilepes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Alert_kilepes.show();
            }
        });
        Imagebutton_angol.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }
    public void Inicializalas()
    {
        Button_uj_jatek = (Button) findViewById(R.id.Button_uj_jatek);
        Button_jatek_folytatas = (Button) findViewById(R.id.Button_jatek_folytatasa);
        Button_eredmenyek = (Button) findViewById(R.id.Button_eredmenyek);
        Button_kilepes = (Button) findViewById(R.id.Button_kilepes);
        Imagebutton_angol = (ImageButton) findViewById(R.id.Imagebutton_angol);
    }
    public void Uj_jatek_ablak()
    {
        Intent Uj_jatek = new Intent(MainActivity.this,MainActivity_uj_jatek.class);
        startActivity(Uj_jatek);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out); // animáció -> ablak váltásnál nem fejülre helyezi a réteget hanem x-másodperc alatt megjelenik
    }
    public void Elert_eredmenyek_ablak()
    {
        Intent Elert_eredmeny_ablak = new Intent(MainActivity.this,MainActivity_eddigi_jatekok.class);
        startActivity(Elert_eredmeny_ablak);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    public void Jatek_folytatasa()
    {
        Intent Jatek_folytatas = new Intent(MainActivity.this,MainActivity_kerdesek.class);
        startActivity(Jatek_folytatas);
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    public void Alert_dialogbox()
    {
        Alert_kilepes = new AlertDialog.Builder(MainActivity.this);
        Alert_kilepes.setTitle("Figyelem!").setMessage("Biztosan ki szeretnél lépni? El nem mentett állásod el fog veszni!")
                .setPositiveButton("Kilépés", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        finish();
                    }
                })
                .setNegativeButton("Vissza", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                })
                .setCancelable(false)
                .setIcon(R.drawable.figyelem)
                .create();
    }
}