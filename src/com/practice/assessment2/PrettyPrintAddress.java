package com.practice.assessment2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Objects;

public class PrettyPrintAddress {
    public String prettyPrintAddress(Address address) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(gson.toJson(address));
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
    public void prettyPrintAllAddresses(ArrayList<Address> addresses) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(gson.toJson(addresses));
        String prettyJsonString = gson.toJson(je);
        System.out.println("=============================calling pretty Print All Addresses()========================================");
        System.out.println(prettyJsonString);
    }

    public void printAllAddressesByType(ArrayList<Address> addresses) {
        for (Address address : addresses) {
            if (address.type().name().contains("Business")) {
                System.out.println("=============================Addresses of Type Bussiness========================================");
                System.out.println(prettyPrintAddress(address));
            }
            if (address.type().name().contains("Postal")) {
                System.out.println("=============================Addresses of Type Postal========================================");
                System.out.println(prettyPrintAddress(address));
            }
            if (address.type().name().contains("Physical")) {
                System.out.println("=============================Addresses of Type Physical========================================");
                System.out.println(prettyPrintAddress(address));
            }
        }

    }

    public void validateAddress(ArrayList<Address> addresses) {
        for (Address address : addresses) {
            boolean valid=true;
            if(address.country().code().equalsIgnoreCase("ZA")){
                if(Objects.nonNull(address.provinceOrState())){
                    valid=true;
                }else{
                    valid=false;
                }
            }
            if(Objects.nonNull(address.postalCode())  && isNumeric(address.postalCode())){
                valid=true;
            }else{
                valid=false;
            }
           if(!valid){
               System.out.println("=============================Addresses of ID "+address.id()+ " is not a valid Address========================================");

           }else{
               System.out.println("=============================Addresses of ID "+address.id()+ " is a valid Address========================================");

           }
        }
    }
   private boolean isNumeric(String PostalCode){
       try {
           Integer.parseInt(PostalCode);
           return true;
       } catch(NumberFormatException e){
           return false;
       }
   }
}
