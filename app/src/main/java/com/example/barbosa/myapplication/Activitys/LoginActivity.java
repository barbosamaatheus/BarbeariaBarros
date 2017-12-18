/*
package com.example.barbosa.myapplication.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.barbosa.myapplication.Objetos.Cliente;

import com.example.barbosa.myapplication.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private Toolbar myToolbar;
    private Button btn_entrar, btn_cadastro;
    private EditText mEmail, mSenha;
    private Cliente cliente;
    private Firebase firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        //firebase = LibraryClass.getFirebase();

        verifyUserLogged();

        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLoginData();

            }
        });

    }
    private void verificarLogin(){
        firebase.authWithPassword(cliente.getEmail().toString(), cliente.getSenha().toString(), new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                cliente.saveTokenSP(LoginActivity.this, authData.getToken());
                closeProgressBar();
                callMainActivity();

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                showSnackbar(firebaseError.getMessage());
                closeProgressBar();
            }
        });

    }
    private void verifyUserLogged(){
        if(firebase.getAuth() != null){
            callMainActivity();
        }else{
            initUser();

            if (!cliente.getTokenSP(this).isEmpty()){
                firebase.authWithPassword("password",cliente.getTokenSP(this) , new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        cliente.saveTokenSP(LoginActivity.this, authData.getToken());
                        callMainActivity();

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                    }
                });

            }
        }
    }
    private void callMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void sendLoginData(){
        openProgressBar();
        initUser();
        verificarLogin();
    }
    @Override
    protected void initViews() {
        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Entrar");
        setSupportActionBar(myToolbar);
        mEmail = (EditText) findViewById(R.id.login_email);
        mSenha = (EditText) findViewById(R.id.login_senha);
        btn_entrar = (Button) findViewById(R.id.btn_login_entrar);
        btn_cadastro = (Button) findViewById(R.id.btn_login_cadastro);


    }
    @Override
    protected void initUser() {
        cliente = new Cliente();
        cliente.setEmail(mEmail.getText().toString());
        cliente.setSenha(password.getText().toString());

    }

}
*/
