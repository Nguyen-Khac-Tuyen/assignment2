package jdbca02gr02;

import java.sql.*;
import java.util.Scanner;

public class AssignmentProduct {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        System.out.println("==========Actions==========");
        System.out.println("1.Add product");
        System.out.println("2.Edit product");
        System.out.println("3.Delete product");
        System.out.println("4.View all product(default sort bu Name of product)");
        System.out.println("5.Search product by id");
        System.out.println("6.Search product by name");
        System.out.println("7.End");
    }
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        Connection connection = getconnection();
        menu();
        while (true){
            System.out.println(": ");
            int choice = input.nextInt();
            if (choice == 1) {
                addProduct();
                menu();
            } else if (choice == 2) {
                editProduct();
                menu();
            } else if (choice == 3) {
                deleteProduct();
                menu();
            } else if (choice == 4) {
                readProductData();
                menu();
            } else if (choice == 5) {
                searchProductById();
                menu();
            } else if (choice == 6) {
                searchProductByName();
                menu();
            } else if (choice == 7) {
                menu();
            }
        }
    }
    public static Connection getconnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/dbdemo";
        String username = "root";
        String password = "";

        Connection connection = DriverManager.getConnection(dbURL, username, password);
        System.out.println("ket noi thanh cong");
        return connection;

    }
    public static void addProduct() throws SQLException {
        Connection conn = getconnection();
        String query = "insert into product values(?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        System.out.println("Enter ID:");
        int id = scanner.nextInt();
        System.out.println("Enter name:");
        String proName = scanner.next();
        System.out.println("Enter desc:");
        String proDesc = scanner.next();
        System.out.println("Enter price:");
        Double price = scanner.nextDouble();
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,proName);
        preparedStatement.setString(3,proDesc);
        preparedStatement.setDouble(4,price);
        int rowInserted = preparedStatement.executeUpdate();
        if (rowInserted>0){
            System.out.println("Create thanh cong");
        }
    }
    public static void editProduct() throws SQLException{

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name:");
        String proName = scanner.next();
        System.out.println("Enter desc:");
        String proDesc = scanner.next();
        System.out.println("Enter price:");
        Double price = scanner.nextDouble();
        System.out.println("Enter ID:");
        int id = scanner.nextInt();
        Connection conn = getconnection();
        String query = "UPDATE product set proName =?, proDesc =?,price=? WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,proName);
        preparedStatement.setString(3,proDesc);
        preparedStatement.setDouble(4,price);
        int rowUpdated = preparedStatement.executeUpdate();
        if (rowUpdated>0){
            System.out.println("update complete");
        }
    }
    public static void deleteProduct() throws SQLException{
        Connection conn = getconnection();
        String query = "DELETE from product WHERE id =?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1,4);
        int rowDeleted = preparedStatement.executeUpdate();
        if (rowDeleted>0){
            System.out.println("Complete");
        }
    }
    public static void readProductData() throws SQLException{
        Connection conn = getconnection();
        String query="select * from product Order by name";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String proName = resultSet.getString("name");
            String proDesc = resultSet.getString(3);
            double price = resultSet.getDouble("price");
            System.out.println(id + " "+ proName+ " "+ proDesc+" "+ price);
        }
    }
    public static void searchProductById() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Connection connection = getconnection();
        String query = "SELECT * from product2 WHERE id = ?";
        PreparedStatement pe  = connection.prepareStatement(query);
        System.out.println("Nhập id cần tìm: ");
        int id2 = sc.nextInt();
        pe.setInt(1,id2);
        ResultSet resultSet = pe.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString("proName");
            String desc = resultSet.getString("proDesc");
            double price = resultSet.getDouble("price");
            System.out.println("id: " + id  + " | name: " + name + " | desc: " + desc  +" | price: " + price);
        }
    }
    private static Connection getConnectionone() {
        return null;
    }
    public static void searchProductByName() throws SQLException{
        Scanner sc = new Scanner(System.in);
        Connection connection = getconnection();
        String query = "SELECT * from product2 WHERE proName = ?";
        PreparedStatement pe = connection.prepareStatement(query);
        System.out.println("Nhập Name cần tìm: ");
        String id2 = sc.next();
        pe.setString(1,id2);
        ResultSet resultSet = pe.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString("proName");
            String desc = resultSet.getString("proDesc");
            double price = resultSet.getDouble("price");
            System.out.println("id: " + id  + " | name: " + name + " | desc: " + desc  +" | price: " + price);
        }
    };
}