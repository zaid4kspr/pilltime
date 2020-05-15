package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;
import android.util.TypedValue;



public class Test_dyn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dyn);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_example);

        // Add textview 1
        TextView textView1 = new TextView(this);
        textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        textView1.setText("programmatically created TextView1");
        textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
        textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
        linearLayout.addView(textView1);

        // Add textview 2
        TextView textView2 = new TextView(this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.RIGHT;
        layoutParams.setMargins(10, 10, 10, 10); // (left, top, right, bottom)
        textView2.setLayoutParams(layoutParams);
        textView2.setText("programmatically created TextView2");
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textView2.setBackgroundColor(0xffffdbdb); // hex color 0xAARRGGBB
        linearLayout.addView(textView2);
    }
}
