package com.example.jose.ejercicio_fragments;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener, Fragment3.OnFragmentInteractionListener , plusClicks{

    private RelativeLayout staticFragment;
    private FrameLayout frameLayoutTop;
    private FrameLayout frameLayoutBot;
    private TextView fragment3Active;
    private TextView fragment2Active;
    private int contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        staticFragment = (RelativeLayout) findViewById(R.id.staticFragment);
        frameLayoutTop = (FrameLayout) findViewById(R.id.frameLayout_top);
        frameLayoutBot = (FrameLayout) findViewById(R.id.frameLayout_bot);
        fragment3Active = (TextView) findViewById(R.id.f3);
        fragment2Active = (TextView) findViewById(R.id.f2);
        contador = 0;

        staticFragment.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                String fragmentTag2 = "Fragment2";
                String fragmentTag3 = "Fragment3";
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment;

                fragment = fragmentManager.findFragmentByTag(fragmentTag3);
                if (fragment != null){
                    fragmentManager.beginTransaction()
                            .remove(fragment)
                            .commit();
                    fragmentManager.popBackStack();
                    fragment3Active.setText("Inactivo");
                    fragment3Active.setTextColor(Color.parseColor("#b41c0b"));
                    Toast.makeText(getApplicationContext(),"Fragment 3 Eliminado.",Toast.LENGTH_SHORT).show();
                }


                fragment = fragmentManager.findFragmentByTag(fragmentTag2);
                if (fragment != null) {
                    fragmentManager.beginTransaction()
                            .remove(fragment)
                            .commit();
                    fragmentManager.popBackStack();
                    fragment2Active.setText("Inactivo");
                    fragment2Active.setTextColor(Color.parseColor("#b41c0b"));
                    Toast toast = Toast.makeText(getApplicationContext(), "Fragment 2 Eliminado", Toast.LENGTH_SHORT);
                    toast.show();

                }

                return false;
            }
        });

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
            fragment2Active.setText("Activo");
            fragment2Active.setTextColor(Color.parseColor("#201ddf"));
        }
    }

    private void addDeleteFragment3() {
        String fragmentTag = "Fragment3";
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment3 fragment = (Fragment3) fragmentManager.findFragmentByTag(fragmentTag);

        if (fragment == null) {
            fragment = Fragment3.newInstance("", "", contador);
            fragmentManager.beginTransaction()
                    .replace(frameLayoutBot.getId(), fragment, fragmentTag)
                    .addToBackStack(fragmentTag)
                    .commit();
            fragment3Active.setText("Activo");
            fragment3Active.setTextColor(Color.parseColor("#201ddf"));
        } else {
            fragmentManager.beginTransaction()
                    .remove(fragment)
                    .commit();
            fragmentManager.popBackStack();
            fragment3Active.setText("Inactivo");
            fragment3Active.setTextColor(Color.parseColor("#b41c0b"));
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String fragmentTag2 = "Fragment2";
        String fragmentTag3 = "Fragment3";
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment2 = fragmentManager.findFragmentByTag(fragmentTag2);
        Fragment fragment3 = fragmentManager.findFragmentByTag(fragmentTag3);
        if(fragment2 == null) {
            fragment2Active.setText("Inactivo");
            fragment2Active.setTextColor(Color.parseColor("#b41c0b"));
        }
        if(fragment3 == null) {
            fragment3Active.setText("Inactivo");
            fragment3Active.setTextColor(Color.parseColor("#b41c0b"));
        }
    }

    @Override
    public void add1click(int click) {
        String fragmentTag3 = "Fragment3";
         FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment3 fragment3 = (Fragment3) fragmentManager.findFragmentByTag(fragmentTag3);
        if(fragment3 != null){
            contador= contador+click;
            fragment3.plusOneClick(click);
        }else{
            Toast.makeText(getApplicationContext(),"Clica el fondo para activar el Fragmento3!",Toast.LENGTH_SHORT).show();

        }


    }
}