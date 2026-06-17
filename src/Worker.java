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
                Thread.sleep(1000); //pause between executions
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (EmptyToDoListException e) {
            System.out.println(e.getMessage());
        }
    }
}
