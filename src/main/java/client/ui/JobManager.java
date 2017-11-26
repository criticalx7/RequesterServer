package client.ui;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */


import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

@Component
class JobManager {
    private final ObservableList<JobAdapter> jobs;
    private final ObjectProperty<JobAdapter> currentJob;

    public JobManager() {
        // extractor to make list view fucking auto update instead of call refresh()
        jobs = FXCollections.observableArrayList(
                j -> new Observable[]{j.idProperty(), j.nameProperty(), j.statusProperty(), j.activeProperty()});
        currentJob = new SimpleObjectProperty<>();
    }

    void add(JobAdapter job) {
        jobs.add(job);
    }

    void delete(JobAdapter job) {
        jobs.remove(job);
    }

    ObservableList<JobAdapter> getJobs() {
        return jobs;
    }

    JobAdapter getCurrentJob() {
        return currentJob.get();
    }

    ObjectProperty<JobAdapter> currentJobProperty() {
        return currentJob;
    }
}
