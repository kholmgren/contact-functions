package contacts.functions.create;

import lombok.Data;

import java.time.Instant;

@Data
public class CreateContactResult {
    private final String id;
    private final String timestamp = Instant.now().toString();
}
