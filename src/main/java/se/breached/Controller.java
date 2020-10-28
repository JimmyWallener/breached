package se.breached;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import se.breached.model.Breached;
import se.breached.validate.ValidateEmail;

public class Controller {
    // API has {Account} - Email/Passwords and {Parameters} and different {Service}. Need to work out logic for that
    private final String uri = "https://haveibeenpwned.com/api/v3/breachedaccount/";
    private final String truncate = "?truncateResponse=false";
    Image image1 = new Image("/logos/breached.png", true);
    @FXML private ImageView imageView;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private Button emailBtn;
    @FXML private Button passwordBtn;
    @FXML private TextFlow textFlow;

    public void initialize() {

        passwordBtn.setOnAction(e -> {
            // TODO: Display text in UI -> Perhaps change color of background depending on breach or not
            // TODO: Handle if value is Null
            HandleResponse hr = new HandleResponse();

            AtomicInteger number = hr.numberOfBreaches(passwordField.getText());
            System.out.println(number);
        });

        emailBtn.setOnAction(e -> {
            // TODO: Attach all values to fields for display -> Also here change color?
            ValidateEmail ve = new ValidateEmail();
            if(ve.validEmail(emailField.getText())){
                String url = String.format("%s%s%s",uri,emailField.getText(),truncate);
                HandleResponse hr = new HandleResponse();
                List<Breached> emailData = hr.breachDataFromEmail(url);
                emailData.forEach(data -> {
                    imageView.setImage(image1);
                    textFlow.setTabSize(2);
                    textFlow.setLineSpacing(2);
                    textFlow.getChildren().add(new Text(data.toString()));
                    textFlow.setStyle("-fx-background-color: red;");
                });
            }
        });
    }

}
