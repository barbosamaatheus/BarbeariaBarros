package com.example.barbosa.myapplication.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.barbosa.myapplication.Objetos.Servico;
import com.example.barbosa.myapplication.R;
import com.example.barbosa.myapplication.Adapter.ServicoAdapter;

import java.util.ArrayList;
import java.util.List;


public class ServicosActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar myToolbar;
    private  List<Servico> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Serviços e Produtos");
        setSupportActionBar(myToolbar);

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
        mRecyclerView.setAdapter(servicoAdapter);
    }
    public List<Servico> criarLista() {
        List<Servico> lista = new ArrayList<Servico>();
        // -->> Combos
        Servico combo1 = new Servico("Combo 1", "Corte e Barba", "40,00");
        Servico combo2 = new Servico("Combo 2", "Corte, hidratação e selagem" +
                " (preço pode variar de acordo o tamanho do cabelo)", "60,00");

        // -->> Cabelo
        Servico lavagem = new Servico("Lavagem", "Cabelo", "5,00");
        Servico acabamento = new Servico("Acabamento", "Cabelo", "10,00");
        Servico hidratacao = new Servico("Hidratação", "Cabelo", "12,00");
        Servico corte = new Servico("Corte", "Cabelo", "25,00");
        Servico corteM = new Servico("Corte Maquina", "Cabelo", "20,00");
        Servico selagem = new Servico("Selagem", "Cabelo, preço pode variar de " +
                "acordo o tamanho do cabelo", "40,00");
        Servico corteA = new Servico("Corte Agendado", "Cabelo", "35,00");

        // -->> Barba
        Servico tonalização = new Servico("Tonalização", "Barba", "15,00");
        Servico barba = new Servico("Barba Pura", "Barba", "20,00");
        Servico designBarba = new Servico("Design de Barba", "Barba", "25,00");
        Servico barbaA= new Servico("Barba Agendada", "Barba", "30,00");

        // -->> Produtos
        Servico pMate = new Servico("Pomada Modeladora", " AC Cosmeticos, Efeito Mate", "25,90");
        Servico pTeia = new Servico("Pomada Modeladora", " AC Cosmeticos, Efeito Teia", "27,90");

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
}

