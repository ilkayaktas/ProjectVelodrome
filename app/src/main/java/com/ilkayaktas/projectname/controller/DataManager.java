package com.ilkayaktas.projectname.controller;

import android.content.Context;

import com.ilkayaktas.projectname.controller.api.IApiHelper;
import com.ilkayaktas.projectname.controller.db.IDbHelper;
import com.ilkayaktas.projectname.controller.pref.IPreferenceHelper;
import com.ilkayaktas.projectname.di.annotations.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by ilkay on 11/03/2017.
 */

@Singleton
public class DataManager implements IDataManager {
	
	private final Context mContext;
	private final IDbHelper mIDbHelper;
	private final IPreferenceHelper mIPreferenceHelper;
	private final IApiHelper mIApiHelper;
	
	@Inject
	public DataManager(@ApplicationContext Context mContext, IDbHelper mIDbHelper, IPreferenceHelper mIPreferenceHelper, IApiHelper mIApiHelper) {
		this.mContext = mContext;
		this.mIDbHelper = mIDbHelper;
		this.mIPreferenceHelper = mIPreferenceHelper;
		this.mIApiHelper = mIApiHelper;
	}

	@Override
	public boolean getDatabaseCreatedStatus() {
		return mIPreferenceHelper.getDatabaseCreatedStatus();
	}

	@Override
	public void setDatabaseCreatedStatus() {
		mIPreferenceHelper.setDatabaseCreatedStatus();
	}
}
