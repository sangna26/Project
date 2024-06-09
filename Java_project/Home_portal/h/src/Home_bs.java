import java.io.*;
import java.sql.*;
import java.util.*;

class Work{
    Scanner sc = new Scanner(System.in);
    static Connection con;
            
    String city;
    String s_price;
    String s_area;
    String catagary;
    String path;
    File f1;

      void insertHomeListing(Connection connection) throws Exception 
      {
        try {
            System.out.println("Enter the details of home.");
            System.out.println("enter city.");
            city=sc.next();
            System.out.println("enter price.");
            Float price=sc.nextFloat();
            s_price=price.toString();
            System.out.println("enter area in Squre meter.");
            Float area=sc.nextFloat();
            s_area=area.toString();
            System.out.println("enter catagary.");
            catagary =sc.next();
            System.out.println("enter image file path.");
            path=sc.next();
            File f =new File(path);
            FileInputStream fis=new FileInputStream(f);
            String sql = "INSERT INTO home_detail(city, price,area,h_catagary,h_photo) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, city);
            preparedStatement.setFloat(2, price);
            preparedStatement.setFloat(3, area);
            preparedStatement.setString(4, catagary);
            preparedStatement.setBlob(5, fis);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("New home listing added.");
            } catch (Exception e) {
                System.out.println("Error.");
            }
            
        } 
        void displayHomeByCity(Connection connection,String name,String wcity) throws Exception 
        {
        try {
            String sql = "SELECT * FROM home_detail where city = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wcity);
            ResultSet CityresultSet = preparedStatement.executeQuery();
            System.out.println("Home Listings:");
            
            if(CityresultSet!=null)
            {
                
                while (CityresultSet.next()) 
                {
                        int id=CityresultSet.getInt(1);
                        city = CityresultSet.getString("city");
                        Float price = CityresultSet.getFloat("price");
                        s_price=price.toString();
                        Float area = CityresultSet.getFloat("area");
                        s_area=area.toString();
                        String catagary =CityresultSet.getString("h_catagary");
                        Blob b=CityresultSet.getBlob("h_photo");
                        byte [] arr=b.getBytes(1,(int)b.length());
                        f1=new File(name+".png");
                        FileOutputStream fos= new FileOutputStream(f1);
                        fos.write(arr);
                        fos.flush();
                        System.out.println("   Id : "+id+" \n city " + city + "\n Price: " + price+"rupees  \n Area: "+area+"squre meter. \n Catagary : "+catagary+" \n Photo : "+f1.getPath());
                }
            }
            else{
                System.out.println("No. home avalble in this City.");
            } 
        } catch (Exception e) {
            System.out.println("Error.");
        }
        }
        void displayHomeByRange(Connection connection,String name,float inrange,float outrange) throws Exception 
        {
            try {
                String sql = "SELECT * FROM home_detail where price between ? and ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setFloat(1, inrange);
            preparedStatement.setFloat(2, outrange);
            ResultSet RangeresultSet = preparedStatement.executeQuery();
            System.out.println("Home Listings:");
            if(RangeresultSet.getFloat("price")>inrange && RangeresultSet.getFloat("price")<outrange)
            {
                System.out.println("No. home avalble in this Range.");
            }
            else{
                while (RangeresultSet.next()) 
                {
                        int id=RangeresultSet.getInt(1);
                        String city = RangeresultSet.getString("city");
                        float price = RangeresultSet.getFloat("price");
                        float area = RangeresultSet.getFloat("area");
                        String catagary=RangeresultSet.getString("h_catagary");
                        Blob b=RangeresultSet.getBlob("h_photo");
                        byte [] arr=b.getBytes(1,(int)b.length());
                        File f1=new File(name+".png");
                    
                        FileOutputStream fos= new FileOutputStream(f1);
                        
                        fos.write(arr);
                        fos.flush();
                        System.out.println("   Id : "+id+" \n city " + city + "\n Price: " + price+"rupees  \n Area: "+area+"squre meter. \n Catagary : "+catagary+"  \n Photo : "+f1.getPath());
                }
            }
            } catch (Exception e) {
                System.out.println("Error.");
            }
            
        }
        void displayHomeByCatagary(Connection connection,String name,String wcatagary) throws Exception 
        {
            try {
                String sql = "SELECT * FROM home_detail where h_catagary = ? order by h_id desc";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wcatagary);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Home Listings:");
            if(resultSet!=null)
            {
                
                while (resultSet.next()) 
                {
                        int id=resultSet.getInt(1);
                        String city = resultSet.getString("city");
                        float price = resultSet.getFloat("price");
                        float area = resultSet.getFloat("area");
                        String catagary = resultSet.getString("h_catagary");
                        Blob b=resultSet.getBlob("h_photo");
                        byte [] arr=b.getBytes(1,(int)b.length());
                        File f1=new File(name+".png");
                        FileOutputStream fos= new FileOutputStream(f1);
                        fos.write(arr);
                        fos.flush();
                        System.out.println("   Id : "+id+" \n city " + city + "\n Price: " + price+"rupees  \n Area: "+area+"squre meter. \n Catagary : "+catagary+"  \n Photo : "+f1.getPath());
                }
            }
            else{
                System.out.println("No. home avalble in this Catagory.");
            }
            } catch (Exception e) {
                System.out.println("Error.");
            }
            
        }
        void displayHomeListings(Connection connection,String name) throws Exception 
        {
            try {
                String sql = "SELECT * FROM home_detail ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Home Listings:");
            while (resultSet.next()) {
                int id=resultSet.getInt(1);
                String city = resultSet.getString("city");
                float price = resultSet.getFloat("price");
                float area = resultSet.getFloat("area");
                Blob b=resultSet.getBlob("h_photo");
                byte [] arr=b.getBytes(1,(int)b.length());
                File f1=new File(name+".png");
                
                FileOutputStream fos= new FileOutputStream(f1);
            
                fos.write(arr);
                fos.flush();
                System.out.println("   Id : "+id+" \n city " + city + "\n Price: " + price+"rupis  \n Area: "+area+"squre meter.  \n Photo : "+f1.getPath());
            }
        } catch (Exception e) {
            System.out.println("Error.");
        }
    }

 
    public static void main(String[] args) throws Exception {
        try {
            Scanner sc=new Scanner(System.in);
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            String url = "jdbc:mysql://localhost:3306/home";
            String user = "root";
            String pass = "";
            Connection con = DriverManager.getConnection(url, user, pass);
            String User_name;
            Long Mobile ;
            Work w=new Work();
            System.out.println("-----Home Buing Selling Portal-----");

             String choice;
            boolean check = false;
        do{
            System.out.println();
            System.out.println("[1] Sign Up");
            System.out.println("[2] Log IN");
            System.out.println("Are You Exsisting User ?");
            choice = sc.next();
            
            if(choice.equalsIgnoreCase("Yes")){
                System.out.print("Username :- ");
                String name = sc.next();
                System.out.print("Password :- ");
                String pass_ = sc.next();
                User_name=name;
                String Query = "select c_name,c_pass from customer where c_name = '"+name+"';";

                Statement s = con.createStatement();

                ResultSet r = s.executeQuery(Query);
                String cpass="";
                String cname="";
                while(r.next()){
                    cname = r.getString(1); 
                    cpass = r.getString(2);
                }
                if(name.equalsIgnoreCase(cname) && pass_.equals(cpass)){
                    System.out.println("SuccessFully LogedIn\nWELCOME BACK...........");
                    check = true;  
                    break; 
                }
                else{
                    System.out.println("Error");
                    check = false;
                }


            }
            else{
               
                System.out.println("Entre Your Name :");
                System.out.print("Username :- ");
                String name = sc.next();
                User_name=name;
                String lenth;
                String m_string;
                do{
                    System.out.println("Entre Your Mobile No :");
                    System.out.print("Mobile No :- ");
                    Mobile = sc.nextLong();
                     m_string=Mobile.toString();
                    lenth = String.valueOf(Mobile);

                }while(lenth.length()!=10);    

                String pass_;
                String cpass;
                    System.out.println("Create Your New Password In Number :");
                    System.out.print("Password :- ");
                    pass_ = sc.next();
                do
                {
                    System.out.println("Confirm Password :-");
                    cpass = sc.next();
                    if(cpass.equals(pass_))
                    {
                        break;
                    }
                } while(cpass.equals(pass_)); 
                
                
                if(pass_ == cpass){
                    System.out.println("SuccessFully Loged IN");
                }
                
                System.out.println("Enter Customer id.");
                Integer cid=sc.nextInt();
                String cString=cid.toString();
                System.out.println("Enter Customer adhdhar no.");
                Long cadhar=sc.nextLong();
                String aString=cadhar.toString();

                String Query = "insert into Customer values(?,?,?,?,?)";

                PreparedStatement p = con.prepareStatement(Query);
                p.setInt(1, cid);
                p.setString(2, name);
                p.setLong(3,Mobile);
                p.setString(4, cpass);
                p.setLong(5, cadhar);
                int r = p.executeUpdate();

                File f=new File(User_name+".docx");
                FileWriter fw=new FileWriter(f,true);
                BufferedWriter bfw=new BufferedWriter(fw);
                bfw.write("Name : "+name);
                bfw.newLine();
                bfw.write("Mobile no. : "+m_string);
                bfw.newLine();;
                bfw.write("Password : "+cpass);
                bfw.newLine();
                bfw.write("Customer Id : "+cString);
                bfw.newLine();
                bfw.write("Customer addhar no. : "+aString);
                bfw.newLine();
                        
                bfw.flush();
                
                
                if(r>0){
                    check = true;
                    break;
                }
                else{
                    check = false;
                }
                
            }

        }while(choice!=null);
         String cname=User_name;
                
            int choice1;
            do{
                System.out.println("Enter your choise.");
                System.out.println("1. For selling Home.");
                System.out.println("2. For Buying Home.");
                System.out.println("3. For Display all Home.");
                System.out.println("4. Exit.");
                 choice1 =sc.nextInt();
                if(choice1==4)
                {
                    break;
                }
                File f=new File(User_name+".docx");
                FileWriter fw=new FileWriter(f,true);
                BufferedWriter bfw=new BufferedWriter(fw);
                
                switch (choice1) {
                    case 1:
                        w.insertHomeListing(con);
                        bfw.newLine();
                        bfw.write("Stutas : Sell");
                        bfw.newLine();
                        bfw.write("City : "+w.city);
                        bfw.newLine();
                        bfw.write("Price : "+w.s_price);
                        bfw.newLine();
                        bfw.write("Area : "+w.s_area+"squre meter");
                        bfw.newLine();
                        bfw.write("Catagory : "+w.catagary);
                        bfw.newLine();
                        bfw.write("Photo Path : "+w.path);
                        bfw.flush();
                        break;
                    case 2:
                        int choice2;
                        boolean flag=true;
                        while(flag){
                            System.out.println("Enter your Choise.");
                            System.out.println("1. Short By City.");
                            System.out.println("2. Short By Range.");
                            System.out.println("3. Short By Catagry.");
                            System.out.println("4. Back to Main Menu.");
                            choice2=sc.nextInt();
                            switch(choice2)
                            {
                                case 1:
                                    System.out.println("enter city where you buy home.");
                                    String wcity=sc.next();
                                    w.displayHomeByCity(con,User_name,wcity);
                                    System.out.println("What you want to buy? using id");
                                    int id=sc.nextInt();
                                    String sqlString="delete from home_detail where h_id=?";
                                    PreparedStatement pst1=con.prepareStatement(sqlString);
                                    pst1.setInt(1, id);
                                    pst1.executeUpdate();
                                    System.out.println("You Buy Your Home.");
                                    bfw.newLine();
                                    bfw.write("Stutas : Buy");
                                    bfw.newLine();
                                    bfw.write("City : "+w.city);
                                    bfw.newLine();
                                    bfw.write("Price : "+w.s_price);
                                    bfw.newLine();
                                    bfw.write("Area : "+w.s_area+"Squre meter");
                                    bfw.newLine();
                                    bfw.write("Catagory : "+w.catagary);
                                    bfw.newLine();
                                    bfw.write("Photo Path : "+w.f1.getPath());
                                    bfw.flush();
                                    break;
                                case 2:
                                    System.out.println("enter range for buying home.");
                                    System.out.println("enter starting range.");
                                    float inrange=sc.nextInt();
                                    System.out.println("enter ending range");
                                    float outrange=sc.nextInt();
                                    w.displayHomeByRange(con,User_name,inrange,outrange);
                                    System.out.println("What you want to buy? using id");
                                    int id1=sc.nextInt();
                                    String sqlString1="delete from home_detail where h_id=?";
                                    PreparedStatement pst2=con.prepareStatement(sqlString1);
                                    pst2.setInt(1, id1);
                                    pst2.executeUpdate();
                                    System.out.println("You Buy Your Home.");
                                    bfw.newLine();
                                    bfw.write("Stutas : Buy");
                                    bfw.newLine();
                                    bfw.write("City : "+w.city);
                                    bfw.newLine();
                                    bfw.write("Price : "+w.s_price);
                                    bfw.newLine();
                                    bfw.write("Area : "+w.s_area+"Squre meter");
                                    bfw.newLine();
                                    bfw.write("Catagory : "+w.catagary);
                                    bfw.newLine();
                                    bfw.write("Photo Path : "+w.f1.getPath());
                                    bfw.flush();
                                    break;
                                case 3:
                                    System.out.println("enter catagary which you buy home.");
                                    String wcatagary=sc.next();
                                    w.displayHomeByCatagary(con, User_name, wcatagary);
                                    System.out.println("What you want to buy? using id");
                                    int id3=sc.nextInt();
                                    String sqlString3="delete from home_detail where h_id=?";
                                    PreparedStatement pst4=con.prepareStatement(sqlString3);
                                    pst4.setInt(1, id3);
                                    pst4.executeUpdate();
                                    System.out.println("You Buy Your Home.");
                                    bfw.newLine();
                                    bfw.write("Stutas : Buy");
                                    bfw.newLine();
                                    bfw.write("City : "+w.city);
                                    bfw.newLine();
                                    bfw.write("Price : "+w.s_price);
                                    bfw.newLine();
                                    bfw.write("Area : "+w.s_area+"Squre meter");
                                    bfw.newLine();
                                    bfw.write("Catagory : "+w.catagary);
                                    bfw.newLine();
                                    bfw.write("Photo Path : "+w.f1.getPath());
                                    bfw.flush();
                                    break;
                                case 4:
                                    flag=false;
                                    break;
                                default:
                                System.out.println("Invalid");


                            } 
                            
                        }
                        
                        break;

                    case 3:
                        System.out.println("Display all details of Home.");
                        w.displayHomeListings(con,User_name);
                        break;
                    default:
                        break;
                }
            }while(choice1!=4);

        }
        catch(FileNotFoundException e)
        {
            System.out.println("Sorry ! File is not found.");
        } catch (Exception e) {
            System.out.println("Error.");
        }     
        }
} 
/*  String sql="insert into home_detail(city,price,area,h_catagary,h_photo) values('Ahmedabad',10000000,5000,'flate','C://Users/photo/flat1.jpeg'),('Junagadh',15000000,8500,'flate','C://Users/photo/flat2.jpeg'),('Rajkot',5900000,5000,'flate','C://Users/photo/flat3.jpeg'),('Ahmedabad',33000000,35000,'flate','C://Users/photo/flat4.jpeg'),('Surat',2500000,5000,'flate','C://Users/photo/flat5.jpeg'),('Ahmedabad',55000000,25000,'bunglow','C://Users/photo/banglow1.jpeg'),('Surat',5500000,20000,'bunglow','C://Users/photo/banglow2.jpeg'),('Junagadh',1250000,30000,'bunglow','C://Users/photo/banglow3.jpeg'),('Rajkot',5000000,45000,'bunglow','C://Users/photo/banglow4.jpeg'),('Junagadh',500000,30000,'bunglow','C://Users/photo/banglow5.jpeg'),('Ahmedabad',5500000,25000,'duplex','C://Users/photo/duplex1.jpeg'),('Rajkot',400000,26000,'duplex','C://Users/photo/duplex2.jpeg'),('Surat',5300000,29000,'duplex','C://Users/photo/duplex3.jpeg'),('Junagadh',5700000,27000,'duplex','C://Users/photo/duplex4.jpeg'),('Surat',540000,28900,'duplex','C://Users/photo/duplex5.jpeg')";
        Statement st=con.createStatement();
        st.executeUpdate(sql);
 */