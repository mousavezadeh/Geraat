package mousavi.Notification;


public class EndofSendingInformationNotification extends BaseNotification {

    public EndofSendingInformationNotification( ){

    }
    @Override
    public String Title() {
        return "پایان ارسال اطلاعات";
    }

    @Override
    public String Text() {
        return "ارسال اطلاعات با موفقیت انجام شد";
    }

    @Override
    public Integer id() {
        return 11;
    }
}
