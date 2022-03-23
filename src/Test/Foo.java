public class Foo extends Person {
    public String name;

    public Foo(String name) { //"zxcv"
        super(name);
        this.name = "asdf";
        System.out.println("Person name : " + super.name);
    }
}
