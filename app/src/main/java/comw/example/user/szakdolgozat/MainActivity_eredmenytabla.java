package comw.example.user.szakdolgozat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    private String filename = "adatok";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eredmenytabla);

        Inicializalas();
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
}