package com.abhinav.covid19tracker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetwork {
    public static NetworkInfo networkInfo;
    public static boolean isConnected(Context context)
    {
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        try {
            networkInfo=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        if (networkInfo!=null&&networkInfo.isAvailable()&&networkInfo.isConnected())
        {
            return true;
        }

        networkInfo=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo!=null&&networkInfo.isAvailable()&&networkInfo.isConnected())
        {
            return true;
        }
        return false;
    }
}

