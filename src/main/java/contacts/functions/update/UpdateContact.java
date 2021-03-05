package contacts.functions.update;

import java.util.function.Function;

public class UpdateContact implements Function<UpdateContactArgs, UpdateContactResult> {
    @Override
    public UpdateContactResult apply(UpdateContactArgs args) {
        System.out.printf("Update user id=%s with name=%s%n", args.getId(), args.getName());

        return new UpdateContactResult(args.getId());
    }
}
