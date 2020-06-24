package Level2.Lesson5;

public class UseThread2 {
    static final int size = 10000000;
    static final int h = size / 2;
    static final float[] arr = new float[size];

    static class FillArrThread extends Thread {
        private float[] arr;
        public FillArrThread(float[] _arr) {
            super();
            this.arr = _arr;
        }
        @Override
        public void run() {
            for(int i=0;i<arr.length;i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }

    public static void FillArr() throws InterruptedException {
        //2
        for(int i=0;i<size;i++){
            arr[i]=1;
        }
        //разбиение
        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        //вычисление
        FillArrThread f1 = new FillArrThread(a1);
        f1.run();
        FillArrThread f2 = new FillArrThread(a2);
        f2.run();
        f1.join();
        f2.join();
        //склейка
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        //результат
        long b = System.currentTimeMillis();
        System.out.println("Время выполнения, мс: " +(b - a));
        //Время выполнения, мс: 3764
    }
    public static void main(String[] args) throws InterruptedException {
        FillArr();
    }
}
