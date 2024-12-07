package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TasksService tasksService = new TasksService();

        System.out.println("Type number:");
        System.out.println("Type 1 to create new task");
        System.out.println("Type 2 to update the description");
        System.out.println("Type 3 to complete task:");
        System.out.println("Type 4 to delete the task:");
        System.out.println("Type 5 to see all your tasks:");
        System.out.println("Type 6 to see all your completed tasks:");
        System.out.println("Type 7 to see all your active tasks:");
        System.out.println("Type 8 to search task by word:");

        int userAction = -1;
        while (userAction < 0) {
            try {
                userAction = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
                userAction = -1;
            }
        }

        switch (userAction) {
            case 1:
                System.out.println("Type the task:");
                String newDescription = scanner.nextLine();
                tasksService.create(new Tasks(newDescription));
                break;
            case 2:
                System.out.println("Type the task id:");
                long id = -1L;
                while (id < 0) {
                    try {
                        id = Long.parseLong(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number");
                        id = -1L;
                    }
                }

                System.out.println("Type the new description for this task:");
                String newDescriptionUpdate = scanner.nextLine();
                tasksService.updateDescription(id, newDescriptionUpdate);
                break;
            case 3:
                System.out.println("Type the task id");
                long idTaskCompleted = -1L;
                while (idTaskCompleted < 0) {
                    try {
                        idTaskCompleted = Long.parseLong(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number");
                        idTaskCompleted = -1L;
                    }
                }
                tasksService.markTaskCompleted(idTaskCompleted);
                break;
            case 4:
                System.out.println("Type the id for the task you want to delete:");
                long idDeleteTask = -1L;
                while (idDeleteTask < 0) {
                    try {
                        idDeleteTask = Long.parseLong(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number");
                        idDeleteTask = -1L;
                    }
                }
                tasksService.deleteById(idDeleteTask);
                break;
            case 5:
                var tasksArrayList = tasksService.getAll();
                for (var task : tasksArrayList) {
                    System.out.println("Task ID: " + task.getId());
                    System.out.println("Task: " + task.getDescription());
                    System.out.println("Task status: " + task.getStatus());
                    System.out.println("--------------------------");
                }
                break;
            case 6:
                var tasksArrayListIsCompleted = tasksService.getByStatus(Status.COMPLETED);
                if (tasksArrayListIsCompleted.isEmpty()) {
                    System.out.println(("You don't have any tasks"));
                }
                for (var task : tasksArrayListIsCompleted) {
                    System.out.println("Task ID: " + task.getId());
                    System.out.println("Task: " + task.getDescription());
                    System.out.println("Task status: Completed");
                    System.out.println("--------------------------");
                }
                break;
            case 7:
                var tasksArrayListActive = tasksService.getByStatus(Status.ACTIVE);
                if (tasksArrayListActive.isEmpty()) {
                    System.out.println(("You don't have any tasks"));
                }
                for (var task : tasksArrayListActive) {
                    System.out.println("Task ID: " + task.getId());
                    System.out.println("Task: " + task.getDescription());
                    System.out.println("Task status: Active");
                    System.out.println("--------------------------");
                }
                break;
            case 8:
                System.out.println("Type the task you want to search:");
                String userTypedTaskDescription = "";
                while (userTypedTaskDescription.isBlank()) {
                    userTypedTaskDescription = scanner.nextLine();
                    if (userTypedTaskDescription.isBlank()) System.out.println("Invalid task description\nType an valid task");
                }

                var tasksArrayListFound = tasksService.getByDescription(userTypedTaskDescription);
                if (tasksArrayListFound.isEmpty()) {
                    System.out.println(("You don't have any tasks"));
                }
                for (var task : tasksArrayListFound) {
                    System.out.println("Task ID: " + task.getId());
                    System.out.println("Task: " + task.getDescription());
                    System.out.println("Task status: " + task.getStatus());
                    System.out.println("--------------------------");
                }
                break;
        }
    }
}