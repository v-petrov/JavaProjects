package linkedList;
import node.linkedList.BluePrint;
import node.linkedList.BluePrint.*;

public class AddingIntegersFromLinkedList {

    public static String number(LinkedList list) {
        StringBuilder num = new StringBuilder();
        Node currNode = list.head;
        while(currNode != null) {
            num.append(currNode.data);
            currNode = currNode.next;
        }
        return num.toString();
    }

    public static String reverseNumber(String num) {
        StringBuilder reverseNum = new StringBuilder();
        for(int i = num.length() - 1; i >= 0; i--) {
            reverseNum.append(num.charAt(i));
        }
        return reverseNum.toString();
    }

    public LinkedList addTwoNumbers(LinkedList l1, LinkedList l2) {
        LinkedList sumList = new BluePrint().new LinkedList();

        String firstNumber = number(l1);
        String secondNumber = number(l2);

        String reverseFirstNumber = reverseNumber(firstNumber);
        String reverseSecondNumber = reverseNumber(secondNumber);

        int firstNum = Integer.parseInt(reverseFirstNumber);
        int secondNum = Integer.parseInt(reverseSecondNumber);

        int sum = firstNum + secondNum;

        if(sum == 0) {
            sumList.insertNode(sum);
        } else {
            while (sum != 0) {
                int remainder = sum % 10;
                sumList.insertNode(remainder);
                sum /= 10;
            }
        }
        return sumList;
    }

    public static void main(String[] args) {
        LinkedList l1 = new BluePrint().new LinkedList();
        LinkedList l2 = new BluePrint().new LinkedList();
        AddingIntegersFromLinkedList add = new AddingIntegersFromLinkedList();

        l1.insertNode(0);

        l2.insertNode(0);

        LinkedList sumList = add.addTwoNumbers(l1, l2);

        l1.printList();
        l2.printList();

        sumList.printList();
    }
}
