package com.example.csc325_firebase_webview_auth.view;


import com.example.csc325_firebase_webview_auth.model.FirestoreContext;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Bucket;
import com.google.firebase.auth.FirebaseAuth;
import java.io.IOException;

import com.google.firebase.cloud.StorageClient;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    public static Scene scene;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize Firebase Firestore and Auth
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();

            StorageClient storageClient = StorageClient.getInstance();
            //Bucket bucket = storageClient.bucket();

        //********** LOADS SPLASH SCREEN PAGE
        scene = new Scene(loadFXML("/files/Splash.fxml"));
        primaryStage.setScene(scene);
        primaryStage.show();

        //FADES TO WHITE THEN LOADS THE LOGIN FILE
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), scene.getRoot()); // Apply to the whole scene
        fadeTransition.setFromValue(1.0); // Start fully visible
        fadeTransition.setToValue(0.0); // End completely transparent
        fadeTransition.setOnFinished(event -> {
            try {
                setRoot("/files/LogIn.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fadeTransition.play();
    }


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
