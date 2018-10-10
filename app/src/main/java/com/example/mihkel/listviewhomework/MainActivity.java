package com.example.mihkel.listviewhomework;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.app.AlertDialog;

import com.example.mihkel.listviewhomework.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
public class MainActivity extends AppCompatActivity  {
    private EditText txt_firstname, txt_lastname;
    private Button btn_clear,btn_add;
    ArrayList<String> Names=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (mListView == null) {
            mListView = (ListView) findViewById(R.id.list);
        }
        txt_firstname = findViewById (R.id.firstnamee);
        txt_lastname =findViewById(R.id.lastname);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Names);
        setListAdapter(adapter);
        mListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, long id) {
                Log.v("long clicked","pos: " + pos);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Delete");
                builder1.setTitle("Delete?");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Names.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder1.setNegativeButton("Cancel",null);
                AlertDialog dialog = builder1.create();
                dialog.show();
                return true;
            }
        });
    }
    public void clear(View view) {
        txt_firstname.setText("");
        txt_lastname.setText("");
    }
    public void add(View view) {
        Names.add(txt_firstname.getText().toString() +" "+ txt_lastname.getText().toString());
        adapter.notifyDataSetChanged();
        txt_firstname.setText("");
        txt_lastname.setText("");
    }
    protected ListView getListView() {
        if (mListView == null) {
            mListView = (ListView) findViewById(R.id.list);
        }
        return mListView;
    }
    protected void setListAdapter(ListAdapter adapter) {
        getListView().setAdapter(adapter);
    }
    protected ListAdapter getListAdapter() {
        ListAdapter adapter = getListView().getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            return ((HeaderViewListAdapter)adapter).getWrappedAdapter();
        } else {
            return adapter;
        }
    }
}