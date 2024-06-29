package com.example.friendsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1;

    String apiurl="https://friendsapi-re5a.onrender.com/adddata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ed1=(EditText) findViewById(R.id.name);
        ed2=(EditText) findViewById(R.id.fname);
        ed3=(EditText) findViewById(R.id.fnickname);
        ed4=(EditText) findViewById(R.id.fdesc);
        b1=(AppCompatButton) findViewById(R.id.addbtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName=ed1.getText().toString();
                String getFriendName=ed2.getText().toString();
                String getFriendNickname=ed3.getText().toString();
                String getDescription=ed4.getText().toString();

                JSONObject friend=new JSONObject();
                try {
                    friend.put("name",getName);
                    friend.put("friendName",getFriendName);
                    friend.put("friendNickName",getFriendNickname);
                    friend.put("DescribeYourFriend",getDescription);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                JsonObjectRequest jasonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiurl,
                        friend,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Friend Added", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jasonObjectRequest);
            }
        });
    }
}