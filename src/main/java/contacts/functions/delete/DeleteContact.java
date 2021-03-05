package contacts.functions.delete;

import contacts.functions.Acl;
import contacts.functions.AuthZClient;
import lombok.SneakyThrows;

import java.util.function.Function;

public class DeleteContact implements Function<DeleteContactArgs, DeleteContactResult> {
    @SneakyThrows
    @Override
    public DeleteContactResult apply(DeleteContactArgs args) {
        System.out.printf("Delete user id=%s%n", args.getId());

        String tuple = String.format("%s:%s#%s@%s",
            "contact",
            args.getId(),
            "owner",
            "group:contactusers#member");

        AuthZClient.delete(Acl.create(tuple));

        return new DeleteContactResult(args.getId());
    }
}
