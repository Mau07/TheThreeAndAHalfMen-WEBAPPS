package com.mobicom.dlsaa;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {

	private static final String DATABASE_NAME = "perksDB";
	private static final int DATABASE_VERSION = 1;
	static final String KEY_ID = "id";
	static final String KEY_TITLE = "title";
	static final String KEY_DISCOUNT = "discount";
	int size=0;
	MainActivity m = new MainActivity();
	PerkListCategories cat = new PerkListCategories();
	PerkList perk = new PerkList();

	public MyDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		getReadableDatabase();
	}
	
    public Cursor getPartners() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String [] sqlSelect = {/*"partnerID",*/ "partnerName"};
        String [] sqlSelect = {"partnerID _id", "partnerName"};
        String sqlTables = "partners";

        String category = perk.getCategory(); 
        
        qb.setTables(sqlTables);
        
        Log.i("System.out","Hello TEST mydatabase " + perk.getCategory());
        Log.i("System.out","Hello TEST2 mydatabase " + category);
        
        if(perk.getCategory() != null){
            /*Cursor c = qb.query(db, sqlSelect, m.getCategory(), null,
                            null, null, null);*/
        	Cursor c = db.rawQuery("SELECT partnerID, partnerName FROM partners WHERE category LIKE '%" + perk.getCategory() +"%'  ORDER BY partnerName COLLATE NOCASE",null);
            c.moveToFirst();
            return c;
            }
            else
            { 
            	/*Cursor c = qb.query(db, sqlSelect, null, null,
                    null, null, null);*/
            	Cursor c = db.rawQuery("SELECT partnerID, partnerName FROM partners ORDER BY partnerName COLLATE NOCASE",null);
            c.moveToFirst();
            return c;
            }

}
    public Cursor partnerSearch(String keyword) {
    	
    	SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String key = keyword;

        //String [] sqlSelect = {/*"partnerID",*/ "partnerName"};
        String [] sqlSelect = {"partnerID _id", "partnerName"};
        String sqlTables = "partners";

        qb.setTables(sqlTables); 
        
        Cursor cursor = db.rawQuery("SELECT partnerID, partnerName, partnerDiscounts FROM partners where keywords LIKE '%"+ key+"%'",null);
        cursor.moveToFirst();
        
        
        
        return cursor;

}
    
    
    
   public ArrayList<HashMap<String, String>> getpartnerSearchResult(Cursor cursor) {
    	
	   ArrayList<HashMap<String, String>> SearchresultMapList = new ArrayList<HashMap<String, String>>(); 
       
	   //Log.i("System.out","Hello!3");
	   //cursor.moveToFirst();
                if (cursor.moveToFirst()) {
                	Log.i("System.out","Hello!");
        	do { 
        	 
        		
        		HashMap<String, String> SearchresultMap = new HashMap<String, String>(); 
        		SearchresultMap.put("KEY_ID", cursor.getString(0));
        		SearchresultMap.put("KEY_TITLE", cursor.getString(1));
        		SearchresultMap.put("KEY_DISCOUNT", cursor.getString(2));
        		
        		//Log.i("System.out","Hello! TRY" + cursor.getString(0));
        		//Log.i("System.out","Hello! TRY" + cursor.getString(1));
        		//Log.i("System.out","Hello! TRY" + cursor.getString(2));
        		
        		SearchresultMapList.add(SearchresultMap);
        	 
        	} while (cursor.moveToNext());
        }    
        
            return SearchresultMapList;

}
    
    
    public Cursor getBranches() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String [] sqlSelect = {/*"partnerID",*/ "partnerName"};
        String [] sqlSelect = {"branchID _id", "partnerBranches"};
        String sqlTables = "partnerBranches";

        qb.setTables(sqlTables);
        if(m.getCategory() != null){
            Cursor d = qb.query(db, sqlSelect, null, null,
                            null, null, null);

            d.moveToFirst();
            return d;}
            else{Cursor d = qb.query(db, sqlSelect, null, null,
                    null, null, null);

    d.moveToFirst();
    return d;
            	
            }

}
    
    public Cursor getPartnerDiscount() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String [] sqlSelect = {/*"partnerID",*/ "partnerName"};
        String [] sqlSelect = {"partnerID _id", "partnerDiscounts"};
        String sqlTables = "partners";

        qb.setTables(sqlTables);
        /*Cursor d = qb.query(db, sqlSelect, null, null,
                        null, null, null);*/
        Cursor d = db.rawQuery("SELECT partnerID, partnerDiscounts FROM partners ORDER BY partnerName COLLATE NOCASE",null); //"SELECT partnerID, partnerDiscounts FROM partners"

        d.moveToFirst();
        return d;

}
    
    public ArrayList getPartnerList(){
    	Cursor cursor = getPartners();
    	ArrayList <String> partners= new ArrayList();
    	
    	if (cursor.moveToFirst())
        { 
           do 
           {
               try{
            	   partners.add(cursor.getString(cursor.getColumnIndex("partnerName")));
               } catch (Exception h){
               Log.i("FAILED", cursor.getString(cursor.getColumnIndex("partnerName")));  
               }
               
             }while (cursor.moveToNext()); 
           size=partners.size();
           getPartnerCount();
        }
    	
    	return partners;
    }
    
    public ArrayList getBranchList(){
    	Cursor cursor = getBranches();
    	ArrayList <String> branches= new ArrayList();
    	
    	if (cursor.moveToFirst())
        { 
           do 
           {
               try{
            	   branches.add(cursor.getString(cursor.getColumnIndex("partnerBranches")));
               } catch (Exception h){
               Log.i("FAILED", cursor.getString(cursor.getColumnIndex("partnerBranches")));  
               }
               
             }while (cursor.moveToNext()); 
           size=branches.size();
           getPartnerCount();
        }
    	
    	return branches;
    }
    
    /*public ArrayList getBranchList(){
    	Cursor cursor = getPartners();
    	ArrayList <String> partners= new ArrayList();
    	
    	if (cursor.moveToFirst())
        { 
           do 
           {
               try{
               partners.add(cursor.getString(cursor.getColumnIndex("partnerName")));
               } catch (Exception h){
               Log.i("FAILED", cursor.getString(cursor.getColumnIndex("partnerName")));  
               }
               
             }while (cursor.moveToNext()); 
           size=partners.size();
           getPartnerCount();
        }
    	
    	return partners;
    }*/
    
    public ArrayList getPartnerDiscountList(){
    	Cursor cursor = getPartnerDiscount();
    	ArrayList <String> discount= new ArrayList();
    	
    	if (cursor.moveToFirst())
        { 
           do 
           {
               try{
               discount.add(cursor.getString(cursor.getColumnIndex("partnerDiscounts")));
               } catch (Exception h){
               Log.i("FAILED", cursor.getString(cursor.getColumnIndex("partnerDicounts")));  
               }
               
             }while (cursor.moveToNext()); 
           size=discount.size();
           getPartnerCount();
        }
    	
    	return discount;
    }
    
    public int getPartnerCount(){
    	int value = size;
    	return value;
    }
    
    public String setCategoryType(String category){
    	String categoryType = category;
    	
    	return categoryType;
    }
    
    public Cursor getLat(){
    	
    	SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String [] sqlSelect = {/*"partnerID",*/ "partnerName"};
        String [] sqlSelect = {"partnerID _id", "partnerName"};
        String sqlTables = "partners";

        qb.setTables(sqlTables);
 
            	/*Cursor c = qb.query(db, sqlSelect, null, null,
                    null, null, null);*/
            Cursor lat = db.rawQuery(" SELECT partnerName, partnerBranches, lat FROM partners, partnerBranches WHERE partners.partnerID = partnerBranches.partnerID ",null);
            lat.moveToFirst();
            return lat;
      
    }
    
    public Cursor getLong(){
    	
    	SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String [] sqlSelect = {/*"partnerID",*/ "partnerName"};
        String [] sqlSelect = {"partnerID _id", "partnerName"};
        String sqlTables = "partners";

        qb.setTables(sqlTables);
 
            	/*Cursor c = qb.query(db, sqlSelect, null, null,
                    null, null, null);*/
            Cursor lng = db.rawQuery(" SELECT partnerName, partnerBranches, long FROM partners, partnerBranches WHERE partners.partnerID = partnerBranches.partnerID ",null);
            lng.moveToFirst();
            return lng;
            

    }

}
