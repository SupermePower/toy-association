package cn.toy.www.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @Auther: zby
 * @Date: 18-11-29 10:17
 * @Description:
 */
public final class ZipFileUtil {


    private ZipFileUtil(){}




    private static final int  BUFFER_SIZE = 2 * 1024;


    public static void toZip(String srcDir, OutputStream out)
        throws RuntimeException{
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {

            zos = new ZipOutputStream(out);
            zos.setEncoding("UTF-8");
            File sourceFile = new File(srcDir);
            compress(sourceFile,zos,sourceFile.getName());
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }














    private static void compress(File sourceFile, ZipOutputStream zos, String name) throws Exception{
        byte[] buf = new byte[BUFFER_SIZE];
        if(sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0){
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                // 空文件夹的处理
                zos.putNextEntry(new ZipEntry(name + "/"));
                // 没有文件，不需要文件的copy
                zos.closeEntry();
            }else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                    // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                    compress(file, zos, name + "/" + file.getName());
                }
            }
        }
    }






    public static void deleteFile (File file) {
        //生成File[]数组   listFiles()方法获取当前目录里的文件夹  文件
        File[] files = file.listFiles();
        //判断是否为空
        // 有没有发现讨论基本一样
        if(files!=null){
            //遍历
            for (File file2 : files) {
                //是文件就删除
                if(file2.isFile()){
                    file2.delete();
                }else if(file2.isDirectory()){
                    //是文件夹就递归
                    deleteFile(file2);
                    //空文件夹直接删除
                    file2.delete();
                }
            }
        }

    }





}
