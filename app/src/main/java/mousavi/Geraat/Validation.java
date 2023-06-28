package mousavi.Geraat;

import android.os.Message;

public  class Validation {
    public static String ValidationMessage;
    public static boolean Len(String value,int len)
    {
        if (value.length()==len)
            return true;
        ValidationMessage="طول رشته وارد شده صحیح نمیباشد";
        return false;
    }
    public static boolean LenBetween(String value,int lenstart,int lenend)
    {
        if ((value.length() >=lenstart)&&(value.length() <=lenend))
            return true;
        ValidationMessage="طول رشته وارد شده صحیح نمیباشد";
        return false;
    }
    public static boolean MobileFormat(String value)
    {
        if (value.startsWith("09"))
            return true;
        ValidationMessage="شماره موبایل وارد شده صحیح نمیباشد";
        return false;
    }
    public static boolean Polomp(String value)
    {
        if (value.length()>=4)
            return true;
        ValidationMessage="پلمپ کنتور حداقل باید 4 رقم باشد";
        return false;
    }
    public static boolean Detectionrow(String value)
    {
        if (value.length()>=1)
            return true;
        ValidationMessage="ردیف تشخیص حداقل باید 1 رقم باشد";
        return false;
    }
    public static boolean Postalcode(String value)
    {
        if (value.length()==10)
            return true;
        ValidationMessage="کد پستی باید 10 رقم باشد";
        return false;
    }
    public static boolean Metertrunk(String value)
    {
        if (value.length()>=4)
            return true;
        ValidationMessage="تنه کنتور حداقل باید 4 رقم باشد";
        return false;
    }
    public static boolean Address(String value)
    {
        if ((value.length()>300) || (value.length() < 5))
        {
            ValidationMessage="آدرس مشترک حداقل 5و حداکثر 300 حرف یا رقم می باشد";
            return false;
        }
            return true;
    }

    public static boolean Branchstatus(Integer value)
    {
        if((value>0)&&(value<10))
            return true;


        ValidationMessage="وضعیت انشعاب معتبر نیست";
        return false;


    }
}
