package adapter;



import model.Employee;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.alsaeng.android.alsa.R;
import com.android.volley.toolbox.NetworkImageView;


public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Employee> EmpItems;
    private final String TAG1 = "ALSA_LOG";


    public CustomListAdapter(Activity activity, List<Employee> EmpItems) {
        this.activity = activity;
        this.EmpItems = EmpItems;
    }

    @Override
    public int getCount() {
        return EmpItems.size();
    }

    @Override
    public Object getItem(int location) {
        return EmpItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);



        TextView Name = (TextView) convertView.findViewById(R.id.emp_name);
        TextView Designation = (TextView) convertView.findViewById(R.id.emp_desg);
        TextView File_NO = (TextView) convertView.findViewById(R.id.file_no);
        TextView Phone_NO = (TextView) convertView.findViewById(R.id.phone_no);
        NetworkImageView img = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        Log.i(TAG1, "Declared all UI elements within List");


        // getting Employee data for the row
        Employee m = EmpItems.get(position);



        // Name
        Name.setText(m.getName());

        // Designation

        Designation.setText("Designation: " + String.valueOf(m.getDesignation()));

        // File_NO
        File_NO.setText("File No: " + String.valueOf(m.getFile_no()));

        // phone_No
        Phone_NO.setText("Phone: +971" + String.valueOf(m.getPhone_Number()));


        //Tap to dial function
        final String ph = String.valueOf(m.getPhone_Number());
        Phone_NO.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.i(TAG1, "someone clicked on the phone No. Taking them to Dialer");
                Intent dial = new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:+971" + ph));
                activity.startActivity(dial);
            }
        }));
        Name.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.i(TAG1, "someone clicked on the Name. Taking them to Dialer");
                Intent dial = new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:+971"+ph));
                activity.startActivity(dial);
            }
        }));
        File_NO.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.i(TAG1, "someone clicked on the File No. Taking them to Dialer");
                Intent dial = new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:+971"+ph));
                activity.startActivity(dial);
            }
        }));
        Designation.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.i(TAG1, "someone clicked on the Designation. Taking them to Dialer");
                Intent dial = new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:+971"+ph));
                activity.startActivity(dial);
            }
        }));
        img.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Log.i(TAG1, "someone clicked on the phone ka image dude. Taking them to Dialer and I love you megha");
                Intent dial = new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:+971"+ph));
                activity.startActivity(dial);
            }
        }));


        return convertView;
    }

}