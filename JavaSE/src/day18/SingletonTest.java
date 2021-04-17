package day18;

public class SingletonTest {
    public static void main(String[] args) {

    }
}
class Bank {
    private Bank(){}
    private static Bank instance = null;
    public static Bank getInstance() {
        if (instance == null) {
            synchronized(Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
