package com.example.mobilecovidinfo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilecovidinfo.R;
import com.example.mobilecovidinfo.database.DatabaseConnection;
import com.example.mobilecovidinfo.databinding.StateInfoBinding;
import com.example.mobilecovidinfo.model.State;
import com.example.mobilecovidinfo.util.DateUtil;
import com.example.mobilecovidinfo.util.NumberUtil;
import com.example.mobilecovidinfo.view.MainFragmentDirections;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StateMainFragmentAdapter extends RecyclerView.Adapter<StateMainFragmentAdapter.ViewHolder> {

    private List<State> stateList;
    private final DatabaseConnection connection;

    public StateMainFragmentAdapter(List<State> stateList, Context context) {
        this.stateList = stateList;
        this.connection = DatabaseConnection.getInstance(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        StateInfoBinding stateInfoBinding = StateInfoBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(stateInfoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.stateInfoBinding.txtState.setText(this.stateList.get(position).getState());
        holder.stateInfoBinding.txtTotalCases.setText(NumberUtil.currencyFormat(String.valueOf(this.stateList.get(position).getCases())));
        holder.stateInfoBinding.txtTotalSuspects.setText(NumberUtil.currencyFormat(String.valueOf(this.stateList.get(position).getSuspects())));
        holder.stateInfoBinding.txtDtAtutalizacao.setText("Ultima atualização: "+DateUtil.dateFormat(this.stateList.get(position).getDatetime()));
        holder.stateInfoBinding.cardItem.setOnClickListener(view -> {
            NavDirections action = MainFragmentDirections.actionMainFragmentToStateDetailsFragment(this.stateList.get(position));
            Navigation.findNavController(view).navigate(action);
        });
        if(this.stateList.get(position).getCases() < 5000) {
            holder.stateInfoBinding.cardItem.setCardBackgroundColor(Color.parseColor("#0F9D58"));
        } else if(this.stateList.get(position).getCases() < 10000) {
            holder.stateInfoBinding.cardItem.setCardBackgroundColor(Color.parseColor("#e5c827"));
        } else {
            holder.stateInfoBinding.cardItem.setCardBackgroundColor(Color.parseColor("#dd361c"));
        }
        State stateDb = this.connection.stateDao().getById(this.stateList.get(position).getUid());
        if(this.stateList.get(position).getDeaths() > stateDb.getDeaths()) {

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return this.stateList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        StateInfoBinding stateInfoBinding;

        public ViewHolder(@NonNull StateInfoBinding stateInfoBinding) {
            super(stateInfoBinding.getRoot());
            this.stateInfoBinding = stateInfoBinding;
        }
    }
}
