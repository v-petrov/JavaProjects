package converter;

import java.io.*;
import java.nio.file.Path;
import java.util.Map;

public class MarkdownConverter implements MarkdownConverterAPI {

    private final static String HTML_BEGINNING = "<html>\n<body>";
    private final static String HTML_ENDING = "</body>\n</html>";

    private final static String NEW_LINE = "\n";

    private final static int OPEN = 0;
    private final static int CLOSE = 1;

    private final static int CODE = 7;
    private final static int BOLD = 8;
    private final static int ITALIC = 9;

    private final Map<Integer, String> toHTML;

    public MarkdownConverter() {
        toHTML = Map.of(1, "<h1> </h1>",
                2, "<h2> </h2>",
                3, "<h3> </h3>",
                4, "<h4> </h4>",
                5, "<h5> </h5>",
                6, "<h6> </h6>",
                7, "<code> </code>",
                8, "<strong> </strong>",
                9, "<em> </em>");
    }

    public static void main(String[] args) {
        MarkdownConverter markdownConverter = new MarkdownConverter();
        Path from = Path.of("source.md");
        Path to = Path.of("output.html");

        markdownConverter.convertMarkdown(from, to);
    }

    private int htmlWriting(String line, StringBuilder htmlLine, int i, int action, char ch) {
        String s = toHTML.get(action);
        String[] tokens = s.split(" ");
        htmlLine.append(tokens[OPEN]);
        if (line.charAt(i) == ch && line.charAt(i + 1) == ch) {
            i++;
        }
        i++;

        while (line.charAt(i) != ch) {
            htmlLine.append(line.charAt(i));
            i++;
        }

        htmlLine.append(tokens[CLOSE]);
        i++;

        return i;
    }

    @Override
    public void convertMarkdown(Reader source, Writer output) {
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(source)) {
            output.write(HTML_BEGINNING + NEW_LINE);

            while ((line = bufferedReader.readLine()) != null) {
                int i = 0;
                StringBuilder htmlLine = new StringBuilder();
                StringBuilder header = new StringBuilder();

                while (i < line.length()) {
                    if (line.charAt(i) != '#' && line.charAt(i) != '*' && line.charAt(i) != '`') {
                        htmlLine.append(line.charAt(i));
                        i++;

                    } else if (line.charAt(i) == '#') {
                        int cnt = 1;
                        i++;

                        while (line.charAt(i) == '#') {
                            cnt++;
                            i++;
                        }

                        header.append("#".repeat(Math.max(0, cnt)));
                        String h = toHTML.get(header.length());
                        String[] tokens = h.split(" ");
                        htmlLine.append(tokens[OPEN]);

                    } else if (line.charAt(i) == '*' && line.charAt(i + 1) == '*') {
                        i = htmlWriting(line, htmlLine, i, BOLD, '*');
                        i++;

                    } else if (line.charAt(i) == '*') {
                        i = htmlWriting(line, htmlLine, i, ITALIC, '*');

                    } else if (line.charAt(i) == '`') {
                        i = htmlWriting(line, htmlLine, i, CODE, '`');
                    }

                }
                if (!header.isEmpty()) {
                    String h = toHTML.get(header.length());
                    String[] tokens = h.split(" ");
                    htmlLine.append(tokens[CLOSE]);
                }
                output.write(htmlLine + NEW_LINE);
            }
            output.write(HTML_ENDING);

        } catch (IOException e) {
            System.out.println("Something went wrong with IO!");
            e.printStackTrace();
        }
    }

    @Override
    public void convertMarkdown(Path from, Path to) {
        try (FileReader source = new FileReader(from.toFile());
             FileWriter output = new FileWriter(to.toFile())) {
            convertMarkdown(source, output);

        } catch (FileNotFoundException e) {
            System.out.println("File couldn't be found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Something went wrong with IO!");
            e.printStackTrace();
        }
    }
}
