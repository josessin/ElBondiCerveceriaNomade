package com.apps.luma.elbondicervecerianomade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        auth = FirebaseAuth.getInstance();
        findViewById(R.id.btn_donde).setOnClickListener(this);
        findViewById(R.id.btn_productos).setOnClickListener(this);

    }


    public void signOut() {
        AuthUI.getInstance().signOut(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_donde:
                locaciones();
                break;
            case R.id.btn_productos:
                break;
        }
    }

    private void locaciones(){
        Intent i = new Intent(this,LocacionesActivity.class);
        startActivity(i);
    }
}
