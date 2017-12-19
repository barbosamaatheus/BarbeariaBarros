
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

public class LoginActivity extends AppCompatActivity {
    private Toolbar myToolbar;
    private Button btn_entrar, btn_cadastro;
    private EditText mEmail, mSenha;
    private Cliente cliente;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Entrar");
        setSupportActionBar(myToolbar);
        mAuth = FirebaseAuth.getInstance();
        initViews();
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
    private void verificarLogin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("meuLog", "signInWithEmail:onComplete:" + task.isSuccessful());
                        callMainActivity();
                        closeProgressBar();

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("meuLog", "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "signInWithEmail:failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }
    private void verifyUserLogged(){

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    callMainActivity();
                    Log.d("meuLog", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("meuLog", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }
    private void callMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void sendLoginData(){
        initUser();
        verificarLogin(cliente.getEmail().toString(), cliente.getSenha().toString());
        openProgressBar();
    }
    protected void initViews() {
        mEmail = (EditText) findViewById(R.id.login_email);
        mSenha = (EditText) findViewById(R.id.login_senha);
        btn_entrar = (Button) findViewById(R.id.btn_login_entrar);
        btn_cadastro = (Button) findViewById(R.id.btn_login_cadastro);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_login);    }
    protected void initUser() {
        cliente = new Cliente();
        cliente.setEmail(mEmail.getText().toString());
        cliente.setSenha(mSenha.getText().toString());

    }
    protected void openProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }
    protected void closeProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}

