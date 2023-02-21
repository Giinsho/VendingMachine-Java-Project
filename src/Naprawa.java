public interface Naprawa{
    boolean bankomatDzialanie = true;

    default Boolean Naprawianie(){
        return bankomatDzialanie;
    }
}
