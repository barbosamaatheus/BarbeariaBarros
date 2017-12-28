package com.example.barbosa.myapplication.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbosa.myapplication.Interface.RecyclerViewOnClickListener;
import com.example.barbosa.myapplication.Objetos.Cliente;
import com.example.barbosa.myapplication.Objetos.Servico;
import com.example.barbosa.myapplication.R;
import com.example.barbosa.myapplication.Adapter.ServicoAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServicosActivity extends AppCompatActivity implements RecyclerViewOnClickListener {
    private RecyclerView mRecyclerView;
    private Toolbar myToolbar;
    private List<Servico> mList;
    private Servico s;
    private Cliente cliente;
    private int pontos;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Serviços e Produtos");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // se precisar reusar a lista
                /*LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                ServicoAdapter servicoAdapter = (ServicoAdapter) mRecyclerView.getAdapter();

                if(mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1){
                    List<Servico> listAux  = criarLista(10);

                    for(int i=0; i<listAux.size(); i ++){
                        servicoAdapter.addListItem(listAux.get(i), mList.size());
                    }
                }*/
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = criarLista();
        ServicoAdapter servicoAdapter = new ServicoAdapter(this, mList);
        servicoAdapter.setRecyclerViewOnClickListener(this);
        mRecyclerView.setAdapter(servicoAdapter);

        ref.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                cliente = dataSnapshot.getValue(Cliente.class);
                pontos = Integer.parseInt(cliente.getPontos());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public List<Servico> criarLista() {
        List<Servico> lista = new ArrayList<Servico>();
        // -->> Combos
        Servico combo1 = new Servico("Combo 1", "Corte e Barba", "40,00", "21");
        Servico combo2 = new Servico("Combo 2", "Corte, hidratação e selagem" +
                " (preço pode variar de acordo o tamanho do cabelo)", "60,00", "31");

        // -->> Cabelo
        Servico lavagem = new Servico("Lavagem", "Cabelo", "5,00", "3");
        Servico acabamento = new Servico("Acabamento", "Cabelo", "10,00", "6");
        Servico hidratacao = new Servico("Hidratação", "Cabelo", "12,00", "7");
        Servico corte = new Servico("Corte", "Cabelo", "25,00", "14");
        Servico corteM = new Servico("Corte Maquina", "Cabelo", "20,00", "11");
        Servico selagem = new Servico("Selagem", "Cabelo, preço pode variar de " +
                "acordo o tamanho do cabelo", "40,00", "21");
        Servico corteA = new Servico("Corte Agendado", "Cabelo", "35,00", "19");

        // -->> Barba
        Servico tonalização = new Servico("Tonalização", "Barba", "15,00", "9");
        Servico barba = new Servico("Barba Pura", "Barba", "20,00", "11");
        Servico designBarba = new Servico("Design de Barba", "Barba", "25,00", "14");
        Servico barbaA = new Servico("Barba Agendada", "Barba", "30,00", "16");

        // -->> Produtos
        Servico pMate = new Servico("Pomada Modeladora", " AC Cosmeticos, Efeito Mate", "25,90", "15");
        Servico pTeia = new Servico("Pomada Modeladora", " AC Cosmeticos, Efeito Teia", "27,90", "16");

        // -->> Inserir na lista
        lista.add(combo1);
        lista.add(combo2);

        lista.add(lavagem);
        lista.add(acabamento);
        lista.add(hidratacao);
        lista.add(corte);
        lista.add(corteM);
        lista.add(selagem);
        lista.add(corteA);

        lista.add(tonalização);
        lista.add(barba);
        lista.add(designBarba);
        lista.add(barbaA);

        lista.add(pMate);
        lista.add(pTeia);

        return lista;
    }

    @Override
    public void onClickListener(View view, int positon) {
        s = mList.get(positon);
        final int valorItemPts = Integer.parseInt(s.getValorPts());

        AlertDialog.Builder builder = new AlertDialog.Builder(ServicosActivity.this);

        builder.setMessage("Este item custa: " + s.getValorPts() + " pontos. Deseja trocar?")
                .setTitle("Trocar Pontos")
                .setPositiveButton("Trocar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (valorItemPts <= pontos) {
                            Map<String, Object> hopperUpdates = new HashMap<String, Object>();
                            int newPontos = pontos - valorItemPts;
                            hopperUpdates.put("pontos", newPontos + "");
                            ref.updateChildren(hopperUpdates);
                            callCamActivity();

                        }else{
                            Toast.makeText(ServicosActivity.this, "Você não tem pontos suficientes para este item",
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        int id = item.getItemId();
        if(id == android.R.id.home){
            intent = new Intent(getApplicationContext(), MainActivity.class);

        }
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);

    }

    private void callCamActivity() {
        Intent intent = new Intent(ServicosActivity.this, CamActivity.class);
        startActivity(intent);
        finish();
    }
}

