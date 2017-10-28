package com.apps.luma.elbondicervecerianomade.exceptions;

import android.widget.Toast;

import com.apps.luma.elbondicervecerianomade.R;
import com.apps.luma.elbondicervecerianomade.StartActivity;

/**
 * Created by Jrepetto on 25/10/2017.
 */

public class LoginException extends RuntimeException {

    public static final String noInternetConnction = String.valueOf(R.string.no_internet_connection);
    public static final String errorGeneric = String.valueOf(R.string.general_error);
    public static final String conecting = String.valueOf(R.string.conecting);


}
