package com.greycellofp.youtubelist;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.Timer;
import java.util.TimerTask;

abstract class TimedTextWatcher implements TextWatcher {
    private Timer timer;
    private long delay = 600;

    TimedTextWatcher(long delay) {
        this.delay = delay;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void afterTextChanged(final Editable editable) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                work(editable.toString());
            }
        }, delay);
    }

    abstract void work(String text);
}
