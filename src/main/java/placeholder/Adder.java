package placeholder;

public class Adder{
    protected String placeholder;

    public Adder(String str){
        this.placeholder = str;
    }

    public int add(int first, int second){
        return first + second;
    }
}