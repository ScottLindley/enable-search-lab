package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<GroceryItem> mGroceryItems;
    private Spinner mSpinner;
    private String mSpinnerSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        mGroceryItems = ShoppingListSQLDatabaseHelper.getInstance(MainActivity.this).getAllItems();


        mSpinner = (Spinner)findViewById(R.id.search_spinner);
        mSpinner.setPrompt("Sort By");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                MainActivity.this, R.array.spinner_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSpinner.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(mGroceryItems);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    public void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            if (mSpinner.getSelectedItem().equals("Name")){
                String query = intent.getStringExtra(SearchManager.QUERY);
                List<GroceryItem> items =
                        ShoppingListSQLDatabaseHelper.getInstance(MainActivity.this)
                            .getSearchedName(query);
                mRecyclerViewAdapter.replaceData(items);
            }else if(mSpinner.getSelectedItem().toString().equals("Type")){
                String query = intent.getStringExtra(SearchManager.QUERY);
                List<GroceryItem> items =
                        ShoppingListSQLDatabaseHelper.getInstance(MainActivity.this)
                            .getSearchedType(query);
                mRecyclerViewAdapter.replaceData(items);
            }else{
                Toast.makeText(this, "Please select a sorting method", Toast.LENGTH_SHORT).show();
            }
    }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this, MainActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        return true;
    }
}
