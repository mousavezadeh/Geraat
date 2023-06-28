package mousavi.Notification;


public class ErrorDateTimeNotification extends BaseNotification {
    public ErrorDateTimeNotification(){

    }
    @Override
    public String Title() {
        return "خطا در انجام عملیات";
    }

    @Override
    public String Text() {
        return "تاریخ و ساعت گوشی را تنظیم نمایید";
    }

    @Override
    public Integer id() {
        return 25;
    }
}
