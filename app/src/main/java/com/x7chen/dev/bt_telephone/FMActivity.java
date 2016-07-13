package com.x7chen.dev.bt_telephone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class FMActivity extends AppCompatActivity {
    TextView textView;
    float mFrequency;
    FMPointing fmPointing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);
        fmPointing = (FMPointing) findViewById(R.id.fm_pointing);


        mFrequency = fmPointing.getFrequency();
        textView = (TextView) findViewById(R.id.tv_frequency);

        ImageButton frequency_reduce = (ImageButton) findViewById(R.id.fm_reduce);
        frequency_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFrequency >= 76.1) {
                    mFrequency -= 0.1;
                    upDateFrequency();
                    fmPointing.setFrequency(mFrequency);
                }
            }
        });
        ImageButton frequency_increase = (ImageButton) findViewById(R.id.fm_increase);
        frequency_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFrequency <= 107.9) {
                    mFrequency += 0.1;
                    upDateFrequency();
                    fmPointing.setFrequency(mFrequency);
                }
            }
        });
        fmPointing.setCallBacks(new FMPointing.CallBacks() {
            @Override
            public void onFrequencyChanged(float frequency) {
                mFrequency = frequency;
                upDateFrequency();
            }
        });
    }
    void upDateFrequency() {
        textView.setText(String.format("%3.1f", mFrequency));
    }
}
