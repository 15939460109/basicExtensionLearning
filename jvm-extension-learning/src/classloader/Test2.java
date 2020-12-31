package classloader;

// 类加载器
public class Test2 {

    public static void main(String[] args) {

//        System.out.println(Test2.class.getClassLoader().toString());
//        System.out.println(Test2.class.getClassLoader().getParent().toString());

        MyClassLoader myClassLoader = new MyClassLoader();
        try {

            Class clazz = myClassLoader.loadClass("TestClassLoader");
            System.out.println(clazz.getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
