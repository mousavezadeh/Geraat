package mousavi.database;

public class pdl_output {
    String _code_control;
    String _code_mane;
    String _eshterak;
    String _id_list_server;
    String _lat;
    String _long;
    String _meghdare_feli;
    String _roz_kar;
    String _tarikh_feli;
    String _tarikh_ghabli;
    String _timesabte;
    String _OfficerDescription;

    public String get_OfficerDescription() {
        return this._OfficerDescription;
    }

    public void set_OfficerDescription(String OfficerDescription) {
        this._OfficerDescription = OfficerDescription;
    }

    public String get_id_list_server() {
        return this._id_list_server;
    }

    public void set_id_list_server(String id_list_server) {
        this._id_list_server = id_list_server;
    }

    public String get_timesabte() {
        return this._timesabte;
    }

    public void set_timesabte(String timesabte) {
        this._timesabte = timesabte;
    }

    public String get_long() {
        return this._long;
    }

    public void set_long(String longt) {
        this._long = longt;
    }

    public String get_lat() {
        return this._lat;
    }

    public void set_lat(String lat) {
        this._lat = lat;
    }

    public String get_roz_kar() {
        return this._roz_kar;
    }

    public void set_roz_kar(String roz_kar) {
        this._roz_kar = roz_kar.trim();
    }

    public String get_eshterak() {
        return this._eshterak;
    }

    public void set_eshterak(String eshterak) {
        this._eshterak = eshterak.trim().substring(eshterak.length() - 8, eshterak.length());
    }

    public String get_tarikh_feli() {
        return this._tarikh_feli;
    }

    public void set_tarikh_feli(String tarikh_feli) {
        this._tarikh_feli = tarikh_feli.trim();
    }

    public String get_tarikh_ghabli() {
        return this._tarikh_ghabli;
    }

    public void set_tarikh_ghabli(String tarikh_ghabli) {
        this._tarikh_ghabli = tarikh_ghabli.trim();
    }

    public String get_meghdare_feli() {
        return this._meghdare_feli;
    }

    public void set_meghdare_feli(String meghdare_feli) {
        this._meghdare_feli = meghdare_feli.trim();
    }

    public String get_code_mane() {
        return this._code_mane;
    }

    public void set_code_mane(String code_mane) {
        this._code_mane = code_mane.trim();
    }

    public String get_code_control() {
        return this._code_control;
    }

    public void set_code_control(String code_control) {
        this._code_control = code_control.trim();
    }

    private String add_zero(String input_data, int default_lenght) {
        return "**********";
    }
}
