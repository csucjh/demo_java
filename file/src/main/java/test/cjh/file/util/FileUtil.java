package test.cjh.file.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {

    public static List<String> getTxtFile(String filePath) {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(filePath);
            br = new BufferedReader(fr);

            String data = null;
            List<String> list = new LinkedList<String>();
            while ((data = br.readLine()) != null) {
                list.add(data);
            }

            return list;
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void writeTxtFile(List<String> list, String filePath) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(filePath, true);
            bw = new BufferedWriter(fw);

            for (String s : list) {
                if (s != null) {
                    bw.write(s);
                    bw.newLine();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeTxtFile(String str, String filePath) {
        if (StringUtils.isBlank(str)) {
            return;
        }
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(filePath, true);
            bw = new BufferedWriter(fw);

            bw.write(str);
            bw.newLine();
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
