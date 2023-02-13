package SSA.tasks;

import java.util.*;

public class UniqueEmails {

    public static void main(String[] args) {
        List<String> emails = List.of("test.email+alex@le.et.com", "test.ema.il+alex@leet.com", "test.em+ai.l+alex@leet.com");
        System.out.println(numUniqueEmails(emails));
    }

    private static int numUniqueEmails(List<String> emails) {
        String[] localNames = new String[emails.size()];
        String[] domainNames = new String[emails.size()];
        String[] tokens;

        for (int i = 0; i < emails.size(); i++) {
            tokens = emails.get(i).split("@");
            localNames[i] = tokens[0];
            domainNames[i] = tokens[1];
        }

        int index = 0;
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < localNames.length; i++) {
            if (localNames[i].contains(".") || localNames[i].contains("+")) {
                while (index < localNames[i].length()) {
                    if (localNames[i].charAt(index) == '+') {
                        break;
                    }
                    if (localNames[i].charAt(index) == '.') {
                        index++;
                        continue;
                    }
                    s.append(localNames[i].charAt(index));
                    index++;
                }
                localNames[i] = s.toString();
                s.delete(0, s.length());
                index = 0;
            }
        }

        Set<String> mails = new HashSet<>();

        for (int i = 0; i < localNames.length; i++) {
            mails.add(localNames[i] + "@" + domainNames[i]);
        }

        System.out.println(mails);

        return mails.size();
    }
}