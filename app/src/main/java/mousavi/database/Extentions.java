package mousavi.database;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import mousavi.database.Dto.IDto;
import mousavi.database.Select.TitleValue.TitleValueDto;

public class Extentions {
    public static class Extention{
        public  static  <T extends IDto> String  ToJson(ArrayList<T> listofdto){
           return new Gson().toJson(listofdto);
        }
        public static String ToStringList(ArrayList<TitleValueDto> arraylistidvalue){
            String res="";
            for (TitleValueDto item : arraylistidvalue)
                res+=item.value+",";

           return res;
        }

    }
}
