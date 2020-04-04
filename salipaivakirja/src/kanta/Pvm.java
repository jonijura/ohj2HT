package kanta;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Joona1
 * @version 2.4.2020
 *
 */
public class Pvm {
    private static final int KPITUUDET[][] = {
            //0  1  2  3  4  5  6  7  8  9 10 11 12
            {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
            {31, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }
    };

    /**
     * @param pvm .
     * @return virhe
     * @example
     * <pre name="test">
     * TarkistaPvm("3.4.2020")===null;
     * TarkistaPvm("3.4")==="anna päivämäärä, erota luvut pisteellä";
     * TarkistaPvm("29.2.2020")===null;
     * TarkistaPvm("29.2.2019")==="päivä virheellinen";
     * </pre>
     */
    public static String TarkistaPvm(String pvm) {
        if(pvm==null)return "null viite";
        var sb = new StringBuilder(pvm);
        int pv=Mjonot.erotaInt(sb,0);
        if(sb.length()!=0)sb.deleteCharAt(0);
        int kk=Mjonot.erotaInt(sb,0);
        if(sb.length()!=0)sb.deleteCharAt(0);
        int vv=Mjonot.erotaInt(sb,-1);
        if(sb.length()>0)return "päivämäärässä ylimääräisiä merkkejä: " +sb.toString();
        if(pv==0||kk==0||vv==0)return "anna päivämäärä, erota luvut pisteellä";
        if(kk>12||kk<0)return "kuukausi on virheellinen";       
        int karkaus = 0;
        if(vv%4==0&&vv%400!=0)karkaus++;
        if(KPITUUDET[karkaus][kk]<pv||pv<0)return "päivä virheellinen";
        if ( vv < 0 ) return "vuosi puuttuu tai on virheellinen";
        if(vv<1990 || vv>2090)return "vuosi on virheellinen";
        return null;
    }

}
