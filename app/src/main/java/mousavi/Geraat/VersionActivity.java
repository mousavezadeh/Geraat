package mousavi.Geraat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class VersionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.version);
		WebView MyWeb=(WebView) findViewById(R.id.webView1);
		MyWeb.getSettings().setBuiltInZoomControls(true);
	

		
	MyWeb.loadUrl("file:///android_asset/VersionHistory/Version.htm");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.version, menu);
		return true;
	}

}
