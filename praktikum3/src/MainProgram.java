/*
 * Test generic SingleList.java
 * 
 * @author: Lely Hiryanto
 */
import mylinkedlist.SingleList;

class MyData {
    int id;
    String name;

    MyData(int theid, String thename) {
        id = theid;
        name = thename;
    }
    @Override
    public String toString() {
        return (Integer.toString(id) + ": " + name + " ");
    }
}

public class MainProgram {    
    public static void main(String[] args) {
        //buat object 'List' dari class SingleList untuk data Integer
		SingleList<Integer> List1 = new SingleList<>();
        List1.pushQ(10);
        List1.pushQ(20);
        List1.pushQ(30);
        List1.cetakList();

        //buat object 'List2' dari class SingleList untuk data character
        SingleList<Character> List2 = new SingleList<>();
        List2.pushS('a');
        List2.pushS('b');
        List2.pushS('c');
        String out;
        while( !List2.isEmpty()) {
            out = List2.remove().toString();
            System.out.print(out);
        }
        System.out.println();

        //buat object 'List3' dari class SingleList untuk'
        //object dari class MyData
        SingleList<MyData> List3 = new SingleList<>();
        MyData data1 = new MyData(1, "Lely");
        MyData data2 = new MyData(2, "Hiryanto");
        MyData data3 = new MyData(3, "Balok");
        List3.pushS(data1);
        List3.pushS(data2);
        List3.pushS(data3);
        List3.cetakList();
        if(List3.remove(data2)) System.out.println("Berhasil!");
        List3.cetakList();
    }
}
