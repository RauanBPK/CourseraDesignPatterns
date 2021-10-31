//package Behavioral;
//
//import javax.print.Doc;
//import javax.swing.text.Document;
//
//public class ExampleCommand {
//
//}
//
//// Não está completo, vai ficar comentado. O importante é a estrutura aq de baixo
//
//
//// Good for decoupling?
//
//// Sender creates a command object,
//// The Invoker invokes the Command for the receiver to perform an action
//// Commands can be kept in a list, database, whatever to be invoked at a specific time or trigger
//
////            Invoker
////               |
////               v
//// Sender --> Command --> Receiver
//
//abstract class Command {
//    abstract void execute();
//    abstract void unexecute();
//    abstract boolean isReversible();
//}
//
//class PasteCommand extends Command {
//
//    private Document document;
//    private int position;
//    private String text;
//
//    public PasteCommand(Document document, int position, String text){
//        this.document = document;
//        this.position = position;
//        this.text = text;
//    }
//
//    @Override
//    void execute() {
//        document.insertText(this.position, this.text);
//    }
//
//    @Override
//    void unexecute() {
//        document.deleteText(this.position, this.text.length());
//    }
//
//    @Override
//    boolean isReversible() {
//        return true;
//    }
//}
//
//class Invoker {
//    CommandManager commandManager = CommandManager.getInstance();
//    Command command = new PasteCommand(aDocument, aPosition, aText);
//    commandManager.invokeCommand(command);
//}