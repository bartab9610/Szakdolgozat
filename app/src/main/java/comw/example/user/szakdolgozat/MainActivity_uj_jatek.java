package comw.example.user.szakdolgozat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_uj_jatek extends AppCompatActivity
{
    private Button MainActivity_2_Button_vissza;
    private Button MainActivity_2_Jatek_inditas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_uj_jatek);

        Inicializalas();
        MainActivity_2_Button_vissza.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Elozo_menupont();
            }
        });
        MainActivity_2_Jatek_inditas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Jatek_inditas();
            }
        });
    }
    public void Inicializalas()
    {
        MainActivity_2_Button_vissza = (Button) findViewById(R.id.Activity_2_button_vissza);
        MainActivity_2_Jatek_inditas = (Button) findViewById(R.id.Activity_2_button_jatek_inditas);
    }
    public void Elozo_menupont()
    {
        Intent Elozo_ablak = new Intent(MainActivity_uj_jatek.this,MainActivity.class);
        startActivity(Elozo_ablak);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    public void Jatek_inditas()
    {
        Intent Jatek_inditas_kerdesek = new Intent(MainActivity_uj_jatek.this, MainActivity_kerdesek.class);
        startActivity(Jatek_inditas_kerdesek);
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}