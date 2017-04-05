package com.example.administrator.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(MainActivity.this);
        textView= (TextView) findViewById(R.id.textview);
        button= (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
    @Subscribe
    public void onEventMainThread(EvebtBus event){

        String msg="onEventMainThread收到了消息"+event.getMsg();
        Log.d("AA",msg);
        Toast.makeText(MainActivity.this,"msg",Toast.LENGTH_LONG).show();
        textView.setText(msg);
    }

    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(MainActivity.this);
    }

}
