package jp.ac.toin.intlab.apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;	
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity 
	implements OnItemSelectedListener {
	Spinner stateSpinner;
	String[] states;
	String stateData;
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        stateSpinner = (Spinner)findViewById(R.id.spinner1);
        try{
        	InputStream in = openFileInput("a.txt");
        	BufferedReader reader =
        		new BufferedReader(new InputStreamReader(in,"UTF-8"));
        	String s;
        	EditText et = (EditText)findViewById(R.id.editText1);
        	while((s = reader.readLine())!= null){
        		et.append(s);
        		et.append("\n");
        	}
        		reader.close();
        }catch(IOException e){
        		e.printStackTrace();
        }
        states = getResources().getStringArray(R.array.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
          this,
          android.R.layout.simple_spinner_item,
          states);
        adapter.setDropDownViewResource(
          android.R.layout.simple_spinner_dropdown_item);
        
        stateSpinner.setAdapter(adapter);
        stateSpinner.setOnItemSelectedListener(this);
        
     
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				// TODO
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				month = month +1;
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int hour24 = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				String startTime = 
						Integer.toString(year) 
						+ "/" + Integer.toString(month) 
						+ "/" + Integer.toString(day)
						+ "-" + Integer.toString(hour24) 
						+ ":" + Integer.toString(minute);
				Intent intent = new Intent(MainActivity.this, SubActivity.class);
				intent.putExtra("state_data", stateData);
				intent.putExtra("time_data", startTime);
				startActivity(intent);
			}
		});
    }
    public void clickEventframLayoutFile(View view) {
		Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();
	}
	@Override
	  public void onItemSelected(
			    AdapterView<?> arg0, 
			    View arg1, 
			    int arg2,
			    long arg3) {
			    
			    int i = arg0.getSelectedItemPosition();
			    Toast.makeText(
			      this, 
			      states[i], 
			      Toast.LENGTH_SHORT).show();
			      stateData = states[i];
			  }

			  @Override
			  public void onNothingSelected(AdapterView<?> arg0) {}

}