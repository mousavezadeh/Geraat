package mousavi.database.Insert;

import android.database.sqlite.SQLiteDatabase;

import mousavi.Request.GetListMobileResponse;
import mousavi.database.BaseSql;
import mousavi.database.Dto.ReadingListItemDto;

public class InsertReadingListItem extends BaseSql {
    GetListMobileResponse readingListItemDto;
    public InsertReadingListItem(SQLiteDatabase sqLiteDatabase, GetListMobileResponse readingListItemDto) {
        super(sqLiteDatabase);
        this.readingListItemDto=readingListItemDto;
    }

    @Override
    public String sql() {
        return "insert into inp (SendToServer,code_mane_feli,doreh , eshterak , parvandeh , shenaseh_ghabz ,"
                + " radif_tashkhis , rozkar , code_mamor , code_shahr ,"
                + " code_rosta , code_mantagheh , name_moshtarek , address ,"
                + " code_posti , code_meli , karbari , ghotr , tedad_vahed ,"
                + " tedad_khanevar , model_kontor , tarikh_nasb , tarikh_taviz ,"
                + " shomare_kontor , tedad_argham , mane_ghabli , bazresi_ghabli ,"
                + " masraf1 , masraf2 , bedehi , meghdar_ghabli , tarikh_ghabli , FKId_List,rowno,karbarititle," +
                "mobile,replacementofmeter,EstateConditionId,BranchStatusId,BranchKindId,EstateId,ShenaseGhabz," +
                "ShenasePardakht,IsUnderConstruction,IsTemporalHousing,MeterStatus)"
                + " values (0,0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
                ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public Object[] objectparameters() {
        return new Object[]{0,
                0,
                readingListItemDto.doreh,
                readingListItemDto.eshterak,
                readingListItemDto.parvandeh,
                readingListItemDto.shenaseh_ghabz,
                readingListItemDto.radif_tashkhis,
                readingListItemDto.rozkar,
                readingListItemDto.code_mamor,
                readingListItemDto.code_shahr,
                readingListItemDto.code_rosta,
                readingListItemDto.code_mantagheh,
                readingListItemDto.name_moshtarek,
                readingListItemDto.address,
                readingListItemDto.code_posti,
                readingListItemDto.code_meli,
                readingListItemDto.karbari,
                readingListItemDto.ghotr,
                readingListItemDto.tedad_vahed,
                readingListItemDto.tedad_khanevar,
                readingListItemDto.model_kontor,
                readingListItemDto.tarikh_nasb,
                readingListItemDto.tarikh_taviz,
                readingListItemDto.shomare_kontor,
                readingListItemDto.tedad_argham,
                readingListItemDto.mane_ghabli,
                readingListItemDto.bazresi_ghabli,
                readingListItemDto.masraf1,
                readingListItemDto.masraf2,
                readingListItemDto.bedehi,
                readingListItemDto.meghdar_ghabli,
                readingListItemDto.tarikh_ghabli,
                readingListItemDto.FKId_List,
                readingListItemDto.rowno,
                readingListItemDto.karbarititle,
                readingListItemDto.mobile,
                readingListItemDto.replacementofmeter,
                readingListItemDto.EstateConditionId,
                readingListItemDto.BranchStatusId,
                readingListItemDto.BranchKindId,
                readingListItemDto.EstateId,

                readingListItemDto.ShenasePardakht,
                readingListItemDto.IsUnderConstruction,
                readingListItemDto.IsTemporalHousing,
                readingListItemDto.MeterStatus

        };
    }

    @Override
    public String[] stringparameters() {
        return new String[]{};
    }

    @Override
    public boolean execute() {
        try{
            sqLiteDatabase.execSQL(sql(), objectparameters());
        }catch (Exception error){
            return false;
        }
        return true;
    }
}
