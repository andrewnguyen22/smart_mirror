package com.example.andrew_nguyen.smart_mirror.latitude_longitude;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.andrew_nguyen.smart_mirror.ui.Home;

/**
 * Created by andrew_nguyen on 9/8/17.
 */

public class Lat_Long_Dialog {
    Context ctx;

    public Lat_Long_Dialog(Context c) {
        ctx = c;
        pop_up();
    }

    public void pop_up() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle("Set New Weather Location");

            LinearLayout layout = new LinearLayout(ctx);
            layout.setOrientation(LinearLayout.VERTICAL);

            final EditText text_box_1 = new EditText(ctx);
            text_box_1.setHint("Enter a Location");
            layout.addView(text_box_1);

            builder.setView(layout);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!TextUtils.isEmpty(text_box_1.getText().toString().trim())) {
                        System.out.println("RESPONSE FROM USER + " + text_box_1.getText().toString());
                        Home.address = text_box_1.getText().toString().trim();
                    }
                    new Lat_Long_Parser().execute();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
