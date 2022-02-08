package com.example.supermarket;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MarketAdapter extends RecyclerView.Adapter  {
    private ArrayList<MarketRate> marketData;
    static View.OnClickListener mOnItemClickListener;
    private boolean isDeleting;
    private Context parentContext;

    public class MarketViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewMarket;
        public TextView textAvgRate;
        public Button deleteButton;
        public Button viewButton;
        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewMarket = itemView.findViewById(R.id.textMarketName);
            textAvgRate = itemView.findViewById(R.id.textAvgRate);
            deleteButton = itemView.findViewById(R.id.buttonDeleteMarket);
            viewButton = itemView.findViewById(R.id.buttonView);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public TextView getMarketTextView() {
            return textViewMarket;
        }
        public TextView getAvgRateTextView() {
            return textAvgRate;
        }
        public Button getDeleteButton() {
            return deleteButton;
        }
        public Button getViewButton() { return viewButton;}
    }



    public MarketAdapter(ArrayList<MarketRate> arrayList, Context context) {
        marketData = arrayList;
        parentContext = context;
    }

    public static void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MarketViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MarketViewHolder mvh = (MarketViewHolder) holder;
        mvh.getMarketTextView().setText(marketData.get(position).getMarketName());
        mvh.getAvgRateTextView().setText(marketData.get(position).getAvgRate()+"");
        //If the adapter is in delete mode, the delete button for each contact is set to be visible
        mvh.getViewButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (mvh.itemView.getContext(), MainActivity.class);
                intent.putExtra("marketId",marketData.get(position).getMarketID());
                mvh.itemView.getContext().startActivity(intent);
            }
        });


        if (isDeleting) {
            mvh.getDeleteButton().setVisibility(View.VISIBLE);
            mvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(position);
                }
            });
        }
        else {
            mvh.getDeleteButton().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return marketData.size();
    }

    private void deleteItem(int position) {
        MarketRate market = marketData.get(position);
        MarketDataSource ds = new MarketDataSource(parentContext);
        try {
            ds.open();
            boolean didDelete = ds.deleteMarket(market.getMarketID());
            ds.close();
            if (didDelete) {
                marketData.remove(position);
                notifyDataSetChanged();
            }
            else {
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception e) {

        }
    }

    public void setDelete(boolean b) {
        isDeleting = b;
    }



    


}


