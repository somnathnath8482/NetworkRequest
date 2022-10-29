package com.artix.networkrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.artix.networkrequest.Helper.Conntants;
import com.artix.networkrequest.Helper.MethodClass;
import com.artix.networkrequest.Interface.OnError;
import com.artix.networkrequest.Interface.OnSuccess;
import com.artix.networkrequest.databinding.ActivityMainBinding;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnError, OnSuccess {
    ActivityMainBinding binding;
    private Main main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_main);



         main = new Main(this,this, this);


       Onclick();
    }

    private void Onclick() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("HardwareIds") String android_id = Settings.Secure.getString(getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                String url = "get-cart/" + android_id;
                main.GetRequest("https://www.alaaddin.in/preview/api/"+url, Conntants.AUTHHEADER);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("email","buyer@gmail.com");
               main.CallPostRequestJSon("https://www.alaaddin.in/preview/api/resend-code", Conntants.AUTHHEADER, MethodClass.Json_rpc_format(map));
              // main.CallPostRequestJSon("https://artixdevl.000webhostapp.com/api/test.php",Conntants.AUTHHEADER,map);
            }
        });

binding.button3.setOnClickListener(view -> {
    HashMap<String, Object> map = new HashMap<>();
    map.put("password","123e456");
    map.put("new_password","123123123");
    map.put("password_confirmation", "123123123");

    main.CallPostRequestFormdata("https://www.alaaddin.in/preview/api/user-change-password",Conntants.AUTHHEADER,map);
    //main.CallPostRequestFormdata("https://artixdevl.000webhostapp.com/api/test.php",Conntants.AUTHHEADER,map);

});
    }


    @Override
    public void OnEror(String url, String code, String message) {

        Toast.makeText(this, "Error in url"+url+"\n "+message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void OnSucces(String url, String code, String res) {
        // Toast.makeText(this, "Success  in url"+url+"\n "+res, Toast.LENGTH_SHORT).show();
        binding.textView.setText(res);


    }
}