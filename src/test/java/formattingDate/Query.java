package formattingDate;

import org.apache.log4j.PropertyConfigurator;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class Query {
    public static final Logger logger=Logger.getLogger(String.valueOf(Query.class));
    //PropertyConfigurator.configure("log4j.properties");
    public void DataConnectionLoader(String sql) throws SQLException{
        String sq="INSERT into public.\"date_format_table_k\"(input_date)"+ "VALUES("+"\'"+sql+"\'"+")";
        //System.out.println(sq);
        Statement st=null;
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        //Connection conn=null;
        Connection_details con=new Connection_details();
        //conn = con.getConnection();
        if(con.getConnection())
        {
            //Connection conn = con.getconnection();
            //System.out.println("connection successfully created");
            logger.info("connection created successfully");
            try{
                //System.out.println("success11");

                //Connection conn=new Connection();
                st = con.connect.createStatement();
                //st=con.connect.createStatement();
               // System.out.println("success");
                //System.out.println("going to execute update");
                logger.info("Going to execute update");
                st.executeUpdate(sq);
                //System.out.println("query updated");
                con.closeConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            logger.info("Inserted the Input into table in input_date column");
        }
    }
    public void dateParse( Date date, Connection_details con_obj, String formattedDate) throws SQLException {
        List<String> lst=new ArrayList<>();
        lst.add("dd-MM-yyyy");
        lst.add("dd-MM-yyyy'T'HH:mm:ss*SSSZZZZ");
        lst.add("yyyy MMM dd HH:mm:ss.SSS zzz");
        lst.add("MMM dd HH:mm:ss ZZZZ yyyy");
        lst.add("dd/MMM/yyyy:HH:mm:ss ZZZZ");
        lst.add("MMM dd, yyyy hh:mm:ss a");
        lst.add("MMM dd yyyy HH:mm:ss");
        lst.add("MMM dd HH:mm:ss yyyy");
        lst.add("MMM dd HH:mm:ss ZZZZ");
        lst.add("MMM dd HH:mm:ss");
        lst.add("dd-MM-yyyy'T'HH:mm:ssZZZZ");
        lst.add("dd-MM-yyyy'T'HH:mm:ss.SSS'Z'");
        lst.add("dd-MM-yyyy HH:mm:ss ZZZZ");
        lst.add("dd-MM-yyyy HH:mm:ssZZZZ");
        lst.add("dd-MM-yyyy HH:mm:ss,SSS");
        lst.add("yyyy/MM/dd*HH:mm:ss");
        lst.add("yyyy MMM dd HH:mm:ss.SSS*zzz");
        lst.add("yyyy MMM dd HH:mm:ss.SSS");
        lst.add("dd-MM-yyyy HH:mm:ss,SSSZZZZ");
        lst.add("dd-MM-yyyy HH:mm:ss.SSS");
        lst.add("dd-MM-yyyy HH:mm:ss.SSSZZZZ");
        //lst.add("dd-MM-yyyy'T'HH:mm:ss.SSS");
        lst.add("dd-MM-yyyy'T'HH:mm:ss");
        lst.add("dd-MM-yyyy'T'HH:mm:ss'Z'");
        lst.add("dd-MM-yyyy'T'HH:mm:ss.SSS");
        lst.add("dd-MM-yyyy'T'HH:mm:ss");
        lst.add("dd-MM-yyyy*HH:mm:ss:SSS");
        lst.add("dd-MM-yyyy*HH:mm:ss");
        lst.add("yy-MM-dd HH:mm:ss,SSS ZZZ");
        lst.add("yy-MM-dd HH:mm:ss,SSS");
        lst.add("yy-MM-dd HH:mm:ss");
        lst.add("yy/MM/dd HH:mm:ss");
        lst.add("yyMMdd HH:mm:ss");
        lst.add("yyyyMMdd HH:mm:ss.SSS");
        lst.add("MM/dd/yy*HH:mm:ss");
        lst.add("MM/dd/yyyy*HH:mm:ss");
        lst.add("MM/dd/yyyy*HH:mm:ss*SSS");
        lst.add("MM/dd/yy HH:mm:ss ZZZZ");
        lst.add("MM/dd/yyyy HH:mm:ss ZZZZ");
        lst.add("HH:mm:ss");
        lst.add("HH:mm:ss.SSS");
        lst.add("HH:mm:ss,SSS");
        lst.add("dd/MMM HH:mm:ss,SSS");
        lst.add("dd/MMM/yyyy:HH:mm:ss");
        lst.add("dd/MMM/yyyy HH:mm:ss");
        lst.add("dd-MMM-yyyy HH:mm:ss");
        lst.add("dd-MMM-yyyy HH:mm:ss.SSS");
        lst.add("dd MMM yyyy HH:mm:ss");
        lst.add("MMdd_HH:mm:ss");
        lst.add("MMdd_HH:mm:ss.SSS");

        //System.out.println(lst);
        //creating a map between formats and parsed string
        Map<String,String> parse_map=new HashMap<>();
        for (String str:lst){
            SimpleDateFormat formater=new SimpleDateFormat(str);
            String dat=formater.format(date);
            //System.out.println(dat);
            parse_map.put(str,dat);

        }
        //System.out.println(parse_map);
        updateTable(parse_map,formattedDate);
    }

    private void updateTable(Map<String, String> parse_map, String formattedDate) throws SQLException {
        String formatted_string=null;
        String value=null;
        Connection_details conn=new Connection_details();
        if(conn.getConnection())
        {
            //System.out.println("running update table");
              logger.info("Update table is running");
                Statement st = conn.connect.createStatement();
                for (String str : parse_map.keySet()) {
                    //System.out.println(str);
                    //formatted_string = str;
                    value=parse_map.get(str);
                    //System.out.println(value);
                    //value = parse_map.get(formatted_string);
                    //System.out.println(str+" "+value+" "+formattedDate);
                    String query ="UPDATE public.\"date_format_table_k\" set "+"\"" + str+"\"" +"="+"\'"+value+"\'"+" where input_date = "+"\'"+formattedDate+"\'";
                    //System.out.println(query);
                    st.executeUpdate(query);
                }

        conn.closeConnection();

        }
        logger.info("Table is updated successfully");
        //System.out.println("table updated");
    }

}
//01 01 1987 12:34:54