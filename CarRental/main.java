import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

class Carinfo {
    private Connection con;
    private Scanner scan;

    public Carinfo(Connection con, Scanner scan) {
        this.scan = scan;
        this.con = con;
    }

    public void addcarinfo() {
        System.out.println("Enter the car model name");
        int carid = scan.nextInt();
        System.out.println("Enter the car model name");
        String name = scan.next();
        System.out.println("Enter the model year");
        int year = scan.nextInt();
        System.out.println("car cost");
        int price = scan.nextInt();
        try {
            String query = "INSERT INTO CARINFO (CAR_ID, CAR_MODEL, CAR_YEAR,CAR_price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1,carid);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, year);
                preparedStatement.setInt(4, price);
                int affected = preparedStatement.executeUpdate();
                if (affected > 0) {
                    System.out.println("Car info added");
                } else {
                    System.out.println("Car info failed to add");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewcar() {
        String query = "SELECT * FROM CARINFO";
        try {
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("car id\t\tcar_model\t\tused year\t\tprice");
                while (resultSet.next()) {
                    String model = resultSet.getString("CAR_MODEL");
                    int year = resultSet.getInt("CAR_YEAR");
                    int carid=resultSet.getInt("car_id");
                    int price=resultSet.getInt("car_price");
                    System.out.println(carid+"\t\t"+model+"\t\t"+year+"\t\t"+price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletecar() {
        String query = " DELETE FROM CARINFO WHERE CAR_id=?";
        System.out.println("enter the car id u want to delete");
        int delid= scan.nextInt();
        try {
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
                preparedStatement.setInt( 1,delid);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Sellinfo {
    private Connection con;
    private Scanner scan;

    public Sellinfo(Connection con, Scanner scan) {
        this.scan = scan;
        this.con = con;
    }

    public void sellcarinfo() {
        System.out.println("Enter the car details");
        System.out.println("car model year :");
        int sellmodely=scan.nextInt();
        System.out.print("car model :");
        String sellmodel=scan.next();
        System.out.print("car range :");
        int sellrange=scan.nextInt();
        try {
            String query = "INSERT INTO SELLCAR_INFO (CARMODEL_NAME, CARMODEL_YEAR,car_range) VALUES (?, ?,?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1,sellmodel);
                preparedStatement.setInt(2, sellmodely);
                preparedStatement.setInt(3,sellrange);
                int affected = preparedStatement.executeUpdate();
                if (affected > 0){
                    System.out.println("sellCar info added");
                } else {
                    System.out.println("sell Car info failed to add");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
class Clients {
    private Connection con;
    private Scanner scan;

     Clients(Connection con, Scanner scan) {
        this.scan = scan;
        this.con = con;
    }

    public void addclientinfo() {
        System.out.println("--------CLIENT INFOMATION------------");
        System.out.print(" NAME :");
        String name= scan.next();
        System.out.print("PHONE NO :");
        long phoneno = scan.nextLong();
        System.out.print("AADHAR NO :");
        long aadhar = scan.nextLong();
        System.out.print("ADDRESS :");
        String address = scan.next();
        System.out.print(" password :");
        String password = scan.next();
        try {
            String query = "INSERT INTO Client_INFO (name, phone_no,aadhar_no,address,password) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setLong(2, phoneno);
                preparedStatement.setLong(3, aadhar);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, password);
                int affected = preparedStatement.executeUpdate();
                if (affected > 0) {
                    System.out.println("Client info added");
                } else {
                    System.out.println("Client info failed to add");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewclient() {
        String query = "SELECT * FROM Client_INFO";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String model = resultSet.getString("");
                int year = resultSet.getInt("CAR_YEAR");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class Login {
    private Connection con;
    private Scanner scan;

    Login(Connection con, Scanner scan) {
        this.scan = scan;
        this.con = con;
    }
    public void login() {
        int flag = 0;
        System.out.print("username :");
        String username = scan.next();
        System.out.print("password :");
        String password = scan.next();
        String query = "SELECT NAME,PASSWORD FROM Client_INFO";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String checkname = resultSet.getString("name");
                String checkpassword = resultSet.getString("password");
                while (Objects.equals(checkname, username) && Objects.equals(password, checkpassword)) {
                    System.out.println("LOGIN SUCCESSFULL!!!");
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                System.out.print("INVALID USER OR PASSWORD !!!");
                System.exit(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
class Rental {
    private Connection con;
    private Scanner scan;

    Rental(Connection con, Scanner scan) {
        this.con = con;
        this.scan = scan;
    }

    public void Rentalcar() {
        System.out.println("Enter the car model name");
        String name = scan.next();
        System.out.println("Enter rental charge/day");
        int car_price = scan.nextInt();
        System.out.println("Enter car capacity");
        int capacity = scan.nextInt();
        try {
            String query = "INSERT INTO rental_car (CAR_name, CAR_price,capacity) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, car_price);
                preparedStatement.setInt(3, capacity);
                int affected = preparedStatement.executeUpdate();
                if (affected > 0) {
                    System.out.println("Car info added");
                } else {
                    System.out.println("Car info failed to add");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rentalcarview() {
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM rental_car");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int car_id = resultSet.getInt("car_id");
                String model = resultSet.getString("car_name");
                int capacity = resultSet.getInt("capacity");
                int rent = resultSet.getInt("CAR_PRICE");
                //formating
                System.out.println(car_id + "  " + model + "  " + capacity + "  " + rent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rentalinfo() throws SQLException {
        System.out.println("Enter the car_id");
        int carid = scan.nextInt();
        System.out.println("Enter the name");
        String name = scan.next();
        System.out.println("total rent day");
        int rentday = scan.nextInt();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM CLIENT_INFO WHERE name= ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            PreparedStatement preparedStatement1 = con.prepareStatement("SELECT * FROM rental_car WHERE CAR_id = ?");
            preparedStatement1.setInt(1, carid);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            if (resultSet.next() && resultSet1.next()) {
                int client_id = resultSet.getInt("client_id");
                String name1 = resultSet.getString("name");
                int car_id = resultSet1.getInt("car_id");
                String carname = resultSet1.getString("CAR_NAME");
                int carrent = resultSet1.getInt("car_price");
                int capacity = resultSet1.getInt("CAPACITY");
                PreparedStatement preparedStatement2 = con.prepareStatement("INSERT INTO CLIENT_RENT(Client_id, Client_name, Car_id, Price, Rent_day,CAPACITY) VALUES(?, ?, ?, ?, ?,?)");
                preparedStatement2.setInt(1, client_id);
                preparedStatement2.setString(2, name1);
                preparedStatement2.setInt(3, car_id);
                preparedStatement2.setInt(4, carrent);
                preparedStatement2.setInt(5, rentday);
                preparedStatement2.setInt(6, capacity);
                int affected = preparedStatement2.executeUpdate();
                if (affected > 0) {
                    System.out.println("Car info added");
                } else {
                    System.out.println("Car info failed to add");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public void billinfo() {
            System.out.println("Enter the name ");
            try {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM client_rent WHERE CLIENT_NAME=?");
                preparedStatement.setString(1, "name");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int client_id = resultSet.getInt("client_id");
                    String clientName = resultSet.getString("client_name");
                    int car_id = resultSet.getInt("car_id");
                    int day= resultSet.getInt("rent_day");
                    int rent = resultSet.getInt("CAR_PRICE");
                    int capacity = resultSet.getInt("capacity");
                    //formating
                    System.out.println(client_id + "  " + clientName + "  "+car_id+"  " + capacity + "  " + day+ "  "+rent*day);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}

public class Car{
    private static final String url = "jdbc:mysql://localhost:3306/CAR";
    private static final String username = "root";
    private static final String password = "Kau_(shik)*1";
    private static final String acesspassword="helloworld@109";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Scanner scan = new Scanner(System.in);
                Connection con = DriverManager.getConnection(url, username, password)) {
                Carinfo carinfo = new Carinfo(con, scan);
                Clients client=new Clients(con,scan);
                Sellinfo sellinfo=new Sellinfo(con,scan);
                Login logins=new Login(con,scan);
                Rental rental=new Rental(con,scan);
                System.out.println("PRESS 1: REGISTRATION  PRESS 2:  LOGIN");
                int reg = scan.nextInt();
            if(reg==1) {
                System.out.println("CLIENT REGISTRATION");
                client.addclientinfo();
            }
            else if(reg==2){
                logins.login();
            }
            else{
                System.out.println("ENTER 1 FOR REGISTRATION OR 2 FOR LOGIN");
            }
            while(true){
                System.out.println("The choices are :");
                System.out.println("1. Rent car");
                System.out.println("2. Sell car");
                System.out.println("3. Buy car");
                System.out.println("4.rent details");
                System.out.println("5.app update");
                System.out.print("Enter the choice :");
                int select =scan.nextInt();
                switch(select)
                {
                    case 1 :System.out.println("CARS FOR RENT");
                        rental.rentalcarview();
                        rental.rentalinfo();
                        break;
                    case 2:sellinfo.sellcarinfo();
                        System.out.println("Your response has been noted!!!\n");
                        return;
                    case 3:System.out.println("CARS for sales");
                        carinfo.viewcar();
                        //rental.rentalinfo();
                        break;
                    case 4:rental.billinfo();
                        break;
                    case 5:System.out.println("enter password for entry");
                    String password1=scan.next();
                    if(Objects.equals(password1, acesspassword)){
                        System.out.println("options");
                        System.out.println("1 :Add 2nd hand cars for sales");
                        System.out.println("2 :delete sold cars");
                        System.out.println("3 :Add rental car");
                        int choice1=scan.nextInt();
                        switch(choice1){
                            case 1:
                                carinfo.addcarinfo();
                                break;
                            case 2:System.out.println("available car");
                             carinfo.viewcar();
                             carinfo.deletecar();
                                break;
                            case 3:rental.Rentalcar();
                            break;
                        }
                    }
                    else {
                        System.out.println("invalid password");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}