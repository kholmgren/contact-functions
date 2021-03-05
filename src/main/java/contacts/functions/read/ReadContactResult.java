package contacts.functions.read;

import lombok.Data;

import java.time.Instant;

@Data
public class ReadContactResult {
    private final String id;
    private String name;
    private final String timestamp = Instant.now().toString();
}
