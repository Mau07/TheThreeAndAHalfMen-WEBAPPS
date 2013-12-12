package com.mobicom.dlsaa;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//import com.example.actionbarsearchview.R;
import com.mobicom.dlsaa.MyDatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PerkList extends Activity implements
		SearchView.OnQueryTextListener {
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
	private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	
	private SearchView mSearchView;
	private TextView mStatusView;

	ListView list;
	LazyAdapter adapter;
	
	String category;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_perk_list);

		// mStatusView = (TextView) findViewById(R.id.status_text);

		db = new MyDatabase(this);


		/*
		 * for (int i = 0; i < 5; i++) { // creating new HashMap HashMap<String,
		 * String> map = new HashMap<String, String>();
		 * 
		 * // adding each child node to HashMap key => value map.put(KEY_ID,
		 * Integer.toString(i)); map.put(KEY_TITLE, "Jollibee");
		 * map.put(KEY_DISCOUNT, "10% off all value meals");
		 * map.put(KEY_DISTANCE, "50 m");
		 * 
		 * // adding HashList to ArrayList songsList.add(map); }
		 */

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    category = extras.getString("category");
		    Log.i("System.out","Hello TEST " + category);
		    //discount = extras.getString("discount");
		    //branch = extras.getString("branch");
		}
		
		db.getPartnerList();
		for (int i = 0; i < db.getPartnerCount(); i++) { // db.getPartnerCount()
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			// db.getPartnerCount()

			// adding each child node to HashMap key => value
			map.put(KEY_ID, Integer.toString(i));
			map.put(KEY_TITLE, db.getPartnerList().get(i).toString());
			map.put(KEY_DISCOUNT, db.getPartnerDiscountList().get(i).toString());
			// map.put(KEY_DISTANCE, "50 m");
			// db.getPartnerList().get(i).toString()
			// adding HashList to ArrayList
			songsList.add(map);
		}

		list = (ListView) findViewById(R.id.list);

		// Getting adapter by passing xml data ArrayList
		adapter = new LazyAdapter(this, songsList);
		list.setAdapter(adapter);

		// Click event for single list row
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(PerkList.this, MainActivity.class);
				/*
				 * for (int j = 0; j < db.getPartnerCount() ; j++) {
				 * intent.putExtra
				 * ("name",db.getPartnerList().get(j).toString()); }
				 */
				// intent.putExtra("name",db.getPartnerList().get(1).toString());
				// String selectedItem = list.getSelectedItem().toString();
				int j = (Integer) (list.getItemAtPosition(position));
				String selectedItem_name = db.getPartnerList().get(j)
						.toString();
				String selectedItem_discount = db.getPartnerDiscountList()
						.get(j).toString();
				String selectedItem_branch = db.getBranchList().get(j)
						.toString();
				intent.putExtra("name", selectedItem_name);
				intent.putExtra("discount", selectedItem_discount);
				intent.putExtra("branch", selectedItem_branch);

				startActivity(intent);

			}
		});
	}
	
	public String getCategory(){
		
		return category;
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
 
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchview_in_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        setupSearchView(searchItem);
 
        return true;
    }
    
    private void setupSearchView(MenuItem searchItem) {
    	 
        if (isAlwaysExpanded()) {
            mSearchView.setIconifiedByDefault(false);
        } else {
            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM
                    | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        }
 
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            List<SearchableInfo> searchables = searchManager.getSearchablesInGlobalSearch();
 
            SearchableInfo info = searchManager.getSearchableInfo(getComponentName());
            for (SearchableInfo inf : searchables) {
                if (inf.getSuggestAuthority() != null
                        && inf.getSuggestAuthority().startsWith("applications")) {
                    info = inf;
                }
            }
            mSearchView.setSearchableInfo(info);
        }
 
        mSearchView.setOnQueryTextListener(this);
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		partners.close();
		db.close();
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		
		db = new MyDatabase(this);
		if (TextUtils.isEmpty(newText)) {
	        list.clearTextFilter();
	    } else {
	    	//list.clear();
	    	ArrayList<HashMap<String, String>> partnerSearchList = db.getpartnerSearchResult(db.partnerSearch(newText));
			
			
			Log.i("System.out","Hello!" + partnerSearchList.get(0));
			Log.i("System.out","Hello!" + partnerSearchList.get(1));
			Log.i("System.out","Hello!" + partnerSearchList.get(2));
			Log.i("System.out","Hello!" + partnerSearchList.get(3));
			Log.i("System.out","Hello!" + partnerSearchList.get(4));
			
			if(partnerSearchList.size() != 0){
			
				for (int i = 0; i < db.getPartnerCount(); i++) { // db.getPartnerCount()
					// creating new HashMap
					HashMap<String, String> map = new HashMap<String, String>();
					// db.getPartnerCount()

					// adding each child node to HashMap key => value
					map.put(KEY_ID, Integer.toString(i));
					map.put(KEY_TITLE, db.getPartnerList().get(i).toString());
					map.put(KEY_DISCOUNT, db.getPartnerDiscountList().get(i).toString());
					// map.put(KEY_DISTANCE, "50 m");
					// db.getPartnerList().get(i).toString()
					// adding HashList to ArrayList
					songsList.add(map);
				}
				
				list.invalidate();
				 list.setAdapter(null);
				 
				
			list = (ListView) findViewById(R.id.list);

			// Getting adapter by passing xml data ArrayList
			adapter = new LazyAdapter(this, partnerSearchList);
			
			list.setAdapter(adapter);
			adapter.notifyDataSetChanged();

			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

				
					Intent intent = new Intent(PerkList.this, MainActivity.class);
					/*
					 * for (int j = 0; j < db.getPartnerCount() ; j++) {
					 * intent.putExtra
					 * ("name",db.getPartnerList().get(j).toString()); }
					 */
					// intent.putExtra("name",db.getPartnerList().get(1).toString());
					// String selectedItem = list.getSelectedItem().toString();
					int j = (Integer) (list.getItemAtPosition(position));
					String selectedItem_name = db.getPartnerList().get(j)
							.toString();
					String selectedItem_discount = db.getPartnerDiscountList()
							.get(j).toString();
					String selectedItem_branch = db.getBranchList().get(j)
							.toString();
					intent.putExtra("name", selectedItem_name);
					intent.putExtra("discount", selectedItem_discount);
					intent.putExtra("branch", selectedItem_branch);

					startActivity(intent);

				}
			});
			}

	    }

	    return true;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		
				return false;
	}
	
    protected boolean isAlwaysExpanded() {
        return false;
    }
}
