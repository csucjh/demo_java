package net.jcip.examples.ex3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionDispenser
 * <p/>
 * Using ThreadLocal to ensure thread confinement
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ConnectionDispenser {
    static String DB_URL = "jdbc:mysql://localhost/mydatabase";

    private ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {

        /**
         * 在第一get被调用时触发，如果get之前先调用set，则不会再调用get
         * @return
         */
        @Override
        public Connection initialValue() {
            try {
                return DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                throw new RuntimeException("Unable to acquire Connection, e");
            }
        }
    };

    public Connection getConnection() {
//        connectionHolder.set(null);
        return connectionHolder.get();
    }

    public static void main(String[] args) {
        ConnectionDispenser dispenser = new ConnectionDispenser();
        dispenser.getConnection();
    }
}
