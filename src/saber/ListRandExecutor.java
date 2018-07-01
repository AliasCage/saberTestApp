package saber;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

public class ListRandExecutor {

    private static final String FILE_NAME = "struct.txt";

    private ListRandExecutor() {
    }

    public static void testSerialize() throws IOException {
        ListRand rand = new ListRand();
        rand.addNode("first_user");
        rand.addNode("second_user");
        rand.addNode("third_user");
        rand.addNode("fourth_user");
        rand.addNode("fifth_user");
        rand.addNode("sixth_user");
        rand.addNode("seventh_user");
        rand.addNode("eighth_user");
        rand.addNode("ninth_user");
        rand.addNode("tenth_user");

        fillRandomNodes(rand);

        rand.serialize(Paths.get(FILE_NAME));

        printList(rand, "Test data list");
    }

    public static void testDeserialize() throws IOException {
        ListRand rand = new ListRand();
        rand.deserialize(Paths.get(FILE_NAME));

        printList(rand, "Test deserialize result");
    }

    private static void fillRandomNodes(ListRand rand) {
        ListNode node = rand.head;
        while (node != null) {
            int index = new Random().nextInt(rand.count);
            ListNode random = rand.head;
            while (index-- > 0) {
                random = random.next;
            }
            node.rand = random;
            node = node.next;
        }
    }

    private static void printList(ListRand list, String msg) {
        System.out.println();
        System.out.println(msg + ", count = " + list.count);
        ListNode head = list.head;
        while (head != null) {
            System.out.println("Data: " + head.data + ", rand: " + head.rand.data);
            head = head.next;
        }
    }

}
