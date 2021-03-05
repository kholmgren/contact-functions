package contacts.functions.delete;

import lombok.Data;

import java.time.Instant;

@Data
public class DeleteContactResult {
    private final String id;
    private final String timestamp = Instant.now().toString();
}
