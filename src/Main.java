import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        class A {
            int a;

            @Override
            public String toString() {
                return "A: " + a;
            }
        }
        A a = new A();
        a.a = 24;
        ArrayList<A> arrayList = new ArrayList<>();
        arrayList.add(a);
        for (A aw : arrayList) {
            aw.a = 2;
        }
        System.out.println(a.a);
    }
}