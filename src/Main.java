
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(Main.class.getResource("views/inputMode.fxml"));
        primaryStage.setTitle("Моделирование по входным параметрам");
        primaryStage.getIcons().add(new Image("views/image/application.png"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(1250);
        primaryStage.setHeight(1000);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
