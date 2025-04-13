import java.io.*;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "aboba.aboba";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;

        do {
            showMenu();
            try {
                userChoice = Integer.parseInt(sc.nextLine());
                switch (userChoice) {
                    case 1:
                        wToFile(sc);
                        break;
                    case 2:
                        readFile(sc);
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
        sc.close();
    }

    public static void showMenu() {
        System.out.println("\n--- Меню редактора ---");
        System.out.println("1. Запис до файлу");
        System.out.println("2. Читання файлу");
        System.out.println("3. Вихід");
        System.out.print("Введіть ваш вибір: ");
    }

    public static int countLines() {
        int count = 0;
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader fr = new BufferedReader(new FileReader(file))) {
                while (fr.readLine() != null) {
                    count++;
                }
            } catch (IOException e) {
                System.out.println("Помилка при підрахунку рядків: " + e.getMessage());
            }
        }
        return count;
    }

    public static void wToFile(Scanner sc) {
        System.out.println("\nВиберіть режим запису:");
        System.out.println("1. Додати до кінця файлу");
        System.out.println("2. Перезаписати файл");
        int mode;
        try {
            mode = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть число. Повертаємося до меню.");
            return;
        }
        boolean append;
        int currentLineNumber = 1;
        if (mode == 1) {
            append = true;
            currentLineNumber = countLines() + 1;
        } else if (mode == 2) {
            append = false;
        } else {
            System.out.println("Невірний режим. Повертаємося до меню.");
            return;
        }
        System.out.println("Введіть рядок для запису:");
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(FILE_NAME, append))) {
            boolean writing = true;
            while (writing) {
                System.out.print("Введіть рядок (" + currentLineNumber + "): ");
                String inputLine = sc.nextLine();
                fw.write(inputLine);
                fw.newLine();
                currentLineNumber++;

                System.out.println("Чи хочете ви ввести ще один рядок? 1-Так 2-Ні");
                String var = sc.nextLine();
                if ("2".equals(var)) {
                    writing = false;
                } else if (!"1".equals(var)) {
                    System.out.println("Невірний вибір, припиняємо введення.");
                    writing = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл: " + e.getMessage());
        }
    }

    public static void readFile(Scanner sc) {
        System.out.println("Вивести повний текст чи тільки деякий його диапазон? 1 - Повний текст 2-Диапазон ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.println("\nВміст файлу:");
                try (BufferedReader fr = new BufferedReader(new FileReader(FILE_NAME))) {
                    String line;
                    int lineNumber = 1;
                    while ((line = fr.readLine()) != null) {
                        System.out.println(lineNumber + ": " + line);
                        lineNumber++;
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не знайдено.");
                } catch (IOException e) {
                    System.out.println("Помилка при читанні файлу: " + e.getMessage());
                }
                break;
            case "2":
                System.out.println("Введіть початок виведення: ");
                int ch1 = sc.nextInt();
                System.out.println("Введіть кінець виведення: ");
                int ch2 = sc.nextInt();
                sc.nextLine();
                try (BufferedReader fr = new BufferedReader(new FileReader(FILE_NAME))) {
                    String line;
                    int lineNumber = 1;
                    while ((line = fr.readLine()) != null) {
                        if (lineNumber >= ch1 && lineNumber <= ch2) {
                            System.out.println(lineNumber + ": " + line);
                        }
                        lineNumber++;
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не знайдено.");
                } catch (IOException e) {
                    System.out.println("Помилка при читанні файлу: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Невірний вибір. Повертаємося до меню.");
                break;
        }
    }
}
