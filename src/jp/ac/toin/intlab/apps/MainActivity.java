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
	Spinner problemSpinner;
	Spinner stateSpinner;
	String[] problems;
	String problemNo;
	String[] states;
	String stateData;
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        problemSpinner = (Spinner)findViewById(R.id.spinner1);
        stateSpinner = (Spinner)findViewById(R.id.spinner2);
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
        problems = getResources().getStringArray(R.array.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                problems);
        
        problemSpinner.setAdapter(adapter);
        problemSpinner.setOnItemSelectedListener(this);

        states = getResources().getStringArray(R.array.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
          this,
          android.R.layout.simple_spinner_item,
          states);
        adapter2.setDropDownViewResource(
          android.R.layout.simple_spinner_dropdown_item);
        
        stateSpinner.setAdapter(adapter2);
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
				intent.putExtra("problem_no", problemNo);
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
			    if(arg0.getId() == problemSpinner.getId()){
			    Toast.makeText(
					      this, 
					      problems[i], 
					      Toast.LENGTH_SHORT).show();
			    Toast.makeText(
			      this, 
			      problems[i], 
			      Toast.LENGTH_SHORT).show();
			      problemNo = problems[i];
			    } else if (arg0.getId() == stateSpinner.getId()){
			      Toast.makeText(
					      this, 
					      states[i], 
					      Toast.LENGTH_SHORT).show();
			    Toast.makeText(
			      this, 
			      states[i], 
			      Toast.LENGTH_SHORT).show();
			      stateData = states[i];
			    }
			  }
			  @Override
			  public void onNothingSelected(AdapterView<?> arg0) {}

}