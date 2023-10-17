import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Metin {
    private File dosya;
    private String[] alfabe = {" ","a","b","c","ç","d","e","f","g","ğ","h"," ",
    "ı","i","j","k","l","m","n"," ","o","ö","p","r","s"," ","ş","t","u","ü","v","y","z"};
    public Metin(){
        dosya = new File("metin.txt");
        if(!dosya.exists()){
            try {
                dosya.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dosyaTemizle();
        dosyaOlustur();
        String[] kelimeler = kelimeBul();
        System.out.println("Kelime Sayisi : "+kelimeler.length);
        String enUzun = uzunKelime(kelimeler);
        String enKisa = kisaKelime(kelimeler);
        System.out.println("En uzun : "+enUzun+" harf sayisi : "+enUzun.length());
        System.out.println("En kisa : "+enKisa+" harf sayisi : "+enKisa.length());
    }

    private String kisaKelime(String[] dizi) {
        String kelime = dizi[0];
        for (int i = 1; i < dizi.length; i++) {
            if(kelime.length() > dizi[i].length()){
                kelime = dizi[i];
            }
        }
        return kelime;
    }

    private String uzunKelime(String[] dizi) {
        String kelime = dizi[0];
        for (int i = 1; i < dizi.length; i++) {
            if(kelime.length() < dizi[i].length()){
                kelime = dizi[i];
            }
        }
        return kelime;
    }

    private String[] kelimeBul() {
        String metin = dosyaOku();
        String[] dizi = metin.split(" ");
        int sayac = 0;
        for (int i = 0; i < dizi.length; i++) {
            if(!dizi[i].isBlank()) sayac++;
        }
        String[] kelime = new String[sayac];
        int indis = 0;
        for (int i = 0; i < dizi.length; i++) {
            if(!dizi[i].isBlank()){
                kelime[indis]= dizi[i];
                indis++;
            }
        }
        return kelime;
    }

    private String dosyaOku() {
        String metin = "";
        try {
            Scanner scanner = new Scanner(dosya);
            metin = scanner.nextLine();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return metin;
    }

    private void dosyaTemizle() {
        try {
            FileWriter fileWriter = new FileWriter(dosya,false);
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void dosyaOlustur() {
        Random rnd = new Random();
        for (int i = 0; i < 500; i++) {
            int indis = rnd.nextInt(alfabe.length);
            dosyaYaz(alfabe[indis]);
        }

    }

    private void dosyaYaz(String harf) {
        try {
            FileWriter fileWriter = new FileWriter(dosya,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(harf);
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
