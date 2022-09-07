package duke;

import duke.items.Deadline;
import duke.items.Event;
import duke.items.Item;
import duke.items.ToDo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Object to handle storing and loading of user's list of tasks.
 */
public class Storage {
    private String filePath = "/src/main/data/items.json";

    /**
     * Creates an object to store & load user's items.
     */
    public Storage() {
        String currDir = System.getProperty("user.dir");
        this.filePath = Paths.get(currDir + this.filePath).toString();
    }

    /**
     * Function to load items from file.
     * @return ArrayList of items loaded from the file.
     */
    protected ArrayList<Item> loadItems(){
        JSONArray itemsJson;
        ArrayList<Item> storedItems = new ArrayList<>(100);

        try (FileReader reader = new FileReader(this.filePath)) {
            JSONParser parser = new JSONParser();
            itemsJson = (JSONArray) parser.parse(reader);
            itemsJson.forEach(obj -> this.parseJsonToItem((JSONObject) obj, storedItems));
        } catch (FileNotFoundException e) {
            System.out.println("Save File does not exist, starting with a new list.");
        } catch (IOException e) {
            System.out.println("Error whilst opening file, please try again later.");
        } catch (ParseException e) {
            System.out.println("Error loading save file, save may be corrupted, please try again later.");
        }

        return storedItems;
    }

    private void parseJsonToItem(JSONObject jsonObj, ArrayList<Item> storedItems) {
        String itemType = (String) jsonObj.get("itemType");
        String name = (String) jsonObj.get("name");
        boolean isDone = (Boolean) jsonObj.get("isDone");
        String time = (String) jsonObj.get("time");
        try {
            switch (itemType) {
                case "[D]":
                    storedItems.add(new Deadline(name, time, isDone));
                    break;
                case "[E]":
                    storedItems.add(new Event(name, time, isDone));
                    break;
                case "[T]":
                    storedItems.add(new ToDo(name, isDone));
                    break;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Failed to load items from save, file may be corrupted, please try again later.");
        }
    }

    /**
     * Function to save items from an Arraylist into file.
     * @param storedItems ArrayList of items from the user to be stored.
     * @return
     */
    protected boolean saveItems(ArrayList<Item> storedItems) {
        JSONArray jsonArray = new JSONArray();
        storedItems.forEach(item -> this.parseItemToJson(item, jsonArray));
        try (FileWriter fileWriter = new FileWriter(this.filePath)){
            fileWriter.write(jsonArray.toJSONString());
        } catch (IOException e) {
            System.out.println("Error Writing to File.");
            return false;
        }
        return true;
    }

    private void parseItemToJson(Item item, JSONArray jsonArray) {
        JSONObject jsonItem = new JSONObject();
        jsonItem.put("name", item.getName());
        jsonItem.put("isDone", item.isDone());
        String itemType = item.getItemType().toString();
        jsonItem.put("itemType", itemType);
        if (item.getDateTimeString() != null) {
            jsonItem.put("time", item.getDateTimeString());
        }
        jsonArray.add(jsonItem);
    }
}