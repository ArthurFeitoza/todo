package org.example;
import java.util.ArrayList;
import java.util.Date;

public class TasksService {
    private final TaskRepository taskRepository;

    public TasksService() {
        this.taskRepository = new TaskRepository();
    }

    public void create(Tasks task){
        if(task == null || task.getDescription() == null || task.getDescription().isBlank()){
            System.out.println("Can't make a task with an empty description");
            return;
        }
        this.taskRepository.create(task);
    }
    public void updateDescription(Long id, String newDescription){
        if(newDescription == null || newDescription.isBlank()){
            System.out.println("Can't make a task with an empty description");
            return;
        }
        var task = taskRepository.getById(id);
        if (task == null) {
            System.out.println("Task not found");
            return;
        }
        task.setDescription(newDescription);
        taskRepository.update(task);
    }
    public void markTaskCompleted(Long id){
        var task = taskRepository.getById(id);
        if (task == null) {
            System.out.println("Task not found");
            return;
        }
        if(task.isCompleted()){
            System.out.println("Task is already completed");
            return;
        }
        task.setCompleted(true);
        long millis = System.currentTimeMillis();
        task.setCompletedAt(new Date(millis));
        taskRepository.update(task);
    }
    public void deleteById(Long id){
        var task = taskRepository.getById(id);
        if (task == null) {
            System.out.println("Task not found");
            return;
        }
        taskRepository.delete(id);
    }
    public ArrayList<Tasks> getAll(){
        return taskRepository.getAll();
    }
}
