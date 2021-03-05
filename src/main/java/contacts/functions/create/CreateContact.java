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

        AuthZClient.create(Acl.create(
            String.format("contact:%s#%s@%s", id, "owner", "group:contactusers#admin")));

        AuthZClient.create(Acl.create(
            String.format("contact:%s#%s@%s", id, "editor", "group:contactusers#member")));

        return new CreateContactResult(id);
    }
}
