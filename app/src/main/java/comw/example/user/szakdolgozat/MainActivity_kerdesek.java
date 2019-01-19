package comw.example.user.szakdolgozat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
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
    private ImageButton MainActivity_3_plusz_egy_perc;
    private TextView Activity_3_textview_kerdes;
    private TextView Activity_3_textview_visszaszamlalo;
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
    private List<Kerdes> Kerdesek_lista = new ArrayList<>();
    private Kerdes Kivalasztott_kerdes;

    private static int keslelteto = 2000;

    private String filename = "adatok";

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

                Sor_vagas = br.readLine();
                Tomb_Kerdes_adatok = Sor_vagas.split(";");
                Kerdes = Tomb_Kerdes_adatok[0];
                Valasz_A = Tomb_Kerdes_adatok[1];
                Valasz_B = Tomb_Kerdes_adatok[2];
                Valasz_C = Tomb_Kerdes_adatok[3];
                Valasz_D = Tomb_Kerdes_adatok[4];
                Helyes_valasz_karakter = Tomb_Kerdes_adatok[5].charAt(0);

                Kerdesek_lista.add(new Kerdes(Kerdes,Valasz_A,Valasz_B,Valasz_C,Valasz_D,Helyes_valasz_karakter)); // feltöltjük a Kerdese_lista-t kérdésekkel
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

        // Beállítjuk a véletlen generált kérdést a megfelelő helyre
        Activity_3_textview_kerdes.setText(Kivalasztott_kerdes.getKerdes());
        Button_valasz_A.setText(Kivalasztott_kerdes.getValasz_A());
        Button_valasz_B.setText(Kivalasztott_kerdes.getValasz_B());
        Button_valasz_C.setText(Kivalasztott_kerdes.getValasz_C());
        Button_valasz_D.setText(Kivalasztott_kerdes.getValasz_D());
        Helyes_valasz_karakter = Kivalasztott_kerdes.getHelyes_valasz();

        MainActivity_3_telefonos_segitseg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                MainActivity_3_telefonos_segitseg.setBackgroundResource(R.drawable.telefonos_segitseg_tiltva);
            }
        });
    }
    public void Inicializalas()
    {
        MainActivity_3_telefonos_segitseg = (ImageButton) findViewById(R.id.Activity_3_imagebutton_telefonon_segitseg);
        MainActivity_3_felezes = (ImageButton) findViewById(R.id.Activity_3_imagebutton_felezes);
        MainActivity_3_nezoi_segitseg = (ImageButton) findViewById(R.id.Activity_3_imagebutton_nezoi_segitseg);
        MainActivity_3_plusz_egy_perc = (ImageButton) findViewById(R.id.Activity_3_imagebutton_plusz_perc);
        Activity_3_textview_kerdes = (TextView) findViewById(R.id.Activity_3_textview_kerdesek);
        Activity_3_textview_visszaszamlalo = (TextView) findViewById(R.id.Activity_3_textview_visszaszamlalo);
        Button_valasz_A = (Button) findViewById(R.id.Activity_3_button_valasz_A);
        Button_valasz_B = (Button) findViewById(R.id.Activity_3_button_valasz_B);
        Button_valasz_C = (Button) findViewById(R.id.Activity_3_button_valasz_C);
        Button_valasz_D = (Button) findViewById(R.id.Activity_3_button_valasz_D);
    }
    public void Button_A_esemeny()
    {
        Button_valasz_A.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if ('A' == Helyes_valasz_karakter)
                {
                    Button_valasz_A.setEnabled(false);
                    Button_valasz_B.setEnabled(false);
                    Button_valasz_C.setEnabled(false);
                    Button_valasz_D.setEnabled(false);
                    Button_valasz_A.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences mentes = getSharedPreferences(filename, Context.MODE_PRIVATE);
                            SharedPreferences.Editor szerkeszto = mentes.edit();
                            int adat = mentes.getInt("teszt",0);
                            @Override
                            public void run()
                            {
                                adat++;
                                szerkeszto.putInt("teszt",adat);
                                szerkeszto.commit();
                                Button_valasz_A.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
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
                            @Override
                            public void run()
                            {
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
                                Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
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
                if ('B' == Helyes_valasz_karakter)
                {
                    Button_valasz_B.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences mentes = getSharedPreferences(filename, Context.MODE_PRIVATE);
                            SharedPreferences.Editor szerkeszto = mentes.edit();
                            int adat = mentes.getInt("teszt",0);
                            @Override
                            public void run()
                            {
                                adat++;
                                szerkeszto.putInt("teszt",adat);
                                szerkeszto.commit();
                                Button_valasz_B.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz! " + adat, Toast.LENGTH_SHORT).show();
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
                            @Override
                            public void run()
                            {
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
                                Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
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
                if ('C' == Helyes_valasz_karakter)
                {
                    Button_valasz_C.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences mentes = getSharedPreferences(filename, Context.MODE_PRIVATE);
                            SharedPreferences.Editor szerkeszto = mentes.edit();
                            int adat = mentes.getInt("teszt",0);
                            @Override
                            public void run()
                            {
                                adat++;
                                szerkeszto.putInt("teszt",adat);
                                szerkeszto.commit();
                                Button_valasz_C.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
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
                            @Override
                            public void run()
                            {
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
                                Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
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
                if ('D' == Helyes_valasz_karakter)
                {
                    Button_valasz_D.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            SharedPreferences mentes = getSharedPreferences(filename, Context.MODE_PRIVATE);
                            SharedPreferences.Editor szerkeszto = mentes.edit();
                            int adat = mentes.getInt("teszt",0);
                            @Override
                            public void run()
                            {
                                adat++;
                                szerkeszto.putInt("teszt",adat);
                                szerkeszto.commit();
                                Button_valasz_D.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
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
                             @Override
                            public void run()
                            {
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
                                Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
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
        new CountDownTimer(90000,1000) // 90000 -> 1:30perc
        {
            @Override
            public void onTick(long l)
            {
                if (l / 1000 > 60)
                {
                    String szoveg = String.format("1:%02d",(l / 1000) - 60);
                    Activity_3_textview_visszaszamlalo.setText(szoveg);
                }
                else
                {
                    String szoveg = String.format("0:%02d",(l / 1000) -1);
                    Activity_3_textview_visszaszamlalo.setText(szoveg);
                }
            }
            @Override
            public void onFinish()
            {
                Activity_3_textview_visszaszamlalo.setText("0:00");
            }
        }.start();
    }
}