package br.com.etec.ddm_a.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;


import br.com.etec.ddm_a.R;

public class TimeTextView extends ViewGroup {


    public TimeTextView(Context context) {
        super(context);
        initView();
    }

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void initView(){
        inflate(getContext(), R.layout.timetext_component,this);
    }
}
