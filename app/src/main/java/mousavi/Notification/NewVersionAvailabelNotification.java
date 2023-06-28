package mousavi.Notification;


public class NewVersionAvailabelNotification extends BaseNotification {

    public NewVersionAvailabelNotification( ){

    }
    @Override
    public String Title() {
        return "خطا در نسخه برنامه";
    }

    @Override
    public String Text() {
        return "جهت قرائت نسخه جدید برنامه را دریافت و نصب نمائید";
    }

    @Override
    public Integer id() {
        return 30;
    }
}
