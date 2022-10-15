package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PartitionLabels {

    private final String string;

    public PartitionLabels(String s) {
        this.string = s;
    }

    public List<Integer> partitions() {
        String s = this.string;
        List<String> sList = new ArrayList<>();
        String partition = "";

        while(s.length() != 0) {

            int lastIndex = s.lastIndexOf(s.charAt(0));

            for (int i = 0; i <= lastIndex; i++) {
                partition += s.charAt(i);
            }

            int nextIndex = 0;

            for (int i = lastIndex + 1; i < s.length(); i++) {
                if (partition.indexOf(s.charAt(i)) != -1) {
                    nextIndex = i;
                }
            }

            for (int i = lastIndex + 1; i <= nextIndex; i++) {
                partition += s.charAt(i);
            }

            sList.add(partition);
            s = s.replace(partition,"" );
            partition = "";
        }

        return sList.stream()
                .map(x -> x.length())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        PartitionLabels p
                = new PartitionLabels("asjdsajdabbbbffarkkklklpp");
        List<Integer> iList = p.partitions();
        System.out.println(iList);
    }
}
