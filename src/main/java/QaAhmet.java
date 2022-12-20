import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class QaAhmet {

    public static void main(String[] args) throws SQLException {

        Connection connection = JdbcUtils.connectToDataBase();
        Statement statement = JdbcUtils.createStatement();


////1. Örnek: Prepared statement kullanarak Tom'un soyadını HANKS yapan sorgu
//1. Adım : PreparedStatemend query'sini olustur
        String sql1 = "update insanlar3 set soyisim= ? WHERE id=?";
        //2.Adım PreparedStatemend objesini oluştur.
        PreparedStatement pst1 = connection.prepareStatement(sql1);
        //3.Adım: setInt(), setString(),... methodlarını kullanarak ? yerlerine değer ata.
        pst1.setString(1, "HANKS");
        pst1.setInt(2, 12363);
    }

//    import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//    public class Test01 {
//
//        @Test
//        public void test01(){
//
//            assertEquals("1","1");
//
//        }
//
//        @Test
//        public void test02(){
//        }
    }
