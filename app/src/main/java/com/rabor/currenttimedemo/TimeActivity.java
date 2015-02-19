/* This class is an example of using a bound service.  Bound services allows us to communicate
 * with the service better */

package com.rabor.currenttimedemo;

import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.os.Binder;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.os.IBinder;
import android.content.Context;
import android.content.ComponentName;
import android.content.ServiceConnection;
import com.rabor.currenttimedemo.MyService.MyLocalBinder;


public class TimeActivity extends ActionBarActivity {

    MyService myService;        // reference to the class
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        Intent i = new Intent(this, MyService.class);
        bindService(i, myConnection, Context.BIND_AUTO_CREATE);     // bind the service
    }

    // display the time
    public void showTime(View view) {
        String currentTime = myService.getCurrentTime();
        TextView timeTextView = (TextView) findViewById(R.id.timeTextView);
        timeTextView.setText(currentTime);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // action class that will bind to the service
    private ServiceConnection myConnection = new ServiceConnection() {
        // what is it that you want to do when you connect
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service;
            myService = binder.getService();        // get service to the class
            isBound = true;                         // bound to a service
        }

        // what do you want to happen
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;                        // disconnect the service
        }
    };
}
