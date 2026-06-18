import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class ToDoList implements Iterable<Job>{
    private List<Job> queue = new ArrayList<>(); // polymorphism to make code more flexible, "programming to an interface rather than an implementation", for example, if needed to switch ArrayList to LinkedList or Vector
    private Stack<Job> undoHistory = new Stack<>();

    public synchronized void addJob (Job job) {
        queue.add(job);
        undoHistory.push(job); // tracking using a stack
        notifyAll(); //for worker threads
    }

    public synchronized void undoLastAdd() {
        if (undoHistory.isEmpty()) {
            System.out.println("Undo failed. To do list is empty");
        } else {
            Job k = undoHistory.pop();
            queue.remove(k);
            System.out.println("Removed: " + k.getJobName() );
        }
    }

    public synchronized Job nextJob() throws EmptyToDoListException { // acts as a warning label. a checked exception created and added within this project
        long waitTime = System.currentTimeMillis() + 5000; // wait 5 seconds for any new jobs, if none, shutdown

        while (queue.isEmpty()) {
            long timeLeft = waitTime - System.currentTimeMillis(); // acts as a countdown

            if (timeLeft <= 0){
                throw new EmptyToDoListException("Worker quit, no new jobs anticipated for five seconds.");
            }

            try {
                wait(timeLeft); //waiting for notifyAll() when new job is added or countdown
            } catch (InterruptedException e) { // checked exception must be taken care of
                Thread.currentThread().interrupt();
                throw new EmptyToDoListException("Worker was ordered to quit");
            }
        }
        return queue.removeFirst();
    }

    @Override
    public Iterator<Job> iterator() {
        return queue.iterator();
    }
}
