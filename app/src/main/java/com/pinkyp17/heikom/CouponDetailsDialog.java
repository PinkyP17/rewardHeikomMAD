package com.pinkyp17.heikom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

public class CouponDetailsDialog extends Dialog {
    private CardModel cardModel;

    public CouponDetailsDialog(@NonNull Context context, CardModel cardModel) {
        super(context);
        this.cardModel = cardModel;
        init();
    }

    private void init() {
        // Set the custom dialog layout
        setContentView(R.layout.activity_code_layout);

        // Configure dialog properties (e.g., width, height, animations)

        // Initialize views and set coupon details content
        CardView cardView = findViewById(R.id.cardView);
        // Set coupon details content using TextViews or other views

        // Set onClickListener for closing the dialog
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // Close the dialog when clicked
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add any additional initialization code here if needed
    }
}
