package com.ilkayaktas.projectname.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.ilkayaktas.projectname.R;
import com.ilkayaktas.projectname.views.activities.base.BaseActivity;
import es.dmoral.toasty.Toasty;

/**
 * Created by iaktas on 13.11.2017 at 09:54.
 */

public class MessagesUtils {
    private MessagesUtils(){}

    public static void showToastInfo(Context context, String message){
        Toasty.info(context, message, Toast.LENGTH_SHORT, true).show();
    }

    public static void showToastError(Context context, String message){
        Toasty.error(context, message, Toast.LENGTH_SHORT, true).show();
    }

    public static void showToastSuccess(Context context, String message){
        Toasty.success(context, message, Toast.LENGTH_SHORT, true).show();
    }

    public static void showToastWarning(Context context, String message){
        Toasty.warning(context, message, Toast.LENGTH_SHORT, true).show();
    }

    public static void showToastNormal(Context context, String message){
        Toasty.normal(context, message).show();
    }

    public static void showToastNormal(Context context, String message, Drawable resource){
        Toasty.normal(context, message, resource).show();
    }

    public static void showSnackbar(Context context, String message){
        Snackbar snackbar = Snackbar.make(((BaseActivity)context).findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(context, R.color.mobss_color_white));
        snackbar.show();
    }

}
