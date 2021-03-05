package contacts.functions.update;

import lombok.Data;

import java.time.Instant;

@Data
public class UpdateContactResult {
    private final String id;
    private final String timestamp = Instant.now().toString();
}
