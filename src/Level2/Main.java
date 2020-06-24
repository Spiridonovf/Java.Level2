package Level2;


import Level2.Lesson4.ChatForm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(ChatForm.class.getResource("chat_form.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("ДЗ Урок 4");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
