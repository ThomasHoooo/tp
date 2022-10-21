package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.product.Product;

/**
 * Parses input arguments and creates a new AddProductCommand object.
 */
public class AddProductCommandParser implements Parser<AddProductCommand> {

    @Override
    public AddProductCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProductCommand.MESSAGE_USAGE));
        }

        Product product = ParserUtil.parseProduct(argMultimap.getValue(PREFIX_NAME).get());
        return new AddProductCommand(product);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
