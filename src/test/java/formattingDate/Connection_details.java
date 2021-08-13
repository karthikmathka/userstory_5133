package formattingDate;

import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Connection_details {

    public static final Logger logger=Logger.getLogger(String.valueOf(Connection_details.class));

    Connection connect = null;
    //String connect;
    public boolean getConnection() {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        boolean flag = false;
        try {
            connect = DriverManager.getConnection("jdbc:postgresql://w3.training5.modak.com:5432/training_2021",
                    "mt3090",
                    "mt3090@m07y21");
            if (connect != null) {
                logger.info("Connection is created");
                //System.out.println("successfully established connection");
                flag = true;
            } else {
                flag = false;
            }
            //connect.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
        
    }
    public void closeConnection(){
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        try{
            connect.close();
            logger.info("Closing connection.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    public Connection getConnection() {
//        //Connection connect = null;
//        return connect;
//    }

//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }

//    public Statement createStatement() {
//    }
}
