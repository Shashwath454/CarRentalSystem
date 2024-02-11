public class Car{
    private static final String url = "";
    private static final String username = "root";
    private static final String password = "";
    private static final String acesspassword="";

    public static void main(String[] args) {
        try {
            Class.forName("");
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
