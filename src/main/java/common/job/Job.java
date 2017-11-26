package common.job;
/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import java.io.Serializable;

public class Job implements Serializable {
    private static final long serialVersionUID = -3713463464521221074L;
    private int id;
    private String name;
    private Status status;
    private boolean active;

    public Job(int id, String name) {
        this.id = id;
        this.name = name;
        status = Status.READY;
        active = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("Job{id=%d, name='%s', status=%s, active ='%s'%n}", id, name, status, active);
    }

}
