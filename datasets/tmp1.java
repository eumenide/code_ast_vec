public class Test{

    Test(){
        super();
    }

    public static void test(){
        test();
        super.test();
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}