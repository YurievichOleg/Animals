package View;

import Presenter.Presenter;

import java.util.Scanner;

public class View {
    Scanner scanner;
    Presenter presenter;

    public View(){
        scanner = new Scanner(System.in);
        presenter = new Presenter();
    }
    public void run(){
        System.out.println("Введите 1 для того что бы завести новое животное. \n" +
                "Введите 2 для того что бы увидеть список команд, которое выполняет животное. \n" +
                "Введите 3 для того что бы обучить животное новым командам\n" +
                "Введите 0 для выхода");

        int a = Integer.parseInt(scanner.nextLine());
        if(a == 1){
            String[] data = new String[4];
            System.out.println("Введите тип животного:");
            data[0] = scanner.nextLine();
            System.out.println("Введите имя животного:");
            data[1] = scanner.nextLine();
            System.out.println("Введите дату рождения животного в формате yyyy-mm-dd:");
            data[2] = scanner.nextLine();
            System.out.println("Введите через запятую команды которые может выполнять животное:");
            presenter.getNewAnimal(data[0], data[1], data[2], data[3]);
            this.run();
        } else if (a == 2) {
            System.out.println("Введите кличку животного, что бы посмотреть комманды которое оно выполняет:");
            String data = scanner.nextLine();
            System.out.println(presenter.seeListOfCommand(data));
            this.run();
        } else if (a == 3) {
            String[] data = new String[2];
            System.out.println("Введите кличку животного, которого обучили новой комманде:");
            data[0] = scanner.nextLine();
            System.out.println("Введите название комманды которому обучилось животное:");
            data[1] = scanner.nextLine();
            presenter.trainNewTeams(data[0], data[1]);
            this.run();
        } else if (a == 0) {
            scanner.close();
        } else {
            System.out.println("Некорректный ввод данных.");
            this.run();
        }
    }
}
