package by.academy.it.travelcompany;


public class PrintService {

    private Printer printer;

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public void printMessage(String message){
        printer.print(message);

    }
}
