package com.example.barbosa.myapplication.Activitys;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.barbosa.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private Toolbar myToolbar, mToolbarBottom;
    private ActionMenuView amvMenu;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onConfigurationChanged(getResources().getConfiguration());

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Barbearia Barros");
        setSupportActionBar(myToolbar);

        mAuth = FirebaseAuth.getInstance();

        amvMenu = (ActionMenuView) findViewById(R.id.amvMenu);
        mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_bottom);
        amvMenu.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.action_sobre:
                        intent = new Intent(MainActivity.this, SobreActivity.class);
                        break;
                    case R.id.action_servico:
                        intent = new Intent(MainActivity.this, ServicosActivity.class);
                        break;
                    case R.id.action_reserva:
                        intent = new Intent(MainActivity.this, ReservaActivity.class);
                        break;
                }
                startActivity(intent);
                return true;
            }

        });

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bottom_main, amvMenu.getMenu());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_top_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_qr:
                intent = new Intent(MainActivity.this, CamActivity.class);
                //Toast.makeText(MainActivity.this, "Em Breve.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_exit:
                mAuth.signOut();
                intent = new Intent(MainActivity.this, LoginActivity.class);

                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        switch (orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                //faça alguma coisa quando mudar pra landscape
                setContentView(R.layout.activity_main_land);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                //faça alguma coisa quando mudar pra potrait
                break;
        }
    }
}