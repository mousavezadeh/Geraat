package mousavi.Notification;


public class ErrorDuringOperationNotification extends BaseNotification {
    String errormessage;
    public ErrorDuringOperationNotification(){
        this.errormessage="خطایی در انجام عملیات رخ داده است لطفا شکیبا باشید تشکر";
    }
    public ErrorDuringOperationNotification(String errormessage){
        this.errormessage=errormessage;
    }
    @Override
    public String Title() {
        return "خطا در انجام عملیات";
    }

    @Override
    public String Text() {
        return errormessage;
    }

    @Override
    public Integer id() {
        return 20;
    }
}
