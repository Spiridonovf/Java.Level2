package Level2.Lesson5;

public class UseТhread1 {
    static final int size = 10000000;
    static final int h = size / 2;
    static final float[] arr = new float[size];
    public static void FillArr(){
        //2
        for(int i=0;i<size;i++){
            arr[i]=1;
        }
        long a = System.currentTimeMillis();
        for(int i=0;i<size;i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        System.out.println("Время выполнения, мс: " +(b - a));
        //Время выполнения, мс: 6475
    }
    public static void main(String[] args) {
        FillArr();
    }
}
