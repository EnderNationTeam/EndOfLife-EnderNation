package de.mxscha.endernationendoflife.utils.jobs;

import java.util.UUID;

public interface IJobAPI {

    String getJob(UUID uuid);
    void setJob(UUID uuid, Job job);
    void fireFromJob(UUID uuid, Job job);
}
