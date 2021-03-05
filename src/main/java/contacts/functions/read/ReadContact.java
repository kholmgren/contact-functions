package contacts.functions.read;

import java.util.function.Function;

public class ReadContact implements Function<ReadContactArgs, ReadContactResult> {
    @Override
    public ReadContactResult apply(ReadContactArgs args) {
        System.out.printf("Read user id=%s%n", args.getId());

        return new ReadContactResult(args.getId());
    }
}
