public class RealJob extends Job{
    public RealJob(String name) {
        super(name);
    }

    @Override
    public void execute() {
        System.out.println("Executing: " + getJobName());

    }
}
