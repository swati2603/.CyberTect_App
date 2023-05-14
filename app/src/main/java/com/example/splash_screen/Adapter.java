package com.example.splash_screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder>
{
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<item> items;
    public Adapter(Context context, ArrayList<item> items, RecyclerViewInterface recyclerViewInterface){
        this.context=context;
        this.items=items;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_view,parent,false);
        return new Adapter.myViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.myViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.emailView.setText((items.get(position).getEmail()));
        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class myViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nameView,emailView;
        public myViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            nameView = itemView.findViewById(R.id.name);
            emailView = itemView.findViewById(R.id.email);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface!=null)
                    {
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}

