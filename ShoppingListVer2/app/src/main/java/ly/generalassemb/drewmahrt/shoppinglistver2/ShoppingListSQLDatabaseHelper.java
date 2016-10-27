package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott Lindley on 10/25/2016.
 */

public class ShoppingListSQLDatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String GROCERIES_TABLE_NAME = "SHOPPING_LIST";
    public static final int DATABASE_VERSION = 7;

    public static final String COL_ID = "_id";
    public static final String COL_NAME = "ITEM_NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";


    public static final String CREATE_TABLE =
            "CREATE TABLE "+ GROCERIES_TABLE_NAME+ " ("+
                    COL_ID+" INTEGER PRIMARY KEY, "+
                    COL_NAME+" TEXT, "+
                    COL_DESCRIPTION +" TEXT, "+
                    COL_PRICE+" INT, "+
                    COL_TYPE+" TEXT)";

    private static ShoppingListSQLDatabaseHelper mInstance;

    private ShoppingListSQLDatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ShoppingListSQLDatabaseHelper getInstance(Context context){
        if(mInstance==null){
            mInstance = new ShoppingListSQLDatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+GROCERIES_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public List<GroceryItem> getAllItems(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                GROCERIES_TABLE_NAME,
                null, null, null, null, null, null
        );

        List<GroceryItem> groceryItems = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                groceryItems.add(
                        new GroceryItem(
                                cursor.getString(cursor.getColumnIndex(COL_NAME)),
                                cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)),
                                cursor.getString(cursor.getColumnIndex(COL_TYPE)),
                                cursor.getString(cursor.getColumnIndex(COL_PRICE))
                        )
                );
                cursor.moveToNext();
            }
        }
        cursor.close();
        return groceryItems;
    }

    public List<GroceryItem> getSearchedName(String query){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                GROCERIES_TABLE_NAME,
                null,
                COL_NAME+" LIKE '%"+query+"%'",
                null,
                null,null,
                COL_NAME,
                null);

        List<GroceryItem> foundItems = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                foundItems.add(new GroceryItem(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COL_TYPE)),
                        cursor.getString(cursor.getColumnIndex(COL_PRICE))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return foundItems;
    }

    public List<GroceryItem> getSearchedType(String query){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                GROCERIES_TABLE_NAME,
                null,
                COL_TYPE+" LIKE '%"+query+"%'",
                null,
                null,null,
                COL_TYPE,
                null);

        List<GroceryItem> foundItems = new ArrayList<>();

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                foundItems.add(new GroceryItem(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COL_TYPE)),
                        cursor.getString(cursor.getColumnIndex(COL_PRICE))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return foundItems;
    }
}
