package comw.example.user.szakdolgozat;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
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

    private static int keslelteto = 2000;

    private CountDownTimer Visszaszamlalo;
    private long Visszaszamlalo_ido = 90000;

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
        /*
        String proba_kerdes = "Mennyi 1+1?;1;2;3;4;B";
        String[] tomb = proba_kerdes.split(";");
        String rendes_kerdes = tomb[0];
        String rendes_a_valasz = tomb[1];
        String rendes_b_valasz = tomb[2];
        String rendes_c_valasz= tomb[3];
        String rendes_d_valasz= tomb[4];
        final String helyes_valasz = tomb[5];
        Activity_3_textview_kerdes.setText(rendes_kerdes);
        Button_valasz_A.setText(rendes_a_valasz);
        Button_valasz_B.setText(rendes_b_valasz);
        Button_valasz_C.setText(rendes_c_valasz);
        Button_valasz_D.setText(rendes_d_valasz);
        */
        try
        {
            InputStream Kerdes_beolvasas = this.getResources().openRawResource(R.raw.fajlbeolvasas_kerdesek);
            BufferedReader br = new BufferedReader(new InputStreamReader(Kerdes_beolvasas ));

            String Sor_vagas;while ((Sor_vagas = br.readLine()) != null)
        {

                Sor_vagas = br.readLine();
                Tomb_Kerdes_adatok = Sor_vagas.split(";");
                Kerdes = Tomb_Kerdes_adatok[0];
                Valasz_A = Tomb_Kerdes_adatok[1];
                Valasz_B = Tomb_Kerdes_adatok[2];
                Valasz_C = Tomb_Kerdes_adatok[3];
                Valasz_D = Tomb_Kerdes_adatok[4];
                Helyes_valasz_karakter = Tomb_Kerdes_adatok[5].charAt(0);
            }
            br.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity_kerdesek.this,"Hiba: " + ex,Toast.LENGTH_SHORT).show();
        }
        Activity_3_textview_kerdes.setText(Kerdes);
        Button_valasz_A.setText(Valasz_A);
        Button_valasz_B.setText(Valasz_B);
        Button_valasz_C.setText(Valasz_C);
        Button_valasz_D.setText(Valasz_D);

        /*Activity_3_textview_kerdes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                for (int i = 0; i < Kerdesek.length; i++)
                {
                    Random veletlen_kerdes = new Random();
                    int kerdesek_id = veletlen_kerdes.nextInt(Kerdesek.length);
                    Activity_3_textview_kerdes.setText(getResources().getString(Kerdesek[i]));
                }
            }
        });*/
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
                    Button_valasz_A.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Button_valasz_A.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        ,keslelteto);
                }
                else
                {
                    Button_valasz_A.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Button_valasz_A.setBackgroundResource(R.drawable.gomb_hibas_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
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
                if ('B' == Helyes_valasz_karakter)
                {
                    Button_valasz_B.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    //  Kivalasztott_valasz_valtas();
                    new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Button_valasz_B.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                                Eredmenyek_oldal();
                            }
                        }
                        ,keslelteto);
                }
                else
                {
                    Button_valasz_B.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Button_valasz_B.setBackgroundResource(R.drawable.gomb_hibas_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
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
                if ('C' == Helyes_valasz_karakter)
                {
                    Button_valasz_C.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Button_valasz_C.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        ,keslelteto);
                }
                else
                {
                    Button_valasz_C.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Button_valasz_C.setBackgroundResource(R.drawable.gomb_hibas_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
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
                if ('D' == Helyes_valasz_karakter)
                {
                    Button_valasz_D.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Button_valasz_D.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        ,keslelteto);
                }
                else
                {
                    Button_valasz_D.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                    new Handler().postDelayed(new Runnable()
                        {
                             @Override
                            public void run()
                            {
                                Button_valasz_D.setBackgroundResource(R.drawable.gomb_hibas_valasz_style);
                                Toast.makeText(MainActivity_kerdesek.this, "Hibás válasz!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        ,keslelteto);
                }
            }
        });
    }
    public void Eredmenyek_oldal()
    {
        Intent Jelenlegi_eredmenyek_megjelenitese = new Intent(MainActivity_kerdesek.this,MainActivity_eredmenytabla.class);
        startActivity(Jelenlegi_eredmenyek_megjelenitese);
        finish();
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
    /*
    public void Kivalasztott_valasz_valtas()
    {
        new CountDownTimer(2000,500)
        {
            @Override
            public void onTick(long l)
            {
                long seged = l/1000;
                switch((int)seged)
                {
                    case 5: Button_valasz_B.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style); break;
                    case 4: Button_valasz_B.setBackgroundResource(R.drawable.button_kerdesek_style); break;
                    case 4: Button_valasz_B.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style); break;
                    case 3: Button_valasz_B.setBackgroundResource(R.drawable.button_kerdesek_style); break;
                    case 2: Button_valasz_B.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style); break;
                    case 1: Button_valasz_B.setBackgroundResource(R.drawable.button_kerdesek_style); break;
                }
            }
            @Override
            public void onFinish()
            {
                Button_valasz_B.setBackgroundResource(R.drawable.gomb_helyes_valasz_style);
            }
        }.start();
    }*/
}