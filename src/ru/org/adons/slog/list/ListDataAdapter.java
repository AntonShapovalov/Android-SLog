package ru.org.adons.slog.list;

import java.util.List;

import ru.org.adons.slog.LogDataHolder;
import ru.org.adons.slog.LogItem;
import ru.org.adons.slog.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListDataAdapter extends BaseAdapter {

	private Context context;
	private int runCount;
	private List<LogItem> items;
	private LayoutInflater inflater;

	private static class ViewHolder {
		TextView package_name;
		TextView percent;
	}

	public ListDataAdapter(Context context) {
		this.context = context;
		LogDataHolder dh = LogDataHolder.getHolder(context);
		runCount = dh.getCount();
		items = dh.getItems();
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public LogItem getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(ru.org.adons.slog.R.layout.activity_log_list_item, null);
			holder = new ViewHolder();
			holder.package_name = (TextView) convertView.findViewById(R.id.log_text_package_name);
			holder.percent = (TextView) convertView.findViewById(R.id.log_text_percent);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.package_name.setText(getItem(position).getName());

		int r = getItem(position).getCount() * 100 / runCount;
		holder.percent.setText(r + "%");
		int bColor;
		if (r <= 25) {
			bColor = R.color.green10;
		} else if (25 < r && r <= 50) {
			bColor = R.color.orange10;
		} else if (50 < r && r <= 75) {
			bColor = R.color.red10;
		} else {
			bColor = R.color.red08;
		}
		holder.percent.setBackgroundColor(context.getResources().getColor(bColor));

		return convertView;
	}

}