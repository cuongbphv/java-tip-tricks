package cuongbphv.demo;

public class ExportCSV implements IExport {
    @Override
    public void export(Object data) {
        System.out.println("Export " + data + " with CSV Format");
    }
}
