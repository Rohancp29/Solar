package com.example.android.samrudhisolar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MyAdaptercommunity extends RecyclerView.Adapter<MyAdaptercommunity.MyViewHolder>  {
    private List<Model> list;
    private Context context;
    RequestOptions option;
    public MyAdaptercommunity(Context context, List<Model> list){
        this.context=context;
        this.list=list;
        option=new RequestOptions().centerCrop().placeholder(R.drawable.imagebackground).error(R.drawable.imagebackground);
    }

    @NonNull
    @Override
    public MyAdaptercommunity.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdaptercommunity.MyViewHolder holder, int position) {
        Model model=list.get(position);
        holder.name.setText(model.getDealername());
        holder.email.setText(model.getEmailcu());
        holder.add.setText(model.getAddresscu());
        holder.phone.setText(model.getPhonecu());
        Glide.with(context).load(list.get(position).getImagecu()).fitCenter().apply(option).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setData(List<Model> filteredList) {
        this.list = (ArrayList<Model>) filteredList;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,add,email,phone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.idTVName);
            add=itemView.findViewById(R.id.idTVAddress);
            email=itemView.findViewById(R.id.idTVEmail);
            phone=itemView.findViewById(R.id.idTVPhoneNumber);
            imageView=itemView.findViewById(R.id.idIVUser);

        }
    }
}

