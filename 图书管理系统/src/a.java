import com.mysql.jdbc.Statement;
import com.sun.org.apache.bcel.internal.generic.Select;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.swing.*;

import com.sun.webkit.dom.HTMLOptionsCollectionImpl;
import sun.plugin2.message.Message;
//import org.apache.*;
import  java.sql.Timestamp;
import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

import javax.swing.event.*;
import java.awt.print.Book;
import java.sql.Connection;

public class a {
    public static void main(String[] args) throws Exception {
        Manage manage=new Manage();
        Mbin a=new Mbin();
       // a.printMbin();
        System.out.println("1.管理员登录   2.学生登录   3.老师登录  4.游客登录");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int select = scanner.nextInt();
          // System.out.println("1.管理员登录   2.学生登录   3.老师登录  4.游客登录");
            if(select==1){
                a.printMbina();
                System.out.println("管理员登录");
                while (true) {
                    a.printMbina();
                    int seleca = scanner.nextInt();
                    switch (seleca) {
                        case 1: {
                            manage.addBookstore();
                            System.out.println();
                        }
                        break;
                        case 2: {
                            manage.updateBookstore();
                            System.out.println();
                        }
                        break;
                        case 3: {
                            manage.deleteBookstore();
                            System.out.println();
                        }
                        break;
                        case 4: {
                            manage.seleteBookstore();
                            System.out.println();
                        }
                        break;
                        case 7: {
                            manage.borrowBookMassage0();
                            System.out.println();
                        }
                        break;
                        case 14: {
                            manage.bookSort();
                            System.out.println();
                        }
                        break;
                        default:
                            System.exit(0);
                    }
                }
            }
            else if(select==2){
                System.out.println("学生登录");
                a.printMbinb();
                while (true) {
                    a.printMbinb();
                    int seleca = scanner.nextInt();
                    switch (seleca) {
                        case 4: {
                            manage.seleteBookstore();
                            System.out.println();
                        }
                        break;
                        case 5: {
                            manage.borrowBookStudent1();
                            System.out.println();
                        }
                        break;
                        case 7: {
                            manage.borrowBookMassage0();
                            System.out.println();
                        }
                        break;
                        case 8: {
                            manage.borrowBookMassage1();
                            System.out.println();
                        }
                        break;
                        case 10: {
                            manage.backBookStudent1();
                            System.out.println();
                        }
                        break;
                        case 12: {
                            manage.backBookMassage1();
                            System.out.println();
                        }
                        break;
                        default:
                            System.exit(0);
                    }
                }
            }
            else if(select==3){
                a.printMbinc();
                System.out.println("老师登录");
                while (true) {
                    a.printMbinc();
                    int seleca = scanner.nextInt();
                    switch (seleca) {
                        case 4: {
                            manage.seleteBookstore();
                            System.out.println();
                        }
                        break;
                        case 6: {
                            manage.borrowBookTeacher2();
                            System.out.println();
                        }
                        break;
                        case 7: {
                            manage.borrowBookMassage0();
                            System.out.println();
                        }
                        break;
                        case 9: {
                            manage.borrowBookMassage2();
                            System.out.println();
                        }
                        break;
                        case 11: {
                            manage.backBookTeacher2();
                            System.out.println();
                        }
                        break;
                        case 13: {
                            manage.backBookMassage2();
                            System.out.println();
                        }
                        break;
                        default:
                            System.exit(0);
                    }
                }
            }
            else {
                a.printMbind();
                System.out.println("游客登录");
                System.out.println("请输入要查的书籍");
                while (true) {
                    a.printMbind();
                    int seleca = scanner.nextInt();
                    switch (seleca) {
                        case 4: {
                            manage.seleteBookstore();
                            System.out.println();
                        }
                        break;
                        case 7: {
                            manage.borrowBookMassage0();
                            System.out.println();
                        }
                        break;
                        default:
                            System.exit(0);
                    }
                }
            }
//            switch (select) {
////                case 1: {
////                    manage.addBookstore();
////                    System.out.println();
////                }
////                break;
////                case 2: {
////                    manage.updateBookstore();
////                    System.out.println();
////                }
////                break;
////                case 3: {
////                    manage.deleteBookstore();
////                    System.out.println();
////                }
////                break;
////                case 4: {
////                    manage.seleteBookstore();
////                    System.out.println();
////                }
////                break;
////                case 5: {
////                    manage.borrowBookStudent1();
////                    System.out.println();
////                }
////                break;
////                case 6: {
////                    manage.borrowBookTeacher2();
////                    System.out.println();
////                }
////                break;
////                case 7: {
////                    manage.borrowBookMassage0();
////                    System.out.println();
////                }
////                break;
////                case 8:{
////                    manage.borrowBookMassage1();
////                    System.out.println();
////                }
////                break;
////                case 9: {
////                    manage.borrowBookMassage2();
////                    System.out.println();
////                }
////                break;
////                case 10: {
////                    manage.backBookStudent1();
////                    System.out.println();
////                }
////                break;
////                case 11: {
////                    manage.backBookTeacher2();
////                    System.out.println();
////                }
////                break;
////                case 12: {
////                    manage.backBookMassage1();
////                    System.out.println();
////                }
////                break;
////                case 13: {
////                    manage.backBookMassage2();
////                    System.out.println();
////                }
////                break;
////                case 14: {
////                    manage.bookSort();
////                    System.out.println();
////                }
////                break;
////                default:System.exit(0);
////            }
        }
    }
}

class Mbin{
    public  void printMbin(){
        System.out.println("    =====欢迎来到通讯录系统====     ");
        System.out.println("===================================");
        System.out.println();
        System.out.println("               1.添加              ");
        System.out.println("               2.修改              ");
        System.out.println("               3.删除              ");
        System.out.println("               4.查询              ");
        System.out.println("               5.学生借阅           ");
        System.out.println("               6.老师借阅           ");
        System.out.println("               7.书籍借阅查询       ");
        System.out.println("               8.学生借阅查询        ");
        System.out.println("               9.老师借阅查询       ");
        System.out.println("               10.学生归还          ");
        System.out.println("               11.老师归还          ");
        System.out.println("               12.学生归还查询      ");
        System.out.println("               13.老师归还查询      ");
        System.out.println("               14.排序              ");

        System.out.println();
        System.out.println();
        System.out.println(" 请输入你的选择 ");
    }
    public  void printMbina(){
        System.out.println("    =====欢迎来到通讯录系统====     ");
        System.out.println("===================================");
        System.out.println();
        System.out.println("               1.添加              ");
        System.out.println("               2.修改              ");
        System.out.println("               3.删除              ");
        System.out.println("               4.查询              ");
        System.out.println("               7.书籍借阅查询       ");
        System.out.println("               14.排序              ");

        System.out.println();
        System.out.println();
        System.out.println(" 请输入你的选择 ");
    }
    public  void printMbinb(){
        System.out.println("    =====欢迎来到通讯录系统====     ");
        System.out.println("===================================");
        System.out.println();

        System.out.println("               4.查询              ");
        System.out.println("               5.学生借阅           ");
        System.out.println("               7.书籍借阅查询       ");
        System.out.println("               8.学生借阅查         ");
        System.out.println("               10.学生归还          ");
        System.out.println("               12.学生归还查询      ");

        System.out.println();
        System.out.println();
        System.out.println(" 请输入你的选择 ");
    }
    public  void printMbinc(){
        System.out.println("    =====欢迎来到通讯录系统====     ");
        System.out.println("===================================");
        System.out.println();
        System.out.println("               4.查询              ");
        System.out.println("               6.老师借阅           ");
        System.out.println("               7.书籍借阅查询       ");
        System.out.println("               9.老师借阅查询       ");
        System.out.println("               11.老师归还          ");
        System.out.println("               13.老师归还查询      ");

        System.out.println();
        System.out.println();
        System.out.println(" 请输入你的选择 ");
    }
    public  void printMbind(){
        System.out.println("    =====欢迎来到通讯录系统====     ");
        System.out.println("===================================");
        System.out.println();

        System.out.println("               4.查询              ");
        System.out.println("               7.书籍借阅查询       ");

        System.out.println();
        System.out.println();
        System.out.println(" 请输入你的选择 ");
    }
}

class BookManage{

    //图书信息
    private String ID;//编号
    private String bname;//图书名称
    private String author;//作者
    private String publishedHouse;//出版社
    private String type;//图书类型
    private String place;//存放位置
    private String date;//出版日期
    private String bookStatus;//书籍状态
    private String borrowPeopleName;//借书人
    private String number;//借书人Id
    private String borrowDate;//借阅日期
    private String backTime;//归还日期

    public BookManage(){
        String ID=" ";//编号
        String bname=" ";//图书名称
        String author=" ";//作者
        String publishedHouse=" ";//出版社
        String type=" ";//图书类型
        String place=" ";//存放位置
        String date=" ";//出版日期
        String bookStatus=" ";//书籍状态
        String borrowPeopleName=" ";//借书人
        String number=" ";//借书人Id
        String borrowDate=" ";//借阅日期
        String backTime=" ";//归还日期
    }
    public BookManage(String ID, String bname,String author,String publishedHouse,String type,String date,String borrowPeopleName, String number,String borrowDate,String backTime) {
        this.ID=ID;//编号
        this.bname=bname;//图书名称
        this.author=author;//作者
        this.publishedHouse=publishedHouse;//出版社
        this.type=type;//图书类型
        this.date=date;//出版日期
        this.borrowPeopleName=borrowPeopleName;//借书人
        this.number=number;//借书人Id
        this.borrowDate=borrowDate;//借阅日期
        this.backTime=backTime;//归还日期
    }
    public BookManage(String ID, String bname,String author,String publishedHouse,String type,String place,String date,String bookStatus,String borrowPeopleName, String number,String borrowDate,String backTime){
        this.ID=ID;//编号
        this.bname=bname;//图书名称
        this.author=author;//作者
        this.publishedHouse=publishedHouse;//出版社
        this.type=type;//图书类型
        this.place=place;//存放位置
        this.date=date;//出版日期
        this.bookStatus=bookStatus;//书籍状态
        this.borrowPeopleName=borrowPeopleName;//借书人
        this.number=number;//借书人Id
        this.borrowDate=borrowDate;//借阅日期
        this.backTime=backTime;//归还日期
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBname() {
        return bname;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public void setPublishedHouse(String publishedHouse) {
        this.publishedHouse = publishedHouse;
    }
    public String getPublishedHouse() {
        return publishedHouse;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    public String getPlace() {
        return place;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }
    public String getBookStatus() {
        return bookStatus;
    }

    public void setBorrowPeopleName(String borrowPeopleName) {
        this.borrowPeopleName = borrowPeopleName;
    }
    public String getBorrowPeopleName() {
        return borrowPeopleName;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }
    public String getBackTime1() {
        DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        backTime = dateFormat.format(calendar.getTime());
        return backTime;
    }
    public String getBackTime(){
        return backTime;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
    public String getBorrowDate1() {
        DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        borrowDate = dateFormat.format(calendar.getTime());
        return borrowDate;
    }
    public String getBorrowDate() {
        return borrowDate;
    }


    //图书所有信息输出
    public void printBookAllmassage(){
        System.out.println("书籍信息为:");
        System.out.println("编号:       "+getID()+" 图书名称:  "+ getBname()+"作者:       "+getAuthor()+"出版社:     "+getPublishedHouse()+"图书类型:   "+getType()+"存放位置:   "+getPlace()+"出版日期:   "+getDate()+"书籍状态:   "+getBookStatus()+"借书人:     "+getBorrowPeopleName()+"借书人Id:   "+getNumber()+"借阅日期:   "+getBorrowDate()+"归还日期    "+getBackTime());
    }

    //图书基本信息输出
    public void printBookmassage(){
        System.out.println("书籍基本信息为:");
        System.out.println("编号:       "+getID()+" 图书名称:  "+ getBname()+"作者:       "+getAuthor()+"出版社:     "+getPublishedHouse()+"图书类型:   "+getType()+"存放位置:   "+getPlace()+"出版日期:   "+"书籍状态:   "+getBookStatus());
    }

    //书籍借阅信息输出
    public void printBookBorrowmassage(){
        System.out.println("书籍借阅信息为:");
        System.out.println("编号:       "+getID()+" 图书名称:  "+ getBname()+"作者:       "+getAuthor()+"出版社:     "+getPublishedHouse()+"图书类型:   "+getPlace()+"出版日期:   "+"借阅日期:   "+getBorrowDate()+"归还日期    "+getBackTime());
    }

    //书籍归还信息输出
    public void printBookBackmassage(){
        System.out.println("书籍借阅信息为:");
        System.out.println("编号:       "+getID()+" 图书名称:  "+ getBname()+"作者:       "+getAuthor()+"出版社:     "+getPublishedHouse()+"图书类型:   "+getPlace()+"出版日期:   "+"借阅日期:   "+getBorrowDate()+"归还日期    "+getBackTime());
    }

}

class Student{
    private String sname;//学生姓名
    private String snumber;//学号

    public Student(){
        String sname="";//学生姓名
        String snumber="";//学号
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getSname() {
        return sname;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }
    public String getSnumber() {
        return snumber;
    }

    public void printStudentMassage(){
        System.out.println();
        System.out.println("姓名：   "+getSname()+"学号：   "+getSnumber());
    }
}


class Teacher{
    private String tname;//老师姓名
    private String tnumber;//工号
    //BookManage tbook;//图书使用信息

    public Teacher(){
        String tname="";//老师姓名
        String tnumber="";//工号
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
    public String getTname() {
        return tname;
    }

    public void setTnumber(String tnumber) {
        this.tnumber = tnumber;
    }
    public String getTnumber() {
        return tnumber;
    }

    public void printTeacherMassage(){
        System.out.println();
        System.out.println("姓名：   "+getTname()+"工号：   "+getTnumber());

    }
}


class Sever{
    // 数据库连接路径
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final  String url = "jdbc:mysql://localhost:3306/bookabcd?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&allowMultiQueries=true";
    //URL指向要访问的数据库名
    private static final  String user = "root";
    //MySQL配置时的用户名
    private static final String password = "123456";
    //MySQL配置时的密码
    private static Connection con = null;
    // 静态代码块（将加载驱动、连接数据库放入静态块中）
    static{

        try {
            Class.forName(driver); // 加载驱动程序
            con = DriverManager.getConnection(url, user, password); // 获取数据库的连接
        }
        catch (SQLException ex) {
            while (ex != null) {
                ex = ex.getNextException();
            }
        }
        catch (Exception ce) {
            ce.printStackTrace();
        }
    }
    // 对外提供一个方法来获取数据库连接

    public static Connection getConnection(){
        return con;
    }
}

class Manage {

    public void addBookstore() throws SQLException {//添加信息
        Connection conn =Sever.getConnection();
        Statement stmt = (Statement) conn.createStatement();

        System.out.println("请输入添加信息：");
        Scanner scc = new Scanner(System.in);
        BookManage x = new BookManage();
        System.out.println("输入编号：");
        String r=scc.next();
        x.setID(r);

        String  sqla= "select * from books where ID ='"+r+"'";
        stmt.execute(sqla);
        if(sqla==null){
            System.out.println("该图书存在");
            stmt.close();
        }
        else {
            System.out.println("输入图书名称：");
            x.setBname(scc.next());
            System.out.println("输入作者：");
            x.setAuthor(scc.next());
            System.out.println("输入出版社：");
            x.setPublishedHouse(scc.next());
            System.out.println("输入图书类型：");
            x.setType(scc.next());
            System.out.println("输入存放位置：");
            x.setPlace(scc.next());
            System.out.println("输入出版日期：");
            x.setDate(scc.next());
            // System.out.println("输入书籍状态：");
            // x.setBookStatus(scc.next());
            System.out.println();
            try {

                // Statement stmt = (Statement) conn.createStatement();
                String sql = "insert into books values('" + x.getID() + "','" + x.getBname() + "','" + x.getAuthor() + "','" + x.getPublishedHouse() + "','" + x.getType() + "','" + x.getPlace() + "','" + x.getDate() + "','" + x.getBookStatus() + " ',' ',' ',' ',' ');";
                stmt.execute(sql);
                //  参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateBookstore() throws SQLException {//修改信息
        Connection con =Sever.getConnection();
//        String driver = "com.mysql.jdbc.Driver";
//        //URL指向要访问的数据库名mydata
//        String url = "jdbc:mysql://localhost:3306/bookmanage?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&allowMultiQueries=true";
//        //MySQL配置时的用户名
//        String user = "root";
//        //MySQL配置时的密码
//        String password = "123456";
        System.out.println("输入要修改的图书编号：");
        BookManage x = new BookManage();
        Scanner sc = new Scanner(System.in);
        String r = sc.next();
        Statement stmt = (Statement) con.createStatement();
        String  sqla= "select * from books where ID ='"+r+"'";
        stmt.execute(sqla);
       // org.apache.commons.collections4.CollectionUtils.isEmpty(Collections.singleton(sqla));
        if(sqla==null){
            System.out.println("该图书不存在");
            stmt.close();}
        else {
            try {
                System.out.println("输入图书名称：");
                x.setBname(sc.next());
                System.out.println("输入作者：");
                x.setAuthor(sc.next());
                System.out.println("输入出版社：");
                x.setPublishedHouse(sc.next());
                System.out.println("输入图书类型：");
                x.setType(sc.next());
                System.out.println("输入存放位置：");
                x.setPlace(sc.next());
                System.out.println("输入出版日期：");
                x.setDate(sc.next());
                //  System.out.println("输入书籍状态：");
                // x.setBookStatus(sc.next());
                //  参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作
                String sql = "update books set bname='" + x.getBname() + "',author='" + x.getAuthor() + "',publishedHouse='" + x.getPublishedHouse() + "',typel='" + x.getType() + "',place='" + x.getPlace() + "',datel='" + x.getDate() + "',bookStatus='" + x.getBookStatus() + "',borrowPeopleName='" + x.getBorrowPeopleName() + "',numberl='" + x.getNumber() + "',borrowDate='" + x.getBorrowDate() + "',backTime='" + x.getBackTime() + "' where ID ='" + r + "'";
                // ResultSet rs=stmt.executeQuery(sql);
                System.out.println(" 修改成功");
                stmt.execute(sql);
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteBookstore() throws SQLException, ParseException {//删除信息
        Connection conn =Sever.getConnection();
        System.out.println("输入过期时间：");
        Scanner sc = new Scanner(System.in);
       String r = sc.next();
       // BookManage bookManage=new BookManage();
       // String time=bookManage.getDate();
       // String timeMax=time+365;

        Statement stmt = (Statement) conn.createStatement();
        String  sqla= "select * from books where datel <'"+r+"'";
        stmt.execute(sqla);
        if(sqla==null){
            System.out.println("该图书不存在");
            stmt.close();}
        else {
            try {
                //Statement stmt = (Statement) conn.createStatement();
                String sql = "DELETE FROM books WHERE datel <\"" + r + "\"";// 参数用?表示，相当于占位符
                stmt.executeUpdate(sql);
                System.out.println("删除成功");
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void seleteBookstore() throws SQLException {//查询基本信息
        Connection conn =Sever.getConnection();
        System.out.println("输入要查询的图书名称：");
        Scanner sc = new Scanner(System.in);
        String r = sc.next();
        Statement stmt = (Statement) conn.createStatement();
        try {
            String sql = "select * from books where bname='"+r+"'";
            ResultSet rs=stmt.executeQuery(sql);
            BookManage bookManage=null;
            while (rs.next()){
                BookManage bookManage1=new BookManage(rs.getString("ID"),rs.getString("bname"),rs.getString("author"),
                        rs.getString("publishedHouse"),rs.getString("typel"),rs.getString("place"),
                        rs.getString("datel"),rs.getString("bookStatus"),rs.getString("borrowPeopleName"),
                        rs.getString("numberl"),rs.getString("borrowDate"), rs.getString("backTime"));
                bookManage1.printBookmassage();
            }
            rs.close();
            stmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void borrowBookStudent1()throws SQLException{
        Connection conn =Sever.getConnection();
        System.out.println("请输入用户名和ID：");
        Scanner sc = new Scanner(System.in);
        String r1 = sc.next();
        String r2 = sc.next();
        Statement stmt = (Statement) conn.createStatement();
        String sql = "select * from student where sname='"+r1+"' and snumber='"+r2+"'";
        ResultSet rs=null;
        rs=stmt.executeQuery(sql);
        if(sql==null) {
            System.out.println("该用户不存在");
        }
        else {
            {
                System.out.println("请输入要借阅的书籍的ID：");
                String r4 = sc.next();
                sql = "select * from books where ID='"+r4+"'";
                ArrayList<BookManage> arrayList=new ArrayList<>();
                rs=stmt.executeQuery(sql);
                BookManage x=null;
                while (rs.next()){
                    x=new BookManage(rs.getString("ID"),rs.getString("bname"),rs.getString("author"),
                            rs.getString("publishedHouse"),rs.getString("typel"),rs.getString("place"),
                            rs.getString("datel"),rs.getString("bookStatus"),rs.getString("borrowPeopleName"),
                            rs.getString("numberl"),rs.getString("borrowDate"),rs.getString("backTime"));
                    arrayList.add(x);
                    //rs.close();
                }
                try {
                    if(!x.getType().equals("borrow")) {
                       // System.out.println("输入借阅日期：");
                        x.setBorrowDate(x.getBorrowDate1());
                        x.setBookStatus("borrow");
                        x.setBorrowPeopleName(r1);
                        x.setNumber(r2);
                        x.setBackTime("");
                        //  参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作

                        sql = "update books set bookStatus='" + x.getBookStatus() + "',borrowPeopleName='" + r1 + "',numberl='" + r2 + "',borrowDate='" + x.getBorrowDate() + "',backTime='" + x.getBackTime() + "'" + " where ID ='" + r4 + "'";
                        stmt.execute(sql);
                        BookManage y = new BookManage(x.getID(), x.getBname(), x.getAuthor(), x.getPublishedHouse(), x.getType(), x.getDate(), r1, r2, x.getBorrowDate(), x.getBackTime());
                        stmt.close();
//
                        try {
                            Statement stat = (Statement) conn.createStatement();
                            String sqlc = "insert into studentborrowmessage values('" + y.getID() + "','" + y.getBname() + "','" + y.getAuthor() + "','" + y.getPublishedHouse() + "','" + y.getType() + "','" + y.getDate() + "','" + y.getBorrowPeopleName() + "','" + y.getNumber() + "','" + y.getBorrowDate() + "','" + y.getBackTime() + "');";
                            stat.executeUpdate(sqlc);
                            //  参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作
                            stat.close();
                            System.out.println("借阅成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("不能借阅，被借阅");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void borrowBookTeacher2()throws SQLException{
        Connection conn =Sever.getConnection();
        System.out.println("请输入用户名和ID：");
        Scanner sc = new Scanner(System.in);
        String r1 = sc.next();
        String r2 = sc.next();
        Statement stmt = (Statement) conn.createStatement();
        String sql = "select * from teacher where tname='"+r1+"' and tnumber='"+r2+"'";
        ResultSet rs=stmt.executeQuery(sql);
        if(sql==null) {
            System.out.println("该用户不存在");
        }
        else {
            {
                System.out.println("请输入要借阅的书籍的ID：");
                String r4 = sc.next();
                sql = "select * from books where ID='"+r4+"'";
                ArrayList<BookManage> arrayList=new ArrayList<>();
                rs=stmt.executeQuery(sql);
                BookManage x=null;
                while (rs.next()){
                    x=new BookManage(rs.getString("ID"),rs.getString("bname"),rs.getString("author"),
                            rs.getString("publishedHouse"),rs.getString("typel"),rs.getString("place"),
                            rs.getString("datel"),rs.getString("bookStatus"),rs.getString("borrowPeopleName"),
                            rs.getString("numberl"),rs.getString("borrowDate"),rs.getString("backTime"));
                    arrayList.add(x);
                }

                try {
                    if(!x.getType().equals("borrow")) {
                      //  System.out.println("输入借阅日期：");
                        x.setBorrowDate(x.getBorrowDate1());
                        x.setBookStatus("borrow");
                        x.setBorrowPeopleName(r1);
                        x.setNumber(r2);
                        x.setBackTime("");
                        //  参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作

                        sql = "update books set bookStatus='" + x.getBookStatus() + "',borrowPeopleName='" + r1 + "',numberl='" + r2 + "',borrowDate='" + x.getBorrowDate() + "',backTime='" + x.getBackTime() + "'" + " where ID ='" + r4 + "'";
                        stmt.execute(sql);
                        BookManage y = new BookManage(x.getID(),x.getBname(),x.getAuthor(),x.getPublishedHouse(),x.getType(),x.getDate(),r1,r2,x.getBorrowDate(),x.getBackTime());
                        stmt.close();
//
                        try {
                            Statement stat = (Statement) conn.createStatement();
                            String sqlc = "insert into teacherborrowmessage values('"+y.getID() +"','"+y.getBname() +"','"+y.getAuthor() +"','"+y.getPublishedHouse() +"','"+y.getType() +"','"+y.getDate() +"','"+y.getBorrowPeopleName()+"','"+y.getNumber()+"','"+y.getBorrowDate() +"','"+y.getBackTime() +"');";
                            stat.executeUpdate(sqlc);
                            //  参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作
                            stat.close();
                            System.out.println("借阅成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("不能借阅，被借阅");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void borrowBookMassage0() throws SQLException{//查询本书在书库目前的借阅情况
        Connection conn = Sever.getConnection();
        System.out.println("输入要查询的图书名称：");
        Scanner sc = new Scanner(System.in);
        String r1 = sc.next();
        Statement stmt = (Statement) conn.createStatement();
        try {
            String sql = "select * from books where bname='"+r1+"' and borrowPeopleName=''";
            ResultSet rs = stmt.executeQuery(sql);
            BookManage bookManage = null;
            while (rs.next()) {
                BookManage bookManage1=new BookManage(rs.getString("ID"),rs.getString("bname"),rs.getString("author"),
                        rs.getString("publishedHouse"),rs.getString("typel"),rs.getString("place"),
                        rs.getString("datel"),rs.getString("bookStatus"),rs.getString("borrowPeopleName"),
                        rs.getString("numberl"),rs.getString("borrowDate"), rs.getString("backTime"));
                bookManage1.printBookmassage();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrowBookMassage1() throws SQLException{//学生查询本人的借阅情况
        Connection conn = Sever.getConnection();
        Statement stmt = (Statement) conn.createStatement();
        System.out.println("请输入用户名和ID：");
        Scanner sc = new Scanner(System.in);
        String r1 = sc.next();
        String r2 = sc.next();
        try {
            // String sql = "select * from studentborrowmessage ";
            String sql = "select * from studentborrowmessage where borrowPeopleName='"+r1+"' and numberl='"+r2+"'";
            ResultSet rs = stmt.executeQuery(sql);
            BookManage bookManage = null;
            while (rs.next()) {
                BookManage bookManage1 = new BookManage(rs.getString("ID"), rs.getString("bname"), rs.getString("author"),
                        rs.getString("publishedHouse"), rs.getString("typel"), rs.getString("datel"),rs.getString("borrowPeopleName"),rs.getString("numberl"),
                        rs.getString("borrowDate"), rs.getString("backTime"));
                bookManage1.printBookBorrowmassage();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrowBookMassage2() throws SQLException{//老师查询本人的借阅情况
        Connection conn = Sever.getConnection();
        Statement stmt = (Statement) conn.createStatement();
        System.out.println("请输入用户名和ID：");
        Scanner sc = new Scanner(System.in);
        String r1 = sc.next();
        String r2 = sc.next();
        try {
            // String sql = "select * from teacherborrowmessage ";
            String sql = "select * from teacherborrowmessage where borrowPeopleName='"+r1+"' and numberl='"+r2+"'";
            ResultSet rs = stmt.executeQuery(sql);
            BookManage bookManage = null;
            while (rs.next()) {
                BookManage bookManage1 = new BookManage(rs.getString("ID"), rs.getString("bname"), rs.getString("author"),
                        rs.getString("publishedHouse"), rs.getString("typel"), rs.getString("datel"),rs.getString("borrowPeopleName"),rs.getString("numberl"),
                        rs.getString("borrowDate"), rs.getString("backTime"));
                bookManage1.printBookBorrowmassage();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backBookStudent1()throws SQLException{
        Connection conn =Sever.getConnection();
        System.out.println("请输入用户名和ID：");
        Scanner sc = new Scanner(System.in);
        String r1 = sc.next();
        String r2 = sc.next();
        Statement stmt = (Statement) conn.createStatement();
        String sql = "select * from student where sname='"+r1+"' and snumber='"+r2+"'";
        stmt.executeQuery(sql);
        if(sql==null) {
            System.out.println("该用户不存在");
        }
        else {
            System.out.println("请输入要归还的书籍：");
            String r3 = sc.next();
            String sqla="select * from books where bname='"+r3+"'";
            stmt.executeQuery(sqla);
            if(sqla==null) {
                System.out.println("该图书不存在");
            }
            else {
                BookManage x = new BookManage();
                System.out.println("请输入要归还的书籍的ID：");
                String r4 = sc.next();
                try {
                    Statement stat = (Statement) conn.createStatement();
                    sql= "select * from studentborrowmessage where ID='"+r4+"'" ;
                    ResultSet rs=null;
                    rs=stmt.executeQuery(sql);
                    BookManage y=null;
                  while (rs.next()) {
                      // BookManage bookManage=null;
                      //  参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作
                      y = new BookManage(rs.getString("ID"), rs.getString("bname"), rs.getString("author"),
                              rs.getString("publishedHouse"), rs.getString("typel"), rs.getString("datel"), rs.getString("borrowPeopleName"), rs.getString("numberl"),
                              rs.getString("borrowDate"), rs.getString("backTime"));
                  }    //rs.close();
                        Statement stab = (Statement) conn.createStatement();
                        String sqlx = "insert into studentbackmessage values('"+y.getID() +"','"+y.getBname() +"','"+y.getAuthor() +"','"+y.getPublishedHouse() +"','"+y.getType() +"','"+y.getDate() +"','"+y.getBorrowPeopleName()+"','"+y.getNumber()+"','"+y.getBorrowDate() +"','"+y.getBackTime() +"');";
                        stat.executeUpdate(sqlx);
                        stab.close();


                }
                catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    // System.out.println("输入归还日期：");
                    x.setBackTime(x.getBackTime1());
                    x.setBookStatus("");
                    x.setBorrowPeopleName("");
                    x.setNumber("");
                    x.setBorrowDate("");

                    sql = "update books set bookStatus='" + x.getBookStatus() + "',borrowPeopleName='" + x.getBorrowPeopleName() + "',numberl='" + x.getNumber() + "',borrowDate='" + x.getBorrowDate() + "',backTime='" + x.getBackTime() + "'" + " where ID ='" + r4 + "'";
                    stmt.execute(sql);

                    //  参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作

                    //  BookManage y = new BookManage(x.getID(), x.getBname(), x.getAuthor(), x.getPublishedHouse(), x.getType(), x.getDate(),x.getBorrowPeopleName(),x.getNumber(), x.getBorrowDate(), x.getBackTime());
                    stmt.close();
                    try {
                        Statement stme = (Statement) conn.createStatement();
                        String sqld="DELETE FROM studentborrowmessage WHERE ID='"+r4+"'";// 参数用?表示，相当于占位符
                        stme.executeUpdate(sqld);
                        stme.close();
                        System.out.println("归还成功");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void backBookTeacher2()throws SQLException{
        Connection conn =Sever.getConnection();
        System.out.println("请输入用户名和ID：");
        Scanner sc = new Scanner(System.in);
        String r1 = sc.next();
        String r2 = sc.next();
        Statement stmt = (Statement) conn.createStatement();
        String sql = "select * from teacher where tname='"+r1+"' and tnumber='"+r2+"'";
        // ResultSet rs=stmt.executeQuery(sql);
        if(sql.length()==0||sql==null) {
            System.out.println("该用户不存在");
        }
        else {
            System.out.println("请输入要归还的书籍：");
            String r3 = sc.next();
            String sqla="select * from books where bname='"+r3+"'";
            stmt.executeQuery(sqla);
            if(sqla.length()==0||sql==null) {
                System.out.println("该图书不存在");
            }
            else {
                BookManage x = new BookManage();
                System.out.println("请输入要归还的书籍的ID：");
                String r4 = sc.next();
                try {
                    Statement stat = (Statement) conn.createStatement();
                    sql= "select * from teacherborrowmessage where ID='"+r4+"'" ;
                    ResultSet rs=null;
                    rs=stmt.executeQuery(sql);
                    BookManage y=null;
                    while (rs.next()) {
                         y = new BookManage(rs.getString("ID"), rs.getString("bname"), rs.getString("author"),
                                rs.getString("publishedHouse"), rs.getString("typel"), rs.getString("datel"), rs.getString("borrowPeopleName"), rs.getString("numberl"),
                                rs.getString("borrowDate"), rs.getString("backTime"));
                        Statement stab = (Statement) conn.createStatement();
                        String sqlx = "insert into teacherbackmessage values('" + y.getID() + "','" + y.getBname() + "','" + y.getAuthor() + "','" + y.getPublishedHouse() + "','" + y.getType() + "','" + y.getDate() + "','" + y.getBorrowPeopleName() + "','" + y.getNumber() + "','" + y.getBorrowDate() + "','" + y.getBackTime() + "');";
                        stat.executeUpdate(sqlx);
                        stab.close();
                    }

                        try {
                           // System.out.println("输入归还日期：");
                            x.setBackTime(x.getBackTime1());
                            x.setBookStatus("");
                            x.setBorrowPeopleName("");
                            x.setNumber("");
                            x.setBorrowDate("");

                            sql = "update books set bookStatus='" + x.getBookStatus() + "',borrowPeopleName='" + x.getBorrowPeopleName() + "',numberl='" + x.getNumber() + "',borrowDate='" + x.getBorrowDate() + "',backTime='" + x.getBackTime() + "'" + " where ID ='" + r4 + "'";
                            stmt.execute(sql);

                            //  参数用?表示，相当于占位符，然后在对参数进行赋值。当真正执行时，这些参数会加载在SQL语句中，把SQL语句拼接完整才去执行。这样就会减少对数据库的操作
                            //  BookManage y = new BookManage(x.getID(), x.getBname(), x.getAuthor(), x.getPublishedHouse(), x.getType(), x.getDate(),x.getBorrowPeopleName(),x.getNumber(), x.getBorrowDate(), x.getBackTime());
                            stmt.close();

                            try {
                                Statement stme = (Statement) conn.createStatement();
                                String sqld="DELETE FROM teacherborrowmessage WHERE ID='"+r4+"'";// 参数用?表示，相当于占位符
                                stme.executeUpdate(sqld);
                                stme.close();
                                System.out.println("归还成功");
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }

                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

        }


    }

    public void backBookMassage1() throws SQLException{//学生查询本人的借阅情况
        Connection conn = Sever.getConnection();
        Statement stmt = (Statement) conn.createStatement();
        System.out.println("请输入用户名和ID：");
        Scanner sc = new Scanner(System.in);
        String r1 = sc.next();
        String r2 = sc.next();
        try {
            //String sql = "select * from studentbackmessage ";
            String sql = "select * from studentBackMessage where borrowPeopleName='"+r1+"' and numberl='"+r2+"'";
            ResultSet rs =null;
            rs=stmt.executeQuery(sql);
            //ArrayList<BookManage> arrayList = new ArrayList<>();
            BookManage bookManage = null;
            while (rs.next()) {
                BookManage  bookManage1 = new BookManage(rs.getString("ID"), rs.getString("bname"), rs.getString("author"),
                        rs.getString("publishedHouse"), rs.getString("typel"), rs.getString("datel"),rs.getString("borrowPeopleName"),rs.getString("numberl"),
                        rs.getString("borrowDate"), rs.getString("backTime"));
                bookManage1.printBookBackmassage();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backBookMassage2() throws SQLException{//老师查询本人的借阅情况
        Connection conn = Sever.getConnection();
        Statement stmt = (Statement) conn.createStatement();
        System.out.println("请输入用户名和ID：");
        Scanner sc = new Scanner(System.in);
        String r1 = sc.next();
        String r2 = sc.next();
        try {
            String sql = "select * from teacherbackmessage";
            //  String sql = "select * from teacherbackmessage where borrowPeopleName='"+r1+"' and numberl='"+r2+"'";
            ResultSet rs =null;
            rs=stmt.executeQuery(sql);
            //ArrayList<BookManage> arrayList = new ArrayList<>();
            BookManage bookManage1 = null;
            while (rs.next()) {
                bookManage1 = new BookManage(rs.getString("ID"), rs.getString("bname"), rs.getString("author"),
                        rs.getString("publishedHouse"), rs.getString("typel"), rs.getString("datel"),rs.getString("borrowPeopleName"),rs.getString("numberl"),
                        rs.getString("borrowDate"), rs.getString("backTime"));
                bookManage1.printBookBackmassage();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bookSort()throws SQLException{//排序
        Connection conn =Sever.getConnection();
        System.out.println("图书名称排序：");
        try {
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books ORDER BY CONVERT(bname USING GB2312)");
            while (rs.next()){
                BookManage bookManage1=new BookManage(rs.getString("ID"),rs.getString("bname"),rs.getString("author"),
                        rs.getString("publishedHouse"),rs.getString("typel"),rs.getString("place"),
                        rs.getString("datel"),rs.getString("bookStatus"),rs.getString("borrowPeopleName"),
                        rs.getString("numberl"),rs.getString("borrowDate"), rs.getString("backTime"));
                bookManage1.printBookmassage();
            }
            //SELECT * FROM conperson ORDER BY CONVERT(NAME USING GB2312)
            //  String sql="select * from books order by bname(NAME USING GB2312)";
            // ResultSet rs=stmt.executeQuery(sql);
            // stmt.execute(sql);
            stmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}


