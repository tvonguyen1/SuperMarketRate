package com.example.supermarket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.fragment.app.DialogFragment;


public class MarketRateDialog extends DialogFragment {
    int liquorRating;
    int produceRating;
    int meatRating;
    int cheeseRating;
    int checkoutRating;
    double avgRating;





    public interface SaveRateListener {
        void didFinishMarketRateDialog(int rate1, int rate2, int rate3, int rate4, int rate5);
    }

    public MarketRateDialog() {
        // Empty constructor required for DialogFragment
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        final View view = inflater.inflate(R.layout.rating_dialog, container);
        getDialog().setTitle("Rate the Super Market!");


        final RatingBar liquorRate = view.findViewById(R.id.liquorRate);
        liquorRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                ratingBar.setRating(rate);
                liquorRating = (int) (ratingBar.getRating());

            }

        });

        final RatingBar produceRate = view.findViewById(R.id.produceRate);
        produceRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                ratingBar.setRating(rate);
                produceRating = (int) (ratingBar.getRating());
            }

        });

        final RatingBar meatRate = view.findViewById(R.id.meatRate);
        meatRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                ratingBar.setRating(rate);
                meatRating = (int) (ratingBar.getRating());
            }

        });
        final RatingBar cheeseRate = view.findViewById(R.id.cheeseRate);
        cheeseRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                ratingBar.setRating(rate);
                cheeseRating = (int) (ratingBar.getRating());
            }

        });


        final RatingBar checkoutRate = view.findViewById(R.id.checkoutRate);
        checkoutRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                ratingBar.setRating(rate);
                checkoutRating = (int)(ratingBar.getRating());
            }

        });
        avgRating = (checkoutRating+liquorRating+cheeseRating+meatRating+produceRating)/5.0;


        Button saveButton = view.findViewById(R.id.buttonSave);

            saveButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    saveItem(liquorRating, produceRating, meatRating, checkoutRating, checkoutRating);

                }
            });




        Button backButton = view.findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getDialog().dismiss();

            }
        });

        return view;

    }

    private void saveItem(int rate1, int rate2, int rate3, int rate4, int rate5) {
        SaveRateListener activity = (SaveRateListener) getActivity();
        activity.didFinishMarketRateDialog(rate1, rate2, rate3, rate4, rate5);
        getDialog().dismiss();

    }







}
