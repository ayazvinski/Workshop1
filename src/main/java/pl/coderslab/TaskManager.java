package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {
    static String[][] tasks;

    public static void main(String[] args) {

        selectOption(args);
        tasks = readFile("tasks.csv");
        addTask(tasks);

    }

    public static void selectOption(String[] args) {
        String[] options = new String[] {"add","remove", "list","exit"};

        System.out.println(ConsoleColors.BLUE + "Please select an option");

        for (int i = 0; i < options.length; i++) {
            System.out.println(ConsoleColors.RESET + options[i]);
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
            nf.printStackTrace();
        }catch (IOException io){
            io.printStackTrace();
    }
        for (int i = 0; i < resultArray.length; i++) {
            for (int j = 0; j < resultArray[i].length; j++) {
                System.out.print(resultArray[i][j]);
            }
            System.out.println();
        }
        return resultArray;
    }

    public static String [][] addTask (String [][] actualTasks) {
        String descrioption = "";
        String dueDate = "";
        String importance;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        descrioption = scanner.nextLine();
        System.out.println("Please add task due date");
        dueDate = scanner.nextLine();
        System.out.println("Is your task important: true/false");
        importance = scanner.nextLine();

        int column = 0;

        for (int i = 0; i < actualTasks.length; i++) {
            if (actualTasks[i].length > column){
                column = actualTasks[i].length;
            }
        }

        String [][] arrayCopy = new String [actualTasks.length+1][column];

        for (int i = 0; i < actualTasks.length; i++) {
            for (int j = 0; j < actualTasks[i].length; j++) {
                arrayCopy[i][j] = actualTasks[i][j];

            }
            arrayCopy[arrayCopy.length-1][0] = descrioption + " ";
            arrayCopy[arrayCopy.length-1][1] = dueDate + " ";
            arrayCopy[arrayCopy.length-1][2] = importance;

        }
        for (int i = 0; i < arrayCopy.length; i++) {
            for (int j = 0; j < arrayCopy[i].length; j++) {
                System.out.print(arrayCopy[i][j]);
            }
            System.out.println();

        }
        return arrayCopy;
    }




}
