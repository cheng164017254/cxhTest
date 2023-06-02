package com.ustb.evaluation.mod01common.utils.IOData;

import com.ustb.evaluation.mod01common.exception.PromptException;

import java.io.*;

//将对象写入到文件中，以及将对象从文件中读取出来
public class FileReadWrite {

    //将对象写入到文件中
    public static void Object2File(Object obj, String fileName) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
        objectOutputStream.writeObject(obj);
        objectOutputStream.close();
        fos.close();
    }

    //将对象写入到文件中
    public static Object File2Object(String fileName) throws Exception {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fis);
        Object obj = objectInputStream.readObject();
        objectInputStream.close();
        fis.close();
        return obj;
    }

    public static void writeToFile(String data, String fileName) throws Exception {
        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
        osw.append(data);
        osw.close();
        fos.close();
    }

    public static void copy(String src, String destDir, String destName) {

        //验证源文件路径
        File srcFile = new File(src);  //源文件
        if (!srcFile.exists()) {
            throw new PromptException("源文件不存在，请确认文件的路径是否正确");
        }

        //目标目录不存在，则创建
        File destDirFile = new File(destDir);  //拷贝文件的路径
        if (!destDirFile.getParentFile().exists()) { //先判断要拷贝的文件的父路径是否存在
            throw new PromptException("目标目录不存在，请确认拷贝目录是否正确");
        }

        String copyFileName = destDir + destName;
        File copyfile = new File(copyFileName);

        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(srcFile);
            output = new FileOutputStream(copyfile);
            //第二种文件读取的方式，这种方式在以后开发中运用较多，建议使用
            byte[] a = new byte[1024];  //建议值设为1024或者2048,太大会浪费空间
            int temp = 0;
            while ((temp = input.read(a)) != -1) {
                output.write(a, 0, temp);
            }

            input.close();
            output.close();
        } catch(java.io.IOException e) {
            throw new PromptException("拷贝文件不成功！");
        }
    }

}


