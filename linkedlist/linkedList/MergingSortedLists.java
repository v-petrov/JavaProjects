package linkedList;
import node.linkedList.BluePrint;
import node.linkedList.BluePrint.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergingSortedLists {

    public LinkedList mergeKLists(LinkedList[] lists) {
        LinkedList sortedLists = new BluePrint().new LinkedList();
        List<Integer> sortedNumbers = new ArrayList<>();

        for(LinkedList list : lists) {
            List<Integer> numbers = arrayNumbers(list);
            sortedNumbers.addAll(numbers);
        }

        sortedNumbers.sort(Comparator.comparingInt((x) -> x));

        for(Integer n : sortedNumbers) {
            sortedLists.insertNode(n);
        }

        return sortedLists;
    }

    public static List<Integer> arrayNumbers(LinkedList list) {
        List<Integer> numbers = new ArrayList<>();
        Node currNode = list.head;
        while(currNode != null) {
            numbers.add(currNode.data);
            currNode = currNode.next;
        }
        return numbers;
    }

    public static void main(String[] args) {
        MergingSortedLists merge = new MergingSortedLists();
        LinkedList[] lists = new LinkedList[3];

        LinkedList l1 = new BluePrint().new LinkedList();
        l1.insertNode(1);
        l1.insertNode(4);
        l1.insertNode(5);

        LinkedList l2 = new BluePrint().new LinkedList();
        l2.insertNode(1);
        l2.insertNode(9);
        l2.insertNode(4);

        LinkedList l3 = new BluePrint().new LinkedList();
        l3.insertNode(2);
        l3.insertNode(6);

        lists[0] = l1;lists[1] = l2;lists[2] = l3;

        LinkedList sortedNumbers = merge.mergeKLists(lists);

        sortedNumbers.printList();
    }
}
