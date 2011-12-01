package jp.ac.toin.intlab.apps;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;	
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CheckActivity extends Activity{
    /** Called when the activity is first created. */
	public SharedPreferences sharedPreferences;
	private EditText editStop;
	private EditText editComment;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);
        final String problemNo = getIntent().getExtras().getString("problem_no");
        TextView tvProblem = (TextView) findViewById(R.id.problemtext);
        tvProblem.setText(problemNo);
        final String stateData = getIntent().getExtras().getString("state_data");
        TextView tvState = (TextView) findViewById(R.id.statetext);
        tvState.setText("State:"+ stateData);
        final String startTime = getIntent().getExtras().getString("start_time_data");
        TextView tvStart = (TextView) findViewById(R.id.starttext);
        tvStart.setText("Start:"+ startTime);
        final String endTime = getIntent().getExtras().getString("end_time_data");
        TextView tvEnd = (TextView) findViewById(R.id.endtext);
        tvEnd.setText("End:" + endTime);
        editStop = (EditText)findViewById(R.id.editStop);
        editComment = (EditText)findViewById(R.id.editComment);
        sharedPreferences = getSharedPreferences("record_PSP_Task", Context.MODE_PRIVATE);
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 
				// finish();
				/* 
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString("problemNo", problemNo);
				editor.putString("stateData", stateData);
				editor.putString("startTime", startTime);
				editor.putString("endTime", endTime);
				editor.putString("editStop", editStop.getText().toString());
				editor.putString("editComment", editComment.getText().toString());
				editor.commit();
				Intent intent = new Intent(CheckActivity.this, MainActivity.class);
				try{
					startActivity(intent);
					finish();
				}catch(Exception ex){
					finish();
				}
				*/
				//EditText et = (EditText)findViewById(R.id.edittext01);
				//String s = et.getText().toString();
				try{
					OutputStream out = openFileOutput("a.txt", MODE_APPEND);
					PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,"UTF-8"));
					//writer.append(s);
					//writer.append("stateData:"+stateData+", ");
					writer.append(problemNo+", ");
					writer.append(stateData+", ");
					writer.append(startTime+", ");
					writer.append(endTime+", ");
					writer.append("Interruption:"+editStop.getText().toString()+", ");
					writer.append("Memo:"+editComment.getText().toString()+", \n");
					writer.close();
					Intent intent = new Intent(CheckActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		});
    }
}
