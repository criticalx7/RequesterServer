package client;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import client.ui.ActionHandler;
import client.ui.JobListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("client-config.xml");
        ActionHandler handler = context.getBean("actionHandler", ActionHandler.class);
        handler.loadJobs();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client.fxml"));
        Parent root = loader.load();
        JobListController controller = loader.getController();
        controller.setHandler(handler);

        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
