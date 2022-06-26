import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //connect to database
        //write a connection string postgres with username postgres and password 140521
        String connectionString = "jdbc:postgresql://localhost:5432/concu?user=postgres&password=140521";
        //connect
        try {
            Connection connection = DriverManager.getConnection(connectionString);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
        }
        String tenMatHang="Thit ba chi";
        Integer soLuong=3;
        String tenNguoiMua="Pham Minh Tuan";
        //create preared statement
        String sql = "INSERT INTO donhang (TenMatHang,SoLuong,TenNguoiMua) VALUES(?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(connectionString);
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tenMatHang);
            preparedStatement.setInt(2, soLuong);
            preparedStatement.setString(3, tenNguoiMua);
            preparedStatement.executeUpdate();
            System.out.println("Inserted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
