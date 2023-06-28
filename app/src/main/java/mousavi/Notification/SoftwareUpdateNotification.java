package mousavi.Notification;


public class SoftwareUpdateNotification extends BaseNotification {
    String show;
    String text;
    Integer id;

/**
 * @param text body of notification
 * @param id notification id for display
 */

    public SoftwareUpdateNotification(String text, Integer id,String show){
        this.text=text;
        this.id=id;
        this.show=show;
    }
    @Override
    public String Title() {
        return "بروز رسانی نرم افزاری";
    }

    @Override
    public String Text() {
        return text;
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public String Show() {
        return show;
    }
}
