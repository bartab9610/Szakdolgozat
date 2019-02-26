package comw.example.user.szakdolgozat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity_eredmenytabla extends AppCompatActivity
{
    private Button Activity_4_Button_nyeremeny_1;
    private Button Activity_4_Button_nyeremeny_2;
    private Button Activity_4_Button_nyeremeny_3;
    private Button Activity_4_Button_nyeremeny_4;
    private Button Activity_4_Button_nyeremeny_5;
    private Button Activity_4_Button_nyeremeny_6;
    private Button Activity_4_Button_nyeremeny_7;
    private Button Activity_4_Button_nyeremeny_8;
    private Button Activity_4_Button_nyeremeny_9;
    private Button Activity_4_Button_nyeremeny_10;
    private Button Activity_4_Button_nyeremeny_11;
    private Button Activity_4_Button_nyeremeny_12;
    private Button Activity_4_Button_nyeremeny_13;

    private AlertDialog.Builder Alert_nyeremeny;
    private AlertDialog.Builder Alert_fonyeremeny;

    private String filename = "adatok";
    private String Nyeremeny = "";

    private static int keslelteto = 500;
    private static int Kovetkezo_kerdes = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eredmenytabla);

        Inicializalas();
        Nyeremeny_kijelolese(); // A megfelelő kérdés számnál megjelöli a helyes válasznál összegét
        Nincs_kerdes_ha_rossz_valasz_van(); // SharedPref-vel lekérem a "Jó válasz volt-e" string-et és ha az eredmény "Jó válasz" akkor lefut a Handler és a következő kérdés, ha "Hibás válasz" akkor nem történik semmi
    }
    public void Inicializalas()
    {
        Activity_4_Button_nyeremeny_1 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_1);
        Activity_4_Button_nyeremeny_2 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_2);
        Activity_4_Button_nyeremeny_3 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_3);
        Activity_4_Button_nyeremeny_4 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_4);
        Activity_4_Button_nyeremeny_5 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_5);
        Activity_4_Button_nyeremeny_6 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_6);
        Activity_4_Button_nyeremeny_7 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_7);
        Activity_4_Button_nyeremeny_8 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_8);
        Activity_4_Button_nyeremeny_9 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_9);
        Activity_4_Button_nyeremeny_10 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_10);
        Activity_4_Button_nyeremeny_11 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_11);
        Activity_4_Button_nyeremeny_12 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_12);
        Activity_4_Button_nyeremeny_13 = (Button) findViewById(R.id.Activity_4_button_nyeremeny_13);
    }
    public void Kovetkezo_kerdes()
    {
        Intent kovetkezo = new Intent(MainActivity_eredmenytabla.this,MainActivity_kerdesek.class);
        startActivity(kovetkezo);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        finish();
    }
    public void Nyeremeny_kijelolese()
    {
        SharedPreferences mentes = getSharedPreferences(filename, Context.MODE_PRIVATE);
        // SharedPreferences.Editor szerkeszto = mentes.edit();
        int adat = mentes.getInt("Jó válaszok száma",0);
        switch (adat)
        {
            case 1:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                // Looper
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                            {
                                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.button_kerdesek_style);
                                new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                            }
                    }
                    ,keslelteto);
                break;
            case 2:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 3:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 4:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 5:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 6:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_6.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_6.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_6.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 7:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_6.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_7.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_7.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_7.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 8:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_6.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_7.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_8.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_8.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_8.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 9:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_6.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_7.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_8.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_9.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_9.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_9.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                        }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 10:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_6.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_7.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_8.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_9.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_10.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_10.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_10.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 11:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_6.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_7.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_8.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_9.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_10.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_11.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_11.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_11.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 12:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_6.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_7.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_8.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_9.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_10.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_11.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_12.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_12.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_12.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
            case 13:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_2.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_3.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_4.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_5.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_6.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_7.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_8.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_9.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_10.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_11.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_12.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                Activity_4_Button_nyeremeny_13.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Activity_4_Button_nyeremeny_13.setBackgroundResource(R.drawable.button_kerdesek_style);
                            new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Activity_4_Button_nyeremeny_13.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
                                    }
                                }
                                ,keslelteto);
                        }
                    }
                    ,keslelteto);
                break;
        }
    }
    public void Nincs_kerdes_ha_rossz_valasz_van()
    {
        SharedPreferences Jo_valasz_voltE = getSharedPreferences(filename,Context.MODE_PRIVATE);
        String Jo_valasz_string = Jo_valasz_voltE.getString("Jó válasz volt-e","");
        final int Jo_valaszok_szama_13 = Jo_valasz_voltE.getInt("Jó válaszok száma",0);
        if (Jo_valasz_string.equals("Jó válasz"))
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    if (Jo_valaszok_szama_13 < 13)
                    {
                        Kovetkezo_kerdes();
                    }
                    else
                    {
                        Alert_fonyeremeny = new AlertDialog.Builder(MainActivity_eredmenytabla.this);
                        Alert_fonyeremeny.setTitle("Gratulálunk!").setMessage("Helyesen válaszoltál az utolsó kérdésre, nyereményed 50.000.000 forint!\n")
                                .setNegativeButton("Új játék kezdése", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        Uj_jatek_kezdese();
                                    }
                                })
                                .setPositiveButton("Kilépés", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        finish();
                                    }
                                })
                                .setCancelable(false)
                                .setIcon(R.drawable.trophy)
                                .create();
                        Alert_fonyeremeny.show();
                    }
                }
            },Kovetkezo_kerdes);
        }
        else
        {
            // Toast.makeText(MainActivity_eredmenytabla.this,"Hibás válasz!",Toast.LENGTH_SHORT).show();
            SharedPreferences Jo_valaszok_szama = getSharedPreferences(filename, Context.MODE_PRIVATE);
            int Jo_valaszok_szama_ellenorzes = Jo_valaszok_szama.getInt("Jó válaszok száma",0);
            switch (Jo_valaszok_szama_ellenorzes)
            {
                case 0:
                    Nyeremeny = "0";
                    break;
                case 1:
                    Nyeremeny = "5.000";
                    break;
                case 2:
                    Nyeremeny = "10.000";
                    break;
                case 3:
                    Nyeremeny = "25.000";
                    break;
                case 4:
                    Nyeremeny = "50.000";
                    break;
                case 5:
                    Nyeremeny = "100.000";
                    break;
                case 6:
                    Nyeremeny = "250.000";
                    break;
                case 7:
                    Nyeremeny = "500.000";
                    break;
                case 8:
                    Nyeremeny = "1.000.000";
                    break;
                case 9:
                    Nyeremeny = "2.500.000";
                    break;
                case 10:
                    Nyeremeny = "5.000.000";
                    break;
                case 11:
                    Nyeremeny = "10.000.000";
                    break;
                case 12:
                    Nyeremeny = "20.000.000";
                    break;
                case 13:
                    Nyeremeny = "50.000.000";
                    break;
            }
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    Alert_nyeremeny = new AlertDialog.Builder(MainActivity_eredmenytabla.this);
                    Alert_nyeremeny.setTitle("Gratulálunk!").setMessage("Erre a kérdésre rosszul válaszoltál, de nyereményed " + Nyeremeny + " forint!\nKövetkező lépésben válassz a lehetőségek közül:")
                            .setNegativeButton("Új játék kezdése", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i)
                                {
                                    Uj_jatek_kezdese();
                                }
                            })
                            .setPositiveButton("Kilépés", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i)
                                {
                                    finish();
                                }
                            })
                            .setCancelable(false)
                            .setIcon(R.drawable.trophy)
                            .create();
                    Alert_nyeremeny.show();
                }
            },2000); // 2 másodperc múlva jelenik meg a szöveg
        }
    }
    public void Uj_jatek_kezdese()
    {
        Intent Uj_jatek = new Intent(MainActivity_eredmenytabla.this,MainActivity_uj_jatek.class);
        startActivity(Uj_jatek);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        finish();
    }
}