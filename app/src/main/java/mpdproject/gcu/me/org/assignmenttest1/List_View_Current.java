package mpdproject.gcu.me.org.assignmenttest1;
// @author Robert McGuire S1429388

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class List_View_Current extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__view__current);

        Intent intent = getIntent();
        ArrayList itemList = (ArrayList)intent.getSerializableExtra("List");
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,
               itemList );

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String data = listView.getItemAtPosition(pos).toString();

            }
        });


*/


        ListView roadListCurrent = (ListView) findViewById(R.id.listView);
        roadListCurrent.setAdapter(adapter);
        roadListCurrent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String roadwork = "You have clicked on " +
                        String.valueOf(adapterView.getItemAtPosition(pos));

                Toast.makeText(List_View_Current.this, roadwork, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                ArrayList itemList = ArrayList.getSerializableExtra("List");

                ArrayList<String> temp = new ArrayList<>();

                for (String temp : itemList) {
                }
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    }

*/