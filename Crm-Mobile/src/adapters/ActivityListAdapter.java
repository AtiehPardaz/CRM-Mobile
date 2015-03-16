package adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.Atieh.crm_mobile.ActivityDetailsActivity;
import com.Atieh.crm_mobile.R;
import com.Atieh.crm_mobile_calendar.Utils;

public class ActivityListAdapter extends BaseAdapter {

	int activitiesnumber;
	Context context;
	private Activity activity;
	private List<List<String[]>> data;
	boolean[][] activitytitle = new boolean[24][30];
	int pos;

	List<List<String[]>> list1;

	private static LayoutInflater inflater = null;
	TextView Hour;

	public ActivityListAdapter(Activity a, List<List<String[]>> activitisList,
			int numberOfactivities, boolean[][] titleSet) {
		activity = a;
		data = activitisList;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		activitiesnumber = numberOfactivities;
		activitytitle = titleSet;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		pos = position;
		View vi = inflater.inflate(R.layout.list_row, null);

		Display display = activity.getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);

		float density = activity.getResources().getDisplayMetrics().density;
		float dpHeight = outMetrics.heightPixels / density;
		float dpWidth = outMetrics.widthPixels / density;

		Hour = (TextView) vi.findViewById(R.id.txtTaskHour); // hour

		// Typeface face =
		// Typeface.createFromAsset(this.activity.getApplicationContext().getAssets(),"nazanin.ttf");
		// title.setTypeface(face);

		LinearLayout root = (LinearLayout) vi.findViewById(R.id.layoutTaskHour);
		
		for (int i = activitiesnumber - 1; i >= 0; i--) {
			LinearLayout child = new LinearLayout(vi.getContext());
			LayoutParams params = new LayoutParams((int) ((outMetrics.widthPixels - 120 )/activitiesnumber), 60);
			params.setMargins(2, 0, 2, 0);
			child.setLayoutParams(params);
			child.setOrientation(LinearLayout.VERTICAL);
			child.setPadding(3, 3, 3, 3);

			TextView activityTitle = new TextView(vi.getContext());
			activityTitle.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			activityTitle.setGravity(Gravity.RIGHT);
			activityTitle.setPadding(0, 0, 10, 2);
			activityTitle.setTextSize(12);

			TextView activityID = new TextView(vi.getContext());
			activityID.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			activityID.setGravity(Gravity.RIGHT);
			activityID.setPadding(0, 0, 10, 2);
			activityID.setTextSize(1);
			activityID.setVisibility(View.GONE);

			Utils.getInstance().prepareTextView(activityTitle);
			Utils.getInstance().prepareTextView(activityID);

			if (!(data.get(position).get(i)[0].equals(""))) {

				child.setBackgroundResource(R.drawable.border);

				child.addView(activityTitle);
				child.addView(activityID);

				String title = "";
				String id = "";
				id = data.get(position).get(i)[0];

				try {
					if (activitytitle[position][i]) {
						title = data.get(position).get(i)[1];
						child.setBackgroundResource(R.drawable.border2);
					} else if ((data.get(position + 1).get(i)[0].equals(""))) {
						child.setBackgroundResource(R.drawable.border3);

					}
				} catch (Exception e) {
					title = "";
				}

				activityTitle.setText(title);
				activityID.setText(id);

				//if (activitytitle[position][i]) {

					child.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {

							LinearLayout l = (LinearLayout) v;

							String str = ((TextView) l.getChildAt(1)).getText()
									.toString();
							Intent i = new Intent(activity,
									ActivityDetailsActivity.class);
							i.putExtra("id", str);
							activity.startActivity(i);
						}
					});

				//}

			}

			root.addView(child);

		}

		Hour.setText(getTimeInPM_AM(position));

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
		// list1.get(position)[2];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public String getTimeInPM_AM(int time) {
		switch (time) {
		case 0:
			return "12:00 AM";
		case 1:
			return "1:00 AM";
		case 2:
			return "2:00 AM";
		case 3:
			return "3:00 AM";
		case 4:
			return "4:00 AM";
		case 5:
			return "5:00 AM";
		case 6:
			return "6:00 AM";
		case 7:
			return "7:00 AM";
		case 8:
			return "8:00 AM";
		case 9:
			return "9:00 AM";
		case 10:
			return "10:00 AM";
		case 11:
			return "11:00 AM";
		case 12:
			return "12:00 PM";
		case 13:
			return "1:00 PM";
		case 14:
			return "2:00 PM";
		case 15:
			return "3:00 PM";
		case 16:
			return "4:00 PM";
		case 17:
			return "5:00 PM";
		case 18:
			return "6:00 PM";
		case 19:
			return "7:00 PM";
		case 20:
			return "8:00 PM";
		case 21:
			return "9:00 PM";
		case 22:
			return "10:00 PM";
		case 23:
			return "11:00 PM";

		default:
			return null;
		}

	}
}