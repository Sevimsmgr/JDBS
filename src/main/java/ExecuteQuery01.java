import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Adim : Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2.Adim : Database'e baglan
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","hvkk2011");
        //3.Adim : Statement olusturma
        Statement st = con.createStatement();

        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.

        String sql1="select country_name from countries where region_id=1";
        boolean r1=st.execute(sql1);
        System.out.println(r1);

        // Recordlari gormek icin ExecuteQuery() methodunu kullanirim
         ResultSet resultSet1 = st.executeQuery(sql1);

         while(resultSet1.next()){
             System.out.println(resultSet1.getString("country_name"));// ya da 1. sütun manasinda 1 yazilabilir

         }

         //2.Örnek : "region_id'nin" 2'den buyuk oldugu country_id ve country_nami'i yazdir

        System.out.println("--------------------------------------------------------------");
        String sql2 = "SELECT country_name,country_id from countries WHERE region_id>2";
        ResultSet resultSet2 = st.executeQuery(sql2);

         while(resultSet2.next()){
             System.out.println(resultSet2.getString("country_name")+"--"+resultSet2.getString("country_id"));
         }

         //3.Örnek : "number_of_employees" degeri en dusuk olan satirin tüm degerlerini cagirin
        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) from companies)";
         ResultSet resultSet3 = st.executeQuery(sql3);
        System.out.println("--------------------------------------------------------------");
         while(resultSet3.next()){
             System.out.println(resultSet3.getInt(1)+"---"+resultSet3.getString(2)+"---"+resultSet3.getInt(3));
         }

    }
}
