package comw.example.user.szakdolgozat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    private String filename = "adatok";

    private static int keslelteto = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eredmenytabla);

        Inicializalas();
        Nyeremeny_kijelolese(); // A megfelelő kérdés számnál megjelöli a helyes válasznál összegét
        Activity_4_Button_nyeremeny_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Kovetkezo_kerdes();
            }
        });
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
        finish();
    }
    public void Nyeremeny_kijelolese()
    {
        SharedPreferences mentes = getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor szerkeszto = mentes.edit();
        int adat = mentes.getInt("Jó válaszok száma",0);
        switch (adat)
        {
            case 1:
                Activity_4_Button_nyeremeny_1.setBackgroundResource(R.drawable.gomb_kivalasztott_valasz_style);
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
            default:
                Toast.makeText(MainActivity_eredmenytabla.this, "Nem sikerült az első válasz nyereményed: 0 Ft", Toast.LENGTH_SHORT).show();
        }
    }
}