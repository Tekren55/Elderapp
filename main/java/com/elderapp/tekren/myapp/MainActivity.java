package com.elderapp.tekren.myapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import android.content.Context;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
/**
 * Created by Tekren on 09.01.2018.
 */

public class MainActivity extends Activity {


    String ArrayName[]={"Rehber",
                        "Mesaj",
                        "Konuş",
                        "İnternet",
                        "PlayStore"};
    ArrayList<String> eslesenTextListe;
    int REQUEST_CODE=100;
    Context context=this;
    CircleMenu circleMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_app_widget);
        Button b1=(Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this,Menu.class));
                Toast.makeText(getApplicationContext(), "acilmadi", Toast.LENGTH_SHORT).show();
            }
        });

        CircleMenu circleMenu=(CircleMenu)findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.ic_plus,R.drawable.ic_minus)
                .addSubMenu(Color.parseColor("#258CFF"),R.drawable.ic_contacts)
                .addSubMenu(Color.parseColor("#6d4c41"),R.drawable.ic_messages)
                .addSubMenu(Color.parseColor("#258CFF"),R.drawable.ic_chrome)
                .addSubMenu(Color.parseColor("#258CFF"),R.drawable.ic_microphone)
                .addSubMenu(Color.parseColor("#258CFF"),R.drawable.ic_playstore)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        switch (i) {
                            case 0:
                                Intent i1 = new Intent(Intent.ACTION_DIAL);
                                startActivity(i1);
                                break;
                            case 1:
                                Intent i2 = new Intent(Intent.ACTION_MAIN);
                                i2.addCategory(Intent.CATEGORY_APP_MESSAGING);
                                startActivity(i2);
                                break;
                            case 2:
                                /*Intent i3 = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("http://www.google.com"));
                                startActivity(i3);
                                */
                                break;
                            case 3:
                                if(okey()){
                                    Intent i4 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                                    i4.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                                    startActivityForResult(i4,REQUEST_CODE);
                                }
                                else{
                                    Toast.makeText(context,"Lütfen internet bağlantınızı kontrol edin!",Toast.LENGTH_SHORT);
                                }

                                break;
                            case 4:
                                Intent i5 = new Intent(Intent.ACTION_VIEW);
                                i5.setData(Uri.parse("market://details?id=com.example.android"));
                                startActivity(i5);
                                break;
                            default:
                                break;

                        }
                    }
                });
    }
    public void baslatRehber(){
        final MediaPlayer mP= MediaPlayer.create(this, R.raw.cegid);
        final FrameLayout frameone=(FrameLayout)findViewById(R.id.frame_one);
        final FrameLayout frametwo=(FrameLayout)findViewById(R.id.frame_two);
        final FrameLayout frametre=(FrameLayout)findViewById(R.id.frame_tre);
        final FrameLayout framefor=(FrameLayout)findViewById(R.id.frame_for);
        final FrameLayout framefiv=(FrameLayout)findViewById(R.id.frame_fiv);
        frameone.setVisibility(View.VISIBLE);
        mP.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frametwo.setVisibility(View.VISIBLE);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frametre.setVisibility(View.VISIBLE);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        framefor.setVisibility(View.VISIBLE);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        framefiv.setVisibility(View.VISIBLE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data, int position) throws InterruptedException {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode==RESULT_OK){
            eslesenTextListe =data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1, eslesenTextListe);

            switch (eslesenTextListe.get(position)){
                case "Telefon etmeyi göster":
                    baslatRehber();
                case "Uygulama indirmeyi göster":
                    baslatDown();
                case "Mesaj atmayı göster":
                    baslatPost();
                case "İnternette gezmeyi göster":
                    baslatSurf();
                case "Resim çekmeyü göster":
                    baslatCap();
            }
        }
    }
    public boolean okey (){
        ConnectivityManager con = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net=con.getActiveNetworkInfo();
        if (net != null && net.isConnected() && net.isAvailable()){
            return true;
        }
        else{
            return false;
        }
    }
}