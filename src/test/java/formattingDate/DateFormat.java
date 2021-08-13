package formattingDate;

import javafx.util.Pair;
import org.apache.log4j.PropertyConfigurator;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

public class DateFormat {


    public static Pair<Boolean, String> CheckDateValid(String DateValue)
    {
        boolean returnVal=false;
        String[] permittedFormats= new String[]{"MMM/dd/yyyy HH:mm:ss","dd MM yyyy HH:mm:ss"};
        SimpleDateFormat sdf=new SimpleDateFormat();

        ParsePosition pos=new ParsePosition(0);
        //sdf.setLenient(false);
        String format=null;
        for (int i=0;i<permittedFormats.length;i++){
            sdf.applyPattern(permittedFormats[i]);
            //pos.setIndex(0);
            pos.setErrorIndex(-1);
            sdf.parse(DateValue,pos);
            if(pos.getErrorIndex()==-1)
            {
                returnVal=true;
                format=permittedFormats[i];
                logger.info("Input is in valid format it can be processed further");
                //System.out.println("Input accepted");
                break;
            }
        }
        logger.info("Input is not valid");
        return new Pair<Boolean, String>(returnVal,format);
    }
    public static final Logger logger=Logger.getLogger(String.valueOf(DateFormat.class));

    public static void main(String[] args) throws Exception{
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        Connection_details con_obj=new Connection_details();
        Query query_obj=new Query();
        logger.info("Requesting user to enter date");
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter the date");

        String date_string=sc.nextLine();
        logger.info("User added the input");


        logger.info("Checking if the date is in valid format");
        Pair<Boolean,String> value=CheckDateValid(date_string);
        String FormattedDate=null;
        if (value.getKey())
        {
            String str= value.getValue();
            SimpleDateFormat dateFormat=new SimpleDateFormat(str);
            Date date=dateFormat.parse(date_string);
            SimpleDateFormat formatter=new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
            FormattedDate=formatter.format(date);
            logger.info("Loading data into DataConnectionLoad method");
            //System.out.println("loading data " +FormattedDate);
            query_obj.DataConnectionLoader(FormattedDate);
            //System.out.println(FormattedDate);
            query_obj.dateParse(date,con_obj,FormattedDate);
        }
        else{
            throw new Exception("please enter input in these formats MMM/dd/yyyy HH:mm:ss or dd MM yyyy HH:mm:ss only");
        }
    }

}


