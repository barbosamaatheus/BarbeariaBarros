package com.example.barbosa.myapplication.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barbosa.myapplication.Objetos.ImagemCard;
import com.example.barbosa.myapplication.Objetos.Servico;
import com.example.barbosa.myapplication.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private List<ImagemCard> mList;
    private LayoutInflater mLayoutInflater;


    public CardAdapter(Context c, List<ImagemCard> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void addListItem(ImagemCard s, int position) {
        mList.add(s);
        notifyItemInserted(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_card, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.mTitulo.setText(mList.get(position).getTitulo());
        myViewHolder.mSubTitulo.setText(mList.get(position).getDescricao());
        myViewHolder.mImagem.setImageResource(mList.get(position).getImagem());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitulo, mSubTitulo;
        public ImageView mImagem;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTitulo = (TextView) itemView.findViewById(R.id.titulo_card);
            mSubTitulo = (TextView) itemView.findViewById(R.id.subTitle_card);
            mImagem = (ImageView) itemView.findViewById(R.id.image_card);
        }
    }

}

