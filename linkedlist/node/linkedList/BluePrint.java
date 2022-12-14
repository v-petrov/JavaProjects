package node.linkedList;

public class BluePrint {
    public class Node {
        public int data;
        public Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public class LinkedList {

        public Node head;

        public void insertNode(int n) {
            Node new_node = new Node(n);

            if(this.head == null) {
                this.head = new_node;
            } else {
                Node last = this.head;

                while(last.next != null) {
                    last = last.next;
                }

                last.next = new_node;
            }
        }

        public void printList() {
            Node currNode = this.head;

            System.out.print("LinkedList: ");

            while(currNode != null) {
                int value = currNode.data;
                System.out.print(value + " ");
                currNode = currNode.next;
            }

            System.out.println();
        }

        public int length() {
            int len = 0;
            if(this.head == null) {
                return len;
            }
            Node currNode = this.head;
            while(currNode != null) {
                len++;
                currNode = currNode.next;
            }

            return len;
        }

        public void consecutiveData(int n) {
            for(int i = 1; i <= n; i++) {
                this.insertNode(i);
            }
        }
    }
}



