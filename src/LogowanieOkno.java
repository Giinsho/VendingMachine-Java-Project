import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class LogowanieOkno extends JFrame{



    JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,back,ok,clear,StronaStartowa;
    JLabel jLogin,jPin;
    JTextField JTlogin,jPinSet;
    Font font = new Font("Geneva",Font.ITALIC, 18);

    GridBagConstraints c = new GridBagConstraints();

    Bankomat bankomat;

    public LogowanieOkno(Bankomat Bankomat){

        this.bankomat = Bankomat;

        setTitle("Logowanie");
        setSize(300,375);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        //odstep
        c.insets = new Insets(4,4,4,4);

        //loginLabel
        jLogin = new JLabel("Login:");
        jLogin.setFont(font);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 0;
        add(jLogin,c);

        //loginText
        JTlogin = new JTextField("");
        JTlogin.setFont(font);
        c.ipadx = 112;
        c.gridx = 1;
        c.gridwidth = 2;
        c.gridy = 0;
        add(JTlogin,c);
        //poprzednie ustawienia

        c.ipadx = 10;

        //pinLabel
        jPin = new JLabel("Pin:            ");
        jPin.setFont(font);
        c.gridx = 0;
        c.gridy = 1;
        add(jPin,c);

        //pinLabelSet
        jPinSet = new JTextField("");
        jPinSet.setEditable(false);
        jPinSet.setFont(font);
        c.ipadx = 112;
        c.gridx = 1;
        c.gridwidth = 3;
        c.gridy = 1;
        add(jPinSet,c);
        //poprzednie ustawienia
        c.gridwidth = 1;
        c.ipadx = 8;

        //1
        b1 = new JButton("1");
        b1.setFont(font);
        c.gridx = 0;
        c.gridy = 2;
        add(b1,c);

        //2
        b2 = new JButton("2");
        b2.setFont(font);
        c.gridx = 1;
        c.gridy = 2;
        add(b2,c);

        //3
        b3 = new JButton("3");
        b3.setFont(font);
        c.gridx = 2;
        c.gridy = 2;
        add(b3,c);

        //4
        b4 = new JButton("4");
        b4.setFont(font);
        c.gridx = 0;
        c.gridy = 3;
        add(b4,c);

        //5
        b5 = new JButton("5");
        b5.setFont(font);
        c.gridx = 1;
        c.gridy = 3;
        add(b5,c);

        //6
        b6 = new JButton("6");
        b6.setFont(font);
        c.gridx = 2;
        c.gridy = 3;
        add(b6,c);

        //7
        b7 = new JButton("7");
        b7.setFont(font);
        c.gridx = 0;
        c.gridy = 4;
        add(b7,c);

        //8
        b8 = new JButton("8");
        b8.setFont(font);
        c.gridx = 1;
        c.gridy = 4;
        add(b8,c);

        //9
        b9 = new JButton("9");
        b9.setFont(font);
        c.gridx = 2;
        c.gridy = 4;
        add(b9,c);

        //0
        b0 = new JButton("0");
        b0.setFont(font);
        c.gridx = 1;
        c.gridy = 5;
        add(b0,c);

        //OK
        ok = new JButton("Ok");
        ok.setFont(font);
        c.gridx = 0;
        c.gridy = 6;
        add(ok,c);

        //back
        back = new JButton("⟵");
        back.setFont(font);
        c.gridx = 1;
        c.gridy = 6;
        add(back,c);

        //clear
        clear = new JButton("Czyść");
        clear.setFont(font);
        c.ipadx = 40;
        c.gridx = 2;
        c.gridwidth = 3;
        c.gridy = 6;
        add(clear,c);

        //strona startowa
        StronaStartowa = new JButton("Menu główne");
        StronaStartowa.setFont(font);
        c.ipadx = 50;
        c.gridx = 0;
        c.gridy = 7;
        add(StronaStartowa,c);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPinSet.setText(jPinSet.getText()+"1");
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPinSet.setText(jPinSet.getText()+"2");
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPinSet.setText(jPinSet.getText()+"3");
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPinSet.setText(jPinSet.getText()+"4");
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPinSet.setText(jPinSet.getText()+"5");
            }
        });

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPinSet.setText(jPinSet.getText()+"6");
            }
        });

        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPinSet.setText(jPinSet.getText()+"7");
            }
        });

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPinSet.setText(jPinSet.getText()+"8");
            }
        });

        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPinSet.setText(jPinSet.getText()+"9");
            }
        });

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPinSet.setText(jPinSet.getText()+"0");
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTlogin.setText("");
                jPinSet.setText("");
            }
        });

        //usuwanie ostatniego znaku
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jPinSet.getText().isEmpty()){
                    jPinSet.setText("");
                } else {
                    String pintext;
                    pintext = jPinSet.getText();
                    pintext = pintext.substring(0,pintext.length()-1);
                    jPinSet.setText(pintext);
                }

            }
        });

        //przejscie do nowego okna
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    int  n = 5;
                    int numerObslugiwanegoUzytkownika = 0;
                    Boolean czyIstnieje = false;
                    String log = JTlogin.getText();
                    String pin = jPinSet.getText();
                    ArrayList<String>[] al = new ArrayList[n];

                    //sprawdzam czy zostaly wprowadzone dane logowania
                    if(log.isEmpty() && pin.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Wystąpił błąd przy podawaniu danych.","Error",JOptionPane.ERROR_MESSAGE);
                        throw new IllegalArgumentException("Wystąpił błąd przy wprowadzaniu danych.");
                    }

                    //odczytuje plik i dodaje dane na liste
                    try(BufferedReader fileReader = new BufferedReader(new FileReader(new File("dane.txt")))){
                        for (int i = 0; i < n; i++) {
                            al[i] = new ArrayList<String>();
                        }
                        String wiersz = fileReader.readLine();
                        int i = 0;

                        while((wiersz = fileReader.readLine()) != null){
                                String[] dane = wiersz.split("\t");

                                //sprawdzenie czy istnieje uzytkownik
                                if(dane[0].equals(log) && dane[1].equals(pin) ){
                                    czyIstnieje = true;
                                    numerObslugiwanegoUzytkownika = i;
                                }
                                for(int j = 0 ; j < 6 ; j++){
                                    al[i].add(dane[j]);
                                }
                                i++;
                        }
                        if(!czyIstnieje){
                            JOptionPane.showMessageDialog(null,"Taki użytkownik nie istnieje.","Brak użytkownika",JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            for (i = 0; i < n; i++) {
                                for (int j = 0; j < al[i].size(); j++) {
                                    System.out.print(al[i].get(j) + " ");
                                }
                                System.out.println();
                            }
                            dispose();

                            String LOGIN = al[numerObslugiwanegoUzytkownika].get(0);
                            int PIN =  Integer.parseInt(al[numerObslugiwanegoUzytkownika].get(1));
                            String IMIE = al[numerObslugiwanegoUzytkownika].get(2);
                            String NAZWISKO = al[numerObslugiwanegoUzytkownika].get(3);
                            int NUMERTEL = Integer.parseInt(al[numerObslugiwanegoUzytkownika].get(4));
                            float SALDO = Float.parseFloat(al[numerObslugiwanegoUzytkownika].get(5));

                            Konto noweLogowanie = new Konto(LOGIN,PIN,IMIE,NAZWISKO,NUMERTEL,SALDO);
                            System.out.println(noweLogowanie.toString());
                            JFrame bankomatOKNO = new BankomatOkno(noweLogowanie,bankomat);
                        }
                    } catch(FileNotFoundException ey){
                        System.out.println("Nie odnaleziono pliku.");
                        ey.printStackTrace();
                    } catch(IOException ex){
                        System.out.println("Plik uszkodzony.");
                    }
            }
        });

        //przejscie na strone startowa
        StronaStartowa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame bank = new Bank(bankomat);
            }
        });
    }

}
