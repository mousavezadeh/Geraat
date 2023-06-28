package mousavi.database;

public class Citys {
    String _CityId;
    String _CityName;
    String _DefaultCityId;
    String _id;

    public String Get_Id() {
        return this._id;
    }

    public void Set_Id(String Id) {
        this._id = Id;
    }

    public String Get_CityId() {
        return this._CityId;
    }

    public void Set_CityId(String CityId) {
        this._CityId = CityId;
    }

    public String Get_CityName() {
        return this._CityName;
    }

    public void Set_CityName(String CityName) {
        this._CityName = CityName;
    }

    public String Get_DefaultCityId() {
        return this._DefaultCityId;
    }

    public void Set_DefaultCityId(String DefaultCityId) {
        this._DefaultCityId = DefaultCityId;
    }
}
