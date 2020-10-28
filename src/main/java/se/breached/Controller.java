package se.breached;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
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
            /* TODO: Make it possible to clear textFlow for another search. Right now, one search locks textFlow
               TODO: Handle if value is Null
               TODO: Create different output results depending if N ≥ 0
             */
            HandleResponse hr = new HandleResponse();
            AtomicInteger number = hr.numberOfBreaches(passwordField.getText());
            Text breached = new Text("Password has been pwned " + number + " times");
            breached.setFont(Font.font("Helvetica", FontWeight.BOLD,20));
            breached.setFill(Color.RED);

            textFlow.getChildren().add(breached);
        });

        emailBtn.setOnAction(e -> {
            // TODO: Make it possible to clear textFlow for another search. Right now, one search locks textFlow
            ValidateEmail ve = new ValidateEmail();
            if(ve.validEmail(emailField.getText())){

                String url = String.format("%s%s%s",uri,emailField.getText(),truncate);
                HandleResponse hr = new HandleResponse();
                List<Breached> emailData = hr.breachDataFromEmail(url);
                emailData.forEach(data -> {
                    imageView.setImage(image1);
                    Text text = new Text("Breached website:");
                    text.setFont(Font.font("Helvetica",FontWeight.BOLD,30));
                    textFlow.setLineSpacing(5);
                    textFlow.setPadding(new Insets(30, 80, 30, 80));
                    textFlow.getChildren().add(text);
                    textFlow.getChildren().add(new Text(data.toString()));
                    textFlow.setStyle("-fx-background-color: red;");
                });
            }
        });
    }

}
