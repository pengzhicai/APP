package com.android.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.aidlserver.IMyAidl;

public class MainActivity extends AppCompatActivity {

    IMyAidl iMyAidl;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidl = IMyAidl.Stub.asInterface(iBinder);
            try {
                String s = iMyAidl.send("fdsafdsa");
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(MainActivity.this, iMyAidl.send("onclick"), Toast.LENGTH_LONG).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        initService();
    }

    private void initService(){
        Intent intent = new Intent();
        intent.setPackage("com.android.aidlserver");
        intent.setAction("android.intent.action.MyAidlService");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
