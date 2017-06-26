package com.example.receiversms;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by innova4b on 22/06/17.
 */

public class SMSBroadcastReceiver extends BroadcastReceiver {
    final  String SMS_RECEIVER="android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(SMS_RECEIVER.equals(intent.getAction())){
            Bundle bundle=intent.getExtras();
            Object[] pdus = (Object[]) bundle.get("pdus");
            String format=bundle.getString("format");

            final SmsMessage[] messages=new SmsMessage[pdus.length];
            for(int i=0; i<pdus.length;i++){
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    messages[i]=SmsMessage.createFromPdu((byte[]) pdus[i],format);

                }else{
                    messages[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                Toast.makeText(context, messages[i].getMessageBody(),Toast.LENGTH_LONG).show();

            }
        }


    }

}
