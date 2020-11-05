package ch.zhaw.prog2.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;


public class UnderstandingCharsets {
    public static void main(String args[]) throws IOException {


        /* Teilaufgabe a
         * In der Vorlesung haben Sie gelernt, dass Java-Klassen fuer Unicode entworfen wurden.
         * Nun ist Unicode aber nicht der einzige Zeichensatz und Java unterstuetz durchaus Alternativen.
         * Welche Zeichensaetze auf einem System konkret unterstuetzt werden haengt von der Konfiguration
         * des Betriebssystems JVM ab.
         * Schreiben Sie den Code, welcher alle unterstuetzten Zeichensaetze auf der Konsole (System.out) ausgibt,
         * zusammen mit dem Standardzeichensatz.
         * https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html
         */

        // Print default character set
        System.out.println(Charset.defaultCharset());

        // Print all available character sets
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for(Charset c : charsets.values()) {
            System.out.println(c);
        }

        /* Ende Teilaufgabe a */


        /* Teilaufgabe b
         * Schreiben Sie Code welcher im Standardzeichensatz zeichenweise (also Zeichen fuer Zeichen)
         * von der Konsole einliest und ebenso im Zeichensatz US_ASCII in eine Datei schreibt.
         * Die Eingabe des Zeichens 'q' soll das Program ordentlich beenden.
         * Die Datei soll "CharSetEvaluation.txt" genannt werden und wird entweder erzeugt oder wenn Sie
         * bereits existiert, einfach geoeffnet und der Inhalt uebeschrieben.
         * Lesen von der Konsole und Schreiben in die Datei soll leistungsoptimiert geschehen, also vom
         * jeweiligen Input-/Output-Medium entkoppelt.
         * Testen Sie Ihr Program mit den folgenden Eingabereihenfolge und Zeichen: a b c d € f g q
         * Oeffnen Sie die Textdatei nach Durchfuehrung des Programs mit einem Texteditor und erklaeren Sie
         * das Ergebnis.
         * Oeffnen Sie die Datei anschliessend mit einem HEX-Editor und vergleichen Sie.
         */
        String source = (args.length >= 1)? args[0] : "./source";
        String target = (args.length >= 1)? args[0] : "./CharSetEvaluation.txt";

        try (FileReader reader = new FileReader(source, StandardCharsets.ISO_8859_1); //ASCII does not contain "€"
             FileWriter writer = new FileWriter(target, StandardCharsets.ISO_8859_1)) {
            int charValue; //variable to hold value
            do {
                charValue = reader.read(); //read next byte from file
                if(charValue != 113) { //if not end of file(-1). 113 = "q"
                    writer.write(charValue);
                }
            } while(charValue != 113);
        } //reader and writer will be automatically closed here (->try-with-resource)
        catch(FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch(IOException e) {
            System.out.println("IO Error: "+ e.getMessage());
        }

    }
}

