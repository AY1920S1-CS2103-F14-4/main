package seedu.address.model.task;

import java.time.LocalDate;
import java.util.Iterator;

import javafx.collections.ObservableList;

<<<<<<< HEAD


||||||| merged common ancestors
=======

>>>>>>> 86b415b3e6c7dce9456a6a6dec6d641d3c453687
/**
 * Manages the task list.
 * It contains the minimal set of list operations.
 */
public class TaskManager {

    private TaskList tasks;

    public TaskManager() {
        tasks = new TaskList();
    }

    public TaskManager(TaskList savedTasks) {
        this();
        tasks = savedTasks;
    }

    public ObservableList<Task> getList() {
        return tasks.getList();
    }

    //task list operations

    public void addTask(Task newTask) {
        tasks.addTask(newTask);
    }

    public void deleteTask(Task taskToRemove) {
        tasks.deleteTask(taskToRemove);
    }

    public Task getTask(int taskId) {
        return tasks.getTask(taskId);
    }

    public void setTask(Task taskToEdit, Task editedTask) {
        tasks.setTask(taskToEdit, editedTask);
    }

    public boolean hasTask(Task task) {
        return tasks.hasTask(task);
    }

    public boolean hasTask(int taskId) {
        return tasks.hasTask(taskId);
    }

    public void setTaskList(TaskList taskList) {
        tasks.setTaskList(taskList.getList());
    }

    /**
     * Checks if all tasks are complete.
     * @return a list of all incomplete tasks
     */
    public ObservableList<Task> allIncomplete(LocalDate date) {
        TaskList incomplete = new TaskList();
        Iterator<Task> tasksIterator = this.tasks.getIterator();
        while (tasksIterator.hasNext()) {
            Task task = tasksIterator.next();
            if (task.getStatus() != TaskStatus.COMPLETED && task.getDate().isBefore(date)) {
                incomplete.addTask(task);
            }
        }
        return incomplete.getList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskManager otherObject = (TaskManager) o;
        return getList().equals(otherObject.getList());
    }
}
