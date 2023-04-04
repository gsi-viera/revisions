import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		String fileName = args[0];
		String searchCriteria = args[1];
		String searchValue = args[2];

		Map<String, List<String>> hashMap = new HashMap<>();

		hashMap = ProcessFile(hashMap, fileName, searchCriteria);

		List<String> results = hashMap.getOrDefault(searchValue, new ArrayList<>());

		if(results.isEmpty())
			System.out.println("No records found for search criteria and value provided");
		else
			for (String elem : results) 
				System.out.println(elem);		
	}

	public Main() {
		super();
	}

	public static Map<String, List<String>> ProcessFile(Map<String, List<String>> hashMap, String fileName,
			String searchCriteria) {
		String line;
		String format = "";

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while ((line = br.readLine()) != null) {
				if (line.startsWith("F")) {
					format = line.substring(1);
				} else if (line.startsWith("D")) {
					String[] fields;
					if (format.equalsIgnoreCase("1")) {
						fields = line.substring(1).split(",");
					} else {
						fields = line.substring(1).split(" ; ");
					}

					if (searchCriteria.equalsIgnoreCase("id")) {
						String city = fields[1].trim();
						String id = fields[2].trim();

						if (!hashMap.containsKey(id)) {
							hashMap.put(id, new ArrayList<>());
						}

						hashMap.get(id).add(city);
					}

					if (searchCriteria.equalsIgnoreCase("city")) {
						String idWithName = fields[2].trim() + "," + fields[0].trim();
						String city = fields[1].trim();

						if (!hashMap.containsKey(city)) {
							hashMap.put(city, new ArrayList<>());
						}

						hashMap.get(city).add(idWithName);
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hashMap;

	}

}