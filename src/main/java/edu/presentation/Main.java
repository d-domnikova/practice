package edu.presentation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.domain.*;
import edu.domain.data.*;
import edu.domain.data.converter.GsonConverter;
import edu.domain.data.converter.JsonConverter;
import edu.domain.data.converter.LocalDateTimeAdapter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
        JsonConverter gsonConverter = new GsonConverter(gson);
        TaskDataSource dataSource = new TaskDataSource(gsonConverter);
        List<Task> tasks = dataSource.readTasks();
        if (tasks==null) tasks = new ArrayList<>();
        TaskRepositories repository = new AppTaskRepositories(dataSource, tasks);

        Scanner command = new Scanner(System.in);
        Scanner input = new Scanner(System.in);

        String help = """
                \n------------------
                Command list:
                1. add
                2. show all
                3. update
                4. delete
                5. search
                6. sort
                7. help
                0. exit
                ------------------
                """;
        boolean check = true;
        while (check){
            System.out.print(help + "\nEnter a command: ");
            switch (command.nextLine().toLowerCase()) {
                case "1", "add" -> {
                    System.out.print("Enter a task's description: ");
                    String body = input.nextLine();
                    System.out.print("Enter a date [HH:mm dd/MM/yyyy]: ");
                    String deadline = input.nextLine();
                    repository.addTask(new Task(body, LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")), false));
                }
                case "2", "show all" -> {
                    System.out.println("\nTODOLIST: ");
                    if(tasks.isEmpty()){
                        System.out.println("Your todolist is empty.");
                    } else {
                        for (Task task : tasks) {
                            System.out.println(task);
                        }
                    }
                }
                case "3", "update" -> {
                    System.out.print("Which task do you want to update? ");
                    int indexUpd = input.nextInt() - 1;
                    if (indexUpd>tasks.size() || indexUpd<0){
                        System.out.println("Error! Enter number from 1 to " + tasks.size());
                    } else {
                        System.out.print("\n"+ tasks.get(indexUpd) +
                                "\nUpdate task, deadline or status? Enter 't', 'd' or 'st': ");
                        String component = command.nextLine();
                        String newValue = "";
                        if (!component.contains("st")) {
                            System.out.print("Enter a new value: ");
                            newValue = command.nextLine();
                        }
                        repository.editTask(tasks.get(indexUpd), component, newValue);
                        System.out.println("Update is successfully complete!");
                    }
                }
                case "4", "delete" -> {
                    System.out.print("Which task do you want to delete? ");
                    int indexDel = input.nextInt()-1;
                    if (indexDel>tasks.size() || indexDel<0){
                        System.out.println("Error! Enter number from 1 to " + tasks.size());
                    } else {
                        repository.deleteTask(indexDel);
                        System.out.println("Delete is successfully complete!");
                    }
                }
                case "5", "search" -> {
                    System.out.print("Enter a keyword/several characters to search: ");
                    System.out.println(repository.searchTasks(input.nextLine()));
                }
                case "6", "sort" -> {
                    repository.sort();
                    System.out.println("Sort is successfully complete!");
                }
                case "7", "help" -> System.out.println(help);
                case "0", "exit" -> {
                    repository.saveChanges();
                    System.out.println("All data successfully saved! \nApplication closed.");
                    check = false;
                }
                default -> System.out.println("Command don't found. \nPlease use 'help' to see all available commands.");
            }
        }
        command.close();
    }
}