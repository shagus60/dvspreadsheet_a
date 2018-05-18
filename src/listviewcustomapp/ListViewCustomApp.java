/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listviewcustomapp;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/* Edit table header 
TableColumn col = new TableColumn("");
TextField colHeaderTextField = new TextField("Pink Elephants");
col.setGraphic(colHeaderTextField);
 */
public class ListViewCustomApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label labelDVSpreadsheet = new Label("DVSpreadsheet");
        labelDVSpreadsheet.setStyle(
                "-fx-font-size: 16px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #333333;"
                + "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5),0,0,0,1 );"
        );
        HBox top = new HBox();
        top.getChildren().add(labelDVSpreadsheet);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(8, 8, 8, 8));

        VBox vb = new VBox(5);
        vb.getChildren().add(top);

        vb.setStyle("-fx-background-color: lightgrey");
        ObservableList<DvColumn> items = FXCollections.observableArrayList();

        ListView<DvColumn> spreadsheet = new ListView<DvColumn>();

        spreadsheet.setItems(items);
        spreadsheet.setOrientation(Orientation.HORIZONTAL);

        VBox.setVgrow(spreadsheet, Priority.ALWAYS);

        Scene scene = new Scene(vb, 1400, 650);

        vb.getChildren().add(spreadsheet);

        HBox hb = new HBox(10);
        hb.setPadding(new Insets(8, 8, 8, 8));
        hb.setAlignment(Pos.CENTER);
        Button addColumn = new Button("Add column");
        hb.getChildren().add(addColumn);

        vb.getChildren().add(hb);

        addColumn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                items.add(new DvColumn());

            }
        });

        final ContextMenu contextMenu = new ContextMenu();
        // final MenuItem addMenuItem = new MenuItem("Add...");
        final MenuItem deleteSelectedMenuItem = new MenuItem("Delete selected Column");
        contextMenu.getItems().addAll(deleteSelectedMenuItem);
        deleteSelectedMenuItem.disableProperty().bind(Bindings.isEmpty(spreadsheet.getSelectionModel().getSelectedItems()));
        deleteSelectedMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //final List<Person> selectedPeople = new ArrayList<>(table.getSelectionModel().getSelectedItems());
                items.remove(spreadsheet.getSelectionModel().getSelectedItem());
                spreadsheet.getSelectionModel().select(-1);

            }
        });

        spreadsheet.setContextMenu(contextMenu);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setTitle("Datavyu Spreadsheet");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

}
