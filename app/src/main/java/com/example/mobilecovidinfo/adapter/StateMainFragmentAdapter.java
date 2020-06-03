package com.example.mobilecovidinfo.adapter;

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

    public StateMainFragmentAdapter(List<State> stateList) {
        this.stateList = stateList;
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
