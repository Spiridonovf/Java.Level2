package Level2.Lesson2;

public class MyArrayDataException extends Exception {
    protected int i;
    protected int j;

    public MyArrayDataException() {
        super();
    }
    public MyArrayDataException(String message, int _i, int _j) {
        super(message);
        this.i = _i;
        this.j = _j;
    }
}