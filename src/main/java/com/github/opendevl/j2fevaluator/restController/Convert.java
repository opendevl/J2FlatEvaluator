package com.github.opendevl.j2fevaluator.restController;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.opendevl.JFlat;

@RestController
public class Convert {

	@RequestMapping("/convert")
	public String convert(@RequestParam("json") String jsonString, @RequestParam("hs") String hs) throws Exception {
		
		List<Object[]> jsonToCSV = new JFlat(jsonString).json2Sheet().headerSeparator(hs).getJsonAsSheet();
		
		String csvString = "";
		String delimiter = ",";
		boolean comma = false;
		
		for (Object[] o : jsonToCSV) {
			comma = false;
			String row = "";
			for (Object t : o) {
				if (t == null) {
					row += (comma == true ? delimiter : "");
				} else {
					row += (comma == true ? delimiter + t.toString() : t.toString());
				}
				if (comma == false)
					comma = true;
			}
			row += "\n";
			csvString += row;
		}
		return csvString;
	}
}
