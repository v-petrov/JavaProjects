package linkedList;

import node.linkedList.BluePrint;
import node.linkedList.BluePrint.*;

public class ReverseNodes {

    public LinkedList reverseKGroup(LinkedList list, int k) {
        if (checkForNull(list)) {
            throw new NullPointerException("List is empty!");
        }
        if (k > list.length()) {
            return list;
        }

        int j = k, temp, l, n = 1, m;

        Node firstNode = list.head;
        Node lastNode = firstNode;

        k = list.length() / k;
        m = k;

        while (k != 0) {
            l = j / 2;
            while (l != 0) {
                lastNode = firstNode;
                for (int i = 0; i < j - n; i++) {
                    lastNode = lastNode.next;
                }
                n += 2;

                temp = firstNode.data;
                firstNode.data = lastNode.data;
                lastNode.data = temp;

                firstNode = firstNode.next;
                l--;
            }
            if (m > 1) {
                firstNode = lastNode.next;
            }
            n = 1;
            k--;
        }

        return list;
    }

    public boolean checkForNull(LinkedList list) {
        return list.head == null;
    }

    public static void main(String[] args) {
        ReverseNodes rn = new ReverseNodes();
        LinkedList l1 = new BluePrint().new LinkedList();
        l1.consecutiveData(9);

        LinkedList l2 = rn.reverseKGroup(l1, 5);
        l2.printList();
    }
}