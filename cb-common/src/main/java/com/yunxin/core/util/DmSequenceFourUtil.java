package com.yunxin.core.util;

import java.io.*;
import java.util.Random;

/**
 * 生成4位随机数
 */
public class DmSequenceFourUtil {
    static String randomWords[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9" };
    static int index1 = 0;
    static int index2 = 0;
    static int index3 = 0;
    static int index4 = 0;
    static boolean isRead = true;

    public static synchronized String getNoRepeatId() throws Exception {
        // 判断是否需要读取文件记录
        if (isRead) {
            String sequence = read("sequence.dat");
            if (sequence != null && sequence.length() == 4) {
                index1 = getIndex(String.valueOf(sequence.charAt(0))) + 1;
                index2 = getIndex(String.valueOf(sequence.charAt(1))) + 1;
                index3 = getIndex(String.valueOf(sequence.charAt(2))) + 1;
                index4 = getIndex(String.valueOf(sequence.charAt(3))) + 1;
            }
            isRead = false;
        }

        getRandom();
        String id = "" + randomWords[index1 == 0 ? 0 : index1 - 1]
                + randomWords[index2 == 0 ? 0 : index2 - 1]
                + randomWords[index3 == 0 ? 0 : index3 - 1] + randomWords[index4 - 1];
        write(id,"sequence.dat");
        return id;

    }
    private static int getIndex(String word) {
        for (int i = 0; i < randomWords.length; i++) {
            if (randomWords[i].equals(word)) {
                return i;
            }
        }
        return 0;
    }

    private static void getRandom() throws Exception {
        if (index4 < 62) {
            index4++;
        } else {
            index4 = 1;
            if (index3 < 62) {
                index3++;
            } else {
                index3 = 1;
                if (index2 < 62) {
                    index2++;
                } else {
                    index2 = 1;
                    if (index1 < 62) {
                        index1++;
                    } else {
                        throw new Exception("结束");
                    }
                }
            }
        }

    }


    private static void write(String sequence,String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file, false);
        fw.write(sequence);
        fw.flush();
        fw.close();
    }
    private static String read(String fileName) throws IOException {
        String sequence = "";
        File file = new File(fileName);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            sequence = br.readLine();
            br.close();
            fr.close();
        } catch (Exception e) {
            System.err.println("读取文件出错");
        }
        return sequence;
    }


    public static void main(String[] ager){
    try {
        for(int i=0;i<10;i++){
            System.out.println(getNoRepeatId());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
