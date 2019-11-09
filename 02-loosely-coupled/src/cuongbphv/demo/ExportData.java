package cuongbphv.demo;

public class ExportData {

    private IExport iExport;

    public ExportData(IExport iExport) {
        this.iExport = iExport;
    }

    public void exportProcess(Object data) {
        this.iExport.export(data);
    }
}
