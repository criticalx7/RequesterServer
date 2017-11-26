package client.ui;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import common.job.Job;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class JobListController {

    public TextField idField;
    public TextField nameField;
    public TabPane tabPane;
    public Tab activeTab;
    public Tab inActiveTab;
    public ListView<JobAdapter> activeList;
    public ListView<JobAdapter> inActiveList;
    public Button addBtn;
    public Button deleteBtn;
    public Button acceptBtn;
    public Button rejectBtn;
    public Button requestBtn;
    public Button refreshBtn;
    public RadioButton managerToggle;
    public RadioButton reviewerToggle;
    public ToggleGroup userTypeGroup;
    public HBox buttonBox;
    public FlowPane formBox;

    private ActionHandler handler;

    public void initialize() {
        addBtn.visibleProperty().bind(managerToggle.selectedProperty());
        addBtn.visibleProperty().bind(activeTab.selectedProperty());
        deleteBtn.visibleProperty().bind(reviewerToggle.selectedProperty());
        deleteBtn.visibleProperty().bind(activeTab.selectedProperty());
        buttonBox.visibleProperty().bind(activeTab.selectedProperty());
        activeList.setCellFactory(c -> new JobCell());
        inActiveList.setCellFactory(c -> new JobCell());
        onSelectUserType();
    }

    @FXML
    public void onAdd() {
        if (!idField.getText().isEmpty() && !nameField.getText().isEmpty()) {
            handler.addJob(Integer.parseInt(idField.getText()), nameField.getText());
        }
    }

    @FXML
    public void onDelete() {
        handler.deleteSelectedJob();
    }

    @FXML
    public void onSend() {
        handler.sendSelectedJob();
    }

    @FXML
    public void onAccept() {
        handler.acceptSelectedJob();
    }

    @FXML
    public void onReject() {
        handler.rejectSelectedJob();
    }

    @FXML
    public void onSelectUserType() {
        if (managerToggle.isSelected()) {
            buttonBox.getChildren().clear();
            buttonBox.getChildren().add(requestBtn);
        } else {
            buttonBox.getChildren().clear();
            buttonBox.getChildren().addAll(acceptBtn, rejectBtn);
        }
    }

    @FXML
    public void onRefresh() {
        handler.loadJobs();
    }

    public void setHandler(ActionHandler controller) {
        this.handler = controller;
        controller.getJobManager().currentJobProperty().bind(activeList.getSelectionModel().selectedItemProperty());

        // bind button that disable when empty.
        ObservableList<JobAdapter> jobs = controller.getJobManager().getJobs();
        BooleanBinding listEmpty = Bindings.isEmpty(jobs);
        buttonBox.disableProperty().bind(listEmpty);

        activeList.setItems(jobs.filtered(j -> j.activeProperty().get()));
        inActiveList.setItems(jobs.filtered(j -> !j.activeProperty().get()));
    }

}
