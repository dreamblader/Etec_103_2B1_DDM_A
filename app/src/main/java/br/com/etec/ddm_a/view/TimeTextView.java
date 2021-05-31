package br.com.etec.ddm_a.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import br.com.etec.ddm_a.R;

public class TimeTextView extends FrameLayout {


    public TimeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.timetext_component,this);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TimeTextView);
        setTitle(array.getString(R.styleable.TimeTextView_title));
        setTime(array.getString(R.styleable.TimeTextView_time));
        array.recycle();
    }

    public void setTitle(String value){
        if(value!=null){
            TextView titleText = findViewById(R.id.am_title_text);
            titleText.setText(value);
        }
    }

    public void setTime(String value){
        if(value!=null) {
            TextView timeText = findViewById(R.id.am_timer_text);
            timeText.setText(value);
        }
    }

}
