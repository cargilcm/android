package com.example.mailmanapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test15droid.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity implements OnClickListener {
	
	private EditText text = null;
	private Button button = null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(this);
        TextView text = (TextView)findViewById(R.id.TextView01);
		text.setHint(null);
		//text = (TextView)findViewById(R.id.textView1);
    }
	
	public void onClick(View v) {
		text = (EditText)findViewById(R.id.editText1);
		text.setText("");
		String hi="hi";
		File f = new File("/mnt/sdcard/test.txt");
		try {
			f.createNewFile();
			FileWriter fw = new FileWriter(f);
			fw.append(hi);
			String out="";
			fw.flush();
			fw.close();
			Scanner scan = new Scanner(f);
			while(scan!=null && scan.hasNext())
				out+=scan.next();
			text.setText(out);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Perform action on click
		DownloadFilesTask task = new DownloadFilesTask();
		text.setText(new DownloadFilesTask().doInBackground(null));//task.execute());
	}
	
	
	 @SuppressWarnings("unused")
	private class DownloadFilesTask extends AsyncTask<String, Integer, String> {
	    
		 private Computater c = null;
	     private String computaterOutput = "";
	     
		 protected String doInBackground(String... urls) {
				try{
					Runnable runner = new Runnable(){
						public void run(){
							c = new Computater();
							computaterOutput = c.getArchiverMonths().get(0);	
						}
					};
					Thread thread = new Thread(runner);
					thread.start();
					runner.run();
				 }
				catch(Exception e){
					Log.i("exception in doInBg",e.toString());
				 }
				return computaterOutput;
		 }
		 public String execute(){
			 return computaterOutput; 
		 }
	 }
}   
