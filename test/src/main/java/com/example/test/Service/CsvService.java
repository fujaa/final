package com.example.test.Service;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
@Service
public class CsvService {

    public <T> byte[] convertToCsv(List<T> data, Class<T> type) throws IOException {
        StringWriter writer = new StringWriter();
        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE)) {
            String[] header = getCSVHeaders(type);
            csvWriter.writeHeader(header);

            for (T item : data) {
                csvWriter.write(item, header);
            }
        }

        return writer.toString().getBytes();
    }

    private <T> String[] getCSVHeaders(Class<T> type) {

        // Example implementation for JobDto class
        return new String[]{"id", "type", "url", "created_at", "company", "company_url", "location", "title", "description", "how_to_apply", "company_logo"};
    }
}
