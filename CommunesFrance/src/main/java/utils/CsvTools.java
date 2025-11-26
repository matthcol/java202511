package utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CsvTools {

    /**
     * Stream CSV file resource with adequate settings
     * @param filename
     * @param charset
     * @param separator
     * @param quoteChar
     * @param hasHeaders
     * @param contextClass
     * @return stream
     */
    public static Stream<String[]> streamCsvResource(
            String filename,
            Charset charset,
            char separator,
            char quoteChar,
            boolean hasHeaders,
            Class<?> contextClass
    ){
        try {
            // resource level 1 : binary
            InputStream inputStream = contextClass.getResourceAsStream(filename);
            // resource level 2 : text with encoding
            InputStreamReader reader = new InputStreamReader(
                    Objects.requireNonNull(inputStream),
                    charset
            );
            // resource level 3 : CSV with separator and quote char
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(hasHeaders ? 1 : 0)
                    .withCSVParser(
                            new CSVParserBuilder()
                                    .withSeparator(separator)
                                    .withQuoteChar(quoteChar)
                                    .build()
                    )
                    .build();
            return StreamSupport.stream(
                            Spliterators.spliteratorUnknownSize(
                                    csvReader.iterator(),
                                    Spliterator.ORDERED
                            ),
                            false
                    )
                    .onClose(() -> {
                        try {
                            csvReader.close();
                            reader.close();
                            inputStream.close();
                        } catch (IOException e) {
                            throw new CsvReadException(e);
                        }
            });
        } catch (NullPointerException | CsvReadException e) {
            throw new CsvReadException(e);
        }
    }

    /**
     * Stream CSV file resource with default settings :
     * - separator ,
     * - quoteChar "
     * - encoding UTF-8
     * - headers on first line
     * @param filename
     * @param contextClass
     * @return stream
     */
    public static Stream<String[]> streamCsvResource(
            String filename,
            Class<?> contextClass
    ){
        return streamCsvResource(filename,  StandardCharsets.UTF_8, ',', '"', true, contextClass);
    }

    /**
     * Read CSV file resource
     * @param filename
     * @param charset
     * @param separator
     * @param quoteChar
     * @param hasHeaders
     * @param contextClass
     * @return list of lines
     */
    public static List<String[]> readCsvResource(
            String filename,
            Charset charset,
            char separator,
            char quoteChar,
            boolean hasHeaders,
            Class<?> contextClass
    ){
        try (Stream<String[]> stream = streamCsvResource(filename, charset, separator, quoteChar, hasHeaders, contextClass)){
            return stream.toList();
        } // auto-close here
    }

    /**
     * Read CSV file resource with default settings :
     * - separator ,
     * - quoteChar "
     * - encoding UTF-8
     * - headers on first line
     * @param filename
     * @param contextClass
     * @return list of lines
     */
    public static List<String[]> readCsvResource(
            String filename,
            Class<?> contextClass
    ){
            try (Stream<String[]> stream = streamCsvResource(filename, contextClass)) {
                return stream.toList();
            } // auto-close here
    }
}
