public class Banknot500 extends Banknoty {
    int ilosc;

    @Override
    void ilosc() {
        this.ilosc = 100;
    }

    @Override
    int wartosc() {
        return this.ilosc*500;
    }

    @Override
    void zmniejszIlosc(int oIle) {
        ilosc -= oIle;
    }

    @Override
    void zwiekszIlosc(int oIle) {
        ilosc += oIle;
    }

    @Override
    int getIlosc() {
        return ilosc;
    }


}