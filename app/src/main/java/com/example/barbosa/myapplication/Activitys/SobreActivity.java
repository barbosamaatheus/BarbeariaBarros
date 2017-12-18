package com.example.barbosa.myapplication.Activitys;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.barbosa.myapplication.Adapter.TextJustify.TextViewEx;
import com.example.barbosa.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SobreActivity extends AppCompatActivity {
    private Toolbar myToolbar, mToolbarBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        onConfigurationChanged(getResources().getConfiguration());

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Sobre");
        setSupportActionBar(myToolbar);

        TextViewEx changed = (TextViewEx) findViewById(R.id.changed);
        changed.setText(getResources().getString(R.string.textoSobre), true);


        mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        mToolbarBottom.setTitle("Contato:");

        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SobreActivity.this);
                switch (item.getItemId()) {
                    case R.id.action_map:
                        String uri = "https://goo.gl/maps/mwpfGvsjhEM2";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(intent);
                        break;
                    case R.id.action_insta:
                        builder.setTitle("Instagram")
                                .setPositiveButton("@barbearia.barros", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        String uri = "https://www.instagram.com/barbearia.barros";
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("@henribarross", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        String uri = "https://www.instagram.com/henribarross";
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                        startActivity(intent);
                                    }
                                });
                        break;
                    case R.id.action_whats:
                        builder.setMessage("(83) 99988-7783")
                                .setTitle("WhatsApp")
                                .setPositiveButton("Copiar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        copy("83999887783");
                                        Toast.makeText(SobreActivity.this, "O numero: (83) 99988-7783, " +
                                                "foi copiado para sua area de trasferencia.", Toast.LENGTH_LONG).show();
                                    }
                                });
                        break;
                }

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
        mToolbarBottom.inflateMenu(R.menu.menu_bottom_sobre);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ic_galeria, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_gallery) {
            Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
            startActivity(intent);
            Toast.makeText(SobreActivity.this, "Em Desemvolvimento.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);

    }

    private void copy(String text) {
        ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData myClip;
        myClip = ClipData.newPlainText("text", text);
        myClipboard.setPrimaryClip(myClip);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;

        switch (orientation) {

            case Configuration.ORIENTATION_LANDSCAPE:
                //faça alguma coisa quando mudar pra landscape
                setContentView(R.layout.activity_sobre_land);


                break;

            case Configuration.ORIENTATION_PORTRAIT:
                //faça alguma coisa quando mudar pra potrait

                break;

        }
    }
}
