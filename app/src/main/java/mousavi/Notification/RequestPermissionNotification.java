package mousavi.Notification;


public class RequestPermissionNotification extends BaseNotification {
    public RequestPermissionNotification(){

    }
    @Override
    public String Title() {
        return "درخواست مجوزهای برنامه";
    }

    @Override
    public String Text() {
        return "کاربر گرامی دسترتسی های مورد نیاز برنامه را تائئید کنید";
    }

    @Override
    public Integer id() {
        return 55;
    }
}
