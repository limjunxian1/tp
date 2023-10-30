package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;

public class UpdatePhotoCommandTest {

    private final String safePath = Paths.get("").toAbsolutePath()
            + "\\src\\main\\resources\\images\\test_photo.png";

    @Test
    public void execute_noIndexCommand_exceptionThrown() {
        AddressBookParser parser = new AddressBookParser();
        try {
            Command command = parser.parseCommand("updatephoto path/images/default_photo.png");
            assertTrue(command instanceof UpdatePhotoCommand);
            UpdatePhotoCommand updatePhotoCommand = (UpdatePhotoCommand) command;
            updatePhotoCommand.execute(new ModelManager());
        } catch (ParseException e) {
            assertEquals(e.getMessage(),
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePhotoCommand.MESSAGE_USAGE));
        } catch (CommandException e) {
            assertEquals(1, 0);
        }
    }

    @Test
    public void execute_noPathCommand_exceptionThrown() {
        AddressBookParser parser = new AddressBookParser();
        try {
            Command aCommand = parser.parseCommand(
                    "add n/John Doe p/98765432 e/johndoe@gmail.com a/John street, block 123, #01-01 t/friend"
            );
            Command upCommand = parser.parseCommand("updatephoto 1");
            assertTrue(aCommand instanceof AddCommand);
            assertTrue(upCommand instanceof UpdatePhotoCommand);
            AddCommand addCommand = (AddCommand) aCommand;
            UpdatePhotoCommand updatePhotoCommand = (UpdatePhotoCommand) upCommand;
            ModelManager temp = new ModelManager();
            addCommand.execute(temp);
            updatePhotoCommand.execute(temp);
        } catch (ParseException e) {
            assertEquals(e.getMessage(),
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdatePhotoCommand.MESSAGE_USAGE));
        } catch (CommandException e) {
            assertEquals(1, 0);
        }
    }

    @Test
    public void execute_invalidIndexInEmptyAddressBook_exceptionThrown() {
        AddressBookParser parser = new AddressBookParser();
        try {
            Command command = parser.parseCommand("updatephoto 0 path/images/default_photo.png");
            assertTrue(command instanceof UpdatePhotoCommand);
            UpdatePhotoCommand updatePhotoCommand = (UpdatePhotoCommand) command;
            updatePhotoCommand.execute(new ModelManager());
        } catch (ParseException e) {
            assertEquals(1, 0);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), "The person index provided is invalid");
        }
    }

    @Test
    public void execute_nonPositiveIndexInNonEmptyAddressBook_exceptionThrown() {
        AddressBookParser parser = new AddressBookParser();
        try {
            Command aCommand = parser.parseCommand(
                    "add n/John Doe p/98765432 e/johndoe@gmail.com a/John street, block 123, #01-01 t/friend"
            );
            Command upCommand = parser.parseCommand("updatephoto 0 path/images/default_photo.png");
            assertTrue(aCommand instanceof AddCommand);
            assertTrue(upCommand instanceof UpdatePhotoCommand);
            AddCommand addCommand = (AddCommand) aCommand;
            UpdatePhotoCommand updatePhotoCommand = (UpdatePhotoCommand) upCommand;
            ModelManager temp = new ModelManager();
            addCommand.execute(temp);
            updatePhotoCommand.execute(temp);
        } catch (ParseException e) {
            assertEquals(1, 0);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), "The person index provided is invalid");
        }
    }

    @Test
    public void execute_outOfBoundIndexInNonEmptyAddressBook_exceptionThrown() {
        AddressBookParser parser = new AddressBookParser();
        try {
            Command aCommand = parser.parseCommand(
                    "add n/John Doe p/98765432 e/johndoe@gmail.com a/John street, block 123, #01-01 t/friend"
            );
            Command upCommand = parser.parseCommand("updatephoto 2 path/images/default_photo.png");
            assertTrue(aCommand instanceof AddCommand);
            assertTrue(upCommand instanceof UpdatePhotoCommand);
            AddCommand addCommand = (AddCommand) aCommand;
            UpdatePhotoCommand updatePhotoCommand = (UpdatePhotoCommand) upCommand;
            ModelManager temp = new ModelManager();
            addCommand.execute(temp);
            updatePhotoCommand.execute(temp);
        } catch (ParseException e) {
            assertEquals(1, 0);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), "The person index provided is invalid");
        }
    }

    @Test
    public void execute_invalidPath_exceptionThrown() {
        AddressBookParser parser = new AddressBookParser();
        try {
            Command aCommand = parser.parseCommand(
                    "add n/John Doe p/98765432 e/johndoe@gmail.com a/John street, block 123, #01-01 t/friend"
            );
            Command upCommand = parser.parseCommand("updatephoto 1 path/images/defaul_photo.png");
            assertTrue(aCommand instanceof AddCommand);
            assertTrue(upCommand instanceof UpdatePhotoCommand);
            AddCommand addCommand = (AddCommand) aCommand;
            UpdatePhotoCommand updatePhotoCommand = (UpdatePhotoCommand) upCommand;
            ModelManager temp = new ModelManager();
            addCommand.execute(temp);
            updatePhotoCommand.execute(temp);
        } catch (ParseException e) {
            assertEquals(1, 0);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), "Invalid file path provided");
        }
    }

    @Test
    public void execute_validCommand() {
        AddressBookParser parser = new AddressBookParser();
        try {
            Command aCommand = parser.parseCommand(
                    "add n/John Doe p/98765432 e/johndoe@gmail.com a/John street, block 123, #01-01 t/friend"
            );
            Command upCommand = parser.parseCommand("updatephoto 1 path/" + safePath);
            assertTrue(aCommand instanceof AddCommand);
            assertTrue(upCommand instanceof UpdatePhotoCommand);
            AddCommand addCommand = (AddCommand) aCommand;
            UpdatePhotoCommand updatePhotoCommand = (UpdatePhotoCommand) upCommand;
            ModelManager temp = new ModelManager();
            addCommand.execute(temp);
            updatePhotoCommand.execute(temp);
            assertEquals(temp.getFilteredPersonList().get(0).getAvatar().getPath(), safePath);
        } catch (ParseException | CommandException e) {
            assertEquals(1, 0);
        }
    }
}











