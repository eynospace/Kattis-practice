import java.util.*;

public class Comparing {
    public static void main(String[] args) {
        // base array used for populating all examples in this program
        int[] baseArr = {3, 7, 4, 3, 5, 6};
        
        
        // examples for MyInteger1 (implemented Comparable)
        MyInteger1[] arr1 = new MyInteger1[6];
        MyInteger1[] arr2 = new MyInteger1[6];
        MyInteger1[] arr3 = new MyInteger1[6];
        
        ArrayList<MyInteger1> list1 = new ArrayList<MyInteger1>();
        ArrayList<MyInteger1> list2 = new ArrayList<MyInteger1>();
        ArrayList<MyInteger1> list3 = new ArrayList<MyInteger1>();
        
        for (int i = 0; i < 6; i++) {
            arr1[i] = new MyInteger1(baseArr[i]);
            arr2[i] = new MyInteger1(baseArr[i]);
            arr3[i] = new MyInteger1(baseArr[i]);
            
            list1.add(new MyInteger1(baseArr[i]));
            list2.add(new MyInteger1(baseArr[i]));
            list3.add(new MyInteger1(baseArr[i]));
        }
        
        // sort by default ordering (ascending order)
        Arrays.sort(arr1);
        System.out.println("Array 1: " + Arrays.toString(arr1));
        Collections.sort(list1);
        System.out.println("List 1: " + list1);
        
        // sort by reverse ordering
        Arrays.sort(arr2, Comparator.reverseOrder());
        System.out.println("Array 2: " + Arrays.toString(arr2));
        Collections.sort(list2, Comparator.reverseOrder());
        System.out.println("List 2: " + list2);
        
        // sort by comparator (comparator uses descending order)
        Arrays.sort(arr3, new MyInteger1Comparator());
        System.out.println("Array 3: " + Arrays.toString(arr3));
        Collections.sort(list3, new MyInteger1Comparator());
        System.out.println("List 3: " + list3);
        
        
        // examples for MyInteger2 (Comparable not implemented)
        MyInteger2[] arr4 = new MyInteger2[6];
        MyInteger2[] arr5 = new MyInteger2[6];
        MyInteger2[] arr6 = new MyInteger2[6];
        
        ArrayList<MyInteger2> list4 = new ArrayList<MyInteger2>();
        ArrayList<MyInteger2> list5 = new ArrayList<MyInteger2>();
        ArrayList<MyInteger2> list6 = new ArrayList<MyInteger2>();
        
        for (int i = 0; i < 6; i++) {
            arr4[i] = new MyInteger2(baseArr[i]);
            arr5[i] = new MyInteger2(baseArr[i]);
            arr6[i] = new MyInteger2(baseArr[i]);
            
            list4.add(new MyInteger2(baseArr[i]));
            list5.add(new MyInteger2(baseArr[i]));
            list6.add(new MyInteger2(baseArr[i]));
        }
        
        // lines below commented out: will not even be able to compile
        // due to not implementing Comparable
        /*
        // sort by default ordering (ascending order)
        Arrays.sort(arr4);
        System.out.println("Array 4: " + Arrays.toString(arr4));
        Collections.sort(list4);
        System.out.println("List 4: " + list4);
        
        // sort by reverse ordering
        Arrays.sort(arr5, Comparator.reverseOrder());
        System.out.println("Array 5: " + Arrays.toString(arr5));
        Collections.sort(list5, Comparator.reverseOrder());
        System.out.println("List 5: " + list5);
        */
        
        // sort by comparator (comparator uses ascending order)
        Arrays.sort(arr6, new MyInteger2Comparator());
        System.out.println("Array 6: " + Arrays.toString(arr6));
        Collections.sort(list6, new MyInteger2Comparator());
        System.out.println("List 6: " + list6);
    }
}

// MyInteger1 implements the Comparable interface, so
// it can be directly used for sorting without using
// a Comparator
// The class definition generally follows the format of:
// class A implements Comparable<A>
class MyInteger1 implements Comparable<MyInteger1> {
    int value;
    
    public MyInteger1(int value) {
        this.value = value;
    }
    
    // a negative result means this < other,
    // a positive result means this > other,
    // a result of zero means this == other
    // as the result of the comparison
    // the method signature generally follows the format of:
    // public int compareTo(A other)
    // the variable name is not fixed; you can choose any name apart from "other"
    public int compareTo(MyInteger1 other) {
        // easy trick for comparing integers
        return value - other.value;
    }
    
    public String toString() {
        return String.valueOf(value);
    }
}

// MyInteger2 does not implement the Comparable interface,
// so it requires a Comparator to sort
class MyInteger2 {
    int value;
    
    public MyInteger2(int value) {
        this.value = value;
    }
    
    public String toString() {
        return String.valueOf(value);
    }
}

// Comparator for MyInteger1 class
// The class definition generally follows the format of:
// class B implements Comparator<A>
// The class A which this comparator compares must be written here,
//              in between the < and > brackets ____________
class MyInteger1Comparator implements Comparator<MyInteger1> {
    
    // a negative result means first < second,
    // a positive result means first > second,
    // a result of zero means first == second
    // note this compare method is written to order from largest to smallest
    // the method signature generally follows the format of:
    // public int compare(A first, A second)
    // as above (see compareTo in MyInteger1), the variable names are not fixed
    public int compare(MyInteger1 first, MyInteger1 second) {
        return second.value - first.value;
    }
}

// Comparator for MyInteger2 class
// The class which this comparator compares must be written here,
//              in between the < and > brackets ____________
class MyInteger2Comparator implements Comparator<MyInteger2> {
    
    public int compare(MyInteger2 first, MyInteger2 second) {
        return first.value - second.value;
    }
}