import java.util.Scanner;


//client
public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner scFolder = new Scanner(System.in);
        System.out.println("Введите путь к директории: ");
        String path = sc.nextLine();
        WorkingDirectory wd = WorkingDirectory.getInstance(path);
        DirectoryExecutor directoryExecutor = new DirectoryExecutor();
        String task = "";
        while (!task.equals("exit")) {
            System.out.println("Текущая директория: " + directoryExecutor.executeOperation(new GetNameDirectory(wd)));
            System.out.println("Введите задание:");
            task = sc.nextLine();
            switch (task) {
                case "1" -> {
                    System.out.println("Содержимое текущего каталога:");
                    directoryExecutor.executeOperation(new PrintDirectoryContents(wd));
                }
                case "2" -> {
                    System.out.println("Абсолютный путь родительского каталога:");
                    System.out.println(directoryExecutor.executeOperation(new ParentDirectoryName(wd)));
                }
                case "3" -> {
                    System.out.println("Переход к родительскому каталогу:");
                    directoryExecutor.executeOperation(new GoToParentDirectory(wd));
                }
                case "4" -> {
                    System.out.println("Введите название каталога ");
                    String folder = scFolder.nextLine();
                    System.out.println("Существует ли каталог " + folder + "?");
                    boolean flag = (boolean) directoryExecutor.executeOperation(new checkChildDirectoryExist(wd, folder));
                    if (flag) {
                        System.out.println("Введёная директория существует");
                    } else {
                        System.out.println("Введёная директория не существует");
                    }
                }
                case "5" -> {
                    System.out.println("Введите название для нового каталога ");
                    String folder = scFolder.nextLine();
                    System.out.println("Создание нового каталога " + folder + " в " + wd.getDirectoryName());
                    boolean flag = (boolean) directoryExecutor.executeOperation(new createNewDirectory(wd, folder));
                    if (flag) {
                        System.out.println("Новый каталог создан");
                    } else {
                        System.out.println("Новый каталог не создан");
                    }
                }
                case "6" -> {
                    System.out.println("Введите имя подкаталог каталога ");
                    String folder = scFolder.nextLine();
                    System.out.println("Переход в подкаталог " + folder + " из " + wd.getDirectoryName());
                    boolean flag = (boolean) directoryExecutor.executeOperation(new GoToChildDirectory(wd, folder));
                    if (flag) {
                        System.out.println("Успешно перешли в каталог");
                    } else {
                        System.out.println("Такого каталога не существует!");
                    }
                }
                case "7" -> {
                    System.out.println("Удаление подкаталогов из " + wd.getDirectoryName());
                   directoryExecutor.executeOperation(new DeleteSubdirectories(wd));
                   System.out.println("Удаление выполнено");

                }
                case "8" -> {
                    System.out.println("Введите расширение: ");
                    String extension = scFolder.nextLine();
                    directoryExecutor.executeOperation(new ListFilesbyExtensions(wd, extension));
                }
                case "9" -> {
                    System.out.println("Иерархический список подкаталогов:");
                    directoryExecutor.executeOperation(new ListAllSubdirectoriesCommand(wd));
                }
                case "10" -> {
                    System.out.println("Введите имя подкаталога ");
                    String folder = scFolder.nextLine();
                    boolean flag = (boolean)directoryExecutor.executeOperation(new CheckNestedDirectoryExists(wd, folder));
                    if (flag) {
                        System.out.println("Каталог существует");
                    } else {
                        System.out.println("Такого каталога не существует!");
                    }
                }
                case "help" ->
                {
                    System.out.println("1) Вывести содержимое текущего каталога.\n" +
                            "2) Показать абсолютное имя родительского каталога.\n" +
                            "3) Перейти к родительскому каталогу и изменить имя текущего каталога.\n" +
                            "4) Проверить существование дочернего каталога с заданным именем.\n" +
                            "5) Создать новый каталог в текущем каталоге.\n" +
                            "6) Перейти в подкаталог текущего каталога и изменить имя текущего каталога.\n" +
                            "7) Удалить все подкаталоги вложенные в данный.\n" +
                            "8) Вывести список файлов определенного формата (расширения) в заданном каталоге.\n" +
                            "9) Вывести иерархический список всех подкаталогов, вложенных в данный.\n" +
                            "10) Проверить существование подкаталога с заданным именем в текущем рабочем каталоге с учетом всех уровней вложенности.");
                }
            }
        }
    }
}