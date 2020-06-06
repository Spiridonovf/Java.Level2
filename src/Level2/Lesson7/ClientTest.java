package Level2.Lesson7;

public class ClientTest {

    public static void delay(int ms)
    {
        try {
            Thread.sleep(ms);
            // any action
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //создаем 2 экземляра клиентов, с выводом в одну консоль
        //с целью удобства тестирования экземпляров ввод сообщений осуществляется не из одной консоли, а из кода
        Client client1 = new Client("localhost", 8189, "login1", "pass1");
        Client client2 = new Client("localhost", 8189, "login2", "pass2");

        //авторизуем и запускаем
        Thread x1 = new Thread(client1);
        x1.start();
        Thread x2 = new Thread(client2);
        x2.start();

        //пауза для того чтобы успеть авторизовать
        delay(2000);
        //шлем сообщения
        client1.sendMessage("ура я тут");
        delay(2000);
        client2.sendMessage("я тоже тут");
        //зашел еще один участник
        Client client3 = new Client("localhost", 8189, "login3", "pass3");
        Thread x3 = new Thread(client3);
        x3.start();
        delay(2000);
        client3.sendMessage("всем превед, мудаки");
        delay(2000);
        //шлем личные сообщения, вывод в одну консоль
        client1.sendMessage("/w nick2 валим из этого мудацкого чата");
        delay(2000);
        client2.sendMessage("/w nick1 да пожалуй");
        delay(2000);
        //завершение работы
        client1.logout();
        delay(2000);
        client2.logout();
        delay(2000);
        client3.sendMessage("раз я один, то и я пошел");
        delay(2000);
        client3.logout();
        delay(2000);
        System.out.println("тестирование завершено");
    }
}
