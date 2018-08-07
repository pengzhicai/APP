package com.android.aidlserver.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.aidlserver.IMyAidl;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new myService();
    };

    class myService extends IMyAidl.Stub {

        @Override
        public String send(String s) throws RemoteException {
            return "pzc " + s;
        }
    }

}
