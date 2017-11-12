package com.example.jose.ejercicio_fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener, Fragment3.OnFragmentInteractionListener {

    private LinearLayout staticFragment;
    private FrameLayout frameLayoutTop;
    private FrameLayout frameLayoutBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        staticFragment = (LinearLayout) findViewById(R.id.staticFragment);
        frameLayoutTop = (FrameLayout) findViewById(R.id.frameLayout_top);
        frameLayoutBot = (FrameLayout) findViewById(R.id.frameLayout_bot);

        staticFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment2();
            }
        });

        frameLayoutTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addDeleteFragment3();
            }
        });

    }

    private void addFragment2() {
        String fragmentTag = "Fragment2";

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);

        if (fragment == null) {
            fragment = Fragment2.newInstance("", "");
            fragmentManager.beginTransaction()
                    .replace(frameLayoutTop.getId(), fragment, fragmentTag)
                    .addToBackStack(fragmentTag)
                    .commit();
        }
    }

    private void addDeleteFragment3() {
        String fragmentTag = "Fragment3";
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);

        if (fragment == null) {
            fragment = Fragment3.newInstance("", "");
            fragmentManager.beginTransaction()
                    .replace(frameLayoutBot.getId(), fragment, fragmentTag)
                    .addToBackStack(fragmentTag)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
            fragmentManager.popBackStack();
        }

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
