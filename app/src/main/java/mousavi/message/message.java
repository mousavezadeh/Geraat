package mousavi.message;

public class message {

	public	enum message_status {information,error};
	public	enum message_information {saved_date,load_data,edit_data};
	public	enum message_error {saved_date,load_data,edit_data};



	public String message_prrocess(message_status message_staus,message_information messageinformation)
	{
		String message="";


		switch (messageinformation) {
			case saved_date:
				message="داده ثبت شد";
				break;

			case edit_data:
				message="داده ویرایش شد";
				break;

			case load_data:
				message="داده فراخوانی شد";
				break;


			default:
				break;
		}

		return message;

	}


	public String message_prrocess(message_status message_staus,message_error messageerror)
	{
		String message="";


		switch (messageerror) {
			case saved_date:
				message="خطا در ثبت داده";
				break;

			case edit_data:
				message="خطا در ویرایش داده";
				break;

			case load_data:
				message="خطا در فراخوانی داده";
				break;


			default:
				break;
		}

		return message;

	}





}
