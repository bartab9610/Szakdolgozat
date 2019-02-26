package comw.example.user.szakdolgozat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity_kerdesek extends AppCompatActivity
{
    private ImageButton MainActivity_3_telefonos_segitseg;
    private ImageButton MainActivity_3_felezes;
    private ImageButton MainActivity_3_nezoi_segitseg;
    private ImageButton MainActivity_3_uj_kerdes;
    private TextView MainActivity_3_textview_kerdes;
    private TextView MainActivity_3_textview_visszaszamlalo;
    private Button Button_valasz_A;
    private Button Button_valasz_B;
    private Button Button_valasz_C;
    private Button Button_valasz_D;

    private String[] Tomb_Kerdes_adatok;
    private String Kerdes;
    private String Valasz_A;
    private String Valasz_B;
    private String Valasz_C;
    private String Valasz_D;
    private char Helyes_valasz_karakter;
    private String Rossz_valasz_karakterek;
    private List<Kerdes> Kerdesek_lista = new ArrayList<>();
    private Kerdes Kivalasztott_kerdes;

    private AlertDialog.Builder Alert_telefonos_segitseg;
    private AlertDialog.Builder Alert_lejart_ido;

    private CountDownTimer Visszaszamlalo;

    private static int keslelteto = 2000;

    private String filename = "adatok";
    private int adat = 0;
    private int Felezes_segitseg_szama = 0;
    private int Nezoi_segitseg_szama = 0;
    private int Uj_kerdes_segitseg_szama = 0;

    private Adatbazis_letrehozo adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kerdesek);

        Inicializalas();

        Button_A_esemeny();
        Button_B_esemeny();
        Button_C_esemeny();
        Button_D_esemeny();
        Kerdes_ido_visszaszamlalo();
        try
        {
            InputStream Kerdes_beolvasas = this.getResources().openRawResource(R.raw.szakdolgozat_kerdesek);
            BufferedReader br = new BufferedReader(new InputStreamReader(Kerdes_beolvasas));

            String Sor_vagas;
            while ((Sor_vagas = br.readLine()) != null)
            {
                // Sor_vagas = br.readLine();
                Tomb_Kerdes_adatok = Sor_vagas.split(";");
                Kerdes = Tomb_Kerdes_adatok[0];
                Valasz_A = Tomb_Kerdes_adatok[1];
                Valasz_B = Tomb_Kerdes_adatok[2];
                Valasz_C = Tomb_Kerdes_adatok[3];
                Valasz_D = Tomb_Kerdes_adatok[4];
                Helyes_valasz_karakter = Tomb_Kerdes_adatok[5].charAt(0);
                Rossz_valasz_karakterek = Tomb_Kerdes_adatok[6];

                Kerdesek_lista.add(new Kerdes(Kerdes,Valasz_A,Valasz_B,Valasz_C,Valasz_D,Helyes_valasz_karakter,Rossz_valasz_karakterek)); // feltöltjük a Kerdese_lista-t kérdésekkel
            }
            br.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity_kerdesek.this,"Hiba: " + ex,Toast.LENGTH_SHORT).show();
        }
        Random Veletlen_kerdes_kivalasztas = new Random();
        int Kerdes_index = Veletlen_kerdes_kivalasztas.nextInt(Kerdesek_lista.size());
        Kivalasztott_kerdes = Kerdesek_lista.get(Kerdes_index);
        int Kitorlendo_kerdes = Kerdes_index; // A Kerdes_index-et átadjuk egy int típusú változónak
        Kerdesek_lista.remove(Kitorlendo_kerdes); // 1. kérdésnél is már kiszedjük a listábol a Kerdes_index-et, hogy ki kérdés cserére nyomunk akkor az első kérdés ne jelenlen meg újra utána!

        // Beállítjuk a véletlen generált kérdést a megfelelő helyre
        MainActivity_3_textview_kerdes.setText(Kivalasztott_kerdes.getKerdes());
        Button_valasz_A.setText(Kivalasztott_kerdes.getValasz_A());
        Button_valasz_B.setText(Kivalasztott_kerdes.getValasz_B());
        Button_valasz_C.setText(Kivalasztott_kerdes.getValasz_C());
        Button_valasz_D.setText(Kivalasztott_kerdes.getValasz_D());
        Helyes_valasz_karakter = Kivalasztott_kerdes.getHelyes_valasz();
        Rossz_valasz_karakterek = Kivalasztott_kerdes.getRossz_valasz_karakterek();
        // Toast.makeText(MainActivity_kerdesek.this,"Kérdések száma: " + Kerdesek_lista.size(),Toast.LENGTH_SHORT).show();
        // Toast.makeText(MainActivity_kerdesek.this,"Rossz válasz karakterek: " + Rossz_valasz_karakterek,Toast.LENGTH_SHORT).show();

        // Telefonos segítség adat lekérdezése és letiltása
        SharedPreferences Telefonos_segitseg_lekeres = getSharedPreferences(filename, Context.MODE_PRIVATE);
        adat = Telefonos_segitseg_lekeres.getInt("Telefonos segítség",0);
        if (adat == 1) // megnézi hogy használtam-e már a telefonos segítséget és ha használtam akkor 1-re növeli az értéket és letiltja a funkciót
        {
            MainActivity_3_telefonos_segitseg.setBackgroundResource(R.drawable.telefonos_segitseg_tiltva_szinezett);
            MainActivity_3_telefonos_segitseg.setEnabled(false);
        }
        MainActivity_3_telefonos_segitseg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences Telefonos_segitseg_lekeres = getSharedPreferences(filename, Context.MODE_PRIVATE);
                SharedPreferences.Editor szerkeszto = Telefonos_segitseg_lekeres.edit();
                adat++;
                szerkeszto.putInt("Telefonos segítség", adat);
                szerkeszto.commit();
                MainActivity_3_telefonos_segitseg.setBackgroundResource(R.drawable.telefonos_segitseg_tiltva_szinezett);
                MainActivity_3_telefonos_segitseg.setEnabled(false);
                Alert_telefonos_segitseg = new AlertDialog.Builder(MainActivity_kerdesek.this);
                Alert_telefonos_segitseg.setTitle("Telefonos segítség!").setMessage("A helyes válasz a: " + Kivalasztott_kerdes.getHelyes_valasz())
                        .setCancelable(true) // Ne egyből írja majd ki a szöveget hanem folyamatosan
                        .setIcon(R.drawable.telefonos_segitseg)
                        .create();
                Alert_telefonos_segitseg.show();
                switch (Kivalasztott_kerdes.getHelyes_valasz())
                {
                    case 'A':
                        Button_valasz_A.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                        new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Button_valasz_A.setBackgroundResource(R.drawable.button_kerdesek_style);
                                }
                            }
                            ,keslelteto);
                        Button_A_esemeny();
                        break;
                    case 'B':
                        Button_valasz_B.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                        new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Button_valasz_B.setBackgroundResource(R.drawable.button_kerdesek_style);
                                }
                            }
                            ,keslelteto);
                        Button_B_esemeny();
                        break;
                    case 'C':
                        Button_valasz_C.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                        new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Button_valasz_C.setBackgroundResource(R.drawable.button_kerdesek_style);
                                }
                            }
                            ,keslelteto);
                        Button_C_esemeny();
                        break;
                    case 'D':
                        Button_valasz_D.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                        new Handler().postDelayed(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Button_valasz_D.setBackgroundResource(R.drawable.button_kerdesek_style);
                                }
                            }
                            ,keslelteto);
                        Button_D_esemeny();
                        break;
                }
            }
        });
        // Felezés segítség adat lekérdezése és letiltása
        SharedPreferences Felezes_segitseg_lekeres = getSharedPreferences(filename, Context.MODE_PRIVATE);
        Felezes_segitseg_szama = Felezes_segitseg_lekeres.getInt("Felezés segítség",0);
        if (Felezes_segitseg_szama == 1)
        {
            MainActivity_3_felezes.setBackgroundResource(R.drawable.felezes_tiltva_szinezett);
            MainActivity_3_felezes.setEnabled(false);
        }
        MainActivity_3_felezes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences Felezes_segitseg_lekeres = getSharedPreferences(filename, Context.MODE_PRIVATE);
                SharedPreferences.Editor szerkeszto = Felezes_segitseg_lekeres.edit();
                Felezes_segitseg_szama++; // gomblenyomásra az értéket megnöveli 1-re
                szerkeszto.putInt("Felezés segítség", Felezes_segitseg_szama); // kiírja az adatot az adatok.xml-be
                szerkeszto.commit(); // commitolja így fríssítve lesz ami alapból 0
                MainActivity_3_felezes.setBackgroundResource(R.drawable.felezes_tiltva_szinezett);
                MainActivity_3_felezes.setEnabled(false);
                Random Veletlen_rossz_karakter_kivalasztas = new Random();
                String Rossz_2_valasz_karakter = Kivalasztott_kerdes.getRossz_valasz_karakterek(); // GET-ter segítségével átadom a változónak a kérdés rossz válasz karaktereket
                String Megszerzett_rossz_karakterek = "";
                for (int i = 0; i < 2; i++)
                {
                    char Rossz_karakterek = Rossz_2_valasz_karakter.charAt(Veletlen_rossz_karakter_kivalasztas.nextInt(3)); // 3 -> mind a 3 karakteren végig megy és ezek közül választ
                    Megszerzett_rossz_karakterek += Rossz_karakterek;
                }
                // Toast.makeText(MainActivity_kerdesek.this," " + Megszerzett_rossz_karakterek,Toast.LENGTH_SHORT).show();
                switch (Megszerzett_rossz_karakterek) // KÉT UGYANOLYAN KARAKTER MÉG BENNE VAN!!!!!!!!!!!!!!!!!!!!!!!!!!
                {
                    case "AB": // Ha "AB" igaz akkor lefut de ha "BA" is igaz akkor is lefut
                        case "BA":
                            Button_valasz_A.setText("");
                            Button_valasz_B.setText("");
                            Button_valasz_A.setEnabled(false);
                            Button_valasz_B.setEnabled(false);
                            break;
                    case "AC":
                        case "CA":
                            Button_valasz_A.setText("");
                            Button_valasz_C.setText("");
                            Button_valasz_A.setEnabled(false);
                            Button_valasz_C.setEnabled(false);
                            break;
                    case "AD":
                        case "DA":
                            Button_valasz_A.setText("");
                            Button_valasz_D.setText("");
                            Button_valasz_A.setEnabled(false);
                            Button_valasz_D.setEnabled(false);
                            break;
                    case "BC":
                        case "CB":
                            Button_valasz_B.setText("");
                            Button_valasz_C.setText("");
                            Button_valasz_B.setEnabled(false);
                            Button_valasz_C.setEnabled(false);
                            break;
                    case "BD":
                        case "DB":
                            Button_valasz_B.setText("");
                            Button_valasz_D.setText("");
                            Button_valasz_B.setEnabled(false);
                            Button_valasz_D.setEnabled(false);
                            break;
                    case "CD":
                        case "DC":
                            Button_valasz_C.setText("");
                            Button_valasz_D.setText("");
                            Button_valasz_C.setEnabled(false);
                            Button_valasz_D.setEnabled(false);
                            break;
                }
            }
        });
        // Nézői segítség adat lekérdezése és letiltása
        SharedPreferences Nezoi_segitseg_lekeres = getSharedPreferences(filename, Context.MODE_PRIVATE);
        Nezoi_segitseg_szama = Nezoi_segitseg_lekeres.getInt("Nézői segítség",0);
        if (Nezoi_segitseg_szama == 1)
        {
            MainActivity_3_nezoi_segitseg.setBackgroundResource(R.drawable.nezoi_segitseg_tiltas);
            MainActivity_3_nezoi_segitseg.setEnabled(false);
        }
        MainActivity_3_nezoi_segitseg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                MainActivity_3_nezoi_segitseg.setBackgroundResource(R.drawable.nezoi_segitseg_tiltas);
                MainActivity_3_nezoi_segitseg.setEnabled(false);
                SharedPreferences Nezoi_segitseg_lekeres = getSharedPreferences(filename, Context.MODE_PRIVATE);
                SharedPreferences.Editor szerkeszto = Nezoi_segitseg_lekeres.edit();
                Nezoi_segitseg_szama++; // gomblenyomásra az értéket megnöveli 1-re
                szerkeszto.putInt("Nézői segítség", Nezoi_segitseg_szama); // kiírja az adatot az adatok.xml-be
                szerkeszto.commit(); // commitolja így fríssítve lesz ami alapból 0
                // IDE JÖN A DINAMIKUS LINEARLAYOUT ÉS ABBA BELE A GRAFIKON!!!!
            }
        });
        // Új kérdés segítség adat lekérdezése és letiltása
        SharedPreferences Uj_kerdes_segitseg = getSharedPreferences(filename, Context.MODE_PRIVATE);
        Uj_kerdes_segitseg_szama = Uj_kerdes_segitseg.getInt("Új kérdés segítség",0);
        if (Uj_kerdes_segitseg_szama == 1)
        {
            MainActivity_3_uj_kerdes.setBackgroundResource(R.drawable.ujkerdes_tiltott);
            MainActivity_3_uj_kerdes.setEnabled(false);
        }
        MainActivity_3_uj_kerdes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                MainActivity_3_uj_kerdes.setBackgroundResource(R.drawable.ujkerdes_tiltott);
                MainActivity_3_uj_kerdes.setEnabled(false);
                Uj_kerdes_segitseg_szama++;
                SharedPreferences Uj_kerdes_segitseg = getSharedPreferences(filename, Context.MODE_PRIVATE);
                SharedPreferences.Editor szerkeszto = Uj_kerdes_segitseg.edit();
                szerkeszto.putInt("Új kérdés segítség", Uj_kerdes_segitseg_szama);
                szerkeszto.commit(); // felülírja az értéket

                Random Veletlen_kerdes_kivalasztas = new Random();
                int Kerdes_index = Veletlen_kerdes_kivalasztas.nextInt(Kerdesek_lista.size());
                Kivalasztott_kerdes = Kerdesek_lista.get(Kerdes_index);
                int Kitorlendo_kerdes = Kerdes_index; // gomb
                Kerdesek_lista.remove(Kitorlendo_kerdes);

                // Beállítjuk a véletlen generált kérdést a megfelelő helyre
                MainActivity_3_textview_kerdes.setText(Kivalasztott_kerdes.getKerdes());
                Button_valasz_A.setText(Kivalasztott_kerdes.getValasz_A());
                Button_valasz_B.setText(Kivalasztott_kerdes.getValasz_B());
                Button_valasz_C.setText(Kivalasztott_kerdes.getValasz_C());
                Button_valasz_D.setText(Kivalasztott_kerdes.getValasz_D());
                Helyes_valasz_karakter = Kivalasztott_kerdes.getHelyes_valasz();
                Rossz_valasz_karakterek = Kivalasztott_kerdes.getRossz_valasz_karakterek();
            }
        });
    }
    public void Inicializalas()
    {
        MainActivity_3_telefonos_segitseg = (ImageButton) findViewById(R.id.Activity_3_imagebutton_telefonon_segitseg);
        MainActivity_3_felezes = (ImageButton) findViewById(R.id.Activity_3_imagebutton_felezes);
        MainActivity_3_nezoi_segitseg = (ImageButton) findViewById(R.id.Activity_3_imagebutton_nezoi_segitseg);
        MainActivity_3_uj_kerdes = (ImageButton) findViewById(R.id.Activity_3_imagebutton_uj_kerdes);
        MainActivity_3_textview_kerdes = (TextView) findViewById(R.id.Activity_3_textview_kerdesek);
        MainActivity_3_textview_visszaszamlalo = (TextView) findViewById(R.id.Activity_3_textview_visszaszamlalo);
        Button_valasz_A = (Button) findViewById(R.id.Activity_3_button_valasz_A);
        Button_valasz_B = (Button) findViewById(R.id.Activity_3_button_valasz_B);
        Button_valasz_C = (Button) findViewById(R.id.Activity_3_button_valasz_C);
        Button_valasz_D = (Button) findViewById(R.id.Activity_3_button_valasz_D);

        adatbazis = new Adatbazis_letrehozo(this);
    }
    public void Button_A_esemeny()
    {
        Button_valasz_A.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Button_valasz_A.setEnabled(false);
                Button_valasz_B.setEnabled(false);
                Button_valasz_C.setEnabled(false);
                Button_valasz_D.setEnabled(false);
                Button_valasz_A.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Visszaszamlalo.cancel(); // leállítja a gomb lenyomására az időt így nem pörög továbbí

                if ('A' == Helyes_valasz_karakter)
                {
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences mentes = getSharedPreferences(filename, Context.MODE_PRIVATE);
                            SharedPreferences.Editor szerkeszto = mentes.edit();
                            int adat = mentes.getInt("Jó válaszok száma",0);
                            String felhasznalo_nev = mentes.getString("Felhasználó név", "");
                            @Override
                            public void run()
                            {
                                adat++;
                                szerkeszto.putInt("Jó válaszok száma",adat);
                                szerkeszto.commit();
                                adatbazis.Adat_modositas(felhasznalo_nev,adat); // paraméterként átadomn a lekérdezett felhasználó nevet és és a jó válaszok számát
                                Button_valasz_A.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                // Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                                Eredmenyek_oldal();
                            }
                        }
                        ,keslelteto);
                }
                else
                {
                    Button_valasz_A.setEnabled(false);
                    Button_valasz_B.setEnabled(false);
                    Button_valasz_C.setEnabled(false);
                    Button_valasz_D.setEnabled(false);
                    Button_valasz_A.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences Jo_valasz_voltE = getSharedPreferences(filename,Context.MODE_PRIVATE); // SharefPref-ből lekérdezem a "Jó válasz volt-e" értéket
                            SharedPreferences.Editor szerkeszto = Jo_valasz_voltE.edit(); // létrehozok egy editort hogy tudjam szerkezteni gombnyomásra
                            String Eredmeny = Jo_valasz_voltE.getString("Jó válasz volt-e",""); // a "Jó válasz volt-e" értékét belerakom egy String változóba
                            @Override
                            public void run()
                            {
                                Eredmeny = "Hibás válasz"; // rossz válasz esetén az elmentett változó értéket átírja "Hibás válasz"-ra
                                szerkeszto.putString("Jó válasz volt-e", Eredmeny); // putstring-vel és commit-val pedig frissítjük az értéket (MainActivity_eredmenytable osztályban minden kérdésnél lekérdezi hogy "Jó válasz" vagy "Hibás válasz"-e a String értéke
                                szerkeszto.commit();
                                Button_valasz_A.setBackgroundResource(R.drawable.gomb_hibas_valasz_style);
                                switch (Helyes_valasz_karakter)
                                {
                                    case 'A': Button_valasz_A.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'B': Button_valasz_B.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'C': Button_valasz_C.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'D': Button_valasz_D.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                }
                                // Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
                                Eredmenyek_oldal();
                            }
                        }
                        ,keslelteto);
                }
            }
        });
    }
    public void Button_B_esemeny()
    {
        Button_valasz_B.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Button_valasz_A.setEnabled(false);
                Button_valasz_B.setEnabled(false);
                Button_valasz_C.setEnabled(false);
                Button_valasz_D.setEnabled(false);
                Button_valasz_B.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Visszaszamlalo.cancel(); // leállítja a gomb lenyomására az időt így nem pörög továbbí

                if ('B' == Helyes_valasz_karakter)
                {
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences mentes = getSharedPreferences(filename, Context.MODE_PRIVATE);
                            SharedPreferences.Editor szerkeszto = mentes.edit();
                            int adat = mentes.getInt("Jó válaszok száma",0);
                            String felhasznalo_nev = mentes.getString("Felhasználó név", "");
                            @Override
                            public void run()
                            {
                                adat++;
                                szerkeszto.putInt("Jó válaszok száma",adat);
                                szerkeszto.commit();
                                adatbazis.Adat_modositas(felhasznalo_nev,adat); // paraméterként átadomn a lekérdezett felhasználó nevet és és a jó válaszok számát
                                Button_valasz_B.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                // Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                                Eredmenyek_oldal();
                            }
                        }
                        ,keslelteto);
                }
                else
                {
                    Button_valasz_A.setEnabled(false);
                    Button_valasz_B.setEnabled(false);
                    Button_valasz_C.setEnabled(false);
                    Button_valasz_D.setEnabled(false);
                    Button_valasz_B.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences Jo_valasz_voltE = getSharedPreferences(filename,Context.MODE_PRIVATE); // SharefPref-ből lekérdezem a "Jó válasz volt-e" értéket
                            SharedPreferences.Editor szerkeszto = Jo_valasz_voltE.edit(); // létrehozok egy editort hogy tudjam szerkezteni gombnyomásra
                            String Eredmeny = Jo_valasz_voltE.getString("Jó válasz volt-e",""); // a "Jó válasz volt-e" értékét belerakom egy String változóba
                            @Override
                            public void run()
                            {
                                Eredmeny = "Hibás válasz"; // rossz válasz esetén az elmentett változó értéket átírja "Hibás válasz"-ra
                                szerkeszto.putString("Jó válasz volt-e", Eredmeny); // putstring-vel és commit-val pedig frissítjük az értéket (MainActivity_eredmenytable osztályban minden kérdésnél lekérdezi hogy "Jó válasz" vagy "Hibás válasz"-e a String értéke
                                szerkeszto.commit();
                                Button_valasz_B.setBackgroundResource(R.drawable.gomb_hibas_valasz_style);
                                switch (Helyes_valasz_karakter)
                                {
                                    case 'A': Button_valasz_A.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'B': Button_valasz_B.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'C': Button_valasz_C.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'D': Button_valasz_D.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                }
                                // Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
                                Eredmenyek_oldal();
                            }
                        }
                        ,keslelteto);
                }
            }
        });
    }
    public void Button_C_esemeny()
    {
        Button_valasz_C.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Button_valasz_A.setEnabled(false);
                Button_valasz_B.setEnabled(false);
                Button_valasz_C.setEnabled(false);
                Button_valasz_D.setEnabled(false);
                Button_valasz_C.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Visszaszamlalo.cancel(); // leállítja a gomb lenyomására az időt így nem pörög továbbí

                if ('C' == Helyes_valasz_karakter)
                {
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences mentes = getSharedPreferences(filename, Context.MODE_PRIVATE);
                            SharedPreferences.Editor szerkeszto = mentes.edit();
                            int adat = mentes.getInt("Jó válaszok száma",0);
                            String felhasznalo_nev = mentes.getString("Felhasználó név", "");
                            @Override
                            public void run()
                            {
                                adat++;
                                szerkeszto.putInt("Jó válaszok száma",adat);
                                szerkeszto.commit();
                                adatbazis.Adat_modositas(felhasznalo_nev,adat); // paraméterként átadomn a lekérdezett felhasználó nevet és és a jó válaszok számát
                                Button_valasz_C.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                // Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                                Eredmenyek_oldal();
                            }
                        }
                        ,keslelteto);
                }
                else
                {
                    Button_valasz_A.setEnabled(false);
                    Button_valasz_B.setEnabled(false);
                    Button_valasz_C.setEnabled(false);
                    Button_valasz_D.setEnabled(false);
                    Button_valasz_C.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences Jo_valasz_voltE = getSharedPreferences(filename,Context.MODE_PRIVATE); // SharefPref-ből lekérdezem a "Jó válasz volt-e" értéket
                            SharedPreferences.Editor szerkeszto = Jo_valasz_voltE.edit(); // létrehozok egy editort hogy tudjam szerkezteni gombnyomásra
                            String Eredmeny = Jo_valasz_voltE.getString("Jó válasz volt-e",""); // a "Jó válasz volt-e" értékét belerakom egy String változóba
                            @Override
                            public void run()
                            {
                                Eredmeny = "Hibás válasz"; // rossz válasz esetén az elmentett változó értéket átírja "Hibás válasz"-ra
                                szerkeszto.putString("Jó válasz volt-e", Eredmeny); // putstring-vel és commit-val pedig frissítjük az értéket (MainActivity_eredmenytable osztályban minden kérdésnél lekérdezi hogy "Jó válasz" vagy "Hibás válasz"-e a String értéke
                                szerkeszto.commit();
                                Button_valasz_C.setBackgroundResource(R.drawable.gomb_hibas_valasz_style);
                                switch (Helyes_valasz_karakter)
                                {
                                    case 'A': Button_valasz_A.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'B': Button_valasz_B.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'C': Button_valasz_C.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'D': Button_valasz_D.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                }
                                // Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
                                Eredmenyek_oldal();
                            }
                        }
                        ,keslelteto);
                }
            }
        });
    }
    public void Button_D_esemeny()
    {
        Button_valasz_D.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Button_valasz_A.setEnabled(false);
                Button_valasz_B.setEnabled(false);
                Button_valasz_C.setEnabled(false);
                Button_valasz_D.setEnabled(false);
                Button_valasz_D.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Visszaszamlalo.cancel(); // leállítja a gomb lenyomására az időt így nem pörög továbbí

                if ('D' == Helyes_valasz_karakter)
                {
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences mentes = getSharedPreferences(filename, Context.MODE_PRIVATE);
                            SharedPreferences.Editor szerkeszto = mentes.edit();
                            int adat = mentes.getInt("Jó válaszok száma",0);
                            String felhasznalo_nev = mentes.getString("Felhasználó név", "");
                            @Override
                            public void run()
                            {
                                adat++;
                                szerkeszto.putInt("Jó válaszok száma",adat);
                                szerkeszto.commit();
                                adatbazis.Adat_modositas(felhasznalo_nev,adat);
                                Button_valasz_D.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                // Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                                Eredmenyek_oldal();
                            }
                        }
                        ,keslelteto);
                }
                else
                {
                    Button_valasz_A.setEnabled(false);
                    Button_valasz_B.setEnabled(false);
                    Button_valasz_C.setEnabled(false);
                    Button_valasz_D.setEnabled(false);
                    Button_valasz_D.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences Jo_valasz_voltE = getSharedPreferences(filename,Context.MODE_PRIVATE); // SharefPref-ből lekérdezem a "Jó válasz volt-e" értéket
                            SharedPreferences.Editor szerkeszto = Jo_valasz_voltE.edit(); // létrehozok egy editort hogy tudjam szerkezteni gombnyomásra
                            String Eredmeny = Jo_valasz_voltE.getString("Jó válasz volt-e",""); // a "Jó válasz volt-e" értékét belerakom egy String változóba
                             @Override
                            public void run()
                            {
                                Eredmeny = "Hibás válasz"; // rossz válasz esetén az elmentett változó értéket átírja "Hibás válasz"-ra
                                szerkeszto.putString("Jó válasz volt-e", Eredmeny); // putstring-vel és commit-val pedig frissítjük az értéket (MainActivity_eredmenytable osztályban minden kérdésnél lekérdezi hogy "Jó válasz" vagy "Hibás válasz"-e a String értéke
                                szerkeszto.commit();
                                Button_valasz_D.setBackgroundResource(R.drawable.gomb_hibas_valasz_style);
                                switch (Helyes_valasz_karakter)
                                {
                                    case 'A': Button_valasz_A.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'B': Button_valasz_B.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'C': Button_valasz_C.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                    case 'D': Button_valasz_D.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                        break;
                                }
                                // Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
                                Eredmenyek_oldal();
                            }
                        }
                        ,keslelteto);
                }
            }
        });
    }
    public void Eredmenyek_oldal()
    {
        Intent Elnyert_osszeg = new Intent(MainActivity_kerdesek.this,MainActivity_eredmenytabla.class);
        startActivity(Elnyert_osszeg);
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    public void Kerdes_ido_visszaszamlalo()
    {
        Visszaszamlalo = new CountDownTimer (62000,1000) // 90000 -> 1:30perc || 91000 hogy 1:30-ről induljon a visszaszámlálás
        {
            @Override
            public void onTick(long l)
            {
                if (l / 1000 > 60)
                {
                    String hatralevo_id = String.format("1:%02d",(l / 1000) - 61); // 60 helyett 61 hogy 1:01 után ne 0:59 legyen hanem 1:00
                    MainActivity_3_textview_visszaszamlalo.setText(hatralevo_id);
                }
                else
                {
                    String hatralevo_id= String.format("0:%02d",(l / 1000) -1);
                    MainActivity_3_textview_visszaszamlalo.setText(hatralevo_id);
                }
            }
            @Override
            public void onFinish()
            {
                MainActivity_3_textview_visszaszamlalo.setText("0:00");
                Alert_lejart_ido = new AlertDialog.Builder(MainActivity_kerdesek.this);
                Alert_lejart_ido.setTitle("Az idő lejárt!").setMessage("Nem tudtál 1 percen belül válaszolni a kérdésre, így a játéknak vége")
                        .setPositiveButton("Kilépés", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                finish(); // nem zárja be az egész alkalmazást hanem vissza dobja a MainActivity_uj_jatek-ra!!!!!!!!!!!!!!!
                            }
                        })
                        .setNegativeButton("Új játék indítása", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                Uj_jatek_kezdese();
                            }
                        })
                        .setCancelable(false) // Ne egyből írja majd ki a szöveget hanem folyamatosan
                        .setIcon(R.drawable.figyelem) // másik kép!!
                        .create();
                Alert_lejart_ido.show();
            }
        }.start();
    }
    public void Uj_jatek_kezdese()
    {
        Intent Uj_jatek = new Intent(MainActivity_kerdesek.this, MainActivity_uj_jatek.class);
        startActivity(Uj_jatek);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}