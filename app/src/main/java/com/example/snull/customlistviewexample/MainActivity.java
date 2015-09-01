package com.example.snull.customlistviewexample;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/*
1 store all the data somewhere [intentions, descriptions, and images]
2 define a structure of a single row of your listview inside single_row.xml
3 define the listview inside main layout and reference it in your activity
4 create your custom adapter that puts the data for each row inside getView
    4.1 create a clss that extends BaseAdapter and implements all the methods
    4.2 Maintain some array inside your BaseAdapter class that will contain the data
    [titles+descriptions+images]
    4.3 use the getView method to fill the data from your array inside the custom structure of that single row for each row
 */

public class MainActivity extends Activity {


    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list= (ListView) findViewById(R.id.listView);
        list.setAdapter(new ScxttAdapter(this));

    }

}

class SingleRow {
    String title;
    String description;
    int image;
    SingleRow(String title,String description, int image) {
        this.title=title;
        this.description=description;
        this.image=image;
    }

}

class ScxttAdapter extends BaseAdapter {

    /* need object array u1, u2, u3, u4 ...
    u1->title, description and image
     */
    ArrayList<SingleRow> list;
    Context context;
    ScxttAdapter(Context c) {
        list = new ArrayList<SingleRow>();
        context = c;
        /*
        1 get the titles, descriptions and images from xml
        2 group each title, its related desc and image into a single row
        3 put the single row objects inside the arraylist
         */
        Resources res=c.getResources();
        String[] titles=res.getStringArray(R.array.titles);
        String[] descriptions=res.getStringArray(R.array.descriptions);
        int[] images={R.drawable.meme1, R.drawable.meme2, R.drawable.meme3, R.drawable.meme4, R.drawable.meme5, R.drawable.meme6, R.drawable.meme7, R.drawable.meme8, R.drawable.meme9, R.drawable.meme10};
        for (int i=0;i<10;i++) {
            list.add(new SingleRow(titles[i],descriptions[i],images[i]));

        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*
        1 get the root view as in the relative layout of the single_row.xml, use inflater to make xml into an object
        2 use the root view to find other views
        3 set the values
         */
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row, viewGroup, false);
        TextView title = (TextView) row.findViewById(R.id.textView);
        TextView description = (TextView) row.findViewById(R.id.textView2);
        ImageView image = (ImageView) row.findViewById(R.id.imageView);

        SingleRow temp = list.get(i);

        title.setText(temp.title);
        description.setText(temp.description);
        image.setImageResource(temp.image);

        return row; //return the rootView of your single_row.xml
    }
}