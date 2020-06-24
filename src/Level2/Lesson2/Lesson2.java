package Level2.Lesson2;

public class Lesson2 {

    private static int arr_size=4;

    public static void main(String[] args) throws MyArraySizeException,MyArrayDataException {
        //массив с некорректным размером
        String[][] arr1 = {{"2", "4", "5", "6","7"},{"2", "4", "5", "6","7"},{"2", "4", "5", "6","7"},{"2", "4", "5", "6","7"}};
        //массив с некорретными ячейками
        String[][] arr2 = {{"2", "4", "5", "rr"},{"2", "4", "5", "6"},{"2", "4", "5", "6"},{"2", "4", "5", "6"}};
        //корректный массив
        String[][] arr3 = {{"2", "4", "5", "7"},{"2", "4", "5", "6"},{"2", "4", "5", "6"},{"2", "4", "5", "6"}};
        try {
            int result1 = SummArray(arr1);
            System.out.println("Сумма элементов массива: " +result1);
        } catch (MyArraySizeException ex){
            System.out.println(ex.getMessage());
        } catch (MyArrayDataException ex){
            System.out.println(ex.getMessage()+" ["+ex.i+","+ex.j+"]");
        }

        try {
            int result2 = SummArray(arr2);
            System.out.println("Сумма элементов массива: " +result2);
        } catch (MyArraySizeException ex){
            System.out.println(ex.getMessage());
        } catch (MyArrayDataException ex){
            System.out.println(ex.getMessage()+" коррдинаты:  ["+ex.i+","+ex.j+"]");
        }

        try {
            int result3 = SummArray(arr3);
            System.out.println("Сумма элементов массива: " +result3);
        } catch (MyArraySizeException ex){
            System.out.println(ex.getMessage());
        } catch (MyArrayDataException ex) {
            System.out.println(ex.getMessage()+" ["+ex.i+","+ex.j+"]");
        }
    }

    private static int SummArray(String arr[][]) throws MyArraySizeException,MyArrayDataException {
        boolean checksize = true;
        int summ_arr = 0;
            //проверка на размер и генерация исключения при превышении
            for (int i = 0; i < arr.length; i++) {
                if ((arr[i].length > arr_size) || (i > arr_size)) {
                    checksize = false;
                    break;
                }
            }
            if (!checksize){
                throw new MyArraySizeException("Ошибка размера");
            }
            //проверка на корректность данных и генерация исключения при некорректности
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[i].length;j++){
                    try{
                    summ_arr +=Integer.parseInt(arr[i][j]);
                    }  catch (NumberFormatException e)
                    {
                        throw new MyArrayDataException("Ошибка в данных: "+e.getMessage(),i,j);
                    }
                }
            }


        return summ_arr;
    }



}
