public abstract class Job {
    private String jobName;

    public Job(String jobName) { // specific constructor => default constructor is removed and any subclass extending this will be forced to insert jobName
        if (jobName == null) {
            this.jobName = "Generic Job"; // if null, default to this
        } else {
            this.jobName = jobName; // sets jobName to the one provided
        }
    }

    public abstract void execute();

    public String getJobName() {
        return jobName; // encapsulated access to Jobname
    }
}
