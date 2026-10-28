package pd8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
//        Set<UserAccount> hashSet = new HashSet<>();
//        UserAccount user1 =
//            new UserAccount(2L, "asdasd@gmail.com", "xyz");
//        UserAccount user2 =
//            new UserAccount(2L, "asdasd@gmail.com", "xyz");

        //Eksperyment A (UserAccountV1)
//        hashSet.add(user1);
//        hashSet.add(user2);
//
//        System.out.println(hashSet.size());
//        System.out.println("user1.equals(user2): " + user1.equals(user2));
//        hashSet.clear();
        // przez to że nie nadpisaliśmy equals() i hashCode()
        // dodało nam duplikat, bo program widzi to jako 2 różne obiekty - mimo że zawartość jest ta sama

        //Eksperyment B (UserAccountV2)
//        hashSet.add(user1);
//        hashSet.add(user2);
//        System.out.println(hashSet.size());
//        System.out.println(
//            "contains: " + hashSet.contains( new UserAccount(2L, "test@gmail.com", "xyz")
//                )
//        );
        // znowu mamy ten sam element w hashSet, bo on korzysta zarówno z equals jak i hashCode()
        // a to HashSet szuka elementu na podstawie kodu haszującego - jako że nei nadpisaliśmy metody,
        // dla kompilatora są to 2 różne obiekty

        //Eksperyment C (UserAccountV3)

//        //hashset
//        hashSet.add(user1);
//        hashSet.add(user2);
//        System.out.println(hashSet.contains(user2));
//        System.out.println(
//            "contains: " + hashSet.contains( new UserAccount(2L, "test@gmail.com", "xyz")
//                )
//        );
//
//        //hashmap
//        Map<UserAccount, String> hashMap = new HashMap<>();
//        hashMap.put(user1,"user1");
//        hashMap.put(user2,"user2");
//        System.out.println(hashMap.size()); // 1
//        System.out.println(hashMap.get(user2)); // user2
//        System.out.println(hashMap.containsKey(user2)); // true

        //teraz program działa poprawnie, bo sprawdza najpierw hashcode, a potem leci equals - i jest git

        //Eksperyment D - dodanie statusu

        UserAccount userV4 = new UserAccount(1L, "test@gmail.com", "Pawel", "ACTIVE");
        Set<UserAccount> hashSet2 = new HashSet<>();
        hashSet2.add(userV4);
        System.out.println("Przed zmianą:");
        System.out.println(hashSet2.contains(userV4)); // true
        userV4.setStatus("INACTIVE");
        System.out.println("Po zmianie:");
        System.out.println(hashSet2.contains(userV4)); //  false
        System.out.println(hashSet2.remove(userV4));   //  false
        System.out.println(hashSet2.size());

        //wydaje mi się że przez to że zmieniliśmy stattus, kod obiektu się zmienił
        // przez to HashSet szuka go według nowego kodu, ale go nie znajduje - dzięki czemu też go nie może usunąć

        //wyczytałem że jeżeli chcemy zmieniać sobie statusy, to lepiej wywalić go z HashSeta, zmienić co chcemy, a potem go znowu dodać
    }

}
