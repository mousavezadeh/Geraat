package mousavi.database.Dto;

import android.database.Cursor;

public class ReadingListItemDto implements IDto {
    public String   name_moshtarek;
    public String   radif_tashkhis;
    public String   eshterak;
    public String   parvandeh;
    public String   address;
    public String  meghdar_ghabli;
    public String   SendToServer;
    public Integer   EstateId;
    public String   karbari;
    public String   tarikh_ghabli;
    public String   bedehi;
    public String   code_mane_feli;
    public String   karkard_feli;
    public String   mane_ghabli;
    public String   doreh;
    public String   rozkar;
    public String   code_mamor;
    public String   code_shahr;
    public String   code_rosta;
    public String   code_mantagheh;
    public String   code_meli;
    public String   shenaseh_ghabz;
    public String   shomare_kontor;
    public Integer   FKId_List;
    public Integer   rowno;
    public String   karbarititle;
    public String   mobile;
    public String   code_posti;
    public String   ghotr;
    public String   tedad_vahed;
    public String   replacementofmeter;
    public String masraf1;
    public String   masraf2;
    public String   tedad_khanevar;
    public String   model_kontor;
    public String   tarikh_nasb;
    public String   tarikh_taviz;
    public String   bazresi_ghabli;
    public String   tedad_argham;
    public String   Replacementofmeter;
    public Integer   EstateConditionId;
    public String   EstateConditionTitle;
    public Integer  BranchKindId;
    public String   BranchStatusId;
    public String   Title;
    public String   KindBranchTitle;
    public String   BranchStatusTitle;
    public String   OfficerDescription;
    public String   ShenasePardakht;
    public Integer   IsUnderConstruction;
    public Integer   IsTemporalHousing;
    public Integer   MeterStatus;

    public ReadingListItemDto() {
        // TODO Auto-generated constructor stub
    }

    public ReadingListItemDto(Cursor cursor)
    {
        try{
            name_moshtarek = ((cursor.getString(cursor.getColumnIndex("name_moshtarek"))));
        }catch(Exception ex){name_moshtarek="";}
        try{
            radif_tashkhis = ((cursor.getString(cursor.getColumnIndex("radif_tashkhis"))));
        }catch (Exception ex){radif_tashkhis="";}
        try{eshterak=((cursor.getString(cursor.getColumnIndex("eshterak"))));
        }catch (Exception ex){eshterak="";}
        try{parvandeh=((cursor.getString(cursor.getColumnIndex("parvandeh"))));
        }catch (Exception ex){parvandeh="";}

        try{
            address=((cursor.getString(cursor.getColumnIndex("address"))));
        }catch (Exception ex){address="";}
        try{meghdar_ghabli=((cursor.getString(cursor.getColumnIndex("meghdar_ghabli")))); }catch (Exception ex){meghdar_ghabli="0";}
        try{SendToServer=((cursor.getString(cursor.getColumnIndex("SendToServer")))); }catch (Exception ex){radif_tashkhis="";}
        try{EstateId=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("EstateId")))); }catch (Exception ex){EstateId=0;}
        try{karbari=((cursor.getString(cursor.getColumnIndex("karbari")))); }catch (Exception ex){radif_tashkhis="";}
        try{tarikh_ghabli=((cursor.getString(cursor.getColumnIndex("tarikh_ghabli")))); }catch (Exception ex){radif_tashkhis="";}
        try{bedehi=((cursor.getString(cursor.getColumnIndex("bedehi")))); }catch (Exception ex){bedehi="0";}
        try{code_mane_feli=((cursor.getString(cursor.getColumnIndex("code_mane_feli")))); }catch (Exception ex){radif_tashkhis="";}
        try{karkard_feli=((cursor.getString(cursor.getColumnIndex("karkard_feli")))); }catch (Exception ex){karkard_feli="";}
        try{mane_ghabli=((cursor.getString(cursor.getColumnIndex("mane_ghabli")))); }catch (Exception ex){mane_ghabli="";}
        try{bazresi_ghabli=((cursor.getString(cursor.getColumnIndex("bazresi_ghabli")))); }catch (Exception ex){bazresi_ghabli="";}

        try{masraf1=((cursor.getString(cursor.getColumnIndex("masraf1")))); }catch (Exception ex){masraf1="";}
        try{masraf2=((cursor.getString(cursor.getColumnIndex("masraf2")))); }catch (Exception ex){masraf2="";}
        try{replacementofmeter=((cursor.getString(cursor.getColumnIndex("replacementofmeter")))); }catch (Exception ex){replacementofmeter="";}


        try{ghotr=((cursor.getString(cursor.getColumnIndex("ghotr")))); }catch (Exception ex){ghotr="";}
        try{tedad_vahed=((cursor.getString(cursor.getColumnIndex("tedad_vahed")))); }catch (Exception ex){tedad_vahed="";}


        try{tedad_khanevar=((cursor.getString(cursor.getColumnIndex("tedad_khanevar")))); }catch (Exception ex){tedad_khanevar="";}
        try{model_kontor=((cursor.getString(cursor.getColumnIndex("model_kontor")))); }catch (Exception ex){model_kontor="";}

        try{tarikh_nasb=((cursor.getString(cursor.getColumnIndex("tarikh_nasb")))); }catch (Exception ex){tarikh_nasb="";}
        try{tarikh_taviz=((cursor.getString(cursor.getColumnIndex("tarikh_taviz")))); }catch (Exception ex){tarikh_taviz="";}

        try{tedad_argham=((cursor.getString(cursor.getColumnIndex("tedad_argham")))); }catch (Exception ex){tedad_argham="";}

        try{doreh=((cursor.getString(cursor.getColumnIndex("doreh")))); }catch (Exception ex){doreh="";}
        try{rozkar=((cursor.getString(cursor.getColumnIndex("rozkar")))); }catch (Exception ex){rozkar="";}
        try{code_mamor=((cursor.getString(cursor.getColumnIndex("code_mamor")))); }catch (Exception ex){code_mamor="";}
        try{code_shahr=((cursor.getString(cursor.getColumnIndex("code_shahr")))); }catch (Exception ex){code_shahr="";}
        try{code_rosta=((cursor.getString(cursor.getColumnIndex("code_rosta")))); }catch (Exception ex){code_rosta="";}
        try{code_mantagheh=((cursor.getString(cursor.getColumnIndex("code_mantagheh")))); }catch (Exception ex){code_mantagheh="";}
        try{code_meli=((cursor.getString(cursor.getColumnIndex("code_meli")))); }catch (Exception ex){code_meli="";}
        try{shenaseh_ghabz=((cursor.getString(cursor.getColumnIndex("shenaseh_ghabz")))); }catch (Exception ex){shenaseh_ghabz="";}
        try{shomare_kontor=((cursor.getString(cursor.getColumnIndex("shomare_kontor")))); }catch (Exception ex){shomare_kontor="";}
        try{FKId_List=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("FKId_List")))); }catch (Exception ex){FKId_List=0;}
        try{rowno=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("rowno")))); }catch (Exception ex){rowno=0;}
        try{karbarititle=((cursor.getString(cursor.getColumnIndex("karbarititle")))); }catch (Exception ex){karbarititle="";}
        try{mobile=((cursor.getString(cursor.getColumnIndex("mobile")))); }catch (Exception ex){radif_tashkhis="";}
        try{code_posti=((cursor.getString(cursor.getColumnIndex("code_posti")))); }catch (Exception ex){radif_tashkhis="";}
        try{Replacementofmeter=((cursor.getString(cursor.getColumnIndex("Replacementofmeter")))); }catch (Exception ex){radif_tashkhis="";}
        try{EstateConditionId=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("EstateConditionId")))); }catch (Exception ex){radif_tashkhis="";}
        try{EstateConditionTitle=((cursor.getString(cursor.getColumnIndex("Title")))); }catch (Exception ex){EstateConditionTitle="عادی";}
        try{BranchKindId=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("BranchKindId")))); }catch (Exception ex){radif_tashkhis="";}
        try{BranchStatusId=((cursor.getString(cursor.getColumnIndex("BranchStatusId")))); }catch (Exception ex){radif_tashkhis="";}
        try{Title=((cursor.getString(cursor.getColumnIndex("Title")))); }catch (Exception ex){radif_tashkhis="";}
        try{KindBranchTitle=((cursor.getString(cursor.getColumnIndex("KindBranchTitle")))); }catch (Exception ex){radif_tashkhis="";}
        try{BranchStatusTitle=((cursor.getString(cursor.getColumnIndex("BranchStatusTitle")))); }catch (Exception ex){radif_tashkhis="";}
        try{OfficerDescription=((cursor.getString(cursor.getColumnIndex("OfficerDescription")))); }catch (Exception ex){radif_tashkhis="";}
        try{ShenasePardakht=((cursor.getString(cursor.getColumnIndex("ShenasePardakht")))); }catch (Exception ex){radif_tashkhis="";}
        try{IsUnderConstruction=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("IsUnderConstruction")))); }catch (Exception ex){IsUnderConstruction=0;}
        try{IsTemporalHousing=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("IsTemporalHousing")))); }catch (Exception ex){IsTemporalHousing=0;}
        try{MeterStatus=(Integer.valueOf(cursor.getString(cursor.getColumnIndex("MeterStatus")))); }catch (Exception ex){MeterStatus=0;}
    }
}
