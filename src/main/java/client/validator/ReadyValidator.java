package client.validator;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import client.ui.JobAdapter;
import common.job.Status;

public class ReadyValidator implements Validator<JobAdapter> {

    @Override
    public boolean validate(JobAdapter object) {
        return object.statusProperty().get() == Status.READY;
    }
}
