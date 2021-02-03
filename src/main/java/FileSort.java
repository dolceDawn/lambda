import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Desc:
 * @Author: zhangliming
 * @Date: 6/8/2020 15:19
 */
public class FileSort {

    public static void main(String[] args) {

        File last = traverseFolder("D:\\opt\\");

        System.out.println(last.getName());

    }

    public static File traverseFolder(String path) {
        File rootFile = new File(path);
        if (rootFile.exists()) {
            File[] allFiles = rootFile.listFiles();
            if (null == allFiles || allFiles.length == 0) {
                return null;
            } else {
                List<File> jarFiles = new ArrayList<>();
                for (File file : allFiles) {
                    String fileName = file.getName();
                    if (fileName.endsWith(".jar")) {
                        jarFiles.add(file);
                        //return file;
                    }
                }
                if (jarFiles.size() > 0) {
                    jarFiles.sort(Comparator.comparing(File::lastModified).reversed());
                    return jarFiles.get(0);
                }

            }
        }
        return null;
    }


}
