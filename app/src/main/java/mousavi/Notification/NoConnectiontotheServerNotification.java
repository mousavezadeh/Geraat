package mousavi.Notification;


public class NoConnectiontotheServerNotification extends BaseNotification {

    public NoConnectiontotheServerNotification( ){

    }
    @Override
    public String Title() {
        return "خطا در انجام عملیات";
    }

    @Override
    public String Text() {
        return "ارتباط با سرور مقدور نمی باشد لطفا تا برطرف شدن مشکل شکیبا باشید";
    }

    @Override
    public Integer id() {
        return 13;
    }
}
