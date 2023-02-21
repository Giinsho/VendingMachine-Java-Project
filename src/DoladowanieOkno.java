import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class DoladowanieOkno extends JFrame {


    JButton j5,j10,j15,j20,j25,j30,j50,j100,powrot;
    JLabel doladuj;
    Font font = new Font("Geneva",Font.ITALIC, 18);
    Konto osoba;
    Bankomat bankomat;


    int  n = 5;
    int numerObslugiwanegoUzytkownika = 0;
    ArrayList<String>[] al = new ArrayList[n];

    public DoladowanieOkno(Konto osoby,Bankomat Bankomat) {
        this.osoba = osoby;
        this.bankomat = Bankomat;

        setTitle("Doladowanie:");

        setSize(360, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //odstep
        setLayout(new FlowLayout(0, 40, 10));

        //koncowka zł
        doladuj = new JLabel("Wybierz kwotę:");
        doladuj.setFont(font);
        doladuj.setPreferredSize(new Dimension(200,30));

        add(doladuj);

        // 5 zl
        j5 = new JButton("5");
        j5.setPreferredSize(new Dimension(100, 30));
        j5.setFont(font);
        add(j5);

        // 10 zl
        j10 = new JButton("10");
        j10.setPreferredSize(new Dimension(100, 30));
        j10.setFont(font);
        add(j10);

        // 15 zl
        j15 = new JButton("15");
        j15.setPreferredSize(new Dimension(100, 30));
        j15.setFont(font);
        add(j15);


        // 20 zl
        j20 = new JButton("20");
        j20.setPreferredSize(new Dimension(100, 30));
        j20.setFont(font);
        add(j20);


        // 25 zl
        j25 = new JButton("25");
        j25.setPreferredSize(new Dimension(100, 30));
        j25.setFont(font);
        add(j25);

        // 30 zl
        j30 = new JButton("30");
        j30.setPreferredSize(new Dimension(100, 30));
        j30.setFont(font);
        add(j30);

        // 50 zl
        j50 = new JButton("50");
        j50.setPreferredSize(new Dimension(100, 30));
        j50.setFont(font);
        add(j50);

        // 100 zl
        j100 = new JButton("100");
        j100.setPreferredSize(new Dimension(100, 30));
        j100.setFont(font);
        add(j100);

        //powrot
        powrot = new JButton("Powrót");
        powrot.setPreferredSize(new Dimension(120, 30));
        powrot.setFont(font);
        add(powrot);


        //doladowanie kwota 5zl
        j5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osoba.DoladowanieTelefonu(Integer.parseInt(j5.getText())); }
        });

        //doladowanie kwota 10zl
        j10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osoba.DoladowanieTelefonu(Integer.parseInt(j10.getText()));
            }
        });

        //doladowanie kwota 15zl
        j15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osoba.DoladowanieTelefonu(Integer.parseInt(j15.getText()));
            }
        });

        //doladowanie kwota 20zl
        j20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osoba.DoladowanieTelefonu(Integer.parseInt(j20.getText()));
            }
        });

        //doladowanie kwota 25zl
        j25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osoba.DoladowanieTelefonu(Integer.parseInt(j25.getText()));
            }
        });

        //doladowanie kwota 30zl
        j30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osoba.DoladowanieTelefonu(Integer.parseInt(j30.getText()));
            }
        });

        //doladowanie kwota 50zl
        j50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osoba.DoladowanieTelefonu(Integer.parseInt(j50.getText()));
            }
        });

        //doladowanie kwota 100zl
        j100.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osoba.DoladowanieTelefonu(Integer.parseInt(j100.getText()));
            }
        });

        powrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                dispose();
                JFrame BankomatOkno = new BankomatOkno(osoba,bankomat);
            }
        });

    }

}

