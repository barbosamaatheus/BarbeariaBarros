package com.example.barbosa.myapplication.Activitys;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.barbosa.myapplication.R;


public class MainActivity extends AppCompatActivity {

    private ImageButton sobre, servicos, agenda;
    private Toolbar myToolbar, mToolbarBottom;
    private Layout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onConfigurationChanged(getResources().getConfiguration());
        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Barbearia Barros");
        setSupportActionBar(myToolbar);


        sobre = (ImageButton) findViewById(R.id.sobre);
        servicos = (ImageButton) findViewById(R.id.servicos);
        agenda = (ImageButton) findViewById(R.id.agenda);

        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SobreActivity.class);
                startActivity(intent);

            }
        });
        servicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServicosActivity.class);
                startActivity(intent);
            }
        });
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReservaActivity.class);
                startActivity(intent);

            }
        });

    }

    private void copy(String text) {
        ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData myClip;
        myClip = ClipData.newPlainText("text", text);
        myClipboard.setPrimaryClip(myClip);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ic_qrcode, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(MainActivity.this, CamActivity.class);
            startActivity(intent);
            //Toast.makeText(MainActivity.this, "Em Breve.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;

        switch (orientation) {

            case Configuration.ORIENTATION_LANDSCAPE:
                //faça alguma coisa quando mudar pra landscape
                setContentView(R.layout.activity_main_land);


                break;

            case Configuration.ORIENTATION_PORTRAIT:
                //faça alguma coisa quando mudar pra potrait

                break;

        }
    }

}
