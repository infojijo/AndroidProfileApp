package network;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

//response codes used in the app mentioned here.
@Retention(RetentionPolicy.SOURCE)
@IntDef(value = {ResponseCodes.SUCCESS, ResponseCodes.FAILURE, ResponseCodes.NETWORK_ERROR})
public @interface ResponseCodes {
    int SUCCESS = 200;
    int FAILURE = 400;
    int NETWORK_ERROR = 500;
}