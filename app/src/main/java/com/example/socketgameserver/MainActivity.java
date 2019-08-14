package com.example.socketgameserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.socketgameserver.customview.XY_;
import com.example.socketgameserver.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity";
    private ActivityMainBinding binding;
    private WebSocket webSocket;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new Gson();
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        getSupportActionBar().hide();
        setSocketData();
    }



    public void ClearClick(View view) {
        binding.customView.clear();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            XY_ xy_ =new XY_(event.getX(),event.getY());
            binding.customView.addPoint(xy_);
            if(webSocket !=null && !binding.switch1.isChecked()){
                webSocket.send(gson.toJson(xy_));
            }

        }
        return true;
    }
    private void setSocketData() {
       OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://192.168.43.154:4000").build();
        webSocket =  client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull final String text) {
                super.onMessage(webSocket, text);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(MainActivity.this, text.toString(), Toast.LENGTH_SHORT).show();
                       if(binding.switch1.isChecked()){
                          // Toast.makeText(MainActivity.this, text.toString(), Toast.LENGTH_SHORT).show();
                           binding.customView.addPoint(gson.fromJson(text,XY_.class));
                       }
                    }
                });
            }

            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(MainActivity.this, "open", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull final Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "run: "+ t.getLocalizedMessage());
                    }
                });
            }
        });
    }
}
