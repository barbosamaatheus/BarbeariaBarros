
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "meulog";
    private Toolbar myToolbar;
    private Button btn_entrar, btn_cadastro, btn_visita;
    private EditText mEmail, mSenha;
    private int Pontos;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private Cliente cliente;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myToolbar = (Toolbar) findViewById(R.id.tb_login);
        myToolbar.setTitle("Entrar");
        setSupportActionBar(myToolbar);

        initViews();

        mAuth = FirebaseAuth.getInstance();

        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLoginData();

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "signInWithEmail:success",
                                    Toast.LENGTH_SHORT).show();
                            closeProgressBar();
                            FirebaseUser user = mAuth.getCurrentUser();
                            callMainActivity();

                        } else {
                            mEmail.setError("Usuario ou Senha incorretos");
                            View focus = mEmail;
                            focus.requestFocus();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Login falhou, Verifique sua conexão com a internet",
                                    Toast.LENGTH_LONG).show();
                            closeProgressBar();


                        }

                        // ...
                    }
                });
    }

    private void verifyUserLogged() {

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

    private void callMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendLoginData() {
        initUser();
        String email = cliente.getEmail().toString();
        String senha = cliente.getSenha().toString();

        if (!email.isEmpty() && !senha.isEmpty()){
            signIn(email,senha);
            openProgressBar();
        }else{
            View focus = null;
            boolean exibir = false;

            if (email.isEmpty()){
                mEmail.setError("Campo vazio");
                focus = mEmail;
                exibir = true;

            }
            if (senha.isEmpty()){
                mSenha.setError("Campo vazio ");
                focus = mSenha;
                exibir = true;

            }
            if(exibir){//com a variável auxiliar atribuida valor booleano true, exibir a menssagem na tela

                focus.requestFocus();
                closeProgressBar();

            }
        }

    }

    protected void initViews() {
        mEmail = (EditText) findViewById(R.id.login_email);
        mSenha = (EditText) findViewById(R.id.login_senha);
        btn_entrar = (Button) findViewById(R.id.btn_login_entrar);
        btn_cadastro = (Button) findViewById(R.id.btn_login_cadastro);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_login);
    }

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

