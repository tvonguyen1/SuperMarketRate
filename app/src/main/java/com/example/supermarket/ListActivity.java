package com.example.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity   {
    RecyclerView marketList;
    MarketAdapter marketAdapter;
    MarketDataSource ds = new MarketDataSource(this);



    ArrayList<MarketRate> markets;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)  {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int marketId = markets.get(position).getMarketID();
            FragmentManager fm = getSupportFragmentManager();
            MarketRateDialog marketRateDialog = new MarketRateDialog();
            marketRateDialog.show(fm,"MarketRate");


        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        MarketAdapter.setOnItemClickListener(onItemClickListener);

        initBackButton();
        initDeleteSwitch();

    }




    private void initBackButton() {
        Button buttonList = findViewById(R.id.buttonMain);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    public void onResume() {
        super.onResume();

        try {
            ds.open();
            markets = ds.getMarkets();

            ds.close();
            //if ContactList is empty open MainActivity first
            //Check Manifest on how to prompt the app to open ContactList first
            if (markets.size() > 0) {
                marketList = findViewById(R.id.rvMarkets);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                marketList.setLayoutManager(layoutManager);
                marketAdapter = new MarketAdapter(markets, this);
                marketAdapter.setOnItemClickListener(onItemClickListener);
                marketList.setAdapter(marketAdapter);

            }


            else {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }

    }
    private void initDeleteSwitch() {
        Switch s = findViewById(R.id.switchDelete);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean status = compoundButton.isChecked();
                marketAdapter.setDelete(status);
                marketAdapter.notifyDataSetChanged();
            }
        });
    }





}
