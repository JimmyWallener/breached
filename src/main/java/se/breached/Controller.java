package se.breached;

import java.util.List;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.*;
import se.breached.api.ApiConnection;
import se.breached.model.Breached;
import se.breached.validate.ValidateEmail;

public class Controller {
    private final Image compromisedImg = new Image("/logos/breached.png", true);
    private final Image notCompromisedImg = new Image("/logos/battery.png", true);
    @FXML private ImageView imageView;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private Button emailBtn;
    @FXML private Button passwordBtn;
    @FXML private TextFlow textFlow;

    public void initialize() {

        passwordBtn.setOnAction(e -> {
            if(textFlow != null){
                textFlow.getChildren().clear();
                textFlow.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #245e7d, #368daa)");
                imageView.setImage(notCompromisedImg);
            }
            hasPasswordBeenCompromised();
        });
        passwordField.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                if(textFlow != null){
                    textFlow.getChildren().clear();
                    textFlow.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #245e7d, #368daa)");
                    imageView.setImage(notCompromisedImg);
                }
                hasPasswordBeenCompromised();
            }
        });

        emailBtn.setOnAction(e -> {
            if(textFlow != null){
                textFlow.getChildren().clear();
                textFlow.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #245e7d, #368daa)");
                imageView.setImage(notCompromisedImg);
            }
            hasEmailBeenCompromised();
        });
        emailField.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)){
                if(textFlow != null){
                    textFlow.getChildren().clear();
                    textFlow.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #245e7d, #368daa)");
                    imageView.setImage(notCompromisedImg);
                }
                hasEmailBeenCompromised();
            }
        });

        imageView.setOnMouseClicked(e -> {
            textFlow.getChildren().clear();
            textFlow.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #245e7d, #368daa)");
            imageView.setImage(notCompromisedImg);
        });

    }
    public void hasPasswordBeenCompromised(){
        HandleResponse hr = new HandleResponse();
        int number = Integer.parseInt(String.valueOf(hr.numberOfBreaches(passwordField.getText().trim())));
        passwordField.clear();
        Text breached;
        Text description;
        if(number == 0) {
            breached = new Text("PASSWORD HAS NOT BEEN BREACHED!\n\n");
            description = new Text("This password has previously appeared in a data breach and should never be used. If you've ever used it anywhere before, change it!");
            breached.setFont(Font.font("Helvetica", FontWeight.BOLD,20));
            textFlow.setStyle("-fx-background-color: green;");
        } else {
            breached = new Text("PASSWORD HAS BEEN COMPROMISED " + number + " OF TIMES!\n\n");
            description = new Text("This password wasn't found in any of the Pwned Passwords loaded into Have I Been Pwned. That doesn't necessarily mean it's a good password, merely that it's not indexed on this site.");
            breached.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
            imageView.setImage(compromisedImg);
            textFlow.setStyle("-fx-background-color: red;");
        }
        textFlow.setPadding(new Insets(100, 100, 0, 100));
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.getChildren().addAll(breached,description);

    }
    public void hasEmailBeenCompromised(){
        ValidateEmail ve = new ValidateEmail();
        if(ve.validEmail(emailField.getText())){

            String uri = "https://haveibeenpwned.com/api/v3/breachedaccount/";
            String truncate = "?truncateResponse=false";
            String url = String.format("%s%s%s", uri,emailField.getText().trim(), truncate);
            HandleResponse hr = new HandleResponse();
            List<Breached> emailData = hr.breachDataFromEmail(url);
            emailField.clear();

            if(emailData != null) {
                emailData.forEach(data -> {
                    imageView.setImage(compromisedImg);
                    Text text = new Text("Breached website:");
                    text.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
                    textFlow.setLineSpacing(5);
                    textFlow.setPadding(new Insets(30, 80, 30, 80));
                    textFlow.getChildren().add(text);
                    textFlow.getChildren().add(new Text(data.toString()));
                    textFlow.setStyle("-fx-background-color: red;");
                });
            } else {
                String errorMsg = ApiConnection.errorMessage();
                Text text = new Text(errorMsg);
                text.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
                textFlow.setLineSpacing(5);
                textFlow.setPadding(new Insets(30, 80, 30, 80));
                textFlow.getChildren().add(text);
            }
        }
    }

}
