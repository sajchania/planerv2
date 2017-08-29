package com.cognifide.interview.planerv2.gui.mainpage;

import com.cognifide.interview.planerv2.server.SimpleRestServer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ServerGui extends Application {

	
	@Override
	public void start(Stage primaryStage) {

		try {
			BorderPane root = new BorderPane();

			GridPane grid = setGridPane();

			setWelcomeTitle(grid);

			final Text actiontarget = setResponseMessagePane(grid);

			setStartServerButton(grid, actiontarget);
			setStopServerButton(grid, actiontarget);
			
			Scene scene = setScene(grid);
			setPrimaryStage(primaryStage, scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void setStartServerButton(GridPane grid, final Text actiontarget) {
		Button startServerButton = new Button("Start server");
		HBox startServerHBox = new HBox(10);
		startServerHBox.setAlignment(Pos.BOTTOM_RIGHT);
		startServerHBox.getChildren().add(startServerButton);
		grid.add(startServerHBox, 1, 2);

		startServerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Thread startServer = new Thread(new Runnable() {

					@Override
					public void run() {
						SimpleRestServer.start();

					}
				});
				startServer.start();

				actiontarget.setFill(Color.FIREBRICK);
				actiontarget.setText("Server started");
			}
		});
	}
	
	public void setStopServerButton(GridPane grid, final Text actiontarget) {
		Button stopServerButton = new Button("Stop server");
		HBox stopServerHBox = new HBox(10);
		stopServerHBox.setAlignment(Pos.BOTTOM_RIGHT);
		stopServerHBox.getChildren().add(stopServerButton);
		grid.add(stopServerHBox, 1, 3);

		stopServerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Thread startServer = new Thread(new Runnable() {

					@Override
					public void run() {
						SimpleRestServer.stop();
					}
				});
				startServer.start();

				actiontarget.setFill(Color.FIREBRICK);
				actiontarget.setText("Server stopped");
			}
		});
	}

	public Text setResponseMessagePane(GridPane grid) {
		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 5);
		return actiontarget;
	}

	public void setWelcomeTitle(GridPane grid) {
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
	}

	private void setPrimaryStage(Stage primaryStage, Scene scene) {
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private GridPane setGridPane() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		return grid;
	}

	private Scene setScene(GridPane grid) {
		Scene scene = new Scene(grid, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
