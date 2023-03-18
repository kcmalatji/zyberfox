package com.practice.assessment2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class AddressApplication {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            ArrayList<Address>addresses = new ArrayList<>();
            System.out.println("=============================calling pretty Print Address()========================================");
            for (Object obj : LoadFileFromLocal()) {

                JSONObject jsonObject = (JSONObject) obj;
                Address address = BuildAddressFromObject(jsonObject);
                addresses.add(address);
                //pretty Print Address
                System.out.println(new PrettyPrintAddress().prettyPrintAddress(address));
            }
            new PrettyPrintAddress().prettyPrintAllAddresses(addresses);
            new PrettyPrintAddress().printAllAddressesByType(addresses);
            new PrettyPrintAddress().validateAddress(addresses);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static Address BuildAddressFromObject(JSONObject jsonObject) {
        JSONObject typeObj = (JSONObject) jsonObject.get("type");
        Type type = null;
        if (Objects.nonNull(typeObj)) {
            type = new Type((String) typeObj.get("code"), (String) typeObj.get("name"));
        }

        JSONObject addressLineDetailObj = (JSONObject) jsonObject.get("addressLineDetail");
        AddressLineDetail addressLineDetail = null;
        if (Objects.nonNull(addressLineDetailObj)) {
            addressLineDetail = new AddressLineDetail((String) addressLineDetailObj.get("line1"), (String) addressLineDetailObj.get("line2"));
        }
        JSONObject provinceOrStateObj = (JSONObject) jsonObject.get("provinceOrState");
        ProvinceOrState provinceOrState = null;
        if (Objects.nonNull(provinceOrStateObj)) {
            provinceOrState = new ProvinceOrState((String) provinceOrStateObj.get("code"), (String) provinceOrStateObj.get("name"));
        }
        JSONObject countryObj = (JSONObject) jsonObject.get("country");
        Country country = null;
        if (Objects.nonNull(countryObj)) {
            country = new Country((String) countryObj.get("code"), (String) countryObj.get("name"));
        }
        Address address = new Address((String) jsonObject.get("id"), type, addressLineDetail, provinceOrState, (String) jsonObject.get("cityOrTown"), country, (String) jsonObject.get("postalCode"), (String) jsonObject.get("lastUpdated"));
        return address;
    }

    private static JSONArray LoadFileFromLocal() throws IOException, ParseException {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path filePath = Paths.get(root.toString(), "src", "com", "practice", "resources", "addresses.json");
        JSONParser parser = new JSONParser();
        JSONArray jSONArray = (JSONArray) parser.parse(new FileReader(filePath.toString()));

        return jSONArray;
    }
}
