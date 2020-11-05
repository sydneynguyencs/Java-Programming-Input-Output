package ch.zhaw.prog2.io;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DirList {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws IOException {
        String pathName = (args.length == 1) ? args[0] : ".";
        File file = new File(pathName);
        String res = "";
        // Write metadata of given file, resp. all of its files if it is a directory
        // Whith each file on one line in the following format.
        if(!file.exists()) {
            System.out.println("File: "+file.getName()+" does not exist.");
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File f : files) {
                getMeta(f, res);
            }

        }
    }

    static void getMeta(File file, String res) {
        // - type of file ('d'=directory, 'f'=file)
        res += file.isDirectory() ? "d" : "f";
        // - readable   'r', '-' otherwise
        res += file.canRead() ? "r" : "-";
        // - writable   'w', '-' otherwise
        res += file.canWrite() ? "w" : "-";
        // - executable 'x', '-' otherwise
        res += file.canExecute() ? "x" : "-";
        // - hidden     'h', '-' otherwise
        res += file.isHidden() ? "h " : "- ";
        // - modified date in format 'yyyy-MM-dd HH:mm:ss'
        res += df.format(file.lastModified());
        // - length in bytes
        res += String.format(" %7d ", file.length());
        // - name of the file
        res += file.getName();
        System.out.println(res);
    }

}
