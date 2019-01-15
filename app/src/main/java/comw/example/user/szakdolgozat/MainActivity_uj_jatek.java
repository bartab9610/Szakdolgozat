package comw.example.user.szakdolgozat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity_uj_jatek extends AppCompatActivity
{
    private Button MainActivity_2_Button_vissza;
    private Button MainActivity_2_Jatek_inditas;
    private Button MainActivity_2_Button_avatarok;
    private ImageView MainActivity_2_Imageview_avatar;
    private EditText MainActivity_2_Edittext_felhasznalo_nev;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
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
                if (!MainActivity_2_Edittext_felhasznalo_nev.getText().toString().equals("") && !MainActivity_2_Edittext_felhasznalo_nev.getText().toString().contains(" "))
                {
                    Eredmenyek_novelese_Mentes();
                    Jatek_inditas();
                }
                else
                {
                    Toast.makeText(MainActivity_uj_jatek.this,"Felhasználó név megadása kötelező!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        MainActivity_2_Button_avatarok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopupMenu Tobbi_avatar_valasztas = new PopupMenu(MainActivity_uj_jatek.this,MainActivity_2_Button_avatarok);
                Tobbi_avatar_valasztas.getMenuInflater().inflate(R.menu.avatar_valasztas,Tobbi_avatar_valasztas.getMenu());
                Tobbi_avatar_valasztas.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem)
                    {
                        if (menuItem.getTitle().equals("Férfi"))
                        {
                            MainActivity_2_Imageview_avatar.setBackgroundResource(R.drawable.ferfi);
                        }
                        else
                        {
                            MainActivity_2_Imageview_avatar.setBackgroundResource(R.drawable.no);
                        }
                        return true;
                    }
                });
                Tobbi_avatar_valasztas.show();
            }
        });
    }
    public void Inicializalas()
    {
        MainActivity_2_Button_vissza = (Button) findViewById(R.id.Activity_2_button_vissza);
        MainActivity_2_Jatek_inditas = (Button) findViewById(R.id.Activity_2_button_jatek_inditas);
        MainActivity_2_Button_avatarok = (Button) findViewById(R.id.Activity_2_button_avatar_valasztas);
        MainActivity_2_Imageview_avatar = (ImageView) findViewById(R.id.Activity_2_imageview_avatar);
        MainActivity_2_Edittext_felhasznalo_nev = (EditText) findViewById(R.id.Activity_2_edittext_becenev);
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
    public void Eredmenyek_novelese_Mentes()
    {
        String filename = "adatok";
        int jo_valaszok_szam = 0;
        SharedPreferences jo_valaszok_mentese = getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor szerkeszto = jo_valaszok_mentese.edit();
        szerkeszto.putInt("teszt",jo_valaszok_szam);
        szerkeszto.commit();
    }
}