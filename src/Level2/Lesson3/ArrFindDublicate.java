package Level2.Lesson3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ArrFindDublicate {

    static ArrayList<String> array_of_words  = new ArrayList<>();
    public static void main(String[] args){
        array_of_words.add("первый");
        array_of_words.add("второй");
        array_of_words.add("второй");
        array_of_words.add("третий");
        array_of_words.add("третий");
        array_of_words.add("третий");
        array_of_words.add("четвертый");
        array_of_words.add("четвертый");
        array_of_words.add("четвертый");
        array_of_words.add("четвертый");
        array_of_words.add("пятый");
        array_of_words.add("пятый");
        array_of_words.add("пятый");
        array_of_words.add("пятый");
        array_of_words.add("пятый");
        System.out.println(array_of_words);
        //вывод уникальных значений
        Set<String> unique_words = new HashSet<String>(array_of_words);
        System.out.println(unique_words);
        //подсчет кол-ва вхождений слова в массив слов
        for(String str:unique_words)
            System.out.println(str+" "+Collections.frequency(array_of_words, str));
        }
}

