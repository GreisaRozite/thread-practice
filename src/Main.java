public class Main {
    public static void main(String[] args) throws InterruptedException {
        ToDoList list = new ToDoList();

        Thread worker1 = new Thread(new Worker(list));
        worker1.start();

        Thread worker2 = new Thread(new Worker(list));
        worker2.start();

        list.addJob(new RealJob("Pick a flower"));
        Thread.sleep(200);

        list.addJob(new RealJob("Trip and fall"));
        list.undoLastAdd();

        list.addJob(new Job("Draw a unicorn"){
            @Override
            public void execute(){
                System.out.println("Drawing...");
            }
        }); // add using anonymous class, thus overriding the 'executing:...' message
        Thread.sleep(1500); //main thread freezes on this line for 1.5 seconds

        list.addJob(new RealJob("Run around"));
        Thread.sleep(200);

        list.addJob(new RealJob("Do a cartwheel"));
        Thread.sleep(6000);
        // next one will never load, since wait time longer than five seconds
        list.addJob(new RealJob("Recite a poem"));
        Thread.sleep(200);

    }
}
