package space.typro.reincarnaciya.typicallauncher.back.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class WebHelper {

    public URL url = null;
    public int connectionTimeOut = 5000;
    public final HashMap<String, String> request = new HashMap<>();
    public REQUEST_METHOD requestMethod = REQUEST_METHOD.POST;
    public String rawAnswer;

    public WebHelper(){}


    public void setRequest(String... request){
        if (request.length%2!=0){
            System.err.println("Задано неверное кол-во параметров");
            return;
        }
        for (int i = 0; i < request.length; i = i+2) {
            this.request.put(request[i], request[i+1]);
        }
    }

    public void request() throws IOException {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
        httpURLConnection.setRequestMethod(this.requestMethod.method);
        httpURLConnection.setDoOutput(true);

        StringJoiner stringJoiner = new StringJoiner("&");

        for (Map.Entry<String, String> entry : this.request.entrySet()){
            stringJoiner.add(URLEncoder.encode(entry.getKey(), "UTF-8")
                    + "=" + URLEncoder.encode(entry.getValue(), "UTF-8")
            );
        }

        byte[] out = stringJoiner.toString().getBytes(StandardCharsets.UTF_8);

        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpURLConnection.addRequestProperty("User-Agent", "testRequestPropertyAgenttt");
        httpURLConnection.setConnectTimeout(this.connectionTimeOut);
        httpURLConnection.connect();

        try (OutputStream outputStream = httpURLConnection.getOutputStream()){
            outputStream.write(out);
            if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()){
                inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                StringBuilder answerBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null){
                    answerBuilder.append(line);
                }
                this.rawAnswer = answerBuilder.toString();
            }
        }
    }

    public void checkConnection(){

    }

    public String getRawAnswer(){
        return "";
    }

    public enum REQUEST_METHOD {
        POST("POST"),
        GET("GET");

        public final String method;

        REQUEST_METHOD(String method) {
            this.method = method;
        }
    }
}
