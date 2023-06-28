package mousavi.Notification;


public class ReadingListNotFoundNotification extends BaseNotification {

    public ReadingListNotFoundNotification( ){

    }
    @Override
    public String Title() {
        return "خطا در لیست قرائت";
    }

    @Override
    public String Text() {
        return "لیست قرائت ایجاد نشده است";
    }

    @Override
    public Integer id() {
        return 30;
    }
}
