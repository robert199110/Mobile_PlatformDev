package mpdproject.gcu.me.org.assignmenttest1;
// @author Robert McGuire S1429388

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class List_View_Planned extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__view__planned);

        Intent intent = getIntent();
        ArrayList itemList = (ArrayList)intent.getSerializableExtra("List");
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,
                itemList );

        ListView roadListCurrent = (ListView) findViewById(R.id.listViewPlanned);
        roadListCurrent.setAdapter(adapter);
        roadListCurrent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String roadwork = "You have clicked on " +
                        String.valueOf(adapterView.getItemAtPosition(pos));

                Toast.makeText(List_View_Planned.this, roadwork, Toast.LENGTH_SHORT).show();
            }
        });
    }
}