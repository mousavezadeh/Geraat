package mousavi.database;

public class code_mane {
    String _code_mane;
    String _title_mane;


    public code_mane(){

    }

    public code_mane(String codemane,String title){
        _code_mane=codemane.trim();
        _title_mane=title.trim();
    }
    public String get_code_mane() {
        return this._code_mane;
    }

    public void set_code_mane(String code_mane) {
        this._code_mane = code_mane.trim();
    }

    public String get_title_mane() {
        return this._title_mane;
    }

    public void set_title_mane(String title_mane) {
        this._title_mane = title_mane.trim();
    }
}
