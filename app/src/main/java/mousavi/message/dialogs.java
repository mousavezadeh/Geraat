package mousavi.message;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import mousavi.Geraat.R;

public class dialogs {

	private	String _dialog_title;
	// public	enum hillo_message {masrafzero,kontordorzadeh};
	private 	Context mycontext;
	public Dialog dialog ;
	private int _dialog_layout;
	private int Visibility=0;
	private Boolean show_default_layout=true;
	private String _hillo_title="";
	private String _hillo_detail="";
	public dialogs(Context mycon) {
		// TODO Auto-generated constructor stub
		dialog = new Dialog(mycon);
		set_default_layout();
		dialog.setTitle("پیام به کاربر");
	}

	public dialogs(Context mycon,Boolean show_title)
	{
		// TODO Auto-generated constructor stub
		dialog = new Dialog(mycon);

		set_default_layout();


		dialog.setTitle("پیام به کاربر");

		TextView h_title=(TextView)dialog.findViewById(R.id.txt_hilo_title);
		TextView h_detail=(TextView)dialog.findViewById(R.id.txt_hilo_detail);
		TextView h_hilo=(TextView)dialog.findViewById(R.id.TextView02);

		h_title.setText("");
	}

	public dialogs(Context mycon,int layout_file_name)
	{
		// TODO Auto-generated constructor stub
		dialog = new Dialog(mycon);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this._dialog_layout=layout_file_name;
		set_layout_dialog();

		//dialog.setTitle("پیام به کاربر");


	}



	public void set_TitleVisibility()
	{
		TextView h_title=(TextView)dialog.findViewById(R.id.txt_hilo_title);
		h_title.setVisibility(View.GONE);
	}

	public void set_layout_dialog(int layout_file) {
		dialog.setContentView(this._dialog_layout);
	}

	private void set_layout_dialog() {
		dialog.setContentView(this._dialog_layout);
	}

	public void show_dialog() {

		if (show_default_layout==false)
			set_layout_dialog();

		dialog.show();
	}


	public void hide_dialog() {
		dialog.dismiss();
	}

	public void set_title_dialog(String title) {
		// TODO Auto-generated method stub
		dialog.setTitle(title);

	}



	public void set_title_hillo_message(String hillo_title) {
		TextView jh1=(TextView)dialog.findViewById(R.id.txt_hilo_title);
		jh1.setText(hillo_title);

	}


	public void set_detail_hillo_message(String hillo_detail) {
		TextView jh1=(TextView)dialog.findViewById(R.id.txt_hilo_detail);
		jh1.setText(hillo_detail);
	}

	public void set_hillo_message(String hillo_detail) {
		TextView jh1=(TextView)dialog.findViewById(R.id.TextView02);
		jh1.setText(hillo_detail);
	}

	public void kfjf()
	{
//		 ImageButton btn_save=(ImageButton)findViewById(R.id.btn_save);

	}


	public Boolean choice_layout()
	{
		if (show_default_layout==true)
		{
			set_default_layout();
		}

		return true;
	}

	private void set_default_layout()
	{
		dialog.setContentView(R.layout.hilo_message);

	}

	private void set_custom_layout(int layout_file_name)
	{
		this._dialog_layout=layout_file_name;
		//dialog.setContentView(layout_file_name);
	}



//	dialog.setContentView();
	//dialog.setTitle("لیست کدهای قرایت");
}
