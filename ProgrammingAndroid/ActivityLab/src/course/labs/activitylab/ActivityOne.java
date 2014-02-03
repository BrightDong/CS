package course.labs.activitylab;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class ActivityOne extends Activity {

	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";
	
	// Lifecycle counters

	// TODO:
	// Create counter variables for onCreate(), onRestart(), onStart() and
	// onResume(), called mCreate, etc.
	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called
//	private int cOnCreate = 0;
//	private int cOnRestart = 0;
//	private int cOnStart = 0;
//	private int cOnResume = 0;
	private int mStart;
	private int mResume;
	private int mRestart;
	private int mCreate;




	// TODO: Create variables for each of the TextViews, called
        // mTvCreate, etc. 
	private TextView mTvCreate;

	private TextView mTvStart;
	private TextView mTvResume;

	private TextView mTvRestart;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		
		// TODO: Assign the appropriate TextViews to the TextView variables
		// Hint: Access the TextView by calling Activity's findViewById()
		// textView1 = (TextView) findViewById(R.id.textView1);
		mTvCreate = (TextView) findViewById(R.id.create);
		mTvRestart = (TextView) findViewById(R.id.restart);
		mTvResume = (TextView) findViewById(R.id.resume);
		mTvStart = (TextView) findViewById(R.id.start);




		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo); 
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO:
				// Launch Activity Two
				// Hint: use Context's startActivity() method

				// Create an intent stating which Activity you would like to start

				
				// Launch the Activity using the intent

				startActivity(new Intent(ActivityOne.this,
						ActivityTwo.class));

			
			}
		});
		
		// Check for previously saved state
		if (savedInstanceState != null) {

			// TODO:
			// Restore value of counters from saved state
			// Only need 4 lines of code, one for every count variable
//			mCreate = savedInstanceState.getInt("cOnCreate");
//			mRestart = savedInstanceState.getInt("cOnRestart");
//			mResume = savedInstanceState.getInt("cOnResume");
//			mStart = savedInstanceState.getInt("cOnStart");
			mCreate = Integer.valueOf(savedInstanceState.getString("cOnCreate"));
			mRestart = Integer.valueOf(savedInstanceState.getString("cOnRestart"));
			mResume = Integer.valueOf(savedInstanceState.getString("cOnResume"));
			mStart = Integer.valueOf(savedInstanceState.getString("cOnStart"));
		
		}

		// TODO: Emit LogCat message
		Log.i(TAG, "onCreate");
		

		// TODO:
		// Update the appropriate count variable
		// Update the user interface via the displayCounts() method
		mCreate++;
		displayCounts();


	}

	// Lifecycle callback overrides

	@Override
	public void onStart() {
		super.onStart();

		// TODO: Emit LogCat message
		Log.i(TAG, "onStart");

		// TODO:
		// Update the appropriate count variable
		// Update the user interface
		mStart++;
		displayCounts();

	}

	@Override
	public void onResume() {
		super.onResume();

		// TODO: Emit LogCat message
		Log.i(TAG, "onResume");

		// TODO:
		// Update the appropriate count variable
		// Update the user interface
		mResume++;
		displayCounts();

	}

	@Override
	public void onPause() {
		super.onPause();

		// TODO: Emit LogCat message
		Log.i(TAG, "onPause");
	}

	@Override
	public void onStop() {
		super.onStop();

		// TODO: Emit LogCat message
		Log.i(TAG, "onStop");
	}

	@Override
	public void onRestart() {
		super.onRestart();

		// TODO: Emit LogCat message
		Log.i(TAG, "onRestart");

		// TODO:
		// Update the appropriate count variable
		// Update the user interface
		mRestart++;
		displayCounts();


	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		// TODO: Emit LogCat message
		Log.i(TAG, "onDestroy");

	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO:
		// Save state information with a collection of key-value pairs
		// 4 lines of code, one for every count variable
		//super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("cOnCreate", String.valueOf(mCreate));	
        savedInstanceState.putString("cOnRestart", String.valueOf(mRestart));	
        savedInstanceState.putString("cOnStart", String.valueOf(mStart));
        savedInstanceState.putString("cOnResume", String.valueOf(mResume));

	}
	
	// Updates the displayed counters
	public void displayCounts() {

		mTvCreate.setText("onCreate() calls: " + mCreate);
		mTvStart.setText("onStart() calls: " + mStart);
		mTvResume.setText("onResume() calls: " + mResume);
		mTvRestart.setText("onRestart() calls: " + mRestart);
	
	}
}
