package com.apps.luma.elbondicervecerianomade;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.apps.luma.elbondicervecerianomade.exceptions.LoginException;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class StartActivity extends AppCompatActivity /*implements View.OnClickListener*/ {
    private static final int RC_SIGN_IN = 123;
    FirebaseAuth auth;

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {
            signIn();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            //User Already signed in
            //AuthUI.getInstance().signOut(this);
            Log.d("AUTH", auth.getCurrentUser().getEmail());
            irAlMenu();
        } else {

            signIn();

        }
        //  findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    private void signIn() {

        if (isNetworkAvailable()) {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setTheme(R.style.AppTheme)
                    .setProviders(
                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))
                    .build(), RC_SIGN_IN);
        } else {
            throw new LoginException();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            // Successfully signed in
            if (resultCode == RESULT_OK) {
                irAlMenu();
                return;
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    finish();
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    //  showSnackbar(R.string.no_internet_connection);
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    //   showSnackbar(R.string.unknown_error);
                    return;
                }
            }

            //  showSnackbar(R.string.unknown_sign_in_response);
        }
    }


    private void irAlMenu() {
        Intent i = new Intent(this, MenuActivity.class);
        i.putExtra("USER_EMAIL", auth.getCurrentUser().getDisplayName());
        startActivity(i);
        finish();
    }


    /*private void showSnackbar(int s) {

        Snackbar
                .make(findViewById(R.id.snack_position), s, Snackbar.LENGTH_LONG)
                .show();
    }*/

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
