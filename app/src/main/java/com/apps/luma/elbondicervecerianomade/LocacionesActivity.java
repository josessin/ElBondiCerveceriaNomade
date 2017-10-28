package com.apps.luma.elbondicervecerianomade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LocacionesActivity extends AppCompatActivity {
    private String info;

    public LocacionesActivity(String info) {
        this.info = info;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locaciones);


   /* @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pedirDatos();
        } else {
            //TODO: Manejar permisos not granted
            finish();
        }
        return;

    }*/

 /*   private void showSnackbar(String s) {

        Snackbar
                .make(findViewById(R.id.snack_position), s, Snackbar.LENGTH_LONG)
                .show();
    }*/

    }
}
