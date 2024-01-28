package edu.ssafy.im;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hello {
    class Iron extends Human{
        int x;

        Iron(int x) {
            super(x);
            this.x = x;
        }

        @Override
        public int compareTo(Human o) {
            // TODO Auto-generated method stub
            return this.x - o.x;
        }
    }

    class Human implements Comparable<Human> {
        int x;

        Human(int x) {
            this.x = x;
        }

        @Override
        public int compareTo(Human o) {
            return 0;
        }
    }

    public static void main(String[] args) {


    }

    private void a() {
        List<Human> arr = new ArrayList<>();
        Iron i = new Iron(2);
        Human h = new Human(1);
        arr.add(i);
        arr.add(h);
        for (Human h1:
                sorted(arr)) {
            System.out.println(h1.x);
        }
    }

    public <T extends Comparable<? super T>> List<T> sorted(List<T> list) {
        return list;
    }
}