import java.util.Random;

class MyTestingClass{
    private int val;
    public MyTestingClass(int v) {
        this.val = v;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(val);
    }
}

public class Main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable<MyTestingClass, String>();
        Random rand = new Random();
//        for (int i = 0; i < 10000; i++) {
//            int val = rand.nextInt(0, 9999999);
//            hashTable.put(new MyTestingClass(val), "Student " + val);
//        }
//
//        System.out.println(hashTable);

        MyTestingClass key = new MyTestingClass(1337);
        hashTable.put(key, "Test case");
        hashTable.replace(key, "New Test case", "Test case");
        System.out.println(hashTable.get(key));
    }
}