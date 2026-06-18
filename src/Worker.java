public class Worker implements Runnable{ // task, executable by a thread
    private ToDoList list;
    public Worker(ToDoList list){
        this.list = list;
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()){
                Job job = list.nextJob();
                job.execute();
                Thread.sleep(2000); //2 second pause between executions
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage()); // sends "worker was ordered to quit" message, stops that worker indefinitely
        } catch (EmptyToDoListException e) {
            System.out.println(e.getMessage()); // sends "worker quit, no new jobs anticipated for 5 seconds" message, stops that worker indefinitely
        }
    }
}
