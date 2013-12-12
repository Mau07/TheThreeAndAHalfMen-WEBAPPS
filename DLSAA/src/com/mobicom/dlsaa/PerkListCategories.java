package com.mobicom.dlsaa;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PerkListCategories extends Activity {

	// All static variables
		static final String URL = "http://api.androidhive.info/music/music.xml";
		// XML node keys
		static final String KEY_SONG = "song"; // parent node
		static final String KEY_ID = "id";
		static final String KEY_TITLE = "title";
		static final String KEY_DISCOUNT = "discount";
		static final String KEY_DISTANCE = "distance";
		private Cursor partners;
		private MyDatabase db;
		String name;
		String discount;
		String category;
		
		ListView list;
	    LazyAdapter adapter;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_perk_list);
			db = new MyDatabase(this);
			
			
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
			    category = extras.getString("category");
			    //discount = extras.getString("discount");
			    //branch = extras.getString("branch");
			}

			ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

			/*for (int i = 0; i < 5; i++) {
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				
				// adding each child node to HashMap key => value
				map.put(KEY_ID, Integer.toString(i));
				map.put(KEY_TITLE, "Jollibee");
				map.put(KEY_DISCOUNT, "10% off all value meals");
				map.put(KEY_DISTANCE, "50 m");

				// adding HashList to ArrayList
				songsList.add(map);
			} */
			
			db.getPartnerList();
			for (int i = 0; i < db.getPartnerCount() ; i++) {
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				//db.getPartnerCount()
				
				// adding each child node to HashMap key => value
				map.put(KEY_ID, Integer.toString(i));
				map.put(KEY_TITLE, db.getPartnerList().get(i).toString());
				map.put(KEY_DISCOUNT, db.getPartnerDiscountList().get(i).toString());
				//map.put(KEY_DISTANCE, "50 m");
	//db.getPartnerList().get(i).toString()
				// adding HashList to ArrayList
				songsList.add(map);
			}
			

			list=(ListView)findViewById(R.id.list);
			
			// Getting adapter by passing xml data ArrayList
	        adapter=new LazyAdapter(this, songsList);        
	        list.setAdapter(adapter);
	        

	        // Click event for single list row
	        list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
								
					Intent intent = new Intent(PerkListCategories.this, MainActivity.class);
		            startActivity(intent);

				}
			});		
		}	
		
		@Override
		protected void onDestroy() {
			super.onDestroy();
			partners.close();
			db.close();
		}
		
		public String getCategory(){
			return category;
		}
	}

