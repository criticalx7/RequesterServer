package server;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */


import common.job.Job;
import common.job.JobReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ServiceHandler implements JobReviewService {
    private final Logger logger = Logger.getLogger(ServiceHandler.class.getName());
    private final SimpleDAO dao;

    @Autowired
    public ServiceHandler(SimpleDAO dao) {
        this.dao = dao;
    }


    @Override
    public void saveReview(Job job) {
        dao.update(job);
        logger.info("save:" + job);

    }

    @Override
    public void requestReview(Job job) {
        dao.insert(job);
        logger.info("accept:" + job);
    }

    @Override
    public List<Job> queryJobs() {
        logger.info("client request jobs query...\n");
        return dao.load();
    }
}
