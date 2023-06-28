package mousavi.database;

import android.database.Cursor;

import org.json.JSONArray;

import java.util.ArrayList;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;


import mousavi.Geraat.GeraatActivity;

public class pdl_input {

    enum EnumUnderConstruction{None,EndofConstruction,IsUnderConstruction}
    enum EnumTemporalHousing{None,EndofTemporalHousing,IsTemporalHousing}
	String _SendToServer;
	//---------------------------------------
	String _doreh;
	String _eshterak;
	String _parvandeh;
	String _Polomp_Kontor;
	//-------------------------------
	String _radif_tashkhis;
	String _roz_kar;
	String _code_mamor;
	String _code_shahr;
	//------------------------------
	String _code_rosta;
	String _code_mantagheh;
	String _name_moshtarek;
	String _address_moshtarek;
	//--------------------------------
	String _code_posti;
	String _code_meli;
	String _karbari;
	String _ghotre_ensheab;
	//--------------------------------
	String _tedad_vahed;
	String _tedad_khanevar;
	String _model_kontor;
	String _tarikh_nasb;
	//----------------------------------
	String _tarikh_taviz;
	String _shomare_kontor;
	String _tedad_argham;
	String _mane_ghabli;
	//-----------------------------------
	String _bazresi_ghabli;
	String _masraf1;
	String _masraf2;
	String _bedehi;
	String _meghdare_ghabli;
	String _tarikh_ghabli;
	//--------------------------------------
	String _code_mane_feli;
	String _tarikh_feli;
	String _karkard_feli;
	String _FKId_List;
	int _RecNo;
	String _karbarititle;
	String _Mobile;
	String _TavizKontor;
	int _EstateConditionid;
    String _EstateConditionTitle;
    int _Vaziat;
    String _VaziatTitle;
    int _KindBranchId;
    String _KindBranchTitle;
    String _OfficerDescription;
    String _ShenaseGhabz;
    String _ShenasePardakht;
    int _EstateId;
    int _Id;
    int _IsUnderConstruction;
    int _IsTemporalHousing;

    String _IsTemporalHousingTitle;
    String _IsUnderConstructionTitle;

    int _MeterStatus;
    public ArrayList<pdl_input> List_Data;
    public Boolean MeterChange=false;
    public Boolean OfficerDescription=false;
    public ArrayList<code_mane> ListAcceptedReadCode;

    public int SearchIndex=-1;

public pdl_input() {
	// TODO Auto-generated constructor stub
}

 private   char Decodechar(char ch)
    {
        switch (ch)
        {
            case 'Y':
                return '0';
            case 'V':
                return '1';
            case 'K':
                return '2';
            case 'Q':
                return '3';
            case 'P':
                return '4';
            case 'M':
                return '5';
            case 'C':
                return '6';
            case 'B':
                return '7';
            case 'Z':
                return '8';
            case 'T':
                return '9';
        }

        return ch;
    }

//region Constructors
public  pdl_input(Cursor cursor)
{
    if (cursor.moveToFirst())
    {
        List_Data=new ArrayList<>();
        do {
            pdl_input temp = new pdl_input();

            try
            {temp.set_MeterStatus((Integer.valueOf(cursor.getString(cursor.getColumnIndex("MeterStatus"))))); }catch(Exception ex){temp.set_MeterStatus(0);}
            try
            {temp.set_IsTemporalHousing((Integer.valueOf(cursor.getString(cursor.getColumnIndex("IsTemporalHousing"))))); }catch(Exception ex){temp.set_IsTemporalHousing(0);}
            try
            {temp.set_IsUnderConstruction((Integer.valueOf(cursor.getString(cursor.getColumnIndex("IsUnderConstruction"))))); }catch(Exception ex){temp.set_IsUnderConstruction(0);}
            try
                {temp.set_ShenaseGhabz(cursor.getString(cursor.getColumnIndex("ShenaseGhabz"))); }catch(Exception ex){temp.set_ShenaseGhabz("");}

            try
            {temp.set_ShenasePardakht(cursor.getString(cursor.getColumnIndex("ShenasePardakht"))); }catch(Exception ex){temp.set_ShenasePardakht("");}


            try
            {
                temp.set_OfficerDescription(cursor.getString(cursor.getColumnIndex("OfficerDescription")));
                if (temp._OfficerDescription.length()>=5) temp.OfficerDescription=true;
            }catch(Exception ex){temp.set_OfficerDescription("");};
            try
            { temp.set_EstateId((Integer.valueOf(cursor.getString(cursor.getColumnIndex("EstateId"))))); }
            catch (Exception name) { temp.set_EstateId(0); }

            try
            {
                temp.set_name_moshtarek(cursor.getString(cursor.getColumnIndex("name_moshtarek")));
            }catch(Exception ex){temp.set_name_moshtarek("");};

            try{
                temp.set_radif_tashkhis(cursor.getString(cursor.getColumnIndex("radif_tashkhis")));
            }catch(Exception ex){temp.set_radif_tashkhis("0");};
            try
            {

                temp.set_eshterak(cursor.getString(cursor.getColumnIndex("eshterak")));
            }catch(Exception ex){};
            try
            {
                temp.set_address_moshtarek(cursor.getString(cursor.getColumnIndex("address")));
            }catch(Exception ex){};
            try
            {
                temp.set_meghdare_ghabli(cursor.getString(cursor.getColumnIndex("meghdar_ghabli")));
            }catch(Exception ex){};
            try{
                temp.set_tarikh_ghabli(cursor.getString(cursor.getColumnIndex("tarikh_ghabli")));
            }catch(Exception ex){};
            try{
                temp.set_bedehi(cursor.getString(cursor.getColumnIndex("bedehi")));
            }catch(Exception ex){};
            try{
                temp.set_code_mane_feli(cursor.getString(cursor.getColumnIndex("code_mane_feli")));
            }catch(Exception ex){};
            try{
                if (cursor.getString(cursor.getColumnIndex("karkard_feli")) != null)
                    temp.set_karkard_feli(cursor.getString(cursor.getColumnIndex("karkard_feli")));
            }catch(Exception ex){};

            try{temp.set_mane_ghabli(cursor.getString(cursor.getColumnIndex("mane_ghabli")));
            }catch(Exception ex){};
            try{temp.set_doreh(cursor.getString(cursor.getColumnIndex("doreh")));
            }catch(Exception ex){};
            try{
                temp.set_roz_kar(cursor.getString(cursor.getColumnIndex("roz_kar")));
            }catch(Exception ex){};
            try{
                temp.set_code_shahr(cursor.getString(cursor.getColumnIndex("code_shahr")));
            }catch(Exception ex){};
            try{
                temp.set_Polomp_Kontor(cursor.getString(cursor.getColumnIndex("shenaseh_ghabz")));
            }catch(Exception ex){};
            try
            {
                temp.set_shomare_kontor(cursor.getString(cursor.getColumnIndex("shomare_kontor")));
            }catch(Exception ex){};
            try
            {
                temp.set_FKId_List(cursor.getString(cursor.getColumnIndex("FKId_List")));
            }
            catch(Exception ex){};
            try
            {
                temp.set_RecNo(Integer.valueOf(cursor.getString(cursor.getColumnIndex("rowno"))));
            }
            catch(Exception ex){};
            try
            {
                temp.set_karbarititle((cursor.getString(cursor.getColumnIndex("karbarititle"))));
            }
            catch(Exception ex){};

            try
            {
                temp.set_karbari((cursor.getString(cursor.getColumnIndex("karbari"))));
            }
            catch(Exception ex){};
            try
            {
                temp.set_Mobile(((cursor.getString(cursor.getColumnIndex("mobile")))));
            } catch (Exception name) {
            }
            try
            {
                temp.set_code_posti(((cursor.getString(cursor.getColumnIndex("code_posti")))));
            } catch (Exception name) {
            }

            try
            {
                temp.set_TavizKontor(((cursor.getString(cursor.getColumnIndex("replacementofmeter")))));
                if (temp._TavizKontor.compareTo("1")==0) temp.MeterChange=true;
            } catch (Exception name) {
            }
            try
            {
                temp.set_EstateConditionid((Integer.valueOf(cursor.getString(cursor.getColumnIndex("EstateConditionId")))));
            } catch (Exception name) {
                temp.set_EstateConditionid(1);
            }
            try
            {
                temp.set_EstateConditionTitle(((cursor.getString(cursor.getColumnIndex("Title")))));
            } catch (Exception name) {
                temp.set_EstateConditionTitle("عادی");
            }
            try
            {
                temp.set_Vaziat((Integer.valueOf(cursor.getString(cursor.getColumnIndex("BranchStatusId")))));
            } catch (Exception name) {
                temp.set_Vaziat(1);
            }
            try
            {
                temp.set_VaziatTitle(((cursor.getString(cursor.getColumnIndex("BranchStatusTitle")))));
            } catch (Exception name) {
                temp.set_EstateConditionTitle("فعال");
            }
            try
            {
                temp.set_KindBranchId((Integer.valueOf(cursor.getString(cursor.getColumnIndex("BranchKindId")))));
            } catch (Exception name) {
                temp.set_Vaziat(1);
            }
            try
            {
                temp.set_KindBranchTitle(((cursor.getString(cursor.getColumnIndex("KindBranchTitle")))));
            } catch (Exception name) {
                temp.set_EstateConditionTitle("آب و فاضلاب");
            }
            try {
                temp.set_SendToServer(cursor.getString(cursor.getColumnIndex("SendToServer")));
            } catch (Exception name) {
            }
            // ------------------------------------------------------
            List_Data.add(temp);


        } while (cursor.moveToNext());
    }
}

public   pdl_input(String DataList) {
    try {
        List_Data=new ArrayList<>();
        JSONArray jsonarray = new JSONArray(DataList);
        for (int i = 0; i < jsonarray.length(); i++) {
            try {
                JSONObject temp_jsonobject = jsonarray.getJSONObject(i);
                pdl_input class_pdl = new pdl_input();

                try {
                    class_pdl.set_doreh(temp_jsonobject.optString("doreh"));
                } catch (Exception doreh) {
                    class_pdl.set_doreh("");
                }
                try {
                    class_pdl.set_eshterak(temp_jsonobject.optString("eshterak"));
                } catch (Exception eshterak) {
                    class_pdl.set_eshterak("0");
                }
                try {
                    class_pdl.set_parvandeh(temp_jsonobject.optString("parvandeh"));
                } catch (Exception parvandeh) {
                    class_pdl.set_parvandeh("0");
                }
                try {
                    class_pdl.set_Polomp_Kontor(temp_jsonobject.optString("Polomp_Kontor"));
                } catch (Exception PolompKontor) {
                    class_pdl.set_Polomp_Kontor("");
                }
                try {
                    class_pdl.set_radif_tashkhis(temp_jsonobject.optString("radif_tashkhis"));
                } catch (Exception tashkhis) {
                    class_pdl.set_radif_tashkhis("");
                }
                try {
                    class_pdl.set_roz_kar(temp_jsonobject.optString("roz_kar"));
                } catch (Exception rozkar) {
                    class_pdl.set_roz_kar("0");
                }
                try {
                    class_pdl.set_code_mamor(temp_jsonobject.optString("code_mamor"));
                } catch (Exception codemamor) {
                    class_pdl.set_code_mamor("");
                }
                try {
                    class_pdl.set_code_shahr(temp_jsonobject.optString("code_shahr"));
                } catch (Exception codeshahr) {
                    class_pdl.set_code_shahr("");
                }
                try {
                    class_pdl.set_code_rosta(temp_jsonobject.optString("code_rosta"));
                } catch (Exception coderosta) {
                    class_pdl.set_code_rosta("");
                }
                try {
                    class_pdl.set_code_mantagheh(temp_jsonobject.optString("code_mantagheh"));
                } catch (Exception codemantaghe) {
                    class_pdl.set_code_mantagheh("");
                }
                try {
                    class_pdl.set_name_moshtarek(((temp_jsonobject.optString("name_moshtarek"))));
                } catch (Exception namemoshtarek) {
                    class_pdl.set_name_moshtarek("");
                }
                try {
                    class_pdl.set_address_moshtarek(((temp_jsonobject.optString("address_moshtarek"))));
                } catch (Exception adresmoshtarek) {
                    class_pdl.set_address_moshtarek("");
                }
                try {
                    class_pdl.set_code_posti(temp_jsonobject.optString("code_posti"));
                } catch (Exception codeposti) {
                    class_pdl.set_code_posti("");
                }
                try {
                    class_pdl.set_code_meli(temp_jsonobject.optString("code_meli"));
                } catch (Exception codemeli) {
                    class_pdl.set_code_meli("");
                }
                try {
                    class_pdl.set_karbari(temp_jsonobject.optString("karbari"));
                } catch (Exception karbari) {
                    class_pdl.set_karbari("");
                }
                try {
                    class_pdl.set_ghotre_ensheab(temp_jsonobject.optString("ghotre_ensheab"));
                } catch (Exception ghotreensheab) {
                    class_pdl.set_ghotre_ensheab("");
                }
                try {
                    class_pdl.set_tedad_vahed(temp_jsonobject.optString("tedad_vahed"));
                } catch (Exception tedadvahed) {
                    class_pdl.set_tedad_vahed("");
                }
                try {
                    class_pdl.set_tedad_khanevar(temp_jsonobject.optString("tedad_khanevar"));
                } catch (Exception tedadkhanevar) {
                    class_pdl.set_tedad_khanevar("");
                }
                try {
                    class_pdl.set_model_kontor(temp_jsonobject.optString("model_kontor"));
                } catch (Exception modelkontor) {
                    class_pdl.set_model_kontor("");
                }
                try {
                    class_pdl.set_tarikh_nasb(temp_jsonobject.optString("tarikh_nasb"));
                } catch (Exception tarikhnasb) {
                    class_pdl.set_tarikh_nasb("");
                }
                try {
                    class_pdl.set_tarikh_taviz(temp_jsonobject.optString("tarikh_taviz"));
                } catch (Exception tarikhtaviz) {
                    class_pdl.set_tarikh_taviz("");
                }
                try {
                    class_pdl.set_shomare_kontor(temp_jsonobject.optString("shomare_kontor"));
                } catch (Exception shomarekontor) {
                    class_pdl.set_shomare_kontor("");
                }
                try {
                    class_pdl.set_tedad_argham(temp_jsonobject.optString("tedad_argham"));
                } catch (Exception tedadargham) {
                    class_pdl.set_tedad_argham("");
                }
                try {
                    class_pdl.set_mane_ghabli(temp_jsonobject.optString("mane_ghabli"));
                } catch (Exception maneghabli) {
                    class_pdl.set_mane_ghabli("");
                }
                try {
                    class_pdl.set_bazresi_ghabli(temp_jsonobject.optString("bazresi_ghabli"));
                } catch (Exception bazresighabli) {
                    class_pdl.set_bazresi_ghabli("");
                }
                try {
                    class_pdl.set_masraf1(temp_jsonobject.optString("masraf1"));
                } catch (Exception masraf1) {
                    class_pdl.set_masraf1("");
                }
                try {
                    class_pdl.set_masraf2(temp_jsonobject.optString("masraf2"));
                } catch (Exception masraf2) {
                    class_pdl.set_masraf2("");
                }
                try {
                    class_pdl.set_bedehi(temp_jsonobject.optString("bedehi"));
                } catch (Exception bedehi) {
                    class_pdl.set_bedehi("0");
                }
                try {
                    class_pdl.set_meghdare_ghabli(temp_jsonobject.optString("meghdare_ghabli"));
                } catch (Exception meghdarghabli) {
                    class_pdl.set_meghdare_ghabli("");
                }
                try {
                    class_pdl.set_tarikh_ghabli(temp_jsonobject.optString("tarikh_ghabli"));
                } catch (Exception tarikhghabli) {
                    class_pdl.set_tarikh_ghabli("");
                }
                try {
                    class_pdl.set_karbarititle(temp_jsonobject.optString("karbarititle").trim());
                } catch (Exception karbarititle) {
                    class_pdl.set_karbarititle("");
                }
                try {
                    class_pdl.set_Mobile(temp_jsonobject.optString("Mobile"));
                } catch (Exception mobile) {
                    class_pdl.set_Mobile("");
                }
                try {
                    class_pdl.set_TavizKontor(temp_jsonobject.optString("TavizKontor"));
                } catch (Exception mobile) {
                    class_pdl.set_TavizKontor("0");
                }

                try {
                    class_pdl.set_RecNo(Integer.valueOf(temp_jsonobject.optString("RecNo").trim()));
                } catch (Exception tarikhghabli) {
                    class_pdl.set_RecNo(0);
                }
                try {
                    class_pdl.set_EstateConditionid(Integer.valueOf(temp_jsonobject.optString("EstateConditionid").trim()));
                } catch (Exception tarikhghabli) {
                    class_pdl.set_EstateConditionid(1);
                }
                try {
                    class_pdl.set_Vaziat(Integer.valueOf(temp_jsonobject.optString("Vaziat")));
                } catch (Exception tarikhghabli) {
                    class_pdl.set_Vaziat(1);
                }
                try {
                    class_pdl.set_KindBranchId(Integer.valueOf(temp_jsonobject.optString("KindBranchId")));
                } catch (Exception tarikhghabli) {
                    class_pdl.set_KindBranchId(1);
                }
                try {
                    class_pdl.set_EstateId(Integer.valueOf(temp_jsonobject.optString("EstateId")));
                    Log.e("Estateid", String.valueOf(class_pdl.get_EstateId()));
                } catch (Exception tarikhghabli) {
                    class_pdl.set_EstateId(0);
                }
                try {
                    class_pdl.set_ShenaseGhabz(temp_jsonobject.optString("ShenaseGhabz"));
                } catch (Exception mobile) {
                    class_pdl.set_ShenaseGhabz("");
                }
                try {
                    class_pdl.set_ShenasePardakht(temp_jsonobject.optString("ShenasePardakht"));
                } catch (Exception mobile) {
                    class_pdl.set_ShenasePardakht("");
                }
                try {
                    class_pdl.set_IsUnderConstruction(Integer.valueOf(temp_jsonobject.optString("IsUnderConstruction")));
                } catch (Exception mobile) {
                    class_pdl.set_IsUnderConstruction(0);
                }
                try
                {
                    class_pdl.set_IsTemporalHousing(Integer.valueOf(temp_jsonobject.optString("IsTemporalHousing")));
                } catch (Exception mobile)
                {
                    class_pdl.set_IsTemporalHousing(0);
                }
                try
                {
                    class_pdl.set_MeterStatus(Integer.valueOf(temp_jsonobject.optString("MeterStatus")));
                } catch (Exception mobile)
                {
                    class_pdl.set_MeterStatus(0);
                }
                List_Data.add(class_pdl);
            } catch (Exception errorr) {
                Log.e("pdlerror",errorr.toString());
            }

        }
    }catch (Exception error){}
}


public ArrayList<pdl_input> MakeList(Cursor cursor)
{
    ArrayList<pdl_input> your_array_list = new ArrayList<pdl_input>();
        if (cursor.moveToFirst())
        {

            do {
                pdl_input pdl=new pdl_input();

                try
                {pdl.set_MeterStatus((Integer.valueOf(cursor.getString(cursor.getColumnIndex("MeterStatus"))))); }catch(Exception ex){pdl.set_MeterStatus(0);};
                try
                {pdl.set_IsTemporalHousing((Integer.valueOf(cursor.getString(cursor.getColumnIndex("IsTemporalHousing"))))); }catch(Exception ex){pdl.set_IsTemporalHousing(0);};
                try
                {
                    pdl.set_IsUnderConstruction((Integer.valueOf(cursor.getString(cursor.getColumnIndex("IsUnderConstruction")))));
                } catch (Exception name) {
                    pdl.set_IsUnderConstruction(0);
                }
                try
                {
                    pdl.set_ShenaseGhabz(cursor.getString(cursor.getColumnIndex("ShenaseGhabz")));
                }catch(Exception ex){pdl.set_ShenaseGhabz("");};
                try
                {
                    pdl.set_ShenasePardakht(cursor.getString(cursor.getColumnIndex("ShenasePardakht")));
                }catch(Exception ex){pdl.set_ShenasePardakht("");};

                try
                {
                    pdl.set_name_moshtarek(cursor.getString(cursor.getColumnIndex("name_moshtarek")));
                }catch(Exception ex){};
                try{
                    pdl.set_radif_tashkhis(cursor.getString(cursor.getColumnIndex("radif_tashkhis")));
                }catch(Exception ex){set_radif_tashkhis("0");};
                try
                {

                    pdl.set_eshterak(cursor.getString(cursor.getColumnIndex("eshterak")));
                }catch(Exception ex){};
                try
                {
                    pdl.set_address_moshtarek(cursor.getString(cursor.getColumnIndex("address")));
                }catch(Exception ex){};
                try
                {
                    pdl.set_meghdare_ghabli(cursor.getString(cursor.getColumnIndex("meghdar_ghabli")));
                }catch(Exception ex){};
                try{
                    pdl.set_tarikh_ghabli(cursor.getString(cursor.getColumnIndex("tarikh_ghabli")));
                }catch(Exception ex){};
                try{
                    pdl.set_bedehi(cursor.getString(cursor.getColumnIndex("bedehi")));
                }catch(Exception ex){};
                try{
                    pdl.set_code_mane_feli(cursor.getString(cursor.getColumnIndex("code_mane_feli")));
                }catch(Exception ex){};
                try{
                    if (cursor.getString(cursor.getColumnIndex("karkard_feli")) != null)
                        pdl.set_karkard_feli(cursor.getString(cursor.getColumnIndex("karkard_feli")));
                }catch(Exception ex){};

                try{pdl.set_mane_ghabli(cursor.getString(cursor.getColumnIndex("mane_ghabli")));
                }catch(Exception ex){};
                try{pdl.set_doreh(cursor.getString(cursor.getColumnIndex("doreh")));
                }catch(Exception ex){};
                try{
                    pdl.set_roz_kar(cursor.getString(cursor.getColumnIndex("rozkar")));
                }catch(Exception ex){};
                try{
                    pdl.set_code_shahr(cursor.getString(cursor.getColumnIndex("code_shahr")));
                }catch(Exception ex){};
                try{
                    pdl.set_Polomp_Kontor(cursor.getString(cursor.getColumnIndex("Polomp_Kontor")));
                }catch(Exception ex){};
                try
                {
                    pdl.set_shomare_kontor(cursor.getString(cursor.getColumnIndex("shomare_kontor")));
                }catch(Exception ex){}
                try
                {
                    pdl.set_FKId_List(cursor.getString(cursor.getColumnIndex("FKId_List")));
                }
                catch(Exception ex){}
                try
                {
                    pdl.set_RecNo(Integer.valueOf(cursor.getString(cursor.getColumnIndex("rowno"))));
                }
                catch(Exception ex){}

                try
                {
                    pdl.set_karbari((cursor.getString(cursor.getColumnIndex("karbari"))));
                }
                catch(Exception ex){}
                try
                {
                    pdl.set_karbarititle((cursor.getString(cursor.getColumnIndex("karbarititle"))));
                }
                catch(Exception ex){}


                try
                {
                    pdl.set_Mobile(((cursor.getString(cursor.getColumnIndex("mobile")))));
                } catch (Exception name) {
                }
                try
                {
                    pdl.set_code_posti(((cursor.getString(cursor.getColumnIndex("code_posti")))));
                } catch (Exception name) {
                }

                try
                {
                    pdl.set_TavizKontor(((cursor.getString(cursor.getColumnIndex("Replacementofmeter")))));
                    if (pdl._TavizKontor.compareTo("1")==0) pdl.MeterChange=true;
                } catch (Exception name) {
                }
                try
                {
                    pdl.set_EstateConditionid((Integer.valueOf(cursor.getString(cursor.getColumnIndex("EstateConditionId")))));
                } catch (Exception name) {
                    pdl.set_EstateConditionid(1);
                }
                try
                {
                    pdl.set_EstateConditionTitle(((cursor.getString(cursor.getColumnIndex("Title")))));
                } catch (Exception name) {
                    pdl.set_EstateConditionTitle("عادی");
                }
//-----------------------------------
                try
                {
                    pdl.set_Vaziat((Integer.valueOf(cursor.getString(cursor.getColumnIndex("BranchStatusId")))));
                } catch (Exception name) {
                    pdl.set_Vaziat(1);
                }
                try
                {
                    pdl.set_VaziatTitle(((cursor.getString(cursor.getColumnIndex("BranchStatusTitle")))));
                } catch (Exception name) {
                    pdl.set_EstateConditionTitle("فعال");
                }
//-----------------------------------
                try
                {
                    pdl.set_KindBranchId((Integer.valueOf(cursor.getString(cursor.getColumnIndex("BranchKindId")))));
                } catch (Exception name) {
                    pdl.set_Vaziat(1);
                }
                try
                {
                    pdl.set_KindBranchTitle(((cursor.getString(cursor.getColumnIndex("KindBranchTitle")))));
                } catch (Exception name) {
                    pdl.set_EstateConditionTitle("آب و فاضلاب");
                }
// ---------------------------------------------------------------------------------

                try {
                    pdl.set_SendToServer(cursor.getString(cursor.getColumnIndex("SendToServer")));
                } catch (Exception name) {
                }
                // ------------------------------------------------------
                try
                {
                    pdl.set_EstateId((Integer.valueOf(cursor.getString(cursor.getColumnIndex("EstateId")))));
                } catch (Exception name) {
                    pdl.set_EstateId(0);
                }

                try {
                    pdl.set_OfficerDescription(cursor.getString(cursor.getColumnIndex("OfficerDescription")));
                    if (pdl._OfficerDescription.length()>=5) pdl.OfficerDescription=true;
                } catch (Exception name) {pdl.set_OfficerDescription("");
                }

//                try
//                {
//                    pdl.set_Id((Integer.valueOf(cursor.getString(cursor.getColumnIndex("id")))));
//                } catch (Exception name) {
//                    pdl.set_Id(0);
//                }
              your_array_list.add(pdl);
            } while (cursor.moveToNext());
        }
        return your_array_list;
    }

   //endregion
//region Set/Get

    private void   AcceptedReadCode(){
        ListAcceptedReadCode= databasetest.AcceptedReadCodeList(_MeterStatus);
    }

    public int get_MeterStatus() {return  _MeterStatus;}
    public  void set_MeterStatus(int meterStatus) {
        this._MeterStatus=meterStatus;
        try {
            AcceptedReadCode();
        }catch (Exception error){ ListAcceptedReadCode=new ArrayList<code_mane>();
                                  ListAcceptedReadCode.add(new code_mane("0","قرائت نشده"));
        }

    }

    public String get_IsTemporalHousingTitle() {return  _IsTemporalHousingTitle;}
    public  void set_IsTemporalHousingTitle(String IsTemporalHousingTitle) {this._IsTemporalHousingTitle=IsTemporalHousingTitle;}

    public int get_IsTemporalHousing() {return  _IsTemporalHousing;}
    public  void set_IsTemporalHousing(int IsTemporalHousing) {
        this._IsTemporalHousing=IsTemporalHousing;
        if (_IsTemporalHousing==EnumTemporalHousing.IsTemporalHousing.ordinal())
            set_IsTemporalHousingTitle("سکونتگاه غیردائم");
        else
            set_IsTemporalHousingTitle("");
    }


    public String get_IsUnderConstructionTitle() {return  _IsUnderConstructionTitle;}
    public  void set_IsUnderConstructionTitle(String IsUnderConstructionTitle) {this._IsUnderConstructionTitle=IsUnderConstructionTitle;}

    public int get_IsUnderConstruction() {return  _IsUnderConstruction;}
    public  void set_IsUnderConstruction(int IsUnderConstruction) {
        this._IsUnderConstruction=IsUnderConstruction;
        if (IsUnderConstruction==EnumUnderConstruction.IsUnderConstruction.ordinal()) set_IsUnderConstructionTitle("ساخت و ساز") ;else set_IsUnderConstructionTitle("");
}

    public int get_Id() {return  _Id;}
    public  void set_Id(int Id) {this._Id=Id;}

    public String get_ShenaseGhabz() {return  _ShenaseGhabz;}
    public void set_ShenaseGhabz(String ShenaseGhabz) {this._ShenaseGhabz=ShenaseGhabz;}

    public String get_ShenasePardakht() {return  _ShenasePardakht;}
    public void set_ShenasePardakht(String ShenasePardakht) {this._ShenasePardakht=ShenasePardakht; }


    public String get_OfficerDescription() {return  _OfficerDescription;}
    public void set_OfficerDescription(String OfficerDescription) {this._OfficerDescription=OfficerDescription;
    if (OfficerDescription.length()>=5) this.OfficerDescription=true;}



    public int get_EstateId() {return  _EstateId;}
    public  void set_EstateId(int EstateId) {this._EstateId=EstateId;}

    public String get_KindBranchTitle() {return  _KindBranchTitle;}
    public void set_KindBranchTitle(String KindBranchTitle) {this._KindBranchTitle=KindBranchTitle;}
    public int get_KindBranchId() {return  _KindBranchId;}
    public  void set_KindBranchId(int KindBranchId) {this._KindBranchId=KindBranchId;}

    public String get_VaziatTitle() {return  _VaziatTitle;}
    public void set_VaziatTitle(String VaziatTitle) {this._VaziatTitle=VaziatTitle;}

    public int get_Vaziat() {return  _Vaziat;}
    public  void set_Vaziat(int Vaziat)
    {this._Vaziat=Vaziat;}

    public String get_EstateConditionTitle()
    {return _EstateConditionTitle;}

    public void set_EstateConditionTitle(String EstateConditionTitle)
    {
        this._EstateConditionTitle=EstateConditionTitle;

    }
//-------------------------------------------------

	public int get_EstateConditionid()
	{return _EstateConditionid;}

	public void set_EstateConditionid(int EstateConditionid)
	{
		this._EstateConditionid=EstateConditionid;
	}

//---------------------------------------
public String get_TavizKontor()
{return _TavizKontor;}

public void set_TavizKontor(String TavizKontor)
{
	this._TavizKontor=TavizKontor;
}
//--------------------------------
public String get_Mobile()
{return _Mobile;}

public void set_Mobile(String Mobile)
{
	this._Mobile=Mobile.trim();
}
//-------------------------------------
public String get_karbarititle()
{return _karbarititle;}

public void set_karbarititle(String karbarititle)
{
	this._karbarititle=karbarititle.trim();
}
//-----------------------------------
public int get_RecNo()
{return _RecNo;}

public void set_RecNo(int RecNo)
{
	this._RecNo=RecNo;
}

//----------------------------------------------------------��� �� ����
public String get_SendToServer()
{return _SendToServer;}

public void set_SendToServer(String SendToServer)
{
	this._SendToServer=SendToServer.trim();
}

//----------------------------------------------------------�� ���� ����
public String get_code_mane_feli()
{return _code_mane_feli;}

public void set_code_mane_feli(String code_mane_feli)
{
	this._code_mane_feli=code_mane_feli.trim();
}
//----------------------------------------------------------����� ����

public String get_tarikh_feli()
{return _tarikh_feli;}

public void set_tarikh_feli(String tarikh_feli)
{
	this._tarikh_feli=tarikh_feli.trim();
}
//----------------------------------------------------------��ј�� ����
public String get_karkard_feli()
{return _karkard_feli;}

public void set_karkard_feli(String karkard_feli)
{
	this._karkard_feli=karkard_feli.trim();
}

//----------------------------------------------------------����

public String get_doreh()
{return _doreh;}

public void set_doreh(String doreh)
{
	this._doreh=doreh.trim();
}
//----------------------------------------------------------����ǘ

public String get_eshterak_for_display()
{
    String temp_Eshterak=_eshterak;
    try
    {
      temp_Eshterak= Integer.valueOf(temp_Eshterak.substring(temp_Eshterak.length()-6, temp_Eshterak.length())).toString();
    }catch (Exception Error){ temp_Eshterak="";}
    return temp_Eshterak;
}
public String get_eshterak()
{return _eshterak;}

public void set_eshterak(String eshterak)
{
	this._eshterak=eshterak.trim();
}
//-----------------------------------------------------------������
public String get_parvandeh()
{return _parvandeh;}

public void set_parvandeh(String parvandeh)
{
	this._parvandeh=parvandeh.trim();
}
//-----------------------------------------------------------����� ���
public String get_Polomp_Kontor()
{return _Polomp_Kontor;}

public void set_Polomp_Kontor(String Polomp_Kontor)
{
	this._Polomp_Kontor=Polomp_Kontor.trim();
}
//-----------------------------------------------------------���� �����
public String get_radif_tashkhis()
{return _radif_tashkhis;}

public void set_radif_tashkhis(String radif_tashkhis)
{
	this._radif_tashkhis=radif_tashkhis.trim();
}
//-----------------------------------------------------------��Ҙ��
public String get_roz_kar()
{return _roz_kar;}

public void set_roz_kar(String roozkar)
{
	this._roz_kar=roozkar.trim();
}

//-----------------------------------------------------------�� �����
public String get_code_mamor()
{return _code_mamor;}

public void set_code_mamor(String code_mamrod)
{
	this._code_mamor=code_mamrod.trim();
}

//-----------------------------------------------------------�� ���
public String get_code_shahr()
{return _code_shahr;}

public void set_code_shahr(String code_shahr)
{
	this._code_shahr=code_shahr.trim();
}

//---------------------------------------------------------------�� �����

public String get_code_rosta()
{return _code_rosta;}

public void set_code_rosta(String code_roosta)
{
	this._code_rosta=code_roosta.trim();
}

//---------------------------------------------------------------�� �����
public String get_code_mantagheh()
{return _code_mantagheh;}

public void set_code_mantagheh(String code_mantagheh)
{
	this._code_mantagheh=code_mantagheh.trim();
}

//---------------------------------------------------------------��� ���ј

public String get_name_moshtarek()
{return _name_moshtarek;}

public void set_name_moshtarek(String name_moshtarak)
{
	this._name_moshtarek=name_moshtarak.trim();
}

//---------------------------------------------------------------���� ���ј
public String get_address_moshtarek()
{return _address_moshtarek;}

public void set_address_moshtarek(String address_moshtarak)
{
	this._address_moshtarek=address_moshtarak.trim();
}

//---------------------------------------------------------------�� ����

public String get_code_posti()
{return _code_posti;}

public void set_code_posti(String code_posti)
{
	this._code_posti=code_posti.trim();
}

//---------------------------------------------------------------�� ���
public String get_code_meli()
{return _code_meli;}

public void set_code_meli(String code_meli)
{
	this._code_meli=code_meli.trim();
}

//---------------------------------------------------------------��� ������

public String get_karbari()
{return _karbari;}

    public int get_karbari_int()
    {
        try {
            return
                    Integer.valueOf(_karbari);
        }catch (Exception Err){return 0;}
    }

public void set_karbari(String karbari)
{
	this._karbari=karbari.trim();
}

//---------------------------------------------------------------��� ������
public String get_ghotre_ensheab()
{return _ghotre_ensheab;}

public void set_ghotre_ensheab(String ghotre_ensheab)
{
	this._ghotre_ensheab=ghotre_ensheab.trim();
}

//---------------------------------------------------------------����� ����

public String get_tedad_vahed()
{return _tedad_vahed;}

public void set_tedad_vahed(String tedad_vahed)
{
	this._tedad_vahed=tedad_vahed.trim();
}

//---------------------------------------------------------------����� ������
public String get_tedad_khanevar()
{return _tedad_khanevar;}

public void set_tedad_khanevar(String tedad_khanevar)
{
	this._tedad_khanevar=tedad_khanevar.trim();
}

//---------------------------------------------------------------��� �����
public String get_model_kontor()
{return _model_kontor;}

public void set_model_kontor(String model_kontor)
{
	this._model_kontor=model_kontor.trim();
}
//--------------------------------------------------------------����� ���

public String get_tarikh_nasb()
{return _tarikh_nasb;}

public void set_tarikh_nasb(String tarikh_nasb)
{
	this._tarikh_nasb=tarikh_nasb.trim();
}

//---------------------------------------------------------------����� �����
public String get_tarikh_taviz()
{return _tarikh_taviz;}

public void set_tarikh_taviz(String tarikh_taviz)
{
	this._tarikh_taviz=tarikh_taviz.trim();
}
//---------------------------------------------------------------����� ����
public String get_shomare_kontor()
{return _shomare_kontor;}

public void set_shomare_kontor(String shomare_kontor)
{
	String temp=shomare_kontor.trim();
	//97-11-30 ���� ���� ���� �� ��� ���� ��Ҙ�� 19 ���� �� ���� �����
	temp=temp.replaceAll("/", "");
	this._shomare_kontor=temp;
}
//---------------------------------------------------------------����� �����
public String get_tedad_argham()
{return _tedad_argham;}

public void set_tedad_argham(String tedad_argham)
{
	this._tedad_argham=tedad_argham.trim();
}
//---------------------------------------------------------------���� ����
public String get_mane_ghabli()
{return _mane_ghabli;}

public void set_mane_ghabli(String mane_ghabli)
{
	this._mane_ghabli=mane_ghabli.trim();
}
//---------------------------------------------------------------������ ����
public String get_bazresi_ghabli()
{return _bazresi_ghabli;}

public void set_bazresi_ghabli(String bazresi_ghabli)
{
	this._bazresi_ghabli=bazresi_ghabli.trim();
}
//---------------------------------------------------------------����� ����1
public String get_masraf1()
{return _masraf1;}

public void set_masraf1(String masraf1)
{
	this._masraf1=masraf1.trim();
}
//---------------------------------------------------------------����� ����2
public String get_masraf2()
{return _masraf2;}

public void set_masraf2(String masraf2)
{
	this._masraf2=masraf2.trim();
}
//---------------------------------------------------------------����
public String get_bedehi()
{return _bedehi;}

public void set_bedehi(String bedehi)
{
	this._bedehi=bedehi.trim();
}
//---------------------------------------------------------------����� ����
public String get_meghdare_ghabli()
{return _meghdare_ghabli;}

public int get_meghdare_ghabli_int()
{   try
    {
        return  Integer.valueOf(_meghdare_ghabli);
    }catch (Exception Error){return 0; }
}

public void set_meghdare_ghabli(String meghdare_ghabli)
{
	this._meghdare_ghabli=meghdare_ghabli.trim();
}
//---------------------------------------------------------------����� ����
public String get_tarikh_ghabli()
{return _tarikh_ghabli;}

public void set_tarikh_ghabli(String tarikh_ghabli)
{
    try {
        //String Temp_Day = tarikh_ghabli.trim().substring(tarikh_ghabli.length() - 2, tarikh_ghabli.length());
        //String Temp_Mon = tarikh_ghabli.trim().substring(tarikh_ghabli.length() - 4, tarikh_ghabli.length() - 2);
        //String Temp_Year = tarikh_ghabli.trim().substring(0, tarikh_ghabli.length() - 4);
        this._tarikh_ghabli = tarikh_ghabli;
    }catch (Exception Error){this._tarikh_ghabli="";}
}
//-------------------------------------------------------------------    ���� ����� ���� ���� ��
public String get_FKId_List()
{return _FKId_List;}

public void set_FKId_List(String FKId_List)
{
	this._FKId_List=FKId_List;
}
//endregion
}
