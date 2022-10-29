package com.artix.networkrequest;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.Nullable;

import com.artix.networkrequest.Interface.OnError;
import com.artix.networkrequest.Interface.OnSuccess;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Main {
    private static final String TAG = "networkrequest";
    Activity activity;

    OnError onError;
    OnSuccess onSuccess;

    public Main(Activity activity, OnError onError, OnSuccess onSuccess) {
        this.activity = activity;
        this.onError = onError;
        this.onSuccess = onSuccess;
    }

    public void CALLGetRequest(String url, @Nullable String authHeader) {
        authHeader = authHeader == null ? "" : authHeader;

        try {
            String finalAuthHeader = authHeader;
            new Thread(
                    () -> {

                        try {
                            URL ur_l = new URL(url);
                            URLConnection myURLConnection = ur_l.openConnection();

                          /* HttpsURLConnection myURLConnection = (HttpsURLConnection)ur_l.openConnection();

                          // String userCredentials = "username:password";
                           // basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

                          // myURLConnection.setRequestProperty ("Authorization", "basicAuth HeaDE");
                           myURLConnection.setRequestMethod("GET");
                           //myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                          // myURLConnection.setRequestProperty("Content-Length", "" + postData.getBytes().length);
                           //myURLConnection.setRequestProperty("Content-Language", "en-US");
                           //myURLConnection.setUseCaches(false);
                           //myURLConnection.setDoInput(true);
                           myURLConnection.setDoOutput(true);

*/

                            myURLConnection.setRequestProperty("Authorization",
                                    finalAuthHeader);
                            InputStream is = myURLConnection.getInputStream();
                            Log.d(TAG, "GetRequest: " + is.toString());


                            StringWriter writer = new StringWriter();
                            IOUtils.copy(is, writer, "UTF-8");
                            String theString = writer.toString();
                            activity.runOnUiThread(() -> {
                                onSuccess.OnSucces(url, "200", theString);
                            });
                        } catch (IOException e) {

                            activity.runOnUiThread(() -> {
                                onError.OnEror(url, 1 + "", e.getMessage());
                            });
                        }

                    }
            ).start();
        } catch (Exception e) {
            onError.OnEror(url, 1 + "", e.getMessage());
        }

    }


    public void CallPostRequestJSon(String url, @Nullable String authHeader, JSONObject object) {
        authHeader = authHeader == null ? "" : authHeader;

        try {
            String finalAuthHeader = authHeader;
            new Thread(
                    () -> {

                        try {
                            byte[] postData = object.toString().getBytes("UTF-8");
                            int postDataLength = postData.length;


                            URL ur_l = new URL(url);
                            HttpsURLConnection myURLConnection = (HttpsURLConnection) ur_l.openConnection();
                            myURLConnection.setRequestMethod("POST");
                            // myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            //myURLConnection.setRequestProperty("Content-Type", "application/json");
                            myURLConnection.setRequestProperty("Content-Length", "" + postDataLength);
                            //myURLConnection.setRequestProperty("Content-Language", "en-US");
                            myURLConnection.setUseCaches(false);
                            myURLConnection.setDoInput(true);
                            myURLConnection.setDoOutput(true);
                            myURLConnection.setRequestProperty("Accept", "*/*");
                            myURLConnection.setRequestProperty("Authorization", finalAuthHeader);
                            myURLConnection.getOutputStream().write(postData);


                            InputStream is = myURLConnection.getInputStream();


                            StringWriter writer = new StringWriter();
                            IOUtils.copy(is, writer, "UTF-8");
                            String theString = writer.toString();
                            activity.runOnUiThread(() -> {
                                onSuccess.OnSucces(url, "200", theString);
                            });
                        } catch (IOException e) {

                            activity.runOnUiThread(() -> {
                                onError.OnEror(url, 1 + "", e.getMessage());
                            });
                        }

                    }
            ).start();
        } catch (Exception e) {
            onError.OnEror(url, 1 + "", e.getMessage());
        }


    }

    public void CallPostRequestFormdata(String url, @Nullable String authHeader, HashMap<String, Object> map) {
        authHeader = authHeader == null ? "" : authHeader;

        try {
            String finalAuthHeader = authHeader;
            new Thread(
                    () -> {

                        try {

                            StringBuilder postData = new StringBuilder();
                            for (Map.Entry<String, Object> param : map.entrySet()) {
                                if (postData.length() != 0) postData.append('&');
                                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                                postData.append('=');
                                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
                            }


                            byte[] postDataBYTE = postData.toString().getBytes("UTF-8");
                            int postDataLength = postDataBYTE.length;


                            URL ur_l = new URL(url);
                            HttpsURLConnection myURLConnection = (HttpsURLConnection) ur_l.openConnection();
                            myURLConnection.setRequestMethod("POST");
                            myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            //myURLConnection.setRequestProperty("Content-Type", "application/json");
                            myURLConnection.setRequestProperty("Content-Length", "" + postDataLength);
                            //myURLConnection.setRequestProperty("Content-Language", "en-US");
                            myURLConnection.setUseCaches(false);
                            myURLConnection.setDoInput(true);
                            myURLConnection.setDoOutput(true);
                            myURLConnection.setRequestProperty("Accept", "*/*");
                            myURLConnection.setRequestProperty("Authorization", finalAuthHeader);
                            myURLConnection.getOutputStream().write(postDataBYTE);


                            InputStream is = myURLConnection.getInputStream();


                            StringWriter writer = new StringWriter();
                            IOUtils.copy(is, writer, "UTF-8");
                            String theString = writer.toString();
                            activity.runOnUiThread(() -> {
                                onSuccess.OnSucces(url, "200", theString);
                            });
                        } catch (IOException e) {

                            activity.runOnUiThread(() -> {
                                onError.OnEror(url, 1 + "", e.getMessage());
                            });
                        }

                    }
            ).start();
        } catch (Exception e) {
            onError.OnEror(url, 1 + "", e.getMessage());
        }


    }

    public void CAllMultipartRequest(String url, @Nullable String authHeader, HashMap<String, Object> map,File f) {
        authHeader = authHeader == null ? "" : authHeader;

        try {
            String finalAuthHeader = authHeader;
            new Thread(
                    () -> {

                        try {
                            CloseableHttpClient httpClient = HttpClients.createDefault();
                            URL ur_l = new URL(url);
                            HttpPost uploadFile = new HttpPost(url);
                            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                            builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
                           // myURLConnection.setRequestProperty("Authorization", finalAuthHeader);


                            builder.addBinaryBody(
                                    "file",
                                    new FileInputStream(f),
                                    ContentType.APPLICATION_OCTET_STREAM,
                                    f.getName()
                            );

                            HttpEntity multipart = builder.build();
                            uploadFile.setEntity(multipart);
                            CloseableHttpResponse response = httpClient.execute(uploadFile);
                            HttpEntity responseEntity = response.getEntity();




                            InputStream is = responseEntity.getContent();


                            StringWriter writer = new StringWriter();
                            IOUtils.copy(is, writer, "UTF-8");
                            String theString = writer.toString();
                            activity.runOnUiThread(() -> {
                                onSuccess.OnSucces(url, "200", theString);
                            });
                        } catch (Exception e) {

                            activity.runOnUiThread(() -> {
                                onError.OnEror(url, 1 + "", e.getMessage());
                            });
                        }

                    }
            ).start();
        } catch (Exception e) {
            onError.OnEror(url, 1 + "", e.getMessage());
        }
    }
}
