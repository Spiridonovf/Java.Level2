package Level2.Lesson3;
import java.util.ArrayList;
import java.util.List;

// класс реализующий запись тел.справочника
class Record  {
    private final long id;
    private String phone;
    private String LastName;

    Record(long _id,String _phone, String _LastName) {
        this.id = _id;
        this.phone = _phone;
        this.LastName = _LastName;
    }
    public long getId() {
        return id;
    }
    public String getPhone() {
        return phone;
    }
    public String getLastName() {
        return LastName;
    }
}
//класс реализующий телефонную книгу
public class PhoneBook {

    private static ArrayList<Record> records = new ArrayList<Record>();
    private long nextId=0;
    private long getNextId() {
        long recordId = this.nextId+1;
        this.nextId = recordId;
        return recordId;
    }
    public void addRecord(String phone, String LastName){
        long recordId = getNextId();
        Record record = new Record(recordId, phone, LastName);
        records.add(record);
    }
    public static ArrayList<Record> getRecords() {
        return records;
    }
    private static List<Record> getbyLastName(String LastName){
        List<Record> result = new ArrayList<Record>();
        for (Record record : records){
            if (record.getLastName()==LastName){
                result.add(record);
            }
        }
        return result;
    }
    public static void PrintAllRecords(){
        System.out.println("========весь телефонный справочник======");
        for (Record rec : getRecords()){
            System.out.println(rec.getPhone()+" "+rec.getLastName());
        }
    }
    private static void PrintRecords(List<Record> recs){
        for (Record rec : recs){
            System.out.println(rec.getPhone()+" "+rec.getLastName());
        }
    }
    public static void FindByLastName(String LastName) {
        List<Record> result = getbyLastName(LastName);
        System.out.println("========поиск по фамилии "+LastName+"======");
        PrintRecords(result);
    }

    public static void main(String[] args){
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addRecord("+7-495-900-01-01","Иванов");
        phoneBook.addRecord("+7-495-900-01-02","Иванов");
        phoneBook.addRecord("+7-495-900-01-03","Иванов");
        phoneBook.addRecord("+7-495-900-01-04","Петров");
        phoneBook.addRecord("+7-495-900-01-05","Петров");
        phoneBook.addRecord("+7-495-900-01-06","Сидоров");
        PrintAllRecords();
        FindByLastName("Иванов");
        FindByLastName("Сидоров");
        FindByLastName("Петров");

    }


}
