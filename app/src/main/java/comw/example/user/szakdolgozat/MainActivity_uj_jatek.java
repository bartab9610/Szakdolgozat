package comw.example.user.szakdolgozat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
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
    private String Avatar_nev = "Férfi";

    private int adat = 0;
    private String Adatbazis_felhasznalo_nev = "";
    private String Adatbazis_avatar_nev = "";

    private int CAMERA_REQUEST_CODE = 123; // kamerakód

    private String filename = "adatok";
    private Adatbazis_letrehozo adatbazis;

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
                    Adat_rogzites();
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
                            Avatar_nev = "Férfi";
                            MainActivity_2_Imageview_avatar.setBackgroundResource(R.drawable.ferfi);
                            MainActivity_2_Imageview_avatar.setImageBitmap(null); // kinullázza a setImageBitmap-ot így nem lesz benne kép
                        }
                        else if (menuItem.getTitle().equals("Fénykép készítése.."))
                        {
                            Intent Avatar_keszites = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(Avatar_keszites,CAMERA_REQUEST_CODE);
                        }
                        else
                        {
                            Avatar_nev = "Nő";
                            MainActivity_2_Imageview_avatar.setBackgroundResource(R.drawable.no);
                            MainActivity_2_Imageview_avatar.setImageBitmap(null); // kinullázza a setImageBitmap-ot így nem lesz benne kép
                        }
                        return true;
                    }
                });
                Tobbi_avatar_valasztas.show();
            }
        });
    }
    @Override
    protected void onActivityResult(int Kamera_kod, int eredmeny, Intent data)
    {
        if (Kamera_kod == CAMERA_REQUEST_CODE && Kamera_kod == Activity.RESULT_OK);
        {
            Bitmap Keszitett_kep = (Bitmap) data.getExtras().get("data");
            MainActivity_2_Imageview_avatar.setImageBitmap(Keszitett_kep);
            MainActivity_2_Imageview_avatar.setBackgroundResource(0); // beállítja a háttérkép elérését 0-ra így a készített kép lesz felül
        }
    }
    public void Inicializalas()
    {
        MainActivity_2_Button_vissza = (Button) findViewById(R.id.Activity_2_button_vissza);
        MainActivity_2_Jatek_inditas = (Button) findViewById(R.id.Activity_2_button_jatek_inditas);
        MainActivity_2_Button_avatarok = (Button) findViewById(R.id.Activity_2_button_avatar_valasztas);
        MainActivity_2_Imageview_avatar = (ImageView) findViewById(R.id.Activity_2_imageview_avatar);
        MainActivity_2_Edittext_felhasznalo_nev = (EditText) findViewById(R.id.Activity_2_edittext_becenev);

        adatbazis = new Adatbazis_letrehozo(this);
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
        String filename = "adatok"; // Ezen a néven található meg a DEVICE FILE EXPLORER részben a fájl mappájának share pref könyvtárában
        int jo_valaszok_szam = 0; // ezeket az adatokat küldi tovább
        int Telefonos_segitseg_szama = 0; // ezeket az adatokat küldi tovább
        int Felezes_segitseg_szama = 0; // ezeket az adatokat küldi tovább
        int Nezoi_segitseg_szama = 0; // ezeket az adatokat küldi tovább
        String felhasznalo_nev = MainActivity_2_Edittext_felhasznalo_nev.getText().toString();
        String Jo_valasz_volte = "Jó válasz"; // SharedPref-be tovább küldön egy string változót "Jó válasz értékkel" ez addig lesz ez az érték amíg rossz választ nem ad meg a játékos
        SharedPreferences jo_valaszok_mentese = getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor szerkeszto = jo_valaszok_mentese.edit();
        szerkeszto.putInt("Jó válaszok száma",jo_valaszok_szam);
        szerkeszto.putInt("Telefonos segítség",Telefonos_segitseg_szama);
        szerkeszto.putInt("Felezés segítség",Felezes_segitseg_szama);
        szerkeszto.putInt("Nézői segítség",Nezoi_segitseg_szama);
        szerkeszto.putString("Felhasználó név", felhasznalo_nev); // tovább küldjük az Edittext-ben lévő szöveget
        szerkeszto.putString("Avatar neve",Avatar_nev);
        szerkeszto.putString("Jó válasz volt-e", Jo_valasz_volte); // alapértelmezettként "Jó válasz"-t küldünk bele a SharedPref-be
        szerkeszto.clear(); // Minden új játék kezdésnél ez kitörli az elmentett értékeket és 0-ra állítja így minden alaphelyzetből indul
        szerkeszto.commit();
    }
    public void Adat_rogzites()
    {
        // SharedPreferences avatar_nev = getSharedPreferences(filename, Context.MODE_PRIVATE);
        // Adatbazis_avatar_nev = avatar_nev.getString("Avatar neve","");
        Adatbazis_avatar_nev = Avatar_nev;
        String adatbazis_avatar_nev = Adatbazis_avatar_nev;

        // SharedPreferences felhasznalo_nev = getSharedPreferences(filename, Context.MODE_PRIVATE);
        // Adatbazis_felhasznalo_nev = felhasznalo_nev.getString("Felhasználó név","");
        Adatbazis_felhasznalo_nev = MainActivity_2_Edittext_felhasznalo_nev.getText().toString();
        String adatbazis_felhasznalo_nev = Adatbazis_felhasznalo_nev;

        SharedPreferences Jo_valaszok_szama = getSharedPreferences(filename, Context.MODE_PRIVATE);
        adat = Jo_valaszok_szama.getInt("Jó válaszok száma:",0);
        int adatbazis_eredmeny = adat;

        boolean eredmeny = adatbazis.Adat_felvetel(adatbazis_avatar_nev,adatbazis_felhasznalo_nev,adatbazis_eredmeny);
        if (eredmeny)
        {
            Toast.makeText(MainActivity_uj_jatek.this,"Sikeres adatrögzítés",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity_uj_jatek.this,"Sikertelen adatrögzítés",Toast.LENGTH_SHORT).show();
        }
    }
}