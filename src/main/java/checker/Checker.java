package checker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import connectors.TelnetConnector;
import connectors.WebConnector;

public class Checker extends Thread {

	private String id;
	private URL url;

	public Checker(String id, URL url) {
		this.id = id;
		this.url = url;
	}

	public static void main(String[] args) throws IOException, CsvException {

		String fileName = "hosts.csv";
		List<String[]> rawLine = readAllLines(fileName);
		ArrayList<Host> hosts = parseLinesToHosts(rawLine);

		for (Host host : hosts) {
			for (String endpoint : host.getEndpoints()) {
				Checker thread = new Checker(host.id, new URL("http://" + endpoint));
				thread.start();
			}
		}
	}

	public static List<String[]> readAllLines(String file) throws IOException, CsvException, FileNotFoundException {

		CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
		CSVReader reader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(parser).build();
		return reader.readAll();
	}

	public static ArrayList<Host> parseLinesToHosts(List<String[]> lines) {

		ArrayList<Host> hosts = new ArrayList<Host>();

		for (String[] line : lines) {

			hosts.add(new Host(line));
		}
		return hosts;
	}

	public void run() {
		boolean statusWeb = WebConnector.canConnectTo(url);
		boolean statustelNet = TelnetConnector.canConnectTo(url);
		System.out.println(id + ";" + url + ";" + statusWeb + ";" + statustelNet);
	}
}
