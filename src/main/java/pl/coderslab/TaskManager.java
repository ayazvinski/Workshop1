package pl.coderslab;

public class TaskManager {

    public static void main(String[] args) {

        selectOption(args);
    }

    public static void selectOption(String[] args) {
        String[] options = new String[] {"add","remove", "list","exit"};

        System.out.println(ConsoleColors.BLUE + "Please select an option");

        for (int i = 0; i < options.length; i++) {
            System.out.println(ConsoleColors.RESET + options[i]);
        }
    }


}
