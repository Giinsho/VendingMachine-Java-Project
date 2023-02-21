import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class WplataOkno extends JFrame {
    JButton wplac,powrot;
    JLabel JLkwota;
    JTextField JTkwota;
    Font font = new Font("Geneva",Font.ITALIC, 18);
    Bankomat bankomat;

    int  n = 5;
    int numerObslugiwanegoUzytkownika = 0;
    ArrayList<String>[] al = new ArrayList[n];

    int kwota;
    Konto osoba;
    public WplataOkno(Konto osoby,Bankomat Bankomat){

        this.bankomat = Bankomat;

        this.osoba = osoby;


        setTitle("Wplata");
        setSize(380,250);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(0,20,20));

        JLkwota = new JLabel("Kwota:");
        JTkwota = new JTextField("");
        wplac = new JButton("Wpłać");
        powrot = new JButton("Powrót");

        JLkwota.setPreferredSize(new Dimension(75,30));
        JTkwota.setPreferredSize(new Dimension(225,30));
        powrot.setPreferredSize(new Dimension(150,30));
        wplac.setPreferredSize(new Dimension(150,30));

        JLkwota.setFont(font);
        JTkwota.setFont(font);
        powrot.setFont(font);
        wplac.setFont(font);

        add(JLkwota);
        add(JTkwota);
        add(wplac);
        add(powrot);

        wplac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    float zmiennaPomFLOAT;
                    int Ilosc20 = 0, Ilosc50 = 0 ,Ilosc100 = 0,Ilosc200 =0 ,Ilosc500 = 0;

                    kwota = Integer.parseInt(JTkwota.getText());
                    if(kwota/10 == 1 || kwota/10 == 3 || kwota < 20 || kwota%10 > 0){
                        JOptionPane.showMessageDialog(null,"Bankomat nie przyjmuje takich nominałów.","Błąd.",JOptionPane.INFORMATION_MESSAGE);
                        throw new IllegalArgumentException("Bankomat nie przyjmuje takich nominałów.");
                    } else {

                        int zmienna = kwota;
                        zmiennaPomFLOAT = osoba.getStanKonta();
                        if(kwota/500 > 0){
                            Ilosc500 = kwota/500;
                            kwota -= Ilosc500*500;
                        }
                        if(kwota/200 > 0){
                            Ilosc200 = kwota/200;
                            kwota -= Ilosc200*200;
                        }

                        if(kwota/100 > 0){
                            Ilosc100 = kwota/100;
                            kwota -= Ilosc100*100;
                        }

                        if((kwota/50 > 0  && kwota%50 != 10 && kwota%50 != 30 && kwota%50 !=20) || kwota == 70){
                            Ilosc50 = kwota/50;
                            kwota -= Ilosc50*50;
                        }

                        if(kwota/20 > 0){
                            Ilosc20 = kwota/20;
                            kwota -= Ilosc20*20;
                        }

                        kwota = zmienna;
                        osoba.Wplac(kwota);
                        if(!(osoba.getStanKonta() == zmiennaPomFLOAT)){
                            bankomat.b500.zwiekszIlosc(Ilosc500);
                            bankomat.b200.zwiekszIlosc(Ilosc200);
                            bankomat.b100.zwiekszIlosc(Ilosc100);
                            bankomat.b50.zwiekszIlosc(Ilosc50);
                            bankomat.b20.zwiekszIlosc(Ilosc20);
                            bankomat.setBanknotyIloscList();
                            System.out.println(osoba.getStanKonta());
                            System.out.println(bankomat.toString());
                            JOptionPane.showMessageDialog(null,"Na konto wpłacono kwotę: "+kwota+"zł.","Error",JOptionPane.INFORMATION_MESSAGE);
                        }


                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Podano błędną kwotę.","Błąd.",JOptionPane.INFORMATION_MESSAGE);
                }

                String[] dane = new String[6];
                float SALDO = osoba.getStanKonta();

                //odczytuje plik i dodaje dane na liste
                try(BufferedReader fileReader = new BufferedReader(new FileReader(new File("dane.txt")))){

                    for (int i = 0; i < n; i++) {
                        al[i] = new ArrayList<String>();
                    }
                    String wiersz = fileReader.readLine();
                    int i = 0;

                    while((wiersz = fileReader.readLine()) != null){
                        dane = wiersz.split("\t");

                        //sprawdzenie uzytkownika
                        if(dane[0].equals(osoba.getLogin()) || dane[4].equals( String.valueOf(osoba.getNrtel()) )){
                            numerObslugiwanegoUzytkownika = i;
                        }

                        for(int j = 0 ; j < 6 ; j++){
                            al[i].add(dane[j]);
                        }
                        i++;
                    }

                    System.out.println(numerObslugiwanegoUzytkownika);
                    al[numerObslugiwanegoUzytkownika].remove(5);
                    al[numerObslugiwanegoUzytkownika].add(5,String.valueOf(SALDO));

                    {
                        for (i = 0; i < n; i++) {
                            for (int j = 0; j < al[i].size(); j++) {
                                System.out.print(al[i].get(j) + " ");
                            }
                            System.out.println();
                        }
                    }

                } catch(FileNotFoundException ey){
                    System.out.println("Nie odnaleziono pliku.");
                    ey.printStackTrace();
                } catch(IOException ex){
                    System.out.println("Plik uszkodzony.");
                }

                //zapisywanie nowego pliku
                try {
                    FileWriter myWriter = new FileWriter("dane.txt");
                    myWriter.write("login\tpin\timie\tnazwisko\tnumertelefonu\tsaldo\n");
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < al[i].size(); j++) {
                            myWriter.write(al[i].get(j) + "\t");
                        }
                        myWriter.write("\n");
                    }

                    myWriter.close();
                    System.out.println("Pomyślnie zapisano plik.");
                } catch (IOException ex) {
                    System.out.println("Błąd zapisu.");
                    ex.printStackTrace();
                }
            }
        });

        powrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame BankomatOkno = new BankomatOkno(osoba,bankomat);
            }
        });
    }
}
