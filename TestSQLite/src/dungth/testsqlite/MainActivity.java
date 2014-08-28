package dungth.testsqlite;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import dungth.testsqlite.model.Lesson;
public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {
	
	ListView mList;
	//MyDatabase mDatabase;
//	SQLiteDatabase mDb;
	//Cursor mCursor;
	ArrayAdapter<String> mAdapter;
	List<String> res;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mList = (ListView)findViewById(R.id.list);
		mList.setOnItemClickListener(this);
		res = new ArrayList<String>();		
		List<Lesson> lessons = Lesson.getAll();
		Log.d("sqlite", lessons.get(1).name);
		for (Lesson lesson : lessons) {
			res.add(lesson.name);
		}
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);
		mList.setAdapter(mAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {				
		Intent intent = new Intent(this, LessonActivity.class);		
		intent.putExtra("lesson_name", res.get(position));
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		
	}
	
	
	
	
}
