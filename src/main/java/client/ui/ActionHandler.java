package client.ui;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */


import client.validator.NotNullValidator;
import client.validator.ReadyValidator;
import client.validator.Validator;
import common.job.Job;
import common.job.JobReviewService;
import common.job.Status;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionHandler {
    private final JobManager jobManager;
    private final JobReviewService service;
    private final Validator<JobAdapter> notNull;
    private final Validator<JobAdapter> isReady;

    @Autowired
    public ActionHandler(JobManager jobManager, JobReviewService service) {
        this.jobManager = jobManager;
        this.service = service;
        notNull = new NotNullValidator();
        isReady = new ReadyValidator();
    }

    void addJob(int id, String name) {
        JobAdapter job = new JobAdapter(new Job(id, name));
        jobManager.add(job);
    }

    void deleteSelectedJob() {
        JobAdapter job = jobManager.getCurrentJob();
        if (notNull.validate(job) && isReady.validate(job)) {
            jobManager.delete(job);
        } else {
            showAlert("Can't do that",
                    "The request is not selected have been sent");
        }
    }

    void sendSelectedJob() {
        JobAdapter job = jobManager.getCurrentJob();
        if (notNull.validate(job) && isReady.validate(job)) {
            job.statusProperty().set(Status.PENDING);
            jobManager.getCurrentJob().save();
            tryConnect(() -> service.requestReview(job.getBean()));
        } else {
            showAlert("Can't do that",
                    "The request is not selected or have been sent");
        }
    }

    void acceptSelectedJob() {
        JobAdapter job = jobManager.getCurrentJob();
        if (notNull.validate(job)) {
            job.accept(true);
            job.save();
            tryConnect(() -> service.saveReview(job.getBean()));
        }

    }

    void rejectSelectedJob() {
        JobAdapter job = jobManager.getCurrentJob();
        if (notNull.validate(job)) {
            job.accept(false);
            job.save();
            tryConnect(() -> service.saveReview(job.getBean()));
        }

    }

    public void loadJobs() {
        tryConnect(() -> {
            jobManager.getJobs().removeIf(j -> j.statusProperty().get() != Status.READY);
            service.queryJobs().forEach(j -> jobManager.add(new JobAdapter(j)));
        });
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    private void tryConnect(Command command) {
        try {
            command.execute();
        } catch (Exception e) {
            showAlert("ERROR", "Can't connect.");
        }
    }

    JobManager getJobManager() {
        return jobManager;
    }

    private interface Command {
        void execute() throws Exception;
    }


}
