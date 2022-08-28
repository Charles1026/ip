import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Naruto and one day I will be Hokage! \nWhat can i do for you?");
        Scanner scanner = new Scanner(System.in);
        Naruto naruto = new Naruto();
        String lineBreak = "-".repeat(20);

        boolean breakLoop = false;
        while (!breakLoop) {
            String rawInput = scanner.nextLine().trim();
            String[] input = rawInput.split(" ", 2);
            System.out.println(lineBreak);
            switch (input[0]) {
                case "list":
                    System.out.println(naruto.getList());
                    break;

                case "todo":
                    try {
                        System.out.println(naruto.addToDo(input[1].trim()));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Whoops! todo needs a description of the task Dattebayo!\n'todo <Task>'");
                    }
                    break;

                case "deadline":
                    try {
                        String[] splitInput = input[1].split("/by", 2);
                        System.out.println(naruto.addDeadline(splitInput[0].trim(), splitInput[1].trim()));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Whoops! deadline needs a description of the task and due date Dattebayo!" +
                                "\n'deadline <Task> /by <Due By>'");
                    }
                    break;

                case "event":
                    try {
                        String[] splitInput = input[1].split("/at", 2);
                        System.out.println(naruto.addEvent(splitInput[0].trim(), splitInput[1].trim()));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Whoops! event needs a description of the task and time Dattebayo!" +
                                "\n'event <Task> /by <Time>'");
                    }
                    break;

                case "mark":
                    try {
                        int item = Naruto.string2Int(input[1]);
                        System.out.println(naruto.markItem(item - 1));
                    } catch(NumberFormatException e) {
                        System.out.println("Whoops! it seems you your index is not an integer Dattebayo!" +
                                "\n'mark <Index>'");
                    } catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println("Whoops! mark needs the index of the item Dattebayo!" +
                                "\n'mark <Index>'");
                    }
                    break;

                case "unmark":
                    try {
                        int index = Naruto.string2Int(input[1]);
                        System.out.println(naruto.unMarkItem(index - 1));
                    } catch(NumberFormatException e) {
                        System.out.println("Whoops! it seems you your index is not an integer Dattebayo!" +
                                "\n'unmark <Index>'");
                    } catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println("Whoops! unmark needs the index of the item Dattebayo!" +
                                "\n'unmark <Index>'");
                    }
                    break;

                case "delete":
                    try {
                        int index = Naruto.string2Int(input[1]);
                        System.out.println(naruto.deleteItem(index - 1));
                    } catch(NumberFormatException e) {
                        System.out.println("Whoops! it seems you your index is not an integer Dattebayo!" +
                                "\n'delete <Index>'");
                    } catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println("Whoops! delete needs the index of the item Dattebayo!" +
                                "\n'delete <Index>'");
                    }
                    break;

                case "bye":
                    System.out.println(naruto.bye());
                    breakLoop = true;
                    break;

                case "help":
                    System.out.println("    Hi are you in need of help? These are the commands you can use Dattebayo:" +
                            "\n      todo <Task>: add a task you need to do" +
                            "\n      deadline <Task> /by<Deadline>: add a deadline you need to complete" +
                            "\n      event <Task> /at<Time>: add an event you need to attend" +
                            "\n      mark <index>: mark an task as completed by index" +
                            "\n      unmark <index>: unmark a completed task by index" +
                            "\n      list: view all you tasks by index" +
                            "\n      delete <index>: remove a specific task by index");

                    break;

                default:
                    System.out.println("I have no clue whats going on here, but I'll act like i do Dattebayo, " +
                            "use help to view the available commands desu.");
            }
            System.out.println(lineBreak);
        }

    }
}
