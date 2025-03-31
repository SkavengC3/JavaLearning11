import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "aboba.aboba";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;

        do {
            showMenu();
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                switch (userChoice) {
                    case 1:
                        wToFile(scanner);
                        break;
                    case 2:
                        readFile();
                        break;
                    case 3:
                        System.out.println("Вихід з редактора.");
                        break;
                    default:
                        System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть числове значення.");
            }
        } while (userChoice != 3);
        scanner.close();
    }

    public static void showMenu() {
        System.out.println("\n--- Меню редактора ---");
        System.out.println("1. Запис до файлу");
        System.out.println("2. Читання файлу");
        System.out.println("3. Вихід");
        System.out.print("Введіть ваш вибір: " );
    }

    public static void wToFile(Scanner scanner) {
        System.out.println("\nВиберіть режим запису:");
        System.out.println("1. Додати до кінця файлу");
        System.out.println("2. Перезаписати файл");
        int mode;
        try {
            mode = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть число. Повертаємося до меню.");
            return;
        }
        boolean append;
        if (mode == 1) {
            append = true;
        } else if (mode == 2) {
            append = false;
        } else {
            System.out.println("Невірний режим. Повертаємося до меню.");
            return;
        }
        System.out.println("Введіть рядок для запису:");
        String inLine = scanner.nextLine();
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME, append);
            fileWriter.write(inLine + "\n");
            fileWriter.close();
            System.out.println("Рядок успішно записано.");
        } catch (IOException e) {
            System.out.println("Помилка при роботі з файлом: " + e.getMessage());
        }
    }

    public static void readFile() {
        System.out.println("\nВміст файлу:");
        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            int character;
            while ((character = fileReader.read()) != -1) {
                System.out.print((char) character);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено.");
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }
}
