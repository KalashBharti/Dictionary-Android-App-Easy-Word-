package com.kalash.easyword;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class fetchData {
    private String URL="https://api.dictionaryapi.dev/api/v2/entries/en/";
    ArrayList<String> list =new ArrayList<>();
    RequestQueue requestQueue;
    ArrayAdapter<String> arrayAdapter;

    fetchData(String word ,RequestQueue r)
    {
        URL = URL +word;
        requestQueue= r;
    }
   public void getData(Context context , ListView listView , TextView similarWord)
    {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject=response.getJSONObject(0);

                    JSONArray jsonArray=jsonObject.getJSONArray("meanings");
                    String definition, partOfSpeech;
                    StringBuilder word= new StringBuilder();
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        jsonObject=jsonArray.getJSONObject(i);
                        JSONArray definitionArray=jsonObject.getJSONArray("definitions");
                        JSONArray jsonArraySimilarWord=jsonObject.getJSONArray("synonyms");
                        partOfSpeech=jsonObject.getString("partOfSpeech")+ ": ";
                        JSONObject definitionObject;
                        for (int j=0;j<definitionArray.length();j++)
                        {
                            definitionObject=definitionArray.getJSONObject(j);

                            definition=definitionObject.getString("definition");
                            Log.d("definitions",partOfSpeech+definition);
                            list.add(partOfSpeech+definition);

                        }
                        for(int j=0;j<jsonArraySimilarWord.length();j++)
                        {
                            word.append(jsonArraySimilarWord.getString(j)).append(", ");
                        }

                    }
                    setlist(context,listView);
                    word.append("....");
                    similarWord.setText(word);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("This word is not Found");
                builder.setTitle("NOT FOUND!");
                builder.setNegativeButton("Ok",(DialogInterface.OnClickListener) (dialog,which)->{
                   dialog.cancel();
                });
                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public void setlist(Context context,ListView listView)
    {
        arrayAdapter=new ArrayAdapter<>(context,R.layout.listlayoutdark,list);
        listView.setAdapter(arrayAdapter);
    }
}
