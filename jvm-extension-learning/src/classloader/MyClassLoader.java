package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            String className = name + ".class";
            File file = new File("D:\\daima\\gitsource\\basicExtensionLearning\\jvm-extension-learning\\src\\testclassloader", className);
            FileInputStream fis = new FileInputStream(file);
            int len = 0;
            ByteArrayOutputStream byteOs = new ByteArrayOutputStream();

            while ((len = fis.read()) != -1) {
                byteOs.write(len);
            }

            byte[] data = byteOs.toByteArray();

            return defineClass("testclassloader." + name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}
