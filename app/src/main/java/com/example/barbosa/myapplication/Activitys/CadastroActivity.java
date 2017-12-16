package com.example.barbosa.myapplication.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.barbosa.myapplication.Objetos.Cliente;
import com.example.barbosa.myapplication.Persistencia.SqLiteOH;
import com.example.barbosa.myapplication.R;

public class CadastroActivity extends AppCompatActivity {
    private EditText mNome, mEmail, mTelefone, mSenha, mCSenha;
    private Button mCadastrar;
    private Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Cadastro");
        setSupportActionBar(myToolbar);

        mNome = (EditText) findViewById(R.id.cadastro_nome);
        mEmail = (EditText) findViewById(R.id.cadastro_email);
        mTelefone = (EditText) findViewById(R.id.cadastro_telefone);
        mSenha = (EditText) findViewById(R.id.cadastro_senha);
        mCSenha = (EditText) findViewById(R.id.cadastro_Csenha);

        mCadastrar = (Button) findViewById(R.id.btn_cadastroo_cadastrar);

        mCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
