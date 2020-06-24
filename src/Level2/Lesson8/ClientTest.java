package Level2.Lesson8;

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
        Client client1 = new Client("localhost", 8189);

        //авторизуем и запускаем
        Thread x1 = new Thread(client1);
        x1.start();
        delay(10000);
        client1.login("login1", "pass1");
        delay(2000);
        client1.sendMessage("ура я 1 тут");
        delay(2000);
        client1.logout();
        delay(2000);

        Client client2 = new Client("localhost", 8189);
        Thread x2 = new Thread(client2);
        x2.start();
        delay(2000);
        client2.login("login2", "pass2");
        delay(2000);
        client2.sendMessage("ура я 2 тут");
        delay(2000);
        client2.logout();
        delay(2000);
        client2.PrintMessage("тестирование завершено");
    }
}
