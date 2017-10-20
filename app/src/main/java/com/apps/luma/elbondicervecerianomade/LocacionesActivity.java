package com.apps.luma.elbondicervecerianomade;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.apps.luma.elbondicervecerianomade.http.FeedTask;
import com.apps.luma.elbondicervecerianomade.http.FeedTaskListener;

import org.json.JSONObject;

import static android.Manifest.permission.INTERNET;

public class LocacionesActivity extends AppCompatActivity implements FeedTaskListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locaciones);
    }

    public void pedirDatos() {
        if (ActivityCompat.checkSelfPermission(this, INTERNET) != PackageManager.PERMISSION_GRANTED) {
            //request permission from user if the app hasn't got the required permission
            ActivityCompat.requestPermissions(this, new String[]{INTERNET}, 10);

            return;
        } else {
            //Desarrollo servidor
            //String url = "http://el-bondi-server.firebaseapp.com/app/loc";
            //Desarrollo local
            String url = "http://10.0.2.2:5000/app/loc?cantidad=15";
            FeedTask ft = new FeedTask(url);
            ft.addListener(this);
            ft.execute();
        }
    }

    @Override
    public void taskCompleted(JSONObject obj) {


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            pedirDatos();
        } else {
            //TODO: Manejar permisos not granted
            finish();
        }
        return;

    }

    private void showSnackbar(String s) {

        Snackbar
                .make(findViewById(R.id.snack_position), s, Snackbar.LENGTH_LONG)
                .show();
    }

}
