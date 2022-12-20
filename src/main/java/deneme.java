import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class deneme {


    public static void executeQuery(String tableName, int columnMenge,String...columnName) {
        Statement statement = JdbcUtils.createStatement();
        if (columnName.length==0){
            try{
                String sqlHepsiniGor = "SELECT * from "+ tableName ;
                ResultSet rs1 = statement.executeQuery(sqlHepsiniGor);
                while(rs1.next()){

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{

        StringBuilder columns = new StringBuilder("");
        for (String w : columnName) {
            columns.append(w).append(",");
        }
        columns.deleteCharAt(columns.length() - 1);




        try {
            String sql2 = "select   " + columns + " from " + tableName;
            ResultSet resultset1 = statement.executeQuery(sql2);
            while (resultset1.next()) {

                for (int i = 0; resultset1.next(); i = i + columnName.length) {


                    for (String j : columnName) {

                        System.out.print(resultset1.getObject(j) + " ");

                    }
                    System.out.println();
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }}

    public static void executeUpdate(String tableName, String setWhat, String newWalue, String where) {


        Statement statement = JdbcUtils.createStatement();

        try {
            String sql1 = "UPDATE " + tableName + "\n" +
                    "SET " + setWhat + "=" + newWalue + "\n" +
                    "WHERE " + where;

            int updateEdilenSatirSayisi = statement.executeUpdate(sql1);
            System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi);

            System.out.println("Table updated succesfully! ");


            //deneme.executeQuery(tableName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
