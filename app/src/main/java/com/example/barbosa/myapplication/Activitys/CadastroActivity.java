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

public class CadastroActivity extends AppCompatActivity {
    private static final String TAG = "meulog";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mNome, mEmail, mSenha;
    private Button mCadastrar;
    private Toolbar myToolbar;
    private Cliente cliente;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Barbearia Barros");
        setSupportActionBar(myToolbar);

        mNome = (EditText) findViewById(R.id.cadastro_nome);
        mEmail = (EditText) findViewById(R.id.cadastro_email);
        mSenha = (EditText) findViewById(R.id.cadastro_senha);
        mCadastrar = (Button) findViewById(R.id.btn_cadastro_cadastrar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_cad);

        mAuth = FirebaseAuth.getInstance();
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
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }

    protected void initUser() {
        cliente = new Cliente();
        cliente.setNome(mNome.getText().toString());
        cliente.setEmail(mEmail.getText().toString());
        cliente.setSenha(mSenha.getText().toString());
    }
    public void sendSignUpData() {
        initUser();
        createUser(cliente.getEmail().toString(), cliente.getSenha().toString());
        openProgressBar();
    }

    private void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "createUserWithEmail:success",
                                    Toast.LENGTH_SHORT).show();

                           /* FirebaseDatabase database = database = FirebaseDatabase.getInstance();
                            DatabaseReference ref = database.getReference("user").child(FirebaseAuth.getInstance()
                                    .getCurrentUser().getUid());
                            ref.child("email").setValue(mEmail.getText().toString());
                            ref.child("uid").setValue(FirebaseAuth.getInstance()
                                    .getCurrentUser().getUid());*/
                            closeProgressBar();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "createUserWithEmail:failure",
                                    Toast.LENGTH_SHORT).show();
                            closeProgressBar();


                        }

                        // ...
                    }
                });
    }

    protected void openProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    protected void closeProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void callLoginActivity() {
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
