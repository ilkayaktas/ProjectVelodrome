package com.ilkayaktas.projectname.controller.services.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.WindowManager;

import com.ilkayaktas.projectname.controller.strategy.Strategy;

import javax.inject.Inject;


/**
 * Created by ilkay on 05/02/2017.
 */

public class MobssAsyncTask extends AsyncTask<Void, Void, Void> {
    
    Activity baseActivity;
    Strategy strategy;
    
    @Inject
    public MobssAsyncTask(Activity baseActivity, Strategy strategy) {
        this.baseActivity = baseActivity;
        this.strategy = strategy;
    }
    
    private ProgressDialog progressDialog;
    
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        startProgressDialogBelow();
    }

    @Override
    protected Void doInBackground(Void... params) {
    
        strategy.execute();
        
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        stopProgressDialog();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Void result) {
        super.onCancelled(result);
    }

    private void startProgressDialogBelow() {
        progressDialog = ProgressDialog.show(baseActivity, "", "Lütfen bekleyiniz!", true);
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams wmlp = progressDialog.getWindow().getAttributes();
        wmlp.y = baseActivity.getResources().getDisplayMetrics().heightPixels / 4;
        progressDialog.getWindow().setAttributes(wmlp);
        progressDialog.setCancelable(false);
    }

    private void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }
}
