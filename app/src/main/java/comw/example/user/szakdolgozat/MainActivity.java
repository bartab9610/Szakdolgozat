package comw.example.user.szakdolgozat;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    private MediaPlayer Intro;
    private Button Button_uj_jatek;
    private Button Button_folytatas;
    private Button Button_eredmenyek;
    private Button Button_kilepes;
    private ImageButton Imagebutton_halkit;
    private ImageButton Imagebutton_angol;
    private AlertDialog.Builder Alert_kilepes;
    private boolean Intro_jatszas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializalas();
        Alert_dialogbox();
        // Intro.start();
        Intro_jatszas = true;
        Button_uj_jatek.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Uj_jatek_ablak();
            }
        });
        Button_folytatas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
        Button_eredmenyek.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Elert_eredmenyek_ablak();
            }
        });
        Button_kilepes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Alert_kilepes.show();
            }
        });
        Imagebutton_angol.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
        Imagebutton_halkit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (Intro_jatszas == true)
                {
                    Intro_jatszas = false;
                    Imagebutton_halkit.setBackgroundResource(R.drawable.hang_off);
                    Intro.setVolume(0, 0);
                }
                else if (Intro_jatszas == false)
                {
                    Intro_jatszas = true;
                    Imagebutton_halkit.setBackgroundResource(R.drawable.hang_on);
                    Intro.setVolume(0, 1);
                }
            }
        });
    }
    public void Inicializalas()
    {
        Intro = MediaPlayer.create(MainActivity.this, R.raw.loim_music);
        Button_uj_jatek = (Button) findViewById(R.id.Button_uj_jatek);
        Button_folytatas = (Button) findViewById(R.id.Button_jatek_folytatasa);
        Button_eredmenyek = (Button) findViewById(R.id.Button_eredmenyek);
        Button_kilepes = (Button) findViewById(R.id.Button_kilepes);
        Imagebutton_halkit = (ImageButton) findViewById(R.id.Imagebutton_halkit);
        Imagebutton_angol = (ImageButton) findViewById(R.id.Imagebutton_angol);
    }
    public void Uj_jatek_ablak()
    {
        Intent Uj_jatek = new Intent(MainActivity.this,MainActivity_uj_jatek.class);
        startActivity(Uj_jatek);
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out); // animáció -> ablak váltásnál nem fejülre helyezi a réteget hanem x-másodperc alatt megjelenik
    }
    public void Elert_eredmenyek_ablak()
    {
        Intent Elert_eredmeny_ablak = new Intent(MainActivity.this,MainActivity_eredmenytabla.class);
        startActivity(Elert_eredmeny_ablak);
        finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    public void Alert_dialogbox()
    {
        Alert_kilepes = new AlertDialog.Builder(MainActivity.this);
        Alert_kilepes.setTitle("Figyelem!").setMessage("Biztosan ki szeretnél lépni? El nem mentett állásod el fog veszni!")
                .setPositiveButton("Kilépés", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        finish();
                    }
                })
                .setNegativeButton("Vissza", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                })
                .setCancelable(false)
                .setIcon(R.drawable.figyelem)
                .create();
    }
    @Override
    protected void onStop()
    {
        Intro.stop();
        super.onStop();
    }
    @Override
    protected void onDestroy()
    {
        Intro.stop();
        super.onDestroy();
    }
}