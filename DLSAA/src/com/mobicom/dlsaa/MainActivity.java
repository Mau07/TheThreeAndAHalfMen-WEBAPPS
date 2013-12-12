package com.mobicom.dlsaa;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.content.DialogInterface;
import android.content.Intent;


public class MainActivity extends Activity implements LocationListener {
	ImageView Img;
	public String category;
	TextView txtView_address;
	TextView txtView_name;
	TextView txtView_branch;
	String test = "test";
	String name;
	String value;
	String discount;
	String branch;
	private GoogleMap miniMap;
	GoogleMapOptions options = new GoogleMapOptions();
	private LocationManager locationManager;
	private String provider;
	static final LatLng PERTH = new LatLng(14.562567, 120.994703); // 14.63541,121.021042
	static final LatLng metroManila = new LatLng(14.63541, 121.021042);
	private SupportMapFragment miniMapFragment;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*miniMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map2))
				.getMap();
		miniMap.setMyLocationEnabled(true);
		try {
			MapsInitializer.initialize(this);
		} catch (GooglePlayServicesNotAvailableException e) {
			e.printStackTrace();
		} */
		
		
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    name = extras.getString("name");
		    discount = extras.getString("discount");
		    branch = extras.getString("branch");
		}
		
		
		
		Img = (ImageView) findViewById(R.id.imageView1);
		txtView_name = (TextView) findViewById(R.id.textView1); //name
		txtView_address = (TextView) findViewById(R.id.textView2); //discount
		txtView_branch = (TextView) findViewById(R.id.textView3); //branch
		txtView_name.setText(name);
		txtView_address.setText(discount);
		txtView_branch.setText(branch);
		
		
		
		Img.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            Intent intent = new Intent(MainActivity.this, MapScreen.class);
	            
				String selectedItem_name = name;
				String selectedItem_discount = discount;
				String selectedItem_branch = branch;
				intent.putExtra("name", selectedItem_name);
				intent.putExtra("discount", selectedItem_discount);
				intent.putExtra("branch", selectedItem_branch);
	            
	            startActivity(intent);
	        }
	    });
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		//case R.id.action_settings:
			//action_settingsMenuItem();
			//break;
		case R.id.action_search:
			action_searchMenuItem();
			break;
		case R.id.perks:
			category = null;
			Intent intent = new Intent(MainActivity.this, PerkList.class);
			startActivity(intent);
		case R.id.cat_clothingfootwear:
			category = "Clothing and Footwear";
			Intent intent2 = new Intent(MainActivity.this, PerkList.class);
			//category = "Clothing and Footwear";
			intent2.putExtra("category",category);
			Log.i("System.out","Hello " + category);
		    startActivity(intent2);
			break;			
		case R.id.cat_culturearts:
			category = "Culture and Arts";
			Intent intent3 = new Intent(MainActivity.this, PerkList.class);
			intent3.putExtra("category","Culture and Arts");
			//category = "Culture and Arts";
		    startActivity(intent3);
			break;			
		case R.id.cat_flowers:
			category = "Flower Shops";
			Intent intent4 = new Intent(MainActivity.this, PerkList.class);
			intent4.putExtra(category, "Flower Shops");
		    startActivity(intent4);		    
			break;			
		case R.id.cat_gadgets:
			category = "Gadgetry";
			Intent intent5 = new Intent(MainActivity.this, PerkList.class);
			intent5.putExtra("category", "Gadgetry");
		    startActivity(intent5);
			break;			
		case R.id.cat_healthwellness:
			category = "Health and Wellness";
			Intent intent6 = new Intent(MainActivity.this, PerkList.class);
			intent6.putExtra("Category", "Health and Wellness");
		    startActivity(intent6);
			break;			
		case R.id.cat_hotelresort:
			category = "Hotels and Resorts";
			Intent intent7 = new Intent(MainActivity.this, PerkList.class);
			intent7.putExtra("category", "Hotels and Resorts");
		    startActivity(intent7);
			break;			
		case R.id.cat_hobbieshousehold:
			category = "Hobbies and Household";
			Intent intent8 = new Intent(MainActivity.this, PerkList.class);
			intent8.putExtra("categories", "Hobbies and Household");
		    startActivity(intent8);
			break;			
		case R.id.cat_motoring:
			category = "Motoring";
			Intent intent9 = new Intent(MainActivity.this, PerkList.class);
			intent9.putExtra("category", "motoring");
		    startActivity(intent9);
			break;			
		case R.id.cat_photography:
			category = "Photography";
			Intent intent10 = new Intent(MainActivity.this, PerkList.class);
			intent10.putExtra("category","Photography");
		    startActivity(intent10);
			break;			
		case R.id.cat_optical:
			category = "Optical Shops";
			Intent intent11 = new Intent(MainActivity.this, PerkList.class);
			intent11.putExtra("category","Optical Shops");
		    startActivity(intent11);
			break;			
		case R.id.cat_restaurantbars:
			category = "Restaurants and Bars";
			Intent intent12 = new Intent(MainActivity.this, PerkList.class);
			intent12.putExtra("category", "Restaurants and Bars");
		    startActivity(intent12);
			break;							
		case R.id.cat_traveltour:
			category = "Travels and Tours";
			Intent intent13 = new Intent(MainActivity.this, PerkList.class);
			intent13.putExtra("category", "Travels and Tours");
		    startActivity(intent13);
			break;			
		
		}
		return true;
	}
	
		
	String getCategory()
	{//category = "Clothing and Footwear";
		return category;
	}
	
	private void action_searchMenuItem(){
		new AlertDialog.Builder(this)
		.setTitle("Search")
		.setMessage("Search is not yet available")
		.setNeutralButton("OK", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			
		}).show();
	}


	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	/*private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (miniMap == null) {
			// miniMap = ((MapFragment)
			// getFragmentManager().findFragmentById(R.id.map)).getMap();
			miniMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map2)).getMap();
			// Marker burgundy = miniMap.addMarker(new
			// MarkerOptions().position(PERTH).draggable(false));
			// Check if we were successful in obtaining the map.
			if (miniMap != null) {
				// The Map is verified. It is now safe to manipulate the map.

			}
		}
	} */

}
