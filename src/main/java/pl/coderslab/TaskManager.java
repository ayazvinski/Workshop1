package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {
    static String[][] tasks;

    public static void main(String[] args) {

        selectOption(args);
        tasks = readFile("tasks.csv");
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


        try{
            Scanner scanner = new Scanner (file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String [] splitLine = line.split(",");

                for (int i = 0; i < splitLine.length; i++) {
                    for (int j = 0; j < splitLine[i].length(); j++) {
                        resultArray[i][j] = splitLine[i];

                    }

                }

            }

        }catch (FileNotFoundException nf){
            System.out.println("noFile");
        }

    }


}
