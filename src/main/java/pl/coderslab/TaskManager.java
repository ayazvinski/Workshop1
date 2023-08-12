package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManager {
    static String[][] tasks;
    static String fileName = "tasks.csv";
    static String[] options = new String[] {"add","remove", "list","exit"};

    public static void main(String[] args) {

        tasks = readFile("tasks.csv");
        selectOption(options);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            switch (line) {
                case "exit":
                saveFile(fileName, tasks);
                    System.out.println(ConsoleColors.RED + "Bye, bye.");
                    System.exit(0);
                    break;
                case "add":
                    addTask();
                    break;
               case "remove":
                    deleteTask(tasks,taskNumber());
                    System.out.println("Value was successfully deleted.");
                    break;
                case "list":
                    listArray(tasks);
                    break;
                default:
                    System.out.println("Please select a correct option.");
            }

            selectOption(options);
        }

    }

    public static void selectOption(String[] args) {

        System.out.println(ConsoleColors.BLUE + "Please select an option");

        for (int i = 0; i < args.length; i++) {
            System.out.println(ConsoleColors.RESET + args[i]);
        }
    }

    public static String[][] readFile(String fileName) {

        String[][] resultArray = null;
        Path file = Paths.get(fileName);

        int row=0;
        int column =0;


        try{
            Scanner scanner = new Scanner (file);

            while (scanner.hasNext()) {
                row++; //liczba wierszy
                String line = scanner.nextLine();
                String[] splitLine = line.split(",");

                if (column < splitLine.length) {
                    column = splitLine.length;  //szukamy najdluższy wiersz
                }
            }

            resultArray = new String[row][column];


            Scanner scanner2 = new Scanner(file);
            int i=0;


                    while(scanner2.hasNext()) {
                        String line2 = scanner2.nextLine();
                        String[] splitLine2 = line2.split(",");

                        for (int j = 0; j < resultArray[i].length; j++) {
                            resultArray[i][j] = splitLine2[j];
                        }
                        i++;
                        }



        }catch (FileNotFoundException nf){
            System.out.println(nf + " Source File not found!");
            System.exit(0);
        }catch (IOException io){
            System.out.println(io + " Source File not found!");
            System.exit(0);
    }
        return resultArray;
    }


    public static void addTask() {
        String description = "";
        String dueDate = "";
        String importance;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please add task description");
        description = scanner.nextLine();
        while (description.equals("")) {
            System.out.println("Value cant be empty!");
            description = scanner.nextLine();
        }

        System.out.println("Please add task due date");
        dueDate = scanner.nextLine();
        while (dueDate.equals("")) {
            System.out.println("Value cant be empty!");
            dueDate = scanner.nextLine();
        }

        System.out.println("Is your task important: true/false");
        importance = scanner.nextLine();
        while (importance.equals("")) {
            System.out.println("Value cant be empty!");
            importance = scanner.nextLine();
        }
        while (!importance.equalsIgnoreCase("True") && !importance.equalsIgnoreCase("False")) {
            System.out.println("Bad value provided! Put True or False.");
            importance = scanner.nextLine();
        }


        int column = 0;

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].length > column) {
                column = tasks[i].length;
            }
        }

        String[][] arrayCopy = new String[tasks.length + 1][column];


        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < tasks[i].length; j++) {
                arrayCopy[i][j] = tasks[i][j];
            }
        }
            arrayCopy[arrayCopy.length - 1][0] = description + " ";
            arrayCopy[arrayCopy.length - 1][1] = dueDate + " ";
            arrayCopy[arrayCopy.length - 1][2] = importance;

            tasks = arrayCopy;

        }

        public static int taskNumber () {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Please select number to remove");
            while (!scanner.hasNextInt()){
                System.out.println("Provided value is not number! Provide number greater than 0");
                scanner.next();
            }
            int number = scanner.nextInt();
            return number;
        }

        public static void deleteTask(String [][] array, int taskNumber) {

        int column = 0;

            for (int i = 0; i < array.length; i++) {
                if (array[i].length > column) {
                    column = array[i].length;
                }
            }

            String [][] newArray = new String[array.length-1][column];

            int newIndex = 0;


            try {
                for (int i = 0; i < array.length; i++) {
               if (i != taskNumber - 1) {
                   for (int j = 0; j < array[i].length; j++) {
                           newArray[newIndex][j] = array[i][j];
                       }
                     newIndex++;
                   }

               }
                tasks = newArray;
           }catch(ArrayIndexOutOfBoundsException ex) {
                System.out.println("Element not exist in tab");
                deleteTask(tasks,taskNumber());
            }


        //    for (int i = 0; i < tasks.length; i++) {
        //       for (int j = 0; j < tasks[i].length; j++) {
        //           System.out.print(tasks[i][j]);
        //        }
        //        System.out.println();
         //   }

        }

    public static void listArray (String[][] args) {

        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length; j++) {
                System.out.print(args[i][j]);
            }
            System.out.println();
        }

    }

    public static void saveFile (String fileName, String [][] toSave){

       try{
           FileWriter writer = new FileWriter(fileName);

        String [] save = new String [tasks.length];
        for (int i = 0; i < save.length; i++) {
            save[i] = String.join(",",toSave[i]);
        }

        for (int i = 0; i < save.length; i++) {
            writer.write(save[i] + "," +"\n");

        }
        writer.close();

        }catch (IOException io){
           io.printStackTrace();
       }

    }






}
