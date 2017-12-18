/*
package com.example.barbosa.myapplication.Activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barbosa.myapplication.Objetos.Cliente;
import com.example.barbosa.myapplication.Objetos.Util.CommonActivity;

import com.example.barbosa.myapplication.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class CadastroActivity extends AppCompatActivity {
    private EditText mNome, mEmail, mTelefone, mSenha, mCSenha;
    private Button mCadastrar;
    private Toolbar myToolbar;
    private Cliente cliente;
    private Firebase firebase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Cadastro");
        setSupportActionBar(myToolbar);

        //firebase = LibraryClass.getFirebase();
        initViews();
        mCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSignUpData();
            }
        });

    }



    protected void initViews() {
        mNome = (EditText) findViewById(R.id.cadastro_nome);
        mEmail = (EditText) findViewById(R.id.cadastro_email);
        mSenha = (EditText) findViewById(R.id.cadastro_Csenha);
        mCadastrar = (Button) findViewById(R.id.btn_cadastroo_cadastrar);
    }


    protected void initUser() {
        cliente = new Cliente();
        cliente.setNome(mNome.getText().toString());
        cliente.setEmail(mEmail.getText().toString());
        cliente.setSenha(password.getText().toString());
    }
    public void sendSignUpData(){
        openProgressBar();
        initUser();
        saveUser();
    }

    private void  saveUser(){
        firebase.createUser(cliente.getEmail(), cliente.getSenha(), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                cliente.setCodigo(stringObjectMap.get("uid").toString());
                cliente.saveDB();
                firebase.unauth();
                showToast("Conta Criada Com Sucesso");
                closeProgressBar();
                finish();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                showSnackbar(firebaseError.getMessage());
                closeProgressBar();
            }
        });
    }

}
*/
/*     mNome = (EditText) findViewById(R.id.cadastro_nome);
        mEmail = (EditText) findViewById(R.id.cadastro_email);
        mTelefone = (EditText) findViewById(R.id.cadastro_telefone);
        mSenha = (EditText) findViewById(R.id.cadastro_senha);
        mCSenha = (EditText) findViewById(R.id.cadastro_Csenha);
        mCadastrar = (Button) findViewById(R.id.btn_cadastroo_cadastrar);
*/
