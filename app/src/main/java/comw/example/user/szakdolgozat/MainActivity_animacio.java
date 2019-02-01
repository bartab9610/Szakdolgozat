package comw.example.user.szakdolgozat;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity_animacio extends AppCompatActivity implements Animation.AnimationListener
{
    private ImageView Imageview_indito_animacio;
    private static int keslelteto = 12000; // 10mp alatt lefut az animáció és 2mp múlva betölti a fő oldalt

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_animacio);

        Inicializalas();
        Animation valami = AnimationUtils.loadAnimation(MainActivity_animacio.this,R.anim.betoltokep_fade_in);
        Imageview_indito_animacio.setAnimation(valami);
        valami.setAnimationListener(MainActivity_animacio.this);
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
    }
    public void Fo_oldal()
    {
        Intent Kezdo_oldal = new Intent(MainActivity_animacio.this, MainActivity.class);
        startActivity(Kezdo_oldal);
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    @Override
    public void onAnimationStart(Animation animation)
    {
        Toast.makeText(MainActivity_animacio.this,"Betöltőkép animáció kezdés! 10mp",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onAnimationEnd(Animation animation)
    {
        Toast.makeText(MainActivity_animacio.this,"Betöltőkép animáció vége! 10mp",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onAnimationRepeat(Animation animation)
    {
    }
}