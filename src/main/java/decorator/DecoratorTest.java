package decorator;

public class DecoratorTest {

    public static void main(String[] args) {
        LaoWang laoWang = new LaoWang();
        Jacket jacket = new Jacket(laoWang);
        Hat hat = new Hat(jacket);
        hat.show();
    }

}
