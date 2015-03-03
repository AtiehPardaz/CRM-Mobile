package GetTasksPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.Atieh.crm_mobile.R;
import com.Atieh.crm_mobile_calendar.Utils;

import android.R.transition;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class TasksListAdapter extends BaseAdapter {

	int tasksnumber ;
	Context context;
	private Activity activity;
	private List<List<String[]>> data;

	List<List<String[]>> list1;

	private static LayoutInflater inflater=null;
	TextView Hour;
	public TasksListAdapter(Activity a, List<List<String[]>> tasksList ,int numberOfTasks) {
		activity = a;
		data = tasksList;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		tasksnumber = numberOfTasks;
	}


	public View getView(int position, View convertView, ViewGroup parent) {



		View vi = inflater.inflate(R.layout.list_row, null);

		Display display = activity.getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics ();
		display.getMetrics(outMetrics);

		float density  = activity.getResources().getDisplayMetrics().density;
		float dpHeight = outMetrics.heightPixels / density;
		float dpWidth  = outMetrics.widthPixels / density;


		Hour = (TextView)vi.findViewById(R.id.txtTaskHour); // hour


		//Typeface face = Typeface.createFromAsset(this.activity.getApplicationContext().getAssets(),"nazanin.ttf");
		//title.setTypeface(face);

		LinearLayout root = (LinearLayout) vi.findViewById(R.id.layoutTaskHour);

		for (int i = tasksnumber-1 ; i >= 0; i--) {

			LinearLayout child = new LinearLayout(vi.getContext());
			child.setLayoutParams(new LayoutParams((int) (dpWidth - 45)/tasksnumber,
					60));
			child.setOrientation(LinearLayout.VERTICAL);
			child.setPadding(3, 3, 3, 3);

			TextView currentMonthTextView = new TextView(vi.getContext());
			currentMonthTextView.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			currentMonthTextView.setGravity(Gravity.RIGHT);
			currentMonthTextView.setPadding(0, 0, 10, 2);
			currentMonthTextView.setTextSize(12);
			Utils.getInstance().prepareTextView(currentMonthTextView);
			
			if (! (data.get(position).get(i)[0].equals(""))) {
				
				child.setBackgroundResource(R.drawable.border);

				child.addView(currentMonthTextView);
				String title = "";
				try {
					//if((data.get(position).get(i)[12]).equals("1")){
					title = data.get(position).get(i)[10];
					//}
				} catch (Exception e) {
					title = "";
				}
				currentMonthTextView.setText(title);

			}

			root.addView(child);

		}



		Hour.setText(Integer.toString(position));



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
		return null;
		//list1.get(position)[2];
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}