import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Bankomat {

    private int stanBankomatu;
    private ArrayList <Integer> banknotyIloscList = new ArrayList();
    Banknot20 b20 = new Banknot20();
    Banknot50 b50 = new Banknot50();
    Banknot100 b100 = new Banknot100();
    Banknot200 b200 = new Banknot200();
    Banknot500 b500 = new Banknot500();


    public Bankomat() {

        b20.ilosc();
        b50.ilosc();
        b100.ilosc();
        b200.ilosc();
        b500.ilosc();


        banknotyIloscList.add(b20.getIlosc());
        banknotyIloscList.add(b50.getIlosc());
        banknotyIloscList.add(b100.getIlosc());
        banknotyIloscList.add(b200.getIlosc());
        banknotyIloscList.add(b500.getIlosc());

    }

    public void setBanknotyIloscList(){
        banknotyIloscList.set(0,b20.getIlosc());
        banknotyIloscList.set(1,b50.getIlosc());
        banknotyIloscList.set(2,b100.getIlosc());
        banknotyIloscList.set(3,b200.getIlosc());
        banknotyIloscList.set(4,b500.getIlosc());

        //zapisywanie nowego pliku
        try {
            FileWriter myWriter = new FileWriter("banknoty.txt");
            myWriter.write("20zł \t50zł \t100zł \t200zł \t500zł \n");
            for (Integer banknoty : banknotyIloscList) {
                myWriter.write(banknoty+" \t");
            }
            myWriter.write("\n");

            myWriter.close();
            System.out.println("Pomyślnie zapisano plik banknotów.");
        } catch (IOException ex) {
            System.out.println("Błąd zapisu.");
            ex.printStackTrace();
        }


    }

    public void setStanBankomatu(){
        stanBankomatu = 0;
        stanBankomatu += b20.wartosc();
        stanBankomatu += b50.wartosc();
        stanBankomatu += b100.wartosc();
        stanBankomatu += b200.wartosc();
        stanBankomatu += b500.wartosc();
    }

    public ArrayList getBanknotyIloscList() { return banknotyIloscList; }

    public int getStanBankomatu() {
        return stanBankomatu;
    }

    @Override
    public String toString() {
        return "Bankomat{" +
                "stanBankomatu=" + stanBankomatu +
                ", banknotyIloscList=" + banknotyIloscList +
                ", b20=" + b20.getIlosc() +
                ", b50=" + b50.getIlosc() +
                ", b100=" + b100.getIlosc() +
                ", b200=" + b200.getIlosc() +
                ", b500=" + b500.getIlosc() +
                '}';
    }
}
