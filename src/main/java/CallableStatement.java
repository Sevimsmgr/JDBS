import java.sql.*;

public class CallableStatement {


    /*
    Java'da methodlar return Type sahibi olsada olmasada Method olarak adlandiriliyor
    SQL'de ise data return ediyorsa "function" denir . Return yapmiyorsa "procedure" olarak adlandirilir.
    PreparedStatementten farki burada functional cagiracagiz

     */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.Adim : Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2.Adim : Database'e baglan
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","hvkk2011");
        //3.Adim : Statement olusturma
        Statement st = con.createStatement();

        //CallableStatement ile Function cagirmayi parameterize edecegiz.

        //1.Adim : Function kodunu yaz
        String sql1 = "CREATE OR REPLACE FUNCTION  toplamaF(x NUMERIC,y NUMERIC) \n" +
                " RETURNS NUMERIC\n" +
                " LANGUAGE plpgsql\n" +
                " AS\n" +
                " $$\n" +
                " BEGIN\n" +
                " RETURN x+y;\n" +
                " END\n" +
                " $$";

        //2.Adim : Function'i calistir yani tanimla
        st.execute(sql1);

        //3.Adim : Fonksiyonu cagir
        java.sql.CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?,?)}");  //

        //4.Adim : Return icin registerOurParameter methodunu parametereler icin ise set() ... methodlarini uygula.
        // (sql'den javaya cagirirken hangi return type ve parameterType oldnu belirtmem gerek)

        cst1.registerOutParameter(1, Types.NUMERIC);  //return
        cst1.setInt(2,6);   // 1.toplanan
        cst1.setInt(3,4);   // 2.toplanan

        //5.Adim : execute() methodu ile CallableStatement'i calistir .
        cst1.execute() ;

        //6.Adim : Sonucu cagirmak icin return data type tipine gore
        System.out.println(cst1.getBigDecimal(1));

        // 2 : ÖRNEK  : Koninin hacmini hesaplayan bir function yazin .
        // 1 . Adim : Function kodunu yaz
        String sql2 =  "CREATE OR REPLACE FUNCTION  konininHacmiF(r NUMERIC,h NUMERIC) \n" +
                " RETURNS NUMERIC\n" +
                " LANGUAGE plpgsql\n" +
                " AS\n" +
                " $$\n" +
                " BEGIN\n" +
                " RETURN 3.14*r*r*h;\n" +
                " END\n" +
                " $$";
        // 2 .ADIM : Functionu calistir ;
        st.execute(sql2);

        // 3 .ADIM : Function'u cagir ;
        java.sql.CallableStatement cst2 = con.prepareCall(sql2);

        // 4 .ADIM : Return icin registerOurParameter() methodunu kullan
        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,1);
        cst2.setInt(3,6);

        // 5 .ADIM : execute() methodu ile CallableStatement'i calistir.
        cst2.execute();

        // 6 .ADIM : Sonucu cagirmak icin return data tipine gore getBigDecimal , getInt ..... ilesouta yaz
        System.out.println(cst2.getBigDecimal(1));


    }
}
