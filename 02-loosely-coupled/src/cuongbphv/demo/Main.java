package cuongbphv.demo;

public class Main {

    public static void main(String[] args) {
        IExport exportJSON = new ExportJSON();
        IExport exportCSV = new ExportCSV();
	    ExportData exportJSONData = new ExportData(exportJSON);
	    ExportData exportCSVData = new ExportData(exportCSV);
        exportJSONData.exportProcess("Something just like this");
        exportCSVData.exportProcess("Something just like this");
    }
}
