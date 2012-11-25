package com.example.badfriend;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.example.badfriend.R;

import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button sendButt;
	EditText phoneNumber;
	EditText messageField;
	Button actButt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sendButt = (Button) findViewById(R.id.button1);
		phoneNumber = (EditText) findViewById(R.id.phoneNum);
		messageField = (EditText) findViewById(R.id.editText2);
		actButt = (Button) findViewById(R.id.button2);
		
		sendButt.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) {

				String phoneNum = phoneNumber.getText().toString();
				String mes = messageField.getText().toString();
				if(phoneNum.length()>0 && mes.length()>0){
					sendSMS(phoneNum,mes);
				}
				else{
					Toast.makeText(getBaseContext(), "Please enter both phone number and message.",
							Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		actButt.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, SMS.class);
				startActivity(intent);
				finish();
			}
		});
		
	}

	
	private void sendSMS(String phoneNum, String mes)
	{
		String sent = "android.telephony.SmsManager.STATUS_ON_ICC_SENT";
		PendingIntent pi = PendingIntent.getActivity(this,  0,  new Intent(sent), 0);
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNum, null, mes, pi, null);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
