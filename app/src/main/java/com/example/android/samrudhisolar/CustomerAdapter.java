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

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private List<CustomerModel> list;
    private Context context;
    RequestOptions option;

    public CustomerAdapter(Context context,List<CustomerModel> list) {

        this.context=context;
        this.list=list;
        option=new RequestOptions().centerCrop().placeholder(R.drawable.imagebackground).error(R.drawable.imagebackground);
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cudata_list,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        CustomerModel customerModel=list.get(position);
        holder.nameTV.setText(customerModel.getNamecu());
        holder.emailTV.setText(customerModel.getEmailcu());
        holder.addressTV.setText(customerModel.getAddresscu());
        holder.phoneTV.setText(customerModel.getPhonecu());
        Glide.with(context).load(list.get(position).getImagecu()).fitCenter().apply(option).into(holder.customerIV);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setData(List<CustomerModel> filteredList) {
        this.list = (ArrayList<CustomerModel>) filteredList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV,emailTV,addressTV,phoneTV;
        ImageView customerIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.idTVCUName);
            emailTV=itemView.findViewById(R.id.idTVCUEmail);
            addressTV=itemView.findViewById(R.id.idTVCUAddress);
            phoneTV=itemView.findViewById(R.id.idTVCUPhoneNumber);
            customerIV=itemView.findViewById(R.id.idIVcu);
        }
    }
}
