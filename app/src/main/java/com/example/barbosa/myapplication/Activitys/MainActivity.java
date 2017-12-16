package com.example.barbosa.myapplication.Activitys;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.barbosa.myapplication.R;


public class MainActivity extends AppCompatActivity {

    private ImageButton sobre, servicos, agenda;
    private Toolbar myToolbar, mToolbarBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Barbearia Barros");
        setSupportActionBar(myToolbar);

        mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        mToolbarBottom.setTitle("Contato:");

        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                switch (item.getItemId()) {
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
                                        Toast.makeText(MainActivity.this, "O numero: (83) 99988-7783, foi copiado para sua area de trasferencia.", Toast.LENGTH_LONG).show();
                                    }
                                });
                        break;
                }
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
        mToolbarBottom.inflateMenu(R.menu.menu_bottom);

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
                Intent intent = new Intent(MainActivity.this, CalendarioActivity.class);
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

}
