package mousavi.Notification;

public abstract class BaseNotification {
    public abstract String Title();
    public abstract String Text();
    public abstract Integer id();
    public boolean shownotification(){
        return true;
    }
    public String Show(){
        return "Notifications.on";
    }
}
