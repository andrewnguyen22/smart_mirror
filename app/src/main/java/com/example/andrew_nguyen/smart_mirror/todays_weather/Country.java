package com.example.andrew_nguyen.smart_mirror.todays_weather;

/**
 * Created by andrew_nguyen on 10/6/17.
 */

public class Country {
    public static String getCountryFromCode(String str) {
        try {
            str = str.toUpperCase();
            switch (str.trim()) {
                case "ABW":
                    return "Aruba";

                case "AFG":
                    return "Afganisan";

                case "AGO":
                    return "Angola";

                case "AIA":
                    return "Anguilla";

                case "ALA":
                    return "Aland Islands";

                case "ALB":
                    return "Albania";

                case "AND":
                    return "Andorra";

                case "ARE":
                    return "United Arab Emirates";

                case "ARG":
                    return "Argentina";

                case "ARM":
                    return "Armenia";

                case "ASM":
                    return "American Samoa";

                case "ATA":
                    return "Antarctica";

                case "ATF":
                    return "French Southern Territories";

                case "ATG":
                    return "Antigua and Barbuda";

                case "AUS":
                    return "Australia";

                case "AUT":
                    return "Austria";

                case "AZE":
                    return "Azerbaijan";

                case "BDI":
                    return "Burundi";

                case "BEL":
                    return "Belgium";

                case "BEN":
                    return "Benin";

                case "BES":
                    return "Bonaire, Sint Eustatius and Saba";

                case "BFA":
                    return "Burkina Faso";

                case "BGD":
                    return "Bangladesh";

                case "BGR":
                    return "Bulgaria";

                case "BHR":
                    return "Bahrain";

                case "BHS":
                    return "Bahamas";

                case "BIH":
                    return "BOSNIA AND HERZEGOVINA";

                case "BLM":
                    return "SAINT BARTHÉLEMY";

                case "BLR":
                    return "BELARUS";

                case "BLZ":
                    return "BELIZE";

                case "BMU":
                    return "BERMUDA";

                case "BOL":
                    return "BOLIVIA, PLURINATIONAL STATE OF";

                case "BRA":
                    return "BRAZIL";

                case "BRB":
                    return "BARBADOS";

                case "BRN":
                    return "BRUNEI DARUSSALAM";

                case "BTN":
                    return "BHUTAN";

                case "BVT":
                    return "BOUVET ISLAND";

                case "BWA":
                    return "BOTSWANA";

                case "CAF":
                    return "CENTRAL AFRICAN REPUBLIC";

                case "CAN":
                    return "CANADA";

                case "CCK":
                    return "COCOS (KEELING) ISLANDS";

                case "CHE":
                    return "SWITZERLAND";

                case "CHL":
                    return "CHILE";

                case "CHN":
                    return "CHINA";

                case "CIV":
                    return "CÔTE D'IVOIRE";

                case "CMR":
                    return "CAMEROON";

                case "COD":
                    return "CONGO, THE DEMOCRATIC REPUBLIC OF THE";

                case "COG":
                    return "CONGO";

                case "COK":
                    return "COOK ISLANDS";

                case "COL":
                    return "COLOMBIA";

                case "COM":
                    return "COMOROS";

                case "CPV":
                    return "CABO VERDE";

                case "CRI":
                    return "COSTA RICA";

                case "CUB":
                    return "CUBA";

                case "CUW":
                    return "CURAÇAO";

                case "CXR":
                    return "CHRISTMAS ISLAND";

                case "CYM":
                    return "CAYMAN ISLANDS";

                case "CYP":
                    return "CYPRUS";

                case "CZE":
                    return "CZECHIA";

                case "DEU":
                    return "GERMANY";

                case "DJI":
                    return "DJIBOUTI";

                case "DMA":
                    return "DOMINICA";

                case "DNK":
                    return "DENMARK";

                case "DOM":
                    return "DOMINICAN REPUBLIC";

                case "DZA":
                    return "ALGERIA";

                case "ECU":
                    return "ECUADOR";

                case "EGY":
                    return "EGYPT";

                case "ERI":
                    return "ERITREA";

                case "ESH":
                    return "WESTERN SAHARA";

                case "ESP":
                    return "SPAIN";

                case "EST":
                    return "ESTONIA";

                case "ETH":
                    return "ETHIOPIA";

                case "FIN":
                    return "FINLAND";

                case "FJI":
                    return "FIJI";

                case "FLK":
                    return "FALKLAND ISLANDS (MALVINAS)";

                case "FRA":
                    return "FRANCE";

                case "FRO":
                    return "FAROE ISLANDS";

                case "FSM":
                    return "MICRONESIA, FEDERATED STATES OF";

                case "GAB":
                    return "GABON";

                case "GBR":
                    return "UNITED KINGDOM";

                case "GEO":
                    return "GEORGIA";

                case "GGY":
                    return "GUERNSEY";

                case "GHA":
                    return "GHANA";

                case "GIB":
                    return "GIBRALTAR";

                case "GIN":
                    return "GUINEA";

                case "GLP":
                    return "GUADELOUPE";

                case "GMB":
                    return "GAMBIA";

                case "GNB":
                    return "GUINEA-BISSAU";

                case "GNQ":
                    return "EQUATORIAL GUINEA";

                case "GRC":
                    return "GREECE";

                case "GRD":
                    return "GRENADA";

                case "GRL":
                    return "GREENLAND";

                case "GTM":
                    return "GUATEMALA";

                case "GUF":
                    return "FRENCH GUIANA";

                case "GUM":
                    return "GUAM";

                case "GUY":
                    return "GUYANA";

                case "HKG":
                    return "HONG KONG";

                case "HMD":
                    return "HEARD ISLAND AND MCDONALD ISLANDS";

                case "HND":
                    return "HONDURAS";

                case "HRV":
                    return "CROATIA";

                case "HTI":
                    return "HAITI";

                case "HUN":
                    return "HUNGARY";

                case "IDN":
                    return "INDONESIA";

                case "IMN":
                    return "ISLE OF MAN";

                case "IND":
                    return "INDIA";

                case "IOT":
                    return "BRITISH INDIAN OCEAN TERRITORY";

                case "IRL":
                    return "IRELAND";

                case "IRN":
                    return "IRAN, ISLAMIC REPUBLIC OF";

                case "IRQ":
                    return "IRAQ";

                case "ISL":
                    return "ICELAND";

                case "ISR":
                    return "ISRAEL";

                case "ITA":
                    return "ITALY";

                case "JAM":
                    return "JAMAICA";

                case "JEY":
                    return "JERSEY";

                case "JOR":
                    return "JORDAN";

                case "JPN":
                    return "JAPAN";

                case "KAZ":
                    return "KAZAKHSTAN";

                case "KEN":
                    return "KENYA";

                case "KGZ":
                    return "KYRGYZSTAN";

                case "KHM":
                    return "CAMBODIA";

                case "KIR":
                    return "KIRIBATI";

                case "KNA":
                    return "SAINT KITTS AND NEVIS";

                case "KOR":
                    return "KOREA, REPUBLIC OF";

                case "KWT":
                    return "KUWAIT";

                case "LAO":
                    return "LAO PEOPLE'S DEMOCRATIC REPUBLIC";

                case "LBN":
                    return "LEBANON";

                case "LBR":
                    return "LIBERIA";

                case "LBY":
                    return "LIBYA";

                case "LCA":
                    return "SAINT LUCIA";

                case "LIE":
                    return "LIECHTENSTEIN";

                case "LKA":
                    return "SRI LANKA";

                case "LSO":
                    return "LESOTHO";

                case "LTU":
                    return "LITHUANIA";

                case "LUX":
                    return "LUXEMBOURG";

                case "LVA":
                    return "LATVIA";

                case "MAC":
                    return "MACAO";

                case "MAF":
                    return "SAINT MARTIN (FRENCH PART)";

                case "MAR":
                    return "MOROCCO";

                case "MCO":
                    return "MONACO";

                case "MDA":
                    return "MOLDOVA, REPUBLIC OF";

                case "MDG":
                    return "MADAGASCAR";

                case "MDV":
                    return "MALDIVES";

                case "MEX":
                    return "MEXICO";

                case "MHL":
                    return "MARSHALL ISLANDS";

                case "MKD":
                    return "MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF";

                case "MLI":
                    return "MALI";

                case "MLT":
                    return "MALTA";

                case "MMR":
                    return "MYANMAR";

                case "MNE":
                    return "MONTENEGRO";

                case "MNG":
                    return "MONGOLIA";

                case "MNP":
                    return "NORTHERN MARIANA ISLANDS";

                case "MOZ":
                    return "MOZAMBIQUE";

                case "MRT":
                    return "MAURITANIA";

                case "MSR":
                    return "MONTSERRAT";

                case "MTQ":
                    return "MARTINIQUE";

                case "MUS":
                    return "MAURITIUS";

                case "MWI":
                    return "MALAWI";

                case "MYS":
                    return "MALAYSIA";

                case "MYT":
                    return "MAYOTTE";

                case "NAM":
                    return "NAMIBIA";

                case "NCL":
                    return "NEW CALEDONIA";

                case "NER":
                    return "NIGER";

                case "NFK":
                    return "NORFOLK ISLAND";

                case "NGA":
                    return "NIGERIA";

                case "NIC":
                    return "NICARAGUA";

                case "NIU":
                    return "NIUE";

                case "NLD":
                    return "NETHERLANDS";

                case "NOR":
                    return "NORWAY";

                case "NPL":
                    return "NEPAL";

                case "NRU":
                    return "NAURU";

                case "NZL":
                    return "NEW ZEALAND";

                case "OMN":
                    return "OMAN";

                case "PAK":
                    return "PAKISTAN";

                case "PAN":
                    return "PANAMA";

                case "PCN":
                    return "PITCAIRN";

                case "PER":
                    return "PERU";

                case "PHL":
                    return "PHILIPPINES";

                case "PLW":
                    return "PALAU";

                case "PNG":
                    return "PAPUA NEW GUINEA";

                case "POL":
                    return "POLAND";

                case "PRI":
                    return "PUERTO RICO";

                case "PRK":
                    return "KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF";

                case "PRT":
                    return "PORTUGAL";

                case "PRY":
                    return "PARAGUAY";

                case "PSE":
                    return "PALESTINE, STATE OF";

                case "PYF":
                    return "FRENCH POLYNESIA";

                case "QAT":
                    return "QATAR";

                case "REU":
                    return "RÉUNION";

                case "ROU":
                    return "ROMANIA";

                case "RUS":
                    return "RUSSIAN FEDERATION";

                case "RWA":
                    return "RWANDA";

                case "SAU":
                    return "SAUDI ARABIA";

                case "SDN":
                    return "SUDAN";

                case "SEN":
                    return "SENEGAL";

                case "SGP":
                    return "SINGAPORE";

                case "SGS":
                    return "SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS";

                case "SHN":
                    return "SAINT HELENA, ASCENSION AND TRISTAN DA CUNHA";

                case "SJM":
                    return "SVALBARD AND JAN MAYEN";

                case "SLB":
                    return "SOLOMON ISLANDS";

                case "SLE":
                    return "SIERRA LEONE";

                case "SLV":
                    return "EL SALVADOR";

                case "SMR":
                    return "SAN MARINO";

                case "SOM":
                    return "SOMALIA";

                case "SPM":
                    return "SAINT PIERRE AND MIQUELON";

                case "SRB":
                    return "SERBIA";

                case "SSD":
                    return "SOUTH SUDAN";

                case "STP":
                    return "SAO TOME AND PRINCIPE";

                case "SUR":
                    return "SURINAME";

                case "SVK":
                    return "SLOVAKIA";

                case "SVN":
                    return "SLOVENIA";

                case "SWE":
                    return "SWEDEN";

                case "SWZ":
                    return "SWAZILAND";

                case "SXM":
                    return "SINT MAARTEN (DUTCH PART)";

                case "SYC":
                    return "SEYCHELLES";

                case "SYR":
                    return "SYRIAN ARAB REPUBLIC";

                case "TCA":
                    return "TURKS AND CAICOS ISLANDS";

                case "TCD":
                    return "CHAD";

                case "TGO":
                    return "TOGO";

                case "THA":
                    return "THAILAND";

                case "TJK":
                    return "TAJIKISTAN";

                case "TKL":
                    return "TOKELAU";

                case "TKM":
                    return "TURKMENISTAN";

                case "TLS":
                    return "TIMOR-LESTE";

                case "TON":
                    return "TONGA";

                case "TTO":
                    return "TRINIDAD AND TOBAGO";

                case "TUN":
                    return "TUNISIA";

                case "TUR":
                    return "TURKEY";

                case "TUV":
                    return "TUVALU";

                case "TWN":
                    return "TAIWAN, PROVINCE OF CHINA";

                case "TZA":
                    return "TANZANIA, UNITED REPUBLIC OF";

                case "UGA":
                    return "UGANDA";

                case "UKR":
                    return "UKRAINE";

                case "UMI":
                    return "UNITED STATES MINOR OUTLYING ISLANDS";

                case "URY":
                    return "URUGUAY";

                case "USA":
                    return "UNITED STATES OF AMERICA";

                case "UZB":
                    return "UZBEKISTAN";

                case "VAT":
                    return "HOLY SEE";

                case "VCT":
                    return "SAINT VINCENT AND THE GRENADINES";

                case "VEN":
                    return "VENEZUELA, BOLIVARIAN REPUBLIC OF";

                case "VGB":
                    return "VIRGIN ISLANDS, BRITISH";

                case "VIR":
                    return "VIRGIN ISLANDS, U.S.";

                case "VNM":
                    return "VIET NAM";

                case "VUT":
                    return "VANUATU";

                case "WLF":
                    return "WALLIS AND FUTUNA";

                case "WSM":
                    return "SAMOA";

                case "YEM":
                    return "YEMEN";

                case "ZAF":
                    return "SOUTH AFRICA";

                case "ZMB":
                    return "ZAMBIA";

                case "ZWE":
                    return "ZIMBABWE";

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
