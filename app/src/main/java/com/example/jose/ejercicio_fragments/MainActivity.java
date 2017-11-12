package com.example.jose.ejercicio_fragments;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener, Fragment3.OnFragmentInteractionListener {

    private LinearLayout fragmentLayoutStatic;
    private FrameLayout frameLayout1;
    private FrameLayout frameLayout2;
    private boolean fragment2Activo = false;
    private boolean fragment3Activo = false;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private boolean primeraActivacion = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment2 = Fragment2.newInstance("","");
        fragment3 = Fragment3.newInstance("","");

        fragmentLayoutStatic = (LinearLayout) findViewById(R.id.staticFragment);
        frameLayout1 = (FrameLayout) findViewById(R.id.FrameLayout1);
        frameLayout2 = (FrameLayout) findViewById(R.id.FrameLayout2);

        fragmentLayoutStatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putFragment2();
            }
        });
        frameLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fragment3Activo){
                    putFragment3();
                }else{
                    putOutFragment3();
                }
            }
        });

    }

    private void putFragment3() {
        if(!primeraActivacion){
            primeraActivacion = true;
            fragment3Activo = true;
            getFragmentManager().beginTransaction().remove(fragment3).commit();
            getFragmentManager().beginTransaction().replace(frameLayout2.getId(), fragment3).addToBackStack(null).commit();
        }else{
            fragment3Activo = true;
            getFragmentManager().beginTransaction().remove(fragment3).commit();
            getFragmentManager().beginTransaction().replace(frameLayout2.getId(), fragment3).commit();
        }
    }
    private void putOutFragment3(){
        getFragmentManager().beginTransaction().remove(fragment3).commit();
        fragment3Activo = false;

    }


    private void putFragment2() {
        if(fragment2Activo){
            getFragmentManager().beginTransaction().remove(fragment2).commit();
            getFragmentManager().beginTransaction().replace(frameLayout1.getId(), fragment2).commit();

        }else {
            getFragmentManager().beginTransaction().remove(fragment2).commit();
            getFragmentManager().beginTransaction().replace(frameLayout1.getId(), fragment2).addToBackStack(null).commit();
            fragment2Activo = true;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
