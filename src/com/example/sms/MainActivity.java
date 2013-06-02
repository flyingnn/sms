package com.example.sms;

import java.util.ArrayList;
import java.util.List;

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

public class MainActivity extends Activity {
	Button button;
	EditText mobile;
	EditText content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mobile = (EditText)findViewById(R.id.mobile);
				content = (EditText)findViewById(R.id.content);
				SmsManager smsManager = SmsManager.getDefault();
				List<String> msgs = smsManager.divideMessage(content.getText().toString());
				sendSMS(mobile.getText().toString(),content.getText().toString());
				/*
				 
				for (String msg:msgs){
					smsManager.sendTextMessage(mobile.getText().toString(), null, msg, null, null);
					
				}
				//Toast.makeText(getBaseContext(),"已发送！", Toast.LENGTH_LONG).show();
				Toast.makeText(MainActivity.this,"已发送！", Toast.LENGTH_LONG).show();
				*/
				}
			
		});
	}
	
	private void sendSMS(String phoneNumber, String message) {
		// ---sends an SMS message to another device---
		SmsManager sms = SmsManager.getDefault();
		PendingIntent pi = PendingIntent.getActivity(this, 0, 
	               new Intent(this,MainActivity.class), 0);
		//if message's length more than 70 ,
		//then call divideMessage to dive message into several part 
	        //and call sendTextMessage()
		//else direct call sendTextMessage()
		if (message.length() > 70) {
			ArrayList<String> msgs = sms.divideMessage(message);
			for (String msg : msgs) {
				sms.sendTextMessage(phoneNumber, null, msg, pi, null);
			}
		} else {
			sms.sendTextMessage(phoneNumber, null, message, pi, null);
		}
		Toast.makeText(MainActivity.this, "短信发送完成", Toast.LENGTH_LONG).show();
	}



}
