package client.ui;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class JobCell extends ListCell<JobAdapter> {
    private final Pane pane = new Pane();
    private final HBox contentsBox = new HBox();
    private final Label info = new Label();
    private final Label status = new Label();

    private final Circle circle = new Circle(5);

    JobCell() {
        contentsBox.setAlignment(Pos.CENTER_LEFT);
        contentsBox.setSpacing(5);
        HBox.setHgrow(pane, Priority.ALWAYS);
        addControl();
    }

    @Override
    protected void updateItem(JobAdapter item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            setGraphic(null);
            setText("");
        } else {
            addContent(item);
        }
    }

    private void addControl() {
        contentsBox.getChildren().add(circle);
        contentsBox.getChildren().add(info);
        contentsBox.getChildren().add(pane);
        contentsBox.getChildren().add(status);
    }

    private void addContent(JobAdapter ja) {
        circle.setFill(getCircleColor(ja));
        info.setText(String.format("%d:  %s", ja.idProperty().get(), ja.nameProperty().get()));
        status.setText(ja.statusProperty().get().toString());
        setGraphic(contentsBox);
    }

    private Color getCircleColor(JobAdapter ja) {
        switch (ja.statusProperty().get()) {
            case READY:
                return Color.AQUA;
            case PENDING:
                return Color.YELLOW;
            case ACCEPT:
                return Color.GREEN;
            case REJECT:
                return Color.RED;
        }
        return null;
    }
}
