package ch.zhaw.prog2.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;


public class FileCopy {

	public static void main(String[] args) throws IOException {

		/* Schreiben Sie ein Program welches Kopien der Dateien des Verzeichnisses "files" erstellt. */

		/* Teilaufgabe a - Verzeichnisstruktur
		 * Das Quell-Verzeichnis soll als Konsolenargument uebergeben und auf Korrektheit ueberprueft werden.
         * Korrekt bedeutet, dass das Verzeichnis existiert und ausser zwei Dateien mit den Namen rmz450.jpg
         * und rmz450-spec.txt nichts weiter enthaelt.
         *
         * C:\Users\Sydney Nguyen\Documents\ZHAW\Vollzeit FS20\Prog2\Praktika\prog2-lab05-it19azh-sydneynguyencs\handout\ByteCharStream>
         * gradle run --args="./files"

         */
		System.out.print("Quell-Verzeichnis: ");
		String sourcePath = args.length >= 1 ? args[0] : "./files"; // gradle run --args="./files"
		File file = new File(sourcePath);
		if(!file.isDirectory()) {
		    System.out.println("File is not a Directory.");
		    return;
        }
        if(file.listFiles().length != 2) {
            System.out.println("There should be only 2 files in this directory.");
            return;
        }
        if((file.listFiles()[0].getName().equals("rmz450.jpg") && file.listFiles()[1].getName().equals("rmz450-spec.txt"))
                || (file.listFiles()[1].getName().equals("rmz450.jpg") && file.listFiles()[0].getName().equals("rmz450-spec.txt"))) {
		    System.out.println("Correctness checked.");
        } else {
		    System.out.println("Falsche Dateinamen.");
		    System.out.println(file.listFiles()[0].getName());
            System.out.println(file.listFiles()[1].getName());

        }


		/* Teilaufgabe b - Kopieren von Dateien
		 * Jede Datei soll zweimal kopiert werden, einmal zeichen-orientiert und einmal byte-orientiert.
         * Dazu soll die jeweilige Datei geoeffnet und Element fuer Element gelesen und ebenso wieder geschrieben werden.
         * Die Kopien sollen so benannt werden, damit aus dem Dateinamen hervorgeht, mit welcher Methode sie erstellt wurde.
         * Oeffnen Sie die Kopien anschliessend mit einem entsprechenden Programm und erklaeren Sie die entsprechenden Effekte.
         * Oeffnen Sie die Kopien anschliessend mit einem HEX-Editor und erklaeren Sie die Gruende fuer die Effekte.

         * FileReader(source, StandardCharsets.ISO_8859_1): char Zeichenweise
         * BufferedReader(new InputStreamReader(new FileInputStream(String path))): char Elementeweise
         * FileInputStream(String path): bytewise
         *
         *
         * */

		String targetDir = args.length >= 1 ? args[0] : "./out";
		new File(targetDir).mkdir();

		for(File f : file.listFiles()) {
		    String targetPath = targetDir + "/" + f.getName() + ".char.out";
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f.getAbsolutePath())));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath)))) {
                String line;
                do {
                    line = reader.readLine();
                    writer.write(line);
                    writer.newLine(); //sonst alles auf einer line
                }
                //while(reader.readLine() != null); falsch, sonst schreibt es jede zweite zeile nicht aus, da hier verwendet
                while (line != null);
            } catch(IOException | NullPointerException e ) {e.getMessage();}

            String targetPath2 = targetDir + "/" + f.getName() + ".byte.out";
            try (InputStream in = new FileInputStream(f.getAbsolutePath());
                 OutputStream out = new FileOutputStream(targetPath2)){
                int charValue;
                do {
                    charValue = in.read();
                    out.write(charValue);
                } while (charValue != -1);
            } catch(IOException e) {e.getMessage();}
        }





	}
}
