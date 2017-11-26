package server;

/*
 * @author Chatchapol Rasameluangon
 * id: 5810404901
 */


import common.job.Job;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

@Repository
class SimpleDAO {
    private final ConcurrentMap<Integer, Job> database = new ConcurrentSkipListMap<>();

    List<Job> load() {
        List<Job> result = new ArrayList<>();
        database.keySet().forEach(key -> result.add(database.get(key)));
        return result;
    }

    void insert(Job job) {
        database.putIfAbsent(job.getId(), job);
    }

//    void delete(Job job) {
//        database.remove(job.getId());
//    }

    void update(Job job) {
        Job updated = database.get(job.getId());
        updated.setId(job.getId());
        updated.setName(job.getName());
        updated.setStatus(job.getStatus());
        updated.setActive(job.isActive());
    }

}
