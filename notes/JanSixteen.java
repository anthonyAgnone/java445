public class JanSixteen {

    // class Child extends Parent {
    // private int sign;

    // String toString(sign);
    // }

    // woo polymorphism. is-a vs has-a

    // int[] arr = {1,2,3,4,5,6} <-- this bboi is allowed
    // A signedFraction in this case is a box with 3 data compartments:
    // numerator/denominator/sign(+-)
    // Fraction only has two bois.
    // Fraction[] sf = {new SignedFraction(), new Fraction()};

    // class c is a child of b and child b is a child of a. therefore child c is a
    // child of a.
    // transitivity

    // Abstract class is similar to an interface. but you want to do it in a way
    // that you want to customize it
    // interface is kind of like standing on the shoulders of a giant of code. and
    // leverage it
    // to do something awesome.
    // example of an abstract class is shape.

    abstract class Shape {
        int area;
        int perimiter;
    }

    class Square extends Shape {
        int side;
        // now area and perimater is definited for you by shape.
    }

    class Circle extends Shape {
        int radius;
    }
    // abstract is basically the concept. you give it functionality. lets say.

    // this arr bag that we are making is templated. it can be anything <String>
    // <Int> <Whatever> It can be anything. But it has to be of the same type
    // ArrBag<String> bag1 = new ArrBag<String>();
    // <T> this is syntactical and required. stands for type
    // You can run the <T> file on it's own but it's not quite cooked. It creates a
    // weirdo instance

    // you can like. just doubble the array if we hit capacity

    // array discipline. have a count(initialized at 0) but then we increase count.
    // no nulls
    // probably don'y need to use this
    // at add make sure you write your upsize
    // in add consider returning an object that has status TEXT and not just a
    // status
    // contains is a true or false. it doesn't matter if there are more than there
    // needs be
    // lol paranoid programming. remember that.
    // maybbe instead of theArray[i].equals use get(i).equals

    // @SuppressWarnings("unchecked") it performs an operation to the element to the
    // element to the immediate right like casting
    // kind of like (int) 3.712 where int is cast on 3.712

    // exclusive or. xor. what they have in common is out. just the things that are
    // exclusive
    // union minus the intersection
}