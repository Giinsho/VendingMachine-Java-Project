import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Bank extends JFrame implements Naprawa,DoladowanieBankomatu{

    JButton logowanie,naprawa,uzupelnienie;
    Font font = new Font("Geneva",Font.ITALIC,18);
    Boolean dzialanie;
    Bankomat bankomat;

    public Bank(Bankomat Bankomat){

        this.bankomat = Bankomat;
        bankomat.setBanknotyIloscList();
        bankomat.setStanBankomatu();
        System.out.println(bankomat.getStanBankomatu());
        System.out.println(bankomat.getBanknotyIloscList());
        System.out.println(bankomat.toString());


        setTitle("Bankomat");
        setSize(300,375);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        logowanie = new JButton("Logowanie");
        logowanie.setPreferredSize(new Dimension(300,30));
        logowanie.setFont(font);

        naprawa = new JButton("Zgloś usterke bankomatu.");
        naprawa.setPreferredSize(new Dimension(300,30));
        naprawa.setFont(font);
        naprawa.setBackground(Color.YELLOW);

        uzupelnienie = new JButton("Bankomat jest pusty.");
        uzupelnienie.setPreferredSize(new Dimension(300,30));
        uzupelnienie.setFont(font);
        uzupelnienie.setBackground(Color.orange);

        add(logowanie);
        add(naprawa);
        add(uzupelnienie);

        //symulacja usterki - losowo
        Random random = new Random();
        dzialanie = random.nextBoolean();
        if(!dzialanie){
            uzupelnienie.setEnabled(false);
            logowanie.setEnabled(false);
            JOptionPane.showMessageDialog(null,"Bankomat jest uszkodzony!\nZgloś usterke.","Uwaga!",JOptionPane.WARNING_MESSAGE);

        }

        naprawa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!dzialanie){
                    dzialanie = Naprawianie();
                    JOptionPane.showMessageDialog(null,"Nasz serwisant postara się naprawić problem.","Dziękujemy!",JOptionPane.INFORMATION_MESSAGE);
                    try{
                        Thread.sleep(3000);
                    } catch(InterruptedException ie) { Thread.currentThread().interrupt(); }
                    JOptionPane.showMessageDialog(null,"Udało się naprawić problem!","Dziękujemy!",JOptionPane.INFORMATION_MESSAGE);
                    uzupelnienie.setEnabled(dzialanie);
                    logowanie.setEnabled(dzialanie);
                } else {
                    JOptionPane.showMessageDialog(null,"Bankomat działa poprawnie.","Informacja",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        uzupelnienie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean uzup = false;
                doladuj(bankomat);
                JOptionPane.showMessageDialog(null,"Wysłano służby, które uzupełnią zapasy.","Dziękujemy!",JOptionPane.INFORMATION_MESSAGE);
                try{
                    Thread.sleep(10000);
                } catch(InterruptedException ie) { Thread.currentThread().interrupt(); }
                uzupelnienie.setEnabled(uzup);
                logowanie.setEnabled(uzup);
                naprawa.setEnabled(uzup);
                JOptionPane.showMessageDialog(null,"Służby są już na miejscu!\nZe względów bezpieczeństwa bankomat zostanie tymczasowo wyłączony.","Informacja",JOptionPane.INFORMATION_MESSAGE);
                try{
                    Thread.sleep(5000);
                } catch(InterruptedException ie) { Thread.currentThread().interrupt(); }
                JOptionPane.showMessageDialog(null,"Uzupełniono zapasy.\nDziękujemy za Twoją cierpliwość.\nBankomat zostanie uruchomiony na nowo.","Informacja",JOptionPane.INFORMATION_MESSAGE);
                uzup = true;
                uzupelnienie.setEnabled(uzup);
                logowanie.setEnabled(uzup);
                naprawa.setEnabled(uzup);

                System.out.println(bankomat.getStanBankomatu());
                System.out.println(bankomat.getBanknotyIloscList());
                System.out.println(bankomat.toString());
            }
        });

        logowanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame loguj = new LogowanieOkno(bankomat);
                dispose();
            }
        });
    }

    public static void main(String[] args){
        Bankomat bankomat = new Bankomat();
        JFrame Okno = new Bank(bankomat);
    }
}
