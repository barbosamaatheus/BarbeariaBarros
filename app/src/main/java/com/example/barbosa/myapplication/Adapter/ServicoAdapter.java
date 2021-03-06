package com.example.barbosa.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barbosa.myapplication.Interface.RecyclerViewOnClickListener;
import com.example.barbosa.myapplication.Objetos.Servico;
import com.example.barbosa.myapplication.R;

import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.MyViewHolder> {
    private List<Servico> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener r){
        mRecyclerViewOnClickListener = r;
    }


    public ServicoAdapter(Context c, List<Servico> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void addListItem(Servico s, int position) {
        mList.add(s);
        notifyItemInserted(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_list, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.mTitulo.setText(mList.get(position).getNome());
        myViewHolder.mSubTitulo.setText(mList.get(position).getDescricao());
        myViewHolder.mPreco.setText(mList.get(position).getPreco());
        myViewHolder.mValorPts.setText(mList.get(position).getValorPts());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitulo, mSubTitulo, mPreco, mValorPts;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTitulo = (TextView) itemView.findViewById(R.id.titulo);
            mSubTitulo = (TextView) itemView.findViewById(R.id.descricao);
            mPreco = (TextView) itemView.findViewById(R.id.preco);
            mValorPts = (TextView) itemView.findViewById(R.id.valorp);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListener != null){
                mRecyclerViewOnClickListener.onClickListener(v, getPosition());
            }

        }
    }

}

