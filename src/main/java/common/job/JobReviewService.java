package common.job;
/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public interface JobReviewService {
    void saveReview(Job job) throws IOException;

    void requestReview(Job job) throws IOException;

    List<Job> queryJobs() throws IOException;
}
