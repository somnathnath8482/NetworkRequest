package com.artix.networkrequest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.artix.networkrequest.Helper.Conntants;
import com.artix.networkrequest.Helper.MethodClass;
import com.artix.networkrequest.Interface.OnError;
import com.artix.networkrequest.Interface.OnSuccess;
import com.artix.networkrequest.databinding.ActivityMainBinding;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnError, OnSuccess, PickiTCallbacks {
    ActivityMainBinding binding;
    private Main main;
    PickiT pickiT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_main);
        pickiT = new PickiT(this, this, this);
        main = new Main(this, this, this);


        Onclick();
    }

    private void Onclick() {
        binding.button.setOnClickListener(view -> {
            @SuppressLint("HardwareIds") String android_id = Settings.Secure.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            String url = "get-cart/" + android_id;
            main.CALLGetRequest("https://www.alaaddin.in/preview/api/" + url, Conntants.AUTHHEADER);
        });

        binding.button2.setOnClickListener(view -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("email", "buyer@gmail.com");
            main.CallPostRequestJSon("https://www.alaaddin.in/preview/api/resend-code", Conntants.AUTHHEADER, MethodClass.Json_rpc_format(map));
            // main.CallPostRequestJSon("https://artixdevl.000webhostapp.com/api/test.php",Conntants.AUTHHEADER,map);
        });

        binding.button3.setOnClickListener(view -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("password", "123e456");
            map.put("new_password", "123123123");
            map.put("password_confirmation", "123123123");

            main.CallPostRequestFormdata("https://www.alaaddin.in/preview/api/user-change-password", Conntants.AUTHHEADER, map);
            //main.CallPostRequestFormdata("https://artixdevl.000webhostapp.com/api/test.php",Conntants.AUTHHEADER,map);

        });

        binding.button4.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
        });
    }


    @Override
    public void OnEror(String url, String code, String message) {

        Toast.makeText(this, "Error in url" + url + "\n " + message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void OnSucces(String url, String code, String res) {
        // Toast.makeText(this, "Success  in url"+url+"\n "+res, Toast.LENGTH_SHORT).show();
        binding.textView.setText(res);


    }

    @Override
    public void PickiTonUriReturned() {

    }

    @Override
    public void PickiTonStartListener() {

    }

    @Override
    public void PickiTonProgressUpdate(int progress) {

    }

    @Override
    public void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String Reason) {
        if (wasSuccessful) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("Name", "Somnath");
            main.CAllMultipartRequest("https://artixdevl.000webhostapp.com/api/test.php",
                    Conntants.AUTHHEADER,
                    map
                    ,
                    new File(path));


        }
    }


    @Override
    public void PickiTonMultipleCompleteListener(ArrayList<String> paths, boolean wasSuccessful, String Reason) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);


        }
    }


}