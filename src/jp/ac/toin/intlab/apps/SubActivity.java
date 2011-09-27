package jp.ac.toin.intlab.apps;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;	
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity extends Activity{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);
        final String stateData = getIntent().getExtras().getString("state_data");
        final String startTime = getIntent().getExtras().getString("time_data");
        TextView tv = (TextView) findViewById(R.id.states);
        TextView tv2 = (TextView) findViewById(R.id.text);
        tv.setText("作業工程：" + stateData);
        tv2.setText("開始時間：" + startTime);
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
				String endTime = 
						Integer.toString(year) 
						+ "/" + Integer.toString(month) 
						+ "/" + Integer.toString(day)
						+ "-" + Integer.toString(hour24) 
						+ ":" + Integer.toString(minute);
				Intent intent2 = new Intent(SubActivity.this, CheckActivity.class);
		        // String startTime = getIntent().getExtras().getString("time_data");
				intent2.putExtra("state_data", stateData);
				intent2.putExtra("start_time_data", startTime);
				intent2.putExtra("end_time_data", endTime);
				try{
					startActivity(intent2);
					finish();
				}catch(Exception ex){
					finish();
				}
			}
		});
    }
    public void clickEventframLayoutFile(View view) {
		Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show();
	}
}
