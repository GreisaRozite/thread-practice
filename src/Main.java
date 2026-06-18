public class Main {
    public static void main(String[] args) throws InterruptedException {
        ToDoList list = new ToDoList();

        Thread worker1 = new Thread(new Worker(list));
        worker1.start();

        Thread worker2 = new Thread(new Worker(list));
        worker2.start();

        list.addJob(new RealJob("Pick a flower")); // worker 1 does this, fin at 2.0 secs
        Thread.sleep(500); //main thread freezes on this line for 0.5 seconds as a buffer between jobs
        // next job gets sent out at 0.5 secs

        list.addJob(new RealJob("Trip and fall"));
        list.undoLastAdd(); // testing undo

        list.addJob(new Job("Draw a unicorn"){
            @Override
            public void execute(){
                System.out.println("Drawing...");
            }
        }); // add using anonymous class, thus overriding the 'executing:...' message
        // worker 2 does this, fin at 2.5 secs

        Thread.sleep(500); // buffer, job gets sent out at 1.0 secs
        list.addJob(new RealJob("Run around")); // worker 1 does this, fin at 3.0 secs

        list.addJob(new RealJob("Do a cartwheel")); // worker 2 does this as soon as free from previous job at 2.5 secs, fin at 4.5 secs
        Thread.sleep(9500); // main thread is currently at 1.0, need to wait 5 secs after last worker to stop program, aka 9.5 secs
        // next one will never load
        list.addJob(new RealJob("Recite a poem"));

    }
}
