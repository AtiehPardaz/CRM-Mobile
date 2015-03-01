package GetTasksPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Atieh.crm_mobile.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TasksListAdapter extends BaseAdapter {

	Context context;
    private Activity activity;
    private List<String[]> data;
    
    List<String[]> list1;
    
    private static LayoutInflater inflater=null;
    TextView Hour;
    public TasksListAdapter(Activity a, List<String[]> d ) {
        activity = a;
        data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView)vi.findViewById(R.id.txtTaskTitle); // title
        Hour = (TextView)vi.findViewById(R.id.txtTaskHour); // hour
        
       
        //ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

		Typeface face = Typeface.createFromAsset(this.activity.getApplicationContext().getAssets(),"nazanin.ttf");
		title.setTypeface(face);
		
        list1 = new ArrayList<>();
        list1 = data;

        
        // Setting all values in listview
        String s = list1.get(position).toString();
        
        
        
        Hour.setText(list1.get(position)[0]);
        
        if(list1.get(position)[1]==null){
        	title.setText(list1.get(position)[1]);
        }
        
        
        
        return vi;
    }


	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		return data.size();
	}


	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list1.get(position)[2];
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}