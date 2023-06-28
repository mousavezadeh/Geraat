package mousavi.database.Insert;

import android.database.sqlite.SQLiteDatabase;

import mousavi.Geraat.Furtherinformation.FurtherInformation;
import mousavi.database.BaseSql;

public class InsertFurtherinformation extends BaseSql {
    FurtherInformation furtherInformation;
    public InsertFurtherinformation(SQLiteDatabase sqLiteDatabase, FurtherInformation furtherInformation) {
        super(sqLiteDatabase);
        this.furtherInformation=furtherInformation;
    }

    @Override
    public String sql() {
        return null;
    }

    @Override
    public Object[] objectparameters() {
        return new Object[0];
    }

    @Override
    public String[] stringparameters() {
        return new String[0];
    }

    @Override
    public boolean execute() {
        return false;
    }
}
