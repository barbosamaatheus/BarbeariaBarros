package com.example.barbosa.myapplication.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbosa.myapplication.Objetos.Cliente;
import com.example.barbosa.myapplication.Objetos.Servico;
import com.example.barbosa.myapplication.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CamActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private TextView mPontos, mTextEntrada;
    private Button btnScan, btnTroca;
    private Cliente cliente;
    private int pontos;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        final Activity activity = this;


        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Pontos");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initViews();

        btnTroca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CamActivity.this, ServicosActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Camera Scan");
                integrator.setCameraId(0);
                integrator.initiateScan();


            }
        });

        ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                cliente = dataSnapshot.getValue(Cliente.class);
                pontos = Integer.parseInt(cliente.getPontos());
                mPontos.setText(cliente.getPontos());
                mTextEntrada.setText("Olá " + cliente.getNome() + ", voce póssui:");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    protected void initViews() {
        mPontos = (TextView) findViewById(R.id.pontos);
        mTextEntrada = (TextView) findViewById(R.id.text_entrada);
        btnScan = (Button) findViewById(R.id.escaniar);
        btnTroca = (Button) findViewById(R.id.trocar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                Map<String, Object> hopperUpdates = new HashMap<String, Object>();
                int newPontos = pontos + 1;
                hopperUpdates.put("pontos", newPontos + "");
                ref.updateChildren(hopperUpdates);
                alert(result.getContents());
            } else {
                alert("Scan cancelado");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void alert(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        int id = item.getItemId();
        if (id == android.R.id.home) {
            intent = new Intent(getApplicationContext(), MainActivity.class);

        }
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    private boolean verifyUserLogged() {
        boolean logado;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            logado = true;

        } else {
            // No user is signed in
            logado = false;
        }
        return logado;
    }
}
