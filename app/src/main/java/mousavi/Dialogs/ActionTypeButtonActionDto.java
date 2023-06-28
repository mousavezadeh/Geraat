package mousavi.Dialogs;

public class ActionTypeButtonActionDto {
    
    public int refControl;
    public IButtonAction buttonAction;

    public ActionTypeButtonActionDto(int actiontype, IButtonAction buttonAction){
        this.refControl=actiontype;
        this.buttonAction=buttonAction;
    }
}
