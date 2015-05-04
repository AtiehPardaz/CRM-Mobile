package adapters;

import java.util.List;

import singleTones.TempActivityID;
import singleTones.TempTaskID;

import com.Atieh.crm_mobile.R;
import com.Atieh.crm_mobile.R.id;
import com.Atieh.crm_mobile.R.layout;

import dataBase.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class CmListFromTaskToServices extends BaseAdapter {
	Context context;
	private Activity activity;
	String[] ids;
	String[] titles;
	List<String> checked;
	database db;
	TextView id;

	List<List<String[]>> list1;

	private static LayoutInflater inflater = null;
	TextView Hour;

	public CmListFromTaskToServices(Activity a, String[] arrayid,
			String[] arraytitle, Context c) {

		activity = a;
		context = c;

		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.ids = arrayid;
		this.titles = arraytitle;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {

		View vi = inflater.inflate(R.layout.rowselectproducteservices, null);
		CheckBox chk = (CheckBox) vi.findViewById(R.id.chk_selproducteservices);
		id = (TextView) vi.findViewById(R.id.tv_id_selproductservices); // id
		TextView title = (TextView) vi
				.findViewById(R.id.tv_title_selproductservices); // title

		id.setText(ids[position]);
		id.setVisibility(View.GONE);
		title.setText(titles[position]);

		chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				db = new database(context);
				db.database();
				db.open();

				if (arg1) {

					ContentValues values = new ContentValues();
					values.put("TaskGUID", TempTaskID.getInstance()
							.getTempTaskID());
					values.put("ServiceGUID", ids[position]);
					values.put("IsDeleted", 0);
					db.mydb.insert("TasksServices", null, values);

				} else {

					String strFilter = "TaskGUID =" + "'"
							+ TempTaskID.getInstance().getTempTaskID()
							+ "' and ServiceGUID =" + "'" + ids[position] + "'";

					db.mydb.delete("TasksServices", strFilter, null);

				}

				db.close();
			}
		});

		return vi;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub

		return ids.length;
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

	public List<String> getselected() {

		return checked;

	}

}