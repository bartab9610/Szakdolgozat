package comw.example.user.szakdolgozat;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity_animacio extends AppCompatActivity implements Animation.AnimationListener
{
    private ImageView Imageview_indito_animacio;
    private TextView Textview_szoveg_megjelenes;
    private static int keslelteto = 17000; // 17 másodperc után indul el a MainActivity
    private static int Ikon_mozgatas = 11000; // betoltokep_fade_in-ben 10 másodperc alatt jelenik meg a kép majd 11.másodpercre 2 másodperc alatt felmozog a helyére
    private static int Szoveg_megjelenes = 14000; // 15.másodpercben a "Legyen Ön is Milliomos" szöveg megjelenik 1.5 másodperc alatt és 18.másodpercre betöltődik a MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_animacio);

        Inicializalas();
        Formazott_szoveg();
        Animation valami = AnimationUtils.loadAnimation(MainActivity_animacio.this,R.anim.betoltokep_fade_in);
        Imageview_indito_animacio.setAnimation(valami);
        valami.setAnimationListener(MainActivity_animacio.this);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Animation Ikon_felmozgas = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.ikon_felmozgas);
                Imageview_indito_animacio.startAnimation(Ikon_felmozgas);
            }
        }
        ,Ikon_mozgatas);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Animation Szoveg_megjelenes = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.szoveg_megjelenes);
                Textview_szoveg_megjelenes.startAnimation(Szoveg_megjelenes);
            }
        }
        ,Szoveg_megjelenes);
        // ((AnimationDrawable) Imageview_indito_animacio.getBackground()).start(); // ezzel indul el a másik animáció
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Fo_oldal();
            }
        }
        ,keslelteto);
    }
    public void Inicializalas()
    {
        Imageview_indito_animacio = (ImageView) findViewById(R.id.Activity_5_indito_animacio);
        Textview_szoveg_megjelenes = (TextView) findViewById(R.id.Activity_5_leugro_szoveg);
    }
    public void Fo_oldal()
    {
        Intent Kezdo_oldal = new Intent(MainActivity_animacio.this, MainActivity.class);
        startActivity(Kezdo_oldal);
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    public void Formazott_szoveg()
    {
        String Legyen_on_is_milliomos_szoveg = "Legyen Ön is Milliomos"; // felveszünk egy tetszőleges String-et
        SpannableString Formazott_szoveg = new SpannableString(Legyen_on_is_milliomos_szoveg); // átadjuk a SpannableString-nek hogy tudjuk formázni
        ForegroundColorSpan Piros_On = new ForegroundColorSpan(getResources().getColor(R.color.Gomb_keret_kek)); // létrehozunk egy változót majd beállítjuk hogy milyen színűre szeretnénk állítani
        Formazott_szoveg.setSpan(Piros_On,7,9,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // megadjuk a ForegroundColorSpan változót majd megadjuk hogy hányadik karaktertől hányadikik akarjuk beállítani
        Textview_szoveg_megjelenes.setText(Formazott_szoveg); // felülírom a létrehozott szöveget a formázottra
    }
    @Override
    public void onAnimationStart(Animation animation)
    {
        // Toast.makeText(MainActivity_animacio.this,"Betöltőkép animáció kezdés! 10mp",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onAnimationEnd(Animation animation)
    {
        // Toast.makeText(MainActivity_animacio.this,"Betöltőkép animáció vége! 10mp",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onAnimationRepeat(Animation animation)
    {
    }
}