import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Integer> icerik = new ArrayList<Integer>();
    public static void dosyaOku()
     {
         try{
             FileInputStream in = new FileInputStream("marş.mp3");

             int oku;
             while( ( oku = in.read() )  != -1)
             {
                icerik.add(oku);
             }
         }
         catch (FileNotFoundException e)
         {
             System.out.println(e);
         }
         catch (IOException e)
         {
             System.out.println(e);
         }
     }

     public static void kopyala(String dosya_ismi)
     {
         try{
             FileOutputStream out = new FileOutputStream(dosya_ismi);
             for(int deger : icerik)
             {
                 out.write(deger);
             }
         }
         catch (FileNotFoundException e)
         {
             System.out.println(e);
         }
         catch (IOException e)
         {
             System.out.println(e);
         }

     }

     public static void main(String[] args)
     {
        dosyaOku();
        long baslangic = System.currentTimeMillis();

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                kopyala("marş2.mp3");
            }
        });
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                kopyala("marş3.mp3");
            }
        });

        Thread th3 = new Thread(new Runnable() {
            @Override
            public void run() {
                kopyala("marş4.mp3");
            }
        });


        th1.start();
        th2.start();
        th3.start();

        try{
            th1.join();
            th2.join();
            th3.join();
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }
        //thread işlemleri bittikten sonra buradan devam edecek
        long bitis = System.currentTimeMillis();


         System.out.println("3 dosya kopyalaması şu kadar sürdü : " + (( bitis - baslangic) / 1000) + " saniye ..");
         // Proje thread kullanılmadan 21 saniye gibi bir süre sürerken thread kullanımında 8 saniyeye düştü
         //Thread'ler işte böyle işlerimizi hızlardıran yapılardır.
         //Threading programlamayı doğru şekilde kullanılır ve doğru threading yöntemi uygulanırsa işlemlerin çok daha kısa bir süre içinde bittiği gözlemlenir.

     }
}