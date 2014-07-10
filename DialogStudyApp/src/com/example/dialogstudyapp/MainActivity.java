package com.example.dialogstudyapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	ProgressDialog pd;

	CharSequence[] newsChannels = { "BBC", "Reuters", "NHK", "DW", "CNN" };
	boolean[] initialStatus = { true, false, false, true, true };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void dailogShowKaro(View v) {
		showDialog(3);

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 100; i++) {
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
					pd.setProgress(i);
				}
				pd.dismiss();
			}
		}).start();

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.ic_launcher);
			builder.setTitle("Are you sure ? ");
			builder.setPositiveButton("OK", new OnClickListener() {
				@Override
				public void onClick(DialogInterface d, int featureId) {
					Toast.makeText(getBaseContext(), "OK Clicked " + featureId,
							Toast.LENGTH_SHORT).show();
				}
			});
			builder.setNegativeButton("Nope", new OnClickListener() {
				@Override
				public void onClick(DialogInterface d, int featureId) {
					Toast.makeText(getBaseContext(),
							"Nope Clicked " + featureId, Toast.LENGTH_SHORT)
							.show();
				}
			});
			builder.setMessage("You are about to delete sensitive data !");

			return builder.create();
		case 2:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setTitle("Choose News Channel");
			builder2.setIcon(R.drawable.ic_launcher);
			builder2.setMultiChoiceItems(newsChannels, initialStatus,
					new OnMultiChoiceClickListener() {
						@Override
						public void onClick(DialogInterface arg0,
								int itemNumber, boolean checkedStatus) {
							CharSequence nc = newsChannels[itemNumber];
							String stat = (checkedStatus == true) ? "Checked"
									: "Unchecked";
							Toast.makeText(getBaseContext(), nc + "  " + stat,
									Toast.LENGTH_SHORT).show();
						}
					});
			return builder2.create();

		case 3:
			pd = new ProgressDialog(this);
			pd.setTitle("Downloading Bryan Adams-Party in Babylon.mp3");
			pd.setIcon(R.drawable.ic_launcher);
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd.setButton("Cancel", new OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Toast.makeText(getBaseContext(), "Download Cancelled",
							Toast.LENGTH_SHORT).show();
				}
			});

			return pd;

		default:
			break;
		}
		return super.onCreateDialog(id);
	}
}
