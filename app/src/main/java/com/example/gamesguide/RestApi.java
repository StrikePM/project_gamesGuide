package com.example.gamesguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.gamesguide.databinding.ActivityRestApiBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RestApi extends AppCompatActivity {
    private ActivityRestApiBinding binding;
    String index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);

        //setup view binding
        binding = ActivityRestApiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //mengganti actionbar dengan toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        binding.fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.fetch_button){
                    index = binding.inputId.getText().toString();
                    try {
                        getData();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    //get data using api link
    public void getData() throws MalformedURLException {
        Uri uri = Uri.parse("https://thronesapi.com/api/v2/Characters").buildUpon().build();
        URL url = new URL(uri.toString());
        new DOTask().execute(url);
    }
    class DOTask extends AsyncTask<URL, Void, String> {
        //connection request
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls [0];
            String data = null;
            try {
                data = NetworkUtils.makeHTTPRequest(url);
            }catch (IOException e){
                e.printStackTrace();
            }
            return data;
        }
        @Override
        protected void onPostExecute(String s){
            try {
                parseJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //get data json
        public void parseJson(String data) throws JSONException{
            JSONArray ja = new JSONArray(data);
            for (int i = 0; i < ja.length(); i++){
                JSONObject obj = ja.getJSONObject(i);
                String Sobj = obj.get("id").toString();
                if (Sobj.equals(index)){
                    String id = obj.get("id").toString();
                    String fn = obj.get("firstName").toString();
                    String ln = obj.get("lastName").toString();
                    String name = obj.get("fullName").toString();
                    String title = obj.get("title").toString();
                    String family = obj.get("family").toString();

                    binding.resultId.setText(id);
                    binding.resultFn.setText(fn);
                    binding.resultLn.setText(ln);
                    binding.resultName.setText(name);
                    binding.resultTitle.setText(title);
                    binding.resultHouse.setText(family);
                    break;
                }
                else{
                    binding.resultName.setText("Not Found");
                }
            }
        }
    }
}