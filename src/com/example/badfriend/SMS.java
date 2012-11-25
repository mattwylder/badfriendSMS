package com.example.badfriend;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMS extends Activity {
	Button sendButt;
	Button backButton;
	EditText phoneNumber;
	EditText messageField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		sendButt = (Button) findViewById(R.id.sendButton);
		backButton = (Button) findViewById(R.id.sendButton);
		phoneNumber = (EditText) findViewById(R.id.phoneNum);
		messageField = (EditText) findViewById(R.id.messageText);
	
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
		
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SMS.this, MainActivity.class);
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
		getMenuInflater().inflate(R.menu.activity_sms, menu);
		return true;
	}

}
