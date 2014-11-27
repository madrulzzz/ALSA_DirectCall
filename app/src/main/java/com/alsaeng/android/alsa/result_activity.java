package com.alsaeng.android.alsa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import adapter.CustomListAdapter;
import app.AppController;
import model.Employee;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.VolleyLog;

public class result_activity extends Activity {

    // Log tag
    private static final String TAG = result_activity.class.getSimpleName();
    private static final String TAG1 = "ALSA_LOG";

    // json url

    private static String url;
    private ProgressDialog pDialog;
    private List<Employee> empList = new ArrayList<Employee>();
    private ListView listView;
    private CustomListAdapter adapter;

    //DATA from ALSA Home activity
    public String Name,Fileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activity);
        //Set Action Bar
        setTitle("ALSA Directory");
        getActionBar().setIcon(R.drawable.ic_launcher);

        //prepare php url query
        Log.i(TAG1, "Sending JSON URL");
        Name =getIntent().getStringExtra("NAME");
        Fileno =getIntent().getStringExtra("FILENO");
        url="http://403.madrulzzz.com/test.php?file_no="+Fileno+"&alsa_name="+Name;

        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomListAdapter(this, empList);
        listView.setAdapter(adapter);

        Log.i(TAG1, "Searching in Progress");
        pDialog = new ProgressDialog(this);
        // showing progress before making http request
        pDialog.setMessage("Searching ALSA...");
        pDialog.show();

        // changing action bar color
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1b1b1b")));

        // creating volley request obj
        Log.i(TAG1, "Querying DB for Matches");
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        Log.i(TAG1, "Query DB for Matches completed.");
                        Log.i(TAG1, "A total of "+response.length()+" Results found");

                        //De Build json
                        Log.i(TAG1, "Debuilding JSON");
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Employee emp = new Employee();
                                emp.setName(obj.getString("empName"));
                                emp.setDesignation(obj.getString("Profession"));
                                emp.setFile_no(obj.getString("FileNo"));
                                String Mob= obj.getString("MobileNo");
                                emp.setPhone_Number(Mob.substring(1));

                                empList.add(emp);

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }

                        if(response.length()==0)
                        {


                            Employee emp = new Employee();
                            emp.setName("No Entries Found");
                            emp.setDesignation("Not Found");
                            emp.setFile_no("Not Found");
                            emp.setPhone_Number("026-581551");
                            empList.add(emp);
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();



            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_al, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this.getBaseContext(), "Developed by Madhav Kishore, IT Engineer, ALSA Engineering and Construction L.L.C madhav.kishore@alsaeng.com",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
