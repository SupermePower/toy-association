package cn.toy.www.util;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.util.Objects;

public class IoUtils {
    /**
     * 获取文件的创建时间
     * @param path
     * @return
     */
    public static Timestamp getFileCreateTime(Path path){
        File file = path.toFile();
        try {
            BasicFileAttributeView basicview= Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS );
            BasicFileAttributes attr = basicview.readAttributes();
            return new Timestamp(attr.creationTime().toMillis());
        } catch (Exception e) {
            e.printStackTrace();
            return new Timestamp(file.lastModified());
        }
    }

    /**
     * 文件不存在的方法
     * @param pathStr
     * @return
     */
    public static Boolean fileNotExist(String pathStr) {
        return !fileExist(pathStr);
    }

    /**
     * 文件存在的方法
     * @param pathStr
     * @return
     */
    public static Boolean fileExist(String pathStr) {
        Path path = Paths.get(pathStr);
        File file = path.toFile();
        return Objects.nonNull(file) && file.exists();
    }
}
