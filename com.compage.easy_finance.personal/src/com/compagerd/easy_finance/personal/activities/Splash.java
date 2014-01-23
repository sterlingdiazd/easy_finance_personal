package com.compagerd.easy_finance.personal.activities;

import com.compagerd.easy_finance.personal.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class Splash extends Activity {

	 ProgressDialog progressDialog;
	 boolean isRegistered;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressDialog = new ProgressDialog(this); 
        new SplashAsyncTask(this).execute(); 
    }

    @Override
	public void onPause() {
		super.onPause();
		progressDialog.dismiss();
	}
    
    private class SplashAsyncTask extends AsyncTask<Void, Integer, Boolean> {

    	Splash splash;
    	
    	private SplashAsyncTask(Splash splash) {
			super();
			this.splash = splash;
		}
    
		@Override
		protected void onPreExecute() {
			 	progressDialog.setMessage("Cargando");
		        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		        progressDialog.setProgress(0);
		        progressDialog.setMax(100);
		        //progressDialog.show();
		}
    	
		@Override
		protected Boolean doInBackground(Void... arg0) {
			
			boolean result = false;
			
			for(int x = 0, y = 10; x < y; x++)
			{
				databaseCall();
				publishProgress(x);	
			}
			
			result = true;
			return result;
		}

		public void databaseCall(){
			try{
				Thread.sleep(100);
			} catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			
			Integer[] integer = values;
			int progress = integer[0].intValue();			
			progressDialog.setProgress(progress);
			
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if(result == true){	
				this.splash.finish();
				startActivity(new Intent(splash.getBaseContext(), Dashboard.class));
			} else {
				Toast.makeText( splash.getApplicationContext(), this.splash.getResources().getString(R.string.login_not_valid_user), Toast.LENGTH_SHORT).show();
			}
		}

    } 
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_splash, menu);
        return true;
    }   
}
