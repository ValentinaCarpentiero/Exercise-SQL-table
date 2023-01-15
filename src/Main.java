import java.sql.*;

public class Main {
    static final String URL = "jdbc:mysql://localhost:3306/newdb";
    static final String USER = "developer";
    static final String PASSWORD = "userpasw";
    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS students ("
                                     + " student_id INT(10) NOT NULL AUTO_INCREMENT, "
                                     + "last_name VARCHAR(30) NOT NULL, "
                                     + "first_name VARCHAR(30) NOT NULL, "
                                     + "PRIMARY KEY (student_id))";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement();
             PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO students (last_name, first_name) VALUES (?, ?)")) {

            statement.executeUpdate(CREATE_TABLE);

            insertStudents(preparedStatement, "Victor", "Oshimen");
            insertStudents(preparedStatement, "Khvicha", "Kvaratskhelia");
            insertStudents(preparedStatement, "Amir", "Rrahmani");
            insertStudents(preparedStatement, "Eljif", "Elmas");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertStudents(PreparedStatement preparedStatement, String firstName, String lastName) throws SQLException {
        preparedStatement.setString(1, lastName);
        preparedStatement.setString(2, firstName);
        preparedStatement.executeUpdate();
    }

}