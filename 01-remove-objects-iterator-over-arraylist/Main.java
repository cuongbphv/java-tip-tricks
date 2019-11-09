import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws Exception {

    List<String> myList = new ArrayList<>();
    myList.add("1");
    myList.add("2");
    myList.add("3");
    myList.add("4");

    // ArrayList Before
    System.out.println(myList);

    // removing element using ArrayList's remove method during iteration
    // This will throw ConcurrentModification if use method ArrayList.remove(object)

    Iterator<String> itr = myList.iterator();
    while (itr.hasNext()) {
      if (itr.next().equals("3")) {
        itr.remove();
      }
    }

    // ArrayList After
    System.out.println(myList);
  }

}