package com.mobicom.dlsaa;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
//import android.support.v4.app.FragmentActivity;
import android.view.Menu;

//import com.google.android.gms.maps.SupportMapFragment;

public class MapScreen extends Activity implements LocationListener {

	private GoogleMap mMap;
	GoogleMapOptions options = new GoogleMapOptions();
	private LocationManager locationManager;
	private String provider;
	static final LatLng PERTH = new LatLng(14.562567, 120.994703); // 14.63541,121.021042
	static final LatLng metroManila = new LatLng(14.63541, 121.021042);
	private SupportMapFragment mMapFragment;

	// public static MapFragment newInstance (GoogleMapOptions options);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_screen);
		setUpMapIfNeeded();
		//ShowLocationActivity showLocation = new ShowLocationActivity();

		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		// Marker burgundy = mMap.addMarker(new
		// MarkerOptions().position(PERTH).draggable(false));
		mMap.setMyLocationEnabled(true);
		// Get the location manager

		try {
			MapsInitializer.initialize(this);
		} catch (GooglePlayServicesNotAvailableException e) {
			e.printStackTrace();
		}

		// MapFragment.newInstance(GoogleMapOptions options);
		/*
		 * CameraPosition cameraPosition = new CameraPosition.Builder()
		 * .target(metroManila) // Sets the center of the map to Mountain View
		 * .zoom(17) // Sets the zoom .bearing(90) // Sets the orientation of
		 * the camera to east .tilt(30) // Sets the tilt of the camera to 30
		 * degrees .build(); // Creates a CameraPosition from the builder
		 * options.mapType(GoogleMap.MAP_TYPE_NORMAL) .compassEnabled(false)
		 * .zoomGesturesEnabled(true) .rotateGesturesEnabled(true)
		 * .scrollGesturesEnabled(true) .camera(cameraPosition)
		 * .tiltGesturesEnabled(false);
		 */

		// CameraUpdate center = CameraUpdateFactory.newLatLng(new
		// LatLng(14.63541,121.021042));
		// mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(metroManila ,
		// 14.0f) );

		// mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

		/*
		 * Marker yourLocation = mMap.addMarker(new MarkerOptions()
		 * .position(new LatLng(showLocation.getLat(), showLocation.getLng()))
		 * //showLocation.getLat(), showLocation.getLng()
		 * .title("You are here"));
		 */
		// setting initial map location and map options
		/*
		 * GoogleMapOptions options = new
		 * GoogleMapOptions().camera(CameraPosition.fromLatLngZoom(new
		 * LatLng(14.63541,121.021042), 20)) .compassEnabled(false)
		 * .mapType(GoogleMap.MAP_TYPE_NORMAL) .rotateGesturesEnabled(true)
		 * .scrollGesturesEnabled(true) .tiltGesturesEnabled(false)
		 * .zoomControlsEnabled(true) .zoomGesturesEnabled(true); Marker
		 * yourLocation = mMap.addMarker(new MarkerOptions() .position(new
		 * LatLng(showLocation.getLat(), showLocation.getLng()))
		 * //showLocation.getLat(), showLocation.getLng()
		 * .title("You are here"));
		 */

		/*
		 * map:cameraBearing="112.5" map:cameraTargetLat="14.565474"
		 * map:cameraTargetLng="120.99408" map:cameraTilt="30"
		 * map:cameraZoom="13" map:mapType="normal" map:uiCompass="false"
		 * map:uiRotateGestures="true" map:uiScrollGestures="true"
		 * map:uiTiltGestures="false" map:uiZoomControls="false"
		 * map:uiZoomGestures="true"
		 */
		/* working code for user location */
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the location provider -> use
		// default
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);
		

		if (location != null) {
			// Getting latitude of the current location
			double latitude = location.getLatitude();

			// Getting longitude of the current location
			double longitude = location.getLongitude();

			// Creating a LatLng object for the current location
			LatLng latLng = new LatLng(latitude, longitude);

			LatLng myPosition = new LatLng(latitude, longitude);
			
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 13));

			mMap.addMarker(new MarkerOptions().position(myPosition).title(
					"You are here"));

			/*CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(myPosition) // Sets the center of the map to
											// Mountain View
					.zoom(17) // Sets the zoom
					.bearing(90) // Sets the orientation of the camera to east
					.tilt(30) // Sets the tilt of the camera to 30 degrees
					.build(); // Creates a CameraPosition from the builder*/
			
			//mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			options.mapType(GoogleMap.MAP_TYPE_NORMAL).compassEnabled(false)
					.zoomGesturesEnabled(true).rotateGesturesEnabled(true)
					.scrollGesturesEnabled(true)/*.camera(cameraPosition)*/
					.tiltGesturesEnabled(false);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// mMap = ((MapFragment)
			// getFragmentManager().findFragmentById(R.id.map)).getMap();
			mMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			// Marker burgundy = mMap.addMarker(new
			// MarkerOptions().position(PERTH).draggable(false));
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				// The Map is verified. It is now safe to manipulate the map.

			}
		}
	}

	/*
	 * private void setUpMapIfNeeded() { // Do a null check to confirm that we
	 * have not already instantiated the map. if (mMap == null) { // Try to
	 * obtain the map from the SupportMapFragment. mMap = mMapFragment.getMap();
	 * // Check if we were successful in obtaining the map. if (mMap != null) {
	 * setUpMap(); } } }
	 * 
	 * private void setUpMap() { mMap.addMarker(new MarkerOptions().position(new
	 * LatLng(0, 0)).title("Marker")); }
	 */

	@Override
	public void onLocationChanged(Location location) {
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

}

