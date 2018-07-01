package saber;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ListRand {
    public ListNode head;
    public ListNode tail;
    public int count;

    private static final String NODE_SPLITTER = ";";
    private static final String ROW_SPLITTER = ":";

    public void serialize(Path path) throws IOException {
        StringBuilder data = new StringBuilder();
        ListNode listNode = this.head;
        while (listNode != null) {
            int index = 0;
            ListNode rand = listNode.rand;
            if (rand == tail) {
                index = count;
            } else {
                while (rand != null) {
                    rand = rand.prev;
                    index++;
                }
            }
            data.append(String.format("%s%s%s%s", listNode.data, ROW_SPLITTER, index, NODE_SPLITTER));
            listNode = listNode.next;
        }

        Files.write(path, data.toString().getBytes());
    }

    public void deserialize(Path path) throws IOException {
        String data = new String(Files.readAllBytes(path));

        String[] nodeArray = data.split(NODE_SPLITTER);
        for (String nodeData : nodeArray) {
            addNode(nodeData.split(ROW_SPLITTER)[0]);
        }
        ListNode node = this.head;
        for (String nodeData : nodeArray) {
            int index = Integer.parseInt(nodeData.split(ROW_SPLITTER)[1]);
            if (index == 1) {
                node.rand = this.head;
            } else if (index == this.count) {
                node.rand = this.tail;
            } else if (index <= (this.count / 2)) {
                ListNode temp = this.head;
                while (index-- > 1) {
                    temp = temp.next;
                }
                node.rand = temp;
            } else {
                ListNode temp = this.tail;
                while (index++ < this.count) {
                    temp = temp.prev;
                }
                node.rand = temp;
            }
            node = node.next;
        }
    }

    public void addNode(String data) {
        ListNode node = new ListNode();
        node.data = data;
        this.count++;
        if (this.count == 1) {
            this.head = node;
            this.tail = node;
            return;
        }
        this.tail.next = node;
        node.prev = this.tail;
        this.tail = node;
    }

}
