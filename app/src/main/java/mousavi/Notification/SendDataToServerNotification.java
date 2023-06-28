package mousavi.Notification;


public class SendDataToServerNotification extends BaseNotification {
    public SendDataToServerNotification(){

    }
    @Override
    public String Title() {
        return "ارسال اطلاعات قرائت";
    }

    @Override
    public String Text() {
        return "در حال ارسال اطلاعات لیست های قرائت به سرور";
    }

    @Override
    public Integer id() {
        return 10;
    }
}
