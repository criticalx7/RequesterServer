package client.validator;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import client.ui.JobAdapter;

public class NotNullValidator implements Validator<JobAdapter> {

    @Override
    public boolean validate(JobAdapter object) {
        return object != null;
    }
}
