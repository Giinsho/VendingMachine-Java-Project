public class Banknot50 extends Banknoty {
    int ilosc;

    @Override
    void ilosc() {
        this.ilosc = 800;
    }

    @Override
    int wartosc() {
        return this.ilosc*50;
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