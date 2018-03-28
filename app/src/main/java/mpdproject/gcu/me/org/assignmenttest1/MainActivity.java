//
//
// Starter code for the Mobile Platform Development Assignment
// Seesion 2017/2018
// @author Robert McGuire S1429388
//

package mpdproject.gcu.me.org.assignmenttest1;

import android.app.LauncherActivity;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.*;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String current = "http://trafficscotland.org/rss/feeds/currentincidents.aspx";
    private String plannedRoadworks = "http://trafficscotland.org/rss/feeds/plannedroadworks.aspx";
    private TextView urlInput;
    private Button startButton;
    private Button incidentButton;
    private String result = "";
    public ArrayList listItems = new ArrayList();
    public ArrayList plannedList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // urlInput = (TextView) findViewById(R.id.urlInput);
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        incidentButton = (Button) findViewById(R.id.plannedRoadworks);
        incidentButton.setOnClickListener(this);
        //startProgress();
        //plannedProgress();
        new MyAsyncTask().execute(current);
        new MyAsyncTask().execute(plannedRoadworks);
        listItems.clear();


    } // End of onCreate




    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.startButton:

                Intent intent = new Intent(this, List_View_Current.class);
                intent.putExtra("List", listItems);
                startActivity(intent);

                //createListView();
                break;

            case R.id.plannedRoadworks:
                Intent intentB = new Intent(this, List_View_Planned.class);
                intentB.putExtra("List", listItems);
                startActivity(intentB);

                Toast.makeText(this, " Button clicked", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    class MyAsyncTask extends AsyncTask<String, String ,String> {

        //Background Processes
        @Override
        protected String doInBackground(String... f_url) {

            BufferedReader in ;
            String inputLine = "";
            try {
                URL url = new URL(f_url[0]);

                URLConnection connection = url.openConnection();
                connection.connect();

                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //
                // Throw away the first 2 header lines before parsing
                //
                //

                while ((inputLine = in.readLine()) != null) {
                    result = result + inputLine;
                    Log.e("MyTag", inputLine);

                }
                in.close();
            } catch (IOException ae) {
                Log.e("MyTag", "ioexception");
            }


            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {

                    Log.d("UI thread", "I am the UI thread");
                    //urlInput.setText(result);
                    parseData(result);

                }
            });
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Downloading");
        }


        @Override

        protected void onPostExecute(String result) {

            super.onPostExecute(result);
        }
    }



    private LinkedList<ItemClass> parseData(String dataToParse)
    {

        ItemClass item = new ItemClass();
        LinkedList <ItemClass> list = new LinkedList<ItemClass>();

        try
        {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader(dataToParse) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                // Found a start tag
                if(eventType == XmlPullParser.START_TAG)
                {
                    // Check which Tag we have
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {

                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Item Start Tag found");




                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        // Do something with text
                        System.out.println("TEST TO CHECK PARSING IS WORKING");
                        Log.e("MyTag","Title is " + temp);
                        item.setTitle(temp);
                        listItems.add(item.getTitle());
                    }
                    else
                        // Check which Tag we have
                        if (xpp.getName().equalsIgnoreCase("description"))
                        {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            // Do something with text
                            Log.e("MyTag","description is " + temp);
                            item.setDescription(temp);
                            listItems.add(item.getDescription());

                        }
                        else
                            // Check which Tag we have
                            if (xpp.getName().equalsIgnoreCase("pubDate"))
                            {
                                // Now just get the associated text
                                String temp = xpp.nextText();
                                // Do something with text
                                Log.e("MyTag","pubDate is " + temp);
                                item.setPubDate(temp.toString());

                                //listItems.add(item.getPubDate());
                            }
                }
                else
                if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {


//                        Toast.makeText(this, widget.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("MyTag","widget is " + item.toString());
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = list.size();
                        Log.e("MyTag","channel size is " + size);
                    }
                }


                // Get the next event
                eventType = xpp.next();

            } // End of while

            //return alist;
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End document");

        return list;



    }


    }
