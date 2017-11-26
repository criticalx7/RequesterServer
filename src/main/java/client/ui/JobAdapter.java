package client.ui;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import common.job.Job;
import common.job.Status;
import javafx.beans.property.*;

public class JobAdapter {
    private final IntegerProperty id = new SimpleIntegerProperty(0);
    private final StringProperty name = new SimpleStringProperty("");
    private final ObjectProperty<Status> status = new SimpleObjectProperty<>(Status.READY);
    private final BooleanProperty active = new SimpleBooleanProperty(true);

    private Job job;

    JobAdapter(Job job) {
        this.job = job;
        reload();
    }

    private void reload() {
        id.set(job.getId());
        name.set(job.getName());
        status.set(job.getStatus());
        active.set(job.isActive());
    }

    void save() {
        if (job != null) {
            job.setId(id.get());
            job.setName(name.get());
            job.setStatus(status.get());
            job.setActive(active.get());
        }
    }

    void accept(boolean value) {
        status.set(value ? Status.ACCEPT : Status.REJECT);
        active.set(false);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public ObjectProperty<Status> statusProperty() {
        return status;
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public Job getBean() {
        return job;
    }
}
