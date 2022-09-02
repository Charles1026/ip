import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Naruto {
    private final ArrayList<Item> storedItems;
    private final Saver saveManager = new Saver();

    public Naruto() {
        this.storedItems = this.saveManager.loadItems();
    }

    public void destructor() {
        this.saveManager.saveItems(this.storedItems);
    }

    private String addItem(Item item) {
        this.storedItems.add(item);
        return "    Alright! I've added it to our list:\n      " + item.toString()
                + "\n    Now we have " + this.storedItems.size() + " tasks in our list Dattebayo!";
    }

    public String addToDo(String item) {
        ToDo todo = new ToDo(item);
        return this.addItem(todo);
    }

    public String addDeadline(String item, String due) {
        try {
            Deadline deadline = new Deadline(item, due);
            return this.addItem(deadline);
        } catch (DateTimeParseException e) {
            return "Error Parsing Date Time Info, Item not added, " +
                    "please use this format /by YYYY-MM-DD HH:MM (omit time if not necessary)";
        }

    }

    public String addEvent(String item, String at) {
        try {
            Event event = new Event(item, at);
            return this.addItem(event);
        } catch (DateTimeParseException e) {
            return "Error Parsing Date Time Info, Item not added, " +
                    "please use this format /at YYYY-MM-DD HH:MM (omit time if necessary)";
        }
    }

    public String getList() {
        if (this.storedItems.isEmpty()) {
            return "    The list is currently empty Dattebayo!";
        }
        StringBuilder list = new StringBuilder("    Here's the list you asked for Dattebayo:");
        for (int count = 0; count < this.storedItems.size(); count++){
            list.append("\n").append("    ").append(count + 1).append(".").append(storedItems.get(count).toString());
        }
        return list.toString();
    }

    public String markItem(int index) {
        if (index >= this.storedItems.size() || index < 0) {
            return "Whoops it appears you entered an invalid index, there are " + this.storedItems.size()
                    + " items in the list Dattebayo!";
        }
        this.storedItems.get(index).setDone();
        return "    Alright! I've marked this task as done Dattebayo:\n  " + this.storedItems.get(index).toString();
    }

    public String unMarkItem(int index) {
        if (index >= this.storedItems.size() || index < 0) {
            return "Whoops it appears you entered an invalid index, there are " + this.storedItems.size()
                    + " items in the list Dattebayo!";
        }
        this.storedItems.get(index).setUnDone();
        return "    Alright! I've marked this task as not done yet Dattebayo: \n  "
                + this.storedItems.get(index).toString();
    }

    public String deleteItem(int index) {
        if (index >= this.storedItems.size() || index < 0) {
            return "Whoops it appears you entered an invalid index, there are " + this.storedItems.size()
                    + " items in the list Dattebayo!";
        }
        Item item = this.storedItems.get(index);
        this.storedItems.remove(index);
        return "    Alright! I've removed this task Dattebayo: \n  " + item.toString()
                + "\n    Now we have " + this.storedItems.size() + " tasks in our list Dattebayo!";
    }

    public String bye() {
        return "    Looks like this is the end for now, till we meet again! Ja Ne!";
    }

    public static int string2Int(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }
}
