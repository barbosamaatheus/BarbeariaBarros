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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.barbosa.myapplication.Objetos.Cliente;
import com.example.barbosa.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    private EditText mNome, mEmail, mSenha;
    private Button mCadastrar;
    private Toolbar myToolbar;
    private Cliente cliente;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Cadastro");
        setSupportActionBar(myToolbar);

        mAuth = FirebaseAuth.getInstance();
        initViews();
        //verifyUserLogged();

        mCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSignUpData();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    protected void initViews() {
        mNome = (EditText) findViewById(R.id.cadastro_nome);
        mEmail = (EditText) findViewById(R.id.cadastro_email);
        mSenha = (EditText) findViewById(R.id.cadastro_senha);
        mCadastrar = (Button) findViewById(R.id.btn_cadastro_cadastrar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_cadastro);

    }
    protected void initUser() {
        cliente = new Cliente();
        cliente.setNome(mNome.getText().toString());
        cliente.setEmail(mEmail.getText().toString());
        cliente.setSenha(mSenha.getText().toString());
    }
    private void verifyUserLogged(){

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                    startActivity(intent);
                    Log.d("meuLog", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("meuLog", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }
    public void sendSignUpData() {
        initUser();
        saveUser(cliente.getEmail().toString(), cliente.getSenha().toString());
        openProgressBar();
    }
    private void saveUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("meuLog", "Cadastro Criado Com Sucesso:" + task.isSuccessful());
                        callLoginActivity();
                        closeProgressBar();

                        if (!task.isSuccessful()) {
                            Toast.makeText(CadastroActivity.this, "Erro No Cadastro",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    protected void openProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
    protected void closeProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
    private void callLoginActivity(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
