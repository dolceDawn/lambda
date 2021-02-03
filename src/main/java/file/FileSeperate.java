package file;

import java.io.*;

public class FileSeperate {
    public static void main(String[] args) throws IOException {
        File f = new File("D:/file/GT12_Sep_2019.txt");
        BufferedReader bf = new BufferedReader(new FileReader(f));
        String str;


        FileWriter fw = new FileWriter(new File("D:/file/result.txtt"));
        //写入中文字符时会出现乱码
        BufferedWriter bw=new BufferedWriter(fw);

        while((str=bf.readLine())!=null)
        {
            String[] s=str.split("\t");

            String wline = s[0]+","+s[1]+","+s[1162];
            bw.write(wline+"\t\n");

        }
        bf.close();
        fw.close();
    }



}
