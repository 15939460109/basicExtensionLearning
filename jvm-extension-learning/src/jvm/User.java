package jvm;

public class User {

    private String name;
    private int age;

    static User user;

    public static void main(String[] args) throws InterruptedException {

        user = new User();

        user = null;

        System.gc();

        Thread.sleep(1000);

        System.out.println(user);
    }

    @Override
    protected void finalize() throws Throwable {
        user = this;
        System.out.println("User finalize");
    }
}
