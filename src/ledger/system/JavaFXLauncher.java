/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ledger.system;

import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXLauncher extends Application {

    private static boolean isJavaFXStarted = false;

    @Override
    public void start(Stage primaryStage) {
        isJavaFXStarted = true;
    }

    public static void initializeJavaFX() {
        if (!isJavaFXStarted) {
            new Thread(() -> Application.launch(JavaFXLauncher.class)).start();
            while (!isJavaFXStarted) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Error initializing JavaFX: " + e.getMessage());
                }
            }
        }
    }
}