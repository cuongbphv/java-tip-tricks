package cuongbphv.demo;

public class ExportJSON implements IExport {
    @Override
    public void export(Object data) {
        System.out.println("Export " + data + " with JSON Format");
    }
}
