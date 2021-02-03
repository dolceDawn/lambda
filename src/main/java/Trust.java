import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 6/2/2020 14:56
 */
public class Trust {

    public static void main(String[] args) throws Exception {

        String sourcepath = "D:\\opt\\upload\\third-party-jar\\temp\\526c96";

        String destpath = "D:\\opt\\upload\\third-party-jar\\526c96";

        //Map<String, Class<?>> clazzInfo = analyzeJars(path);

        File file = traverseFolder(sourcepath);

        System.out.println(file.getName());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile());

        String source = sourcepath + File.separator + file.getName();

        String dest = destpath + File.separator + file.getName();

        FileUtils.copyFile(new File(source), new File(dest));


    }

    public static Map<String, Class<?>> analyzeJars(String path) throws Exception {
        //load jar file
        loadJar(path);

        Map<String, Class<?>> clazzInfo = new HashMap<>(16);

        try {
            JarFile jarfile = new JarFile(path);
            Enumeration entrylist = jarfile.entries();
            while(entrylist.hasMoreElements()) {
                JarEntry jarentry = (JarEntry)entrylist.nextElement();
                if (jarentry.getName().indexOf("META-INF") < 0) {
                    String classFullName = jarentry.getName();
                    if (classFullName.endsWith(".class")) {
                        String className = classFullName.substring(0, classFullName.length()-6).replace("/", ".");
                        Class<?> connectClass = Class.forName(className);

                        String shortName = className.substring(className.lastIndexOf(".") + 1, className.length());
                        clazzInfo.put(shortName, connectClass);
                    }
                }
            }
            jarfile.close();
        } catch(IOException e) {
            System.out.println(e);
        }

        return clazzInfo;
    }

    public static void loadJar(String jarPath) {
        File jarFile = new File(jarPath);
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (method == null) {
            return;
        }
        boolean accessible = method.isAccessible();
        try {
            //释放权限
            method.setAccessible(true);
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            URL url = jarFile.toURI().toURL();
            method.invoke(classLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //归还权限
            method.setAccessible(accessible);
        }
    }


    public static File traverseFolder(String path) {
        File rootFile = new File(path);
        if (rootFile.exists()) {
            File[] allFiles = rootFile.listFiles();
            if (null == allFiles || allFiles.length == 0) {
                return null;
            } else {
                for (File file : allFiles) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".jar")) {
                        return file;
                    }
                }
            }
        }
        return null;
    }

}
