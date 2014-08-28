package dungth.testsqlite;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase  extends SQLiteAssetHelper {
	private static final String DATABASE_NAME = "development.sqlite3";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "lessons";
    public static final String COL_NAME = "name";    
    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
