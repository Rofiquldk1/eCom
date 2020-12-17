package com.example.loginagami;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {
    private EditText editText_host_url,editText_user_name,editText_password;
    private Button btn_submit;
    private String url="http://192.168.0.105/project/information/information.php";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_host_url = findViewById(R.id.login_hosturl);
        editText_user_name = findViewById(R.id.login_username);
        editText_password = findViewById(R.id.login_password);

        btn_submit = findViewById(R.id.login_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final HashMap<String,String> params = new HashMap<>();
                params.put("HostUrl",editText_host_url.getText().toString());
                params.put("UserName",editText_user_name.getText().toString());
                params.put("Password",editText_password.getText().toString());

                StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                        LoginActivity.this, LoginActivity.this){
                    @Override
                    protected Map<String, String> getParams(){
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });



    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        FirebaseMessaging.getInstance().subscribeToTopic(response);
    }
}