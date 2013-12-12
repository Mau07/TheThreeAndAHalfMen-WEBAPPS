package com.mobicom.dlsaa;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Search {

	/*
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    final MenuItem search = menu.add(0, MENU_SEARCH, MENU_SEARCH, getString(R.string.search));
	    search.setIcon(R.drawable.ic_action_search);
	    search.setActionView(R.layout.action_search);
	    search.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case MENU_SEARCH:
	            mSearch = (EditText) item.getActionView();
	            mSearch.addTextChangedListener(mFilterTextWatcher);
	            mSearch.requestFocus();
	            mSearch.postDelayed(new Runnable() {
	                @Override
	                public void run() {
	                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
	                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	                }
	            }, 200);

	            break;
	    }
	    return true;
	}*/
}
