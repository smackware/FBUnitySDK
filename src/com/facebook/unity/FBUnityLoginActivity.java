package com.facebook.unity;

import android.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import com.facebook.Session;
import com.unity3d.player.UnityPlayerActivity;

public class FBUnityLoginActivity extends UnityPlayerActivity  {
	public static final String LOGIN_PARAMS = "login_params";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.w("FBTAG", "FBUnityLoginActivity.onCreate");
        super.onCreate(savedInstanceState);
		restoreSession(savedInstanceState);
        FBLogin.login(getIntent().getStringExtra(LOGIN_PARAMS), this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.w("FBTAG", "FBUnityLoginActivity.onStart");
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		FBLogin.onActivityResult(this, requestCode, resultCode, data);
		//this.finish();
	}
	
    @Override
    protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean("rerun", true);
		Log.e("FBTAG", "FBUnityLoginActivity.onSaveInstanceState");
        super.onSaveInstanceState(outState);
		Session session = Session.getActiveSession();		
		Log.e("FBTAG", "FBUnityLoginActivity.onSaveInstanceState - saving session: " + (session == null ? "null" : session.toString()));
        Session.saveSession(session, outState);
    }

	private void restoreSession(Bundle savedInstanceState)
	{
		Session session = Session.restoreSession(this, null, null, savedInstanceState);
		if (session == null) {
			Log.w("FBTAG", "FBUnityLoginActivity.restoreSession - stored session not found - creating a new session");
			session = new Session(this);
		}
		else
		{
			Log.e("FBTAG", "FBUnityLoginActivity.restoreSession - restoring session: " + (session == null ? "null" : session.toString()));		
		}
		Session.setActiveSession(session);		
	}
	
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.e("FBTAG", "FBUnityLoginActivity.onRestoreInstanceState");
		super.onRestoreInstanceState(savedInstanceState);		
		restoreSession(savedInstanceState);
		//FBLogin.login(getIntent().getStringExtra(LOGIN_PARAMS), this);
	}
	
	@Override
    protected void onRestart()
	{
		super.onRestart();
		Log.w("FBTAG", "FBUnityLoginActivity.onRestart");
	}

	@Override
    protected void onResume()
	{
		super.onResume();
		Log.w("FBTAG", "FBUnityLoginActivity.onResume");
	}	

	@Override
    protected void onPause()
	{
		super.onPause();
		Log.w("FBTAG", "FBUnityLoginActivity.onPause");
	}	
	
	@Override
    protected void onStop()
	{
		super.onStop();
		Log.w("FBTAG", "FBUnityLoginActivity.onStop");
	}	

	@Override
    protected void onDestroy()
	{
		super.onDestroy();
		Log.w("FBTAG", "FBUnityLoginActivity.onDestroy");
	}		
}
