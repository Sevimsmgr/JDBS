import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       //1.Adim : Driver'a kaydol
        Class.forName("org.postgresql.Driver");
        //2.Adim : Database'e baglan
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","hvkk2011");
        //3.Adim : Statement olusturma
        Statement st = con.createStatement();
        // 4.Adim : Query calistir

        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.

        /*
        execute() methodu DDL(create, drop, alter table) ve DQL(select) için kullanılabilir.
        1) Eğer execute() methodu DDL için kullanılırsa 'false' return yapar.
        2) Eğer execute() methodu DQL için kullanılırsa ResultSet alındığında 'true' aksi hale 'false' verir.
         */

       boolean sql1= st.execute("CREATE TABLE workers(worker_id VARCHAR(20),worker_name VARCHAR(20),worker_salary INT) ");
       System.out.println(sql1);
       String sql2= "ALTER TABLE workers ADD worker_address varchar(80)";
       st.execute(sql2);

       //3.Ornek ; workers table siliin
        String sql3 = "drop table workers";
        st.execute(sql3);

        // 5.Adim : Baglanti ve Statemnt'i kapat
        con.close();
        st.close();



    }
}
