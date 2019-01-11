package comw.example.user.szakdolgozat;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity_animacio extends AppCompatActivity
{
    private ImageView Imageview_indito_animacio;
    private static int keslelteto = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_animacio);

        Inicializalas();
        ((AnimationDrawable) Imageview_indito_animacio.getBackground()).start();
        new Handler().postDelayed(new Runnable() {
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
}