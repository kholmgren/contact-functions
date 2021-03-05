package contacts.functions.create;

import contacts.functions.Acl;
import contacts.functions.AuthZClient;

import java.util.UUID;
import java.util.function.Function;

public class CreateContact implements Function<CreateContactArgs, CreateContactResult> {
    @Override
    public CreateContactResult apply(CreateContactArgs args) {
        System.out.printf("Create user name=%s%n", args.getName());

        String id = UUID.randomUUID().toString();

        String tuple = String.format("%s:%s#%s@%s",
            "contact",
            id,
            "owner",
            "group:contactusers#member");

        AuthZClient.create(Acl.create(tuple));

        return new CreateContactResult(id);
    }
}
