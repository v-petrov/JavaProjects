package converter;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;

public interface MarkdownConverterAPI {

    void convertMarkdown(Reader source, Writer output);
    void convertMarkdown(Path from, Path to);
}
