package com.example.barbosa.myapplication.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.barbosa.myapplication.R;

public class LoginActivity extends AppCompatActivity {
    private Toolbar myToolbar;
    private Button btn_entrar, btn_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Entrar");
        setSupportActionBar(myToolbar);

        btn_entrar = (Button) findViewById(R.id.btn_login_entrar);
        btn_cadastro = (Button) findViewById(R.id.btn_login_cadastro);
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}
