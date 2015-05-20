package adapters;

import java.util.List;

import singleTones.TempTaskID;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.Atieh.crm_mobile.R;

import dataBase.database;

public class CmListFromTaskToProduct extends BaseAdapter {

	Context context;
	private Activity activity;
	String[] ids;
	String[] titles;
	String[] selected;

	List<String> checked;
	database db;
	TextView id;

	List<List<String[]>> list1;

	private static LayoutInflater inflater = null;
	TextView Hour;

	public CmListFromTaskToProduct(Activity a, String[] arrayid,
			String[] arraytitle, String[] arrayselected, Context c) {

		activity = a;
		context = c;

		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.ids = arrayid;
		this.titles = arraytitle;
		this.selected = arrayselected;

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

		if(selected[position].equals("1")){
			chk.setChecked(true);
		}
		else {
			chk.setChecked(false);
		}
		
		chk.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {

				db = new database(context);
				db.database();
				db.open();

				if (arg1) {

					ContentValues values = new ContentValues();
					values.put("TaskGUID", TempTaskID.getInstance()
							.getTempTaskID());
					values.put("ProductGUID", ids[position]);
					values.put("IsDeleted", 0);
					db.mydb.insert("TasksProducts", null, values);

				} else {

					String strFilter = "TaskGUID =" + "'"
							+ TempTaskID.getInstance().getTempTaskID()
							+ "' and ProductGUID =" + "'" + ids[position] + "'";

					db.mydb.delete("TasksProducts", strFilter, null);
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