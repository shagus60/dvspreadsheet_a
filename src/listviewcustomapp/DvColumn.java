/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listviewcustomapp;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;
//https://stackoverflow.com/questions/41004084/dynamically-updating-a-listview-from-a-textfield-javafx


public class DvColumn extends VBox {

    int counter = 0;

    int width = 300;

    private final ObservableList<DvCell> dvcellz
            = FXCollections.observableArrayList();

    TableView<DvCell> tableView; // = new TableView<DvCell>();

    public DvColumn() {

        setSpacing(5);
        setPrefWidth(width);

        TextField tfHeader = new TextField();

        tfHeader.setPrefWidth(width);
        // tfHeader.setMaxWidth(width);
        tfHeader.setPromptText("Enter column name");
        tfHeader.setAlignment(Pos.CENTER);
        getChildren().add(tfHeader);

        // TableView tablView = new TableView();
        getTableView();
        tableView.setPlaceholder(new Label("Click Add cell button to add a cell."));
        setVgrow(tableView, Priority.ALWAYS);

        getChildren().add(tableView);
        Button btn = new Button("Add cell");
        //  Label btn = new Label();
        //btn.setGraphic(new Label("+"));
        //btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        HBox hbcreateNewCell = new HBox();
        hbcreateNewCell.setPadding(new Insets(8, 8, 8, 8));
        hbcreateNewCell.getChildren().add(btn);

        getChildren().add(hbcreateNewCell);

        btn.setOnAction(
                new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event
            ) {
                // data.add(new DvColumn.DvCell("onset", "offset", "coding"));

                System.out.println("Adding .....");

                //   System.out.println("First item " + dvcellz.get(0));
                dvcellz.add(new DvCell("00 00 000", "00 00 000", "(Some code)"));

            }
        }
        );

        hbcreateNewCell.setAlignment(Pos.CENTER);

        setSpacing(5);

        /*
        tfHeader.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("New text: " + newValue);
                listView.setUserData(newValue);
            }
        });
         */
    }

    // public StringProperty defectLongDescriptionProperty() {
    //     return defectLongDescription;
    // }
    ////  public void setDefectLongDescription(String def) {
    //   defectLongDescription.set(def);
    // }
    /*
    public ContextMenu contextMenuForTableView() {
        ContextMenu contextMenu = new ContextMenu();

        // final MenuItem addMenuItem = new MenuItem("Add...");
        final MenuItem deleteSelectedMenuItem = new MenuItem("Delete selected cell");

        contextMenu.getItems()
                .addAll(deleteSelectedMenuItem);
        //  deleteSelectedMenuItem.disableProperty()
        //          .bind(Bindings.isEmpty(tv.getSelectionModel().getSelectedItems()));
        deleteSelectedMenuItem.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                //final List<Person> selectedPeople = new ArrayList<>(table.getSelectionModel().getSelectedItems());
                tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());

            }
        }
        );

        tableView.setContextMenu(contextMenu);

        contextMenu.getItems().addAll(deleteSelectedMenuItem);

        return contextMenu;
    }
     */
    public void getTableView() {
        tableView = new TableView();

        final TableColumn onSetCol = new TableColumn();

        onSetCol.setSortable(false);

        onSetCol.setText("Onset");
        onSetCol.setMinWidth(70);

        //  codeCol.setMinWidth(150);
        //  codeCol.setMaxWidth(150);
        onSetCol.setCellValueFactory(new PropertyValueFactory("onSet"));
        // codeCol.setCellFactory(ComboBoxTableCell.forTableColumn(defectList));
        onSetCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // codeCol.setCellFactory(cellFactory);
        onSetCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<DvCell, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<DvCell, String> t) {
                ((DvCell) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setOnSet(t.getNewValue());

            }
        });

        TableColumn offSetCol = new TableColumn();
        offSetCol.setSortable(false);
        // offSetCol.setMinWidth(160);
        // offSetCol.setMaxWidth(160);

        offSetCol.setMinWidth(70);

        offSetCol.setText("OffSet");
        offSetCol.setCellValueFactory(new PropertyValueFactory("offSet"));

        offSetCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // locationCol.setCellFactory(cellFactory);

        offSetCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<DvCell, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<DvCell, String> t) {
                ((DvCell) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setOffSet(t.getNewValue());
            }
        });

        TableColumn codeCol = new TableColumn();
        codeCol.setSortable(false);
        // codeCol.setMinWidth(160);
        // codeCol.setMaxWidth(160);
        codeCol.setMinWidth(100);
        codeCol.setText("Code");
        codeCol.setCellValueFactory(new PropertyValueFactory("code"));

        codeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        // locationCol.setCellFactory(cellFactory);

        codeCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<DvCell, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<DvCell, String> t) {
                ((DvCell) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setCode(t.getNewValue());
            }
        });

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        tableView.setEditable(true);

        tableView.getColumns().addAll(onSetCol, offSetCol, codeCol);
        // tableView.setMaxHeight(218);

        tableView.setItems(dvcellz);

        //tableView.setContextMenu(contextMenuForTableView());
        ContextMenu contextMenu = new ContextMenu();

        // final MenuItem addMenuItem = new MenuItem("Add...");
        final MenuItem deleteSelectedMenuItem = new MenuItem("Delete selected cell");

        contextMenu.getItems()
                .addAll(deleteSelectedMenuItem);
        deleteSelectedMenuItem.disableProperty()
                .bind(Bindings.isEmpty(tableView.getSelectionModel().getSelectedItems()));
        deleteSelectedMenuItem.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                //final List<Person> selectedPeople = new ArrayList<>(table.getSelectionModel().getSelectedItems());
                tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());

            }
        }
        );

        tableView.setContextMenu(contextMenu);

        // contextMenu.getItems().addAll(deleteSelectedMenuItem);
        //   return tableView; 
        // return tableView;
    }

    public TextFlow emptyTableMessage() {

        Text text1 = new Text("Click ");
        text1.setFill(Color.RED);
        // text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 12));
        Text text2 = new Text("Add cell");
        text2.setFill(Color.BLUE);

        Text text3 = new Text(" button to add a cell");

        TextFlow textFlow = new TextFlow(text1, text2, text3);

        return textFlow;

    }
}
