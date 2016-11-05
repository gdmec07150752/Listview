package com.example.weijia.listview;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.listview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"ArrayAdapter");
        menu.add(0,2,0,"SimpleCursorAdapter");
        menu.add(0,3,0,"SimpleAdapter");
        menu.add(0,4,0,"BaseAdapter");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                arrayAdapter();
                break;
            case 2:
                simpleCursorAdapter();
                break;
            case 3:
                simpleAdapter();
                break;
            case 4:
                baseAdapter();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void arrayAdapter() {
        final String[] weeks={"星期天","星期一","星期二","星期三","星期四","星期五","星期六"};
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,weeks);
        lv.setAdapter(adapter);
    }

    private void simpleCursorAdapter() {
        final Cursor mCursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        startManagingCursor(mCursor);
        SimpleCursorAdapter mAdapter=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,
                mCursor,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{android.R.id.text1},
                0);
        lv.setAdapter(mAdapter);
    }
    private void simpleAdapter() {
        final List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("title","G5");
        map.put("info","325");
        map.put("img",R.drawable.icon1);
        list.add(map);

        map=new HashMap<String,Object>();
        map.put("title","G5");
        map.put("info","220");
        map.put("img",R.drawable.icon1);
        list.add(map);

        map=new HashMap<String,Object>();
        map.put("title","G5");
        map.put("info","526");
        map.put("img",R.drawable.icon1);
        list.add(map);

        SimpleAdapter mAdapter=new SimpleAdapter(this,list,R.layout.sa,
                new String[]{"img","title","info"},
                new int[]{R.id.imageView, R.id.titleView, R.id.infoView});
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,list.get(position).get("title").toString(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    static class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView info;
        public Button btn;
        public LinearLayout layout;
    }

    private void baseAdapter() {
        class MyBaseAdapter extends BaseAdapter{
            private List<Map<String,Object>> data;
            private Context context;
            private LayoutInflater myli;

            public MyBaseAdapter(Context context,List<Map<String,Object>> data){
                super();
                this.data=data;
                this.context=context;
                this.myli=myli;
            }

            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder=null;
                if (convertView==null){
                    holder=new ViewHolder();
                    convertView=myli.inflate(R.layout.l1,parent,false);
                    holder.img= (ImageView) convertView.findViewById(R.id.img);
                    holder.btn= (Button) convertView.findViewById(R.id.btn);
                    holder.title= (TextView) convertView.findViewById(R.id.title1);
                    holder.info= (TextView) convertView.findViewById(R.id.info1);
                    holder.layout= (LinearLayout) convertView.findViewById(R.id.l1);
                    convertView.setTag(holder);
                }else{
                    holder= (ViewHolder) convertView.getTag();
                }
                holder.img.setImageResource(Integer.parseInt(data.get(position).get("img").toString()));
                holder.title.setText((Integer) data.get(position).get("title".toString()));
                holder.info.setText(data.get(position).get("info").toString());

                if (position%2==1){
                    holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAccent));
                }else{
                    holder.layout.setBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimary));
                }
                ;
                return convertView;
            }
        }

        final List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("title","G5");
        map.put("info","325");
        map.put("img",R.drawable.icon1);
        list.add(map);

        map=new HashMap<String,Object>();
        map.put("title","G5");
        map.put("info","220");
        map.put("img",R.drawable.icon1);
        list.add(map);

        map=new HashMap<String,Object>();
        map.put("title","G5");
        map.put("info","526");
        map.put("img",R.drawable.icon1);
        list.add(map);

        map=new HashMap<String,Object>();
        map.put("title","G5");
        map.put("info","403");
        map.put("img",R.drawable.icon1);
        list.add(map);

        MyBaseAdapter myba=new MyBaseAdapter(this,list);
        lv.setAdapter(myba);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,list.get(position).get("title").toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void filter(View v){
        EditText et= (EditText) findViewById(R.id.et1);
        ArrayAdapter adapter= (ArrayAdapter) lv.getAdapter();
        adapter.getFilter().filter(et.getText().toString());
    }
}
