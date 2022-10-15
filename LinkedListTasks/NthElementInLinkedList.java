package exercises;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {

    Node head;

    public void insertNode(LinkedList list, int n) {
        Node new_node = new Node(n);

        if(list.head == null) {
            list.head = new_node;
        } else {
            Node last = list.head;

            while(last.next != null) {
                last = last.next;
            }

            last.next = new_node;
        }

    }

    public void printList(LinkedList list) {
        Node currNode = list.head;

        System.out.print("LinkedList: ");

        while(currNode != null) {
            int value = currNode.data;
            System.out.print(value + " ");
            currNode = currNode.next;
        }

        System.out.println();
    }

    public static int iterations(LinkedList list) {
        Node currNode = list.head;
        int cnt = -1;
        while(currNode != null) {
            cnt++;
            currNode = currNode.next;
        }
        return cnt;
    }


    public void removeNthElementFromTheEnd(LinkedList list, int n) {
        Node currNode = list.head;
        int cnt = LinkedList.iterations(list);
        cnt -= n;

        if(cnt == -1) {
            list.head = currNode.next;
        } else {
            while(cnt != 0) {
                currNode = currNode.next;
                cnt--;
            }
            currNode.next = currNode.next.next;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.insertNode(list, 1);
        list.insertNode(list, 2);
        list.insertNode(list, 3);
        list.insertNode(list, 4);
        list.insertNode(list, 5);

        list.printList(list);
        try {
            list.removeNthElementFromTheEnd(list, 3);
            list.printList(list);
        } catch (NullPointerException e) {
            System.out.println("Sorry there is no such element!");
        }
    }

}
