package com.lco.todolast;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourceAdapter extends RecyclerView.Adapter<CourceAdapter.CourceViewHolder> {
    List<Todo> noteslist;
    private Context context;
    public CourceAdapter(List<Todo>noteslist,Context context){
        this.noteslist=noteslist;
        this.context=context;
    }
    @NonNull
    @Override
    public CourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.item,parent,false);
        return new CourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourceViewHolder holder, int position) {
    Todo data=noteslist.get(position);
    holder.titlerecycler.setText(data.title);
    holder.descrecycler.setText(data.desc);
    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class CourceViewHolder extends RecyclerView.ViewHolder{
        TextView titlerecycler,descrecycler;

        public CourceViewHolder(@NonNull View itemView) {
            super(itemView);
            titlerecycler=itemView.findViewById(R.id.ittitle);
            descrecycler=itemView.findViewById(R.id.itdesc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Todo todo=noteslist.get(getAdapterPosition());
                    Intent i=new Intent(context,ViewDetails.class);
                    i.putExtra("id",todo.id);
                    i.putExtra("title",todo.title);
                    i.putExtra("desc",todo.desc);
                    context.startActivity(i);
                }
            });

        }
    }
}
