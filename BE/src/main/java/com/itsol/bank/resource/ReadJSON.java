package com.itsol.bank.resource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.itsol.bank.entity.Account;

public class ReadJSON {
	@SuppressWarnings("unchecked")
	public List<Account> getDataJson() {
		JSONParser parser = new JSONParser();
		List<Account> list = new ArrayList<>();
		try {
			Object obj = parser.parse(new FileReader("./src/main/java/com/itsol/bank/resource/accounts.json"));
			JSONArray listObj = (JSONArray) obj;
			Iterator<JSONObject> iterator = listObj.iterator();
			while (iterator.hasNext()) {
				Account acc = parseAccountObject(iterator.next());
				list.add(acc);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private Account parseAccountObject(JSONObject jsonObject) 
    {
		Long account_number = (Long) jsonObject.get("account_number");    
		Long balance = (Long) jsonObject.get("balance");
        String firstname = (String) jsonObject.get("firstname");
        String lastname = (String) jsonObject.get("lastname");
        Long age = (Long) jsonObject.get("age");
        String gender = (String) jsonObject.get("gender");
        String address = (String) jsonObject.get("address");
        String employer = (String) jsonObject.get("employer");
        String email = (String) jsonObject.get("email");
        String city = (String) jsonObject.get("city");
        String state = (String) jsonObject.get("state");
        Account acc = new Account(account_number, firstname, address, balance, gender, city, employer, state, age, email, lastname);
        return acc;
    }
}
