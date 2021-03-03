package sample.android.example.alarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static androidx.core.content.ContextCompat.getSystemService;

public class MainActivity extends AppCompatActivity{

    private TextView textView;
    private EditText editText;
    MyBroadcastReceiver myBroadcastReceiver;
    IntentFilter intentFilter;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBroadcastReceiver = new MyBroadcastReceiver();
        editText = (EditText)findViewById(R.id.editText);

        intentFilter = new IntentFilter("action");
        findViewById(R.id.button).setOnClickListener(btnClickListener);
    }


    //Activityが表示された時
    @Override
    protected void onResume() {
            super.onResume();
            //registerReceiver 実行するBroadcastReceverを登録
            registerReceiver(myBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //BroadcastReceverを解除
        unregisterReceiver(myBroadcastReceiver);
    }
    //ボタンを押したときの処理
    View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = editText.getText().toString();
            Intent intent = new Intent("action");
            intent.putExtra("KEY",str);
            sendBroadcast(intent);
        }
    };

    private class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            textView = (TextView)findViewById(R.id.textview);
            String get = intent.getStringExtra("KEY");
            textView.setText(get);
        }
    }
}
