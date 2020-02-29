package by.academy.it;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class App {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(1);
        integerList.remove(new Integer(1));
        System.out.println(integerList);
    }
}
