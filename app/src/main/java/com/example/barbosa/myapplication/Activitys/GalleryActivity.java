package com.example.barbosa.myapplication.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.barbosa.myapplication.Adapter.CardAdapter;
import com.example.barbosa.myapplication.Adapter.ServicoAdapter;
import com.example.barbosa.myapplication.Objetos.ImagemCard;
import com.example.barbosa.myapplication.Objetos.Servico;
import com.example.barbosa.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar myToolbar;
    private List<ImagemCard> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        myToolbar = (Toolbar) findViewById(R.id.tb_main);
        myToolbar.setTitle("Fotos");
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
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = criarLista();
        CardAdapter cardAdapter = new CardAdapter(this, mList);
        mRecyclerView.setAdapter(cardAdapter);


    }
    public List<ImagemCard> criarLista() {
        List<ImagemCard> lista = new ArrayList<ImagemCard>();
        for(int i = 0; i < 10; i++){
            ImagemCard imagemCard = new ImagemCard(R.mipmap.barbearia, "Titulo", "descrição");
            lista.add(imagemCard);

        }


        return lista;
    }
}
