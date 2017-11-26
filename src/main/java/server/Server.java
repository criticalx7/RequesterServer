package server;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("server-config.xml");
        System.out.println("Server established, waiting for request...");
    }
}
