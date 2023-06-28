package mousavi.Geraat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);
		EditText txt_username=(EditText)findViewById(R.id.txt_username);
		EditText txt_password=(EditText)findViewById(R.id.txt_password);
		Button btn_login=(Button)findViewById(R.id.btn_login);
		
		
		if ((txt_username.getText().toString()=="1")&&(txt_password.getText().toString()=="1"))
		{
			Intent intent = new Intent(getApplicationContext(),display_main_menu.class);
			startActivity(intent);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
