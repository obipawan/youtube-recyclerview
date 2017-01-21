package com.greycellofp.youtubelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity {
    private static final String FRAGMENT_TAG = MainActivityFragment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Fresco.initialize(this);
        ((EditText) findViewById(R.id.search)).addTextChangedListener(new TimedTextWatcher(600) {
            @Override
            public void work(final String text) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainActivityFragment fragment =
                                (MainActivityFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
                        fragment.setSearchString(text);
                    }
                });
            }
        });
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, new MainActivityFragment(), FRAGMENT_TAG)
                .commit();
    }

}
