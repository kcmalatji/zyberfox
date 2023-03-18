package com.practice.assessment2;

public record Address(String id, Type type,AddressLineDetail addressLineDetail,
                      ProvinceOrState provinceOrState,String cityOrTown,Country country,String postalCode,String lastUpdated) {
}
