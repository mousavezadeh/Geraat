package mousavi.Notification;


public class DeviceNotRegisterNotification extends BaseNotification {

    public DeviceNotRegisterNotification( ){

    }
    @Override
    public String Title() {
        return "خطا در شناسایی دستگاه";
    }

    @Override
    public String Text() {
        return "شناسه دستگاه در نرم افزار مشترکین تعریف نشده است";
    }

    @Override
    public Integer id() {
        return 30;
    }
}
