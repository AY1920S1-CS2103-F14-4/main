package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.task.Task;



/**
 * Panel containing the list of tasks.
 */
public class UnassignedTaskListPanel extends UiPart<Region> {
    private static final String FXML = "UnassignedTaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(UnassignedTaskListPanel.class);

    @FXML
    private ListView<Task> unassignedTaskListView;

    public UnassignedTaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        unassignedTaskListView.setItems(taskList);
        unassignedTaskListView.setCellFactory(listView -> new UnassignedTaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code AssignedTaskCard}.
     */
    class UnassignedTaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new UnassignedTaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}

//<?xml version="1.0" encoding="UTF-8"?>
//<?import javafx.scene.image.Image?><?import javafx.scene.Scene?><?import javafx.scene.layout.VBox?><?import javafx.scene.control.MenuBar?><?import javafx.scene.control.Menu?><?import javafx.scene.control.MenuItem?><?import javafx.scene.control.TabPane?><?import javafx.scene.control.Tab?><?import javafx.scene.control.SplitPane?>
//<?import java.net.URL?>
//<?import javafx.geometry.Insets?>
//<?import javafx.scene.control.Menu?>
//<?import javafx.scene.control.MenuBar?>
//<?import javafx.scene.control.MenuItem?>
//<?import javafx.scene.image.Image?>
//<?import javafx.scene.layout.StackPane?>
//<?import javafx.scene.layout.VBox?>
//<?import javafx.scene.Scene?>
//<?import javafx.stage.Stage?>
//<?import javafx.scene.control.SplitPane?>
//<?import javafx.scene.control.TabPane?>
//<?import javafx.scene.control.Tab?>
//<?import javafx.scene.layout.AnchorPane?>
//<fx:root type="javafx.stage.Stage" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1"
//         title="Address App" minWidth="450" minHeight="600" onCloseRequest="#handleExit">
//  <icons>
//    <Image url="@/images/address_book_32.png" />
//  </icons>
//  <scene>
//    <Scene>
//      <stylesheets>
//        <URL value="@DarkTheme.css" />
//        <URL value="@Extensions.css" />
//      </stylesheets>
//
//      <VBox>
//          <MenuBar fx:id="menuBar" VBox.vgrow="NEVER">
//            <Menu mnemonicParsing="false" text="File">
//              <MenuItem mnemonicParsing="false" onAction="#handleExit" text="Exit" />
//            </Menu>
//            <Menu mnemonicParsing="false" text="Help">
//              <MenuItem fx:id="helpMenuItem" mnemonicParsing="false" onAction="#handleHelp" text="Help" />
//            </Menu>
//          </MenuBar>
//
//        <TabPane tabClosingPolicy="UNAVAILABLE">
//          <tabs>
//            <Tab text="Home">
//              <SplitPane>
//                <VBox>
//        <!--          <StackPane fx:id="statusbarPlaceholder" VBox.vgrow="NEVER" />-->
//        <!--          <VBox fx:id="personList" styleClass="pane-with-border" minWidth="340" prefWidth="340" VBox.vgrow="ALWAYS">-->
//        <!--            <padding>-->
//        <!--              <Insets top="10" right="10" bottom="10" left="10" />-->
//        <!--            </padding>-->
//        <!--            <StackPane fx:id="personListPanelPlaceholder" VBox.vgrow="ALWAYS"/>-->
//        <!--          </VBox>-->
//                  <VBox fx:id="driverList" styleClass="pane-with-border" minWidth="300" prefWidth="300" VBox.vgrow="ALWAYS">
//                    <padding>
//                      <Insets top="10" right="10" bottom="10" left="10" />
//                    </padding>
//                    <StackPane fx:id="driverListPanelPlaceholder" VBox.vgrow="ALWAYS"/>
//                  </VBox>
//
//                  <StackPane VBox.vgrow="NEVER" fx:id="resultDisplayPlaceholder" styleClass="pane-with-border"
//                             minHeight="100" prefHeight="100" maxHeight="100">
//                    <padding>
//                      <Insets top="5" right="10" bottom="5" left="10" />
//                    </padding>
//                  </StackPane>
//
//                  <StackPane VBox.vgrow="NEVER" fx:id="commandBoxPlaceholder" styleClass="pane-with-border">
//                    <padding>
//                      <Insets top="15" right="10" bottom="15" left="10" />
//                    </padding>
//                  </StackPane>
//
//                </VBox>
//                  <SplitPane orientation="VERTICAL">
//
//                    <VBox fx:id="taskList" styleClass="pane-with-border" minWidth="200" prefWidth="200" VBox.vgrow="ALWAYS">
//                      <padding>
//                        <Insets top="10" right="10" bottom="10" left="10" />
//                      </padding>
//                      <StackPane fx:id="assignedTaskListPanelPlaceholder" VBox.vgrow="ALWAYS"/>
//                    </VBox>
//
//                    <VBox fx:id="unassignedTaskList" styleClass="pane-with-border" minWidth="200" prefWidth="200" VBox.vgrow="ALWAYS">
//                    <padding>
//                      <Insets top="10" right="10" bottom="10" left="10" />
//                    </padding>
//                    <StackPane fx:id="unassignedTaskListPanelPlaceholder" VBox.vgrow="ALWAYS"/>
//                    </VBox>
//
//                    <VBox fx:id="customerList" styleClass="pane-with-border" minWidth="200" prefWidth="200" VBox.vgrow="ALWAYS">
//                      <padding>
//                        <Insets top="10" right="10" bottom="10" left="10" />
//                      </padding>
//                      <StackPane fx:id="customerListPanelPlaceholder" VBox.vgrow="ALWAYS"/>
//                    </VBox>
//
//                  </SplitPane>
//              </SplitPane>
//            </Tab>
//
//            <Tab text="Statistics">
//              <content>
//
//                <SplitPane>
//                  <VBox>
//
//                    <VBox fx:id="driverList" styleClass="pane-with-border" minWidth="300" prefWidth="300" VBox.vgrow="ALWAYS">
//                      <padding>
//                        <Insets top="10" right="10" bottom="10" left="10" />
//                      </padding>
//                      <StackPane fx:id="driverListPanelPlaceholder" VBox.vgrow="ALWAYS"/>
//                    </VBox>
//
//                    <StackPane VBox.vgrow="NEVER" fx:id="resultDisplayPlaceholder" styleClass="pane-with-border"
//                               minHeight="100" prefHeight="100" maxHeight="100">
//                      <padding>
//                        <Insets top="5" right="10" bottom="5" left="10" />
//                      </padding>
//                    </StackPane>
//
//                    <StackPane VBox.vgrow="NEVER" fx:id="commandBoxPlaceholder" styleClass="pane-with-border">
//                      <padding>
//                        <Insets top="15" right="10" bottom="15" left="10" />
//                      </padding>
//                    </StackPane>
//
//                  </VBox>
//                  <VBox></VBox>
//
//                </SplitPane>
//
//              </content>
//            </Tab>
//
//          </tabs>
//        </TabPane>
//      </VBox>
//    </Scene>
//  </scene>
//</fx:root>
