package com.mycompany.mavenproject1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameSequenceGenerator {

    // the purpose of this class is to create the game sequence as one encoded string, which is then shared to all the clients using the 
    // ClientHandler class
    private String gameSequence;

    // to store country names with their country code. The country code is stored because the filenames for the flag are stored with the country code. ie. "us.png" --> United States
    public static Map<String, String> codesAndCountry;
    // to ensure that there are no duplicate questions
    ArrayList<String> alreadyAsked = new ArrayList();
    
    public GameSequenceGenerator() throws JsonProcessingException { 
        loadFlagData();
    }

    public String createSequenceAutomatic(int noOfQs) {
        // calls the generateQuestion() method and formats the questionSequences into one big gameSequence.

        // divided the 15 questions of the game with the "!" symbol so that the clients can interpret the sequence/
        String questions = "!";

        for (int i = 0; i < noOfQs; i++) { // 15 Qs
            questions += generateQuestion();
        }
        return questions;

    }

    public String generateQuestion() {
        Random rand = new Random();

        // Set of country codes. Used for generating questions.
        Set codes = codesAndCountry.keySet();
        Object[] countryCodeKeys = codes.toArray();
        int index = 0;
        String code = "";

        boolean alreadyExists = true;// will start of as true. What it does it checks if a Q is already asked

        while (alreadyExists) {
            alreadyExists = false;

            // gets a random country code
            index = rand.nextInt(254);
            code = (String) countryCodeKeys[index];

            // checks if that country code has been used before or not in a previous question
            for (String existingCode : alreadyAsked) {  //  alrAsked is the array w previously asked Q
                if (code.equals(existingCode)) { // since Strings are non-primative, we need .equals();
                    alreadyExists = true;
                }
            }
        }
        // at this point, a unique code will be chosen.

        // store the flag filename and the correct answer. the correct answer will be the country which that flag belongs to.
        String imageFileName = code + ".png";
        String correctAnswer = codesAndCountry.get(code);

        // add correct answer to 'alreadyAsked' array so that the same country doesn't show up more than once, and so that it doesn't appear as an option for a future question
        alreadyAsked.add(code);

        // to store 3 other false options
        String[] falseAnswers = new String[3];

        int i = 0;
        while (falseAnswers[0] == null || falseAnswers[1] == null || falseAnswers[2] == null) {

            int incorrectAnswerIndex = rand.nextInt(254);
            String incorrectCode = (String) countryCodeKeys[incorrectAnswerIndex];

            boolean wasPreviousAnswer = false;

            for (String existingCode : alreadyAsked) {  //  alrAsked is the array w previously asked Q
                if (incorrectCode.equals(existingCode)) { // since Strings are non-primative, we need .equals();
                    wasPreviousAnswer = true;
                }
            }
            // At this point, a falseAnswer, which hasn't been the correct answer in a previous question is chosen 

            // checking if the generated false optin isn't the same as the correct option of this question
            if (incorrectAnswerIndex != index && wasPreviousAnswer == false) { // to avoid two of the same options 
                falseAnswers[i] = codesAndCountry.get(incorrectCode);
                i++;
            }

        }

        return "," +imageFileName + ","+ correctAnswer + "," + falseAnswers[0] + "," + falseAnswers[1] + "," + falseAnswers[2] + "," + "!";

    }

    public static void loadFlagData() throws JsonProcessingException {

        // to store country names with their country code. The country code is stored because the filenames for the flag are stored with the country code. ie. "us.png" --> United States
        // Data from: https://flagpedia.net/download/country-codes-case-upper.json
        // Stored in JSON format. It will be converted to a HashMap.
        String jsonStr = "{\n"
                + "    \"ad\": \"Andorra\",\n"
                + "    \"ae\": \"United Arab Emirates\",\n"
                + "    \"af\": \"Afghanistan\",\n"
                + "    \"ag\": \"Antigua and Barbuda\",\n"
                + "    \"ai\": \"Anguilla\",\n"
                + "    \"al\": \"Albania\",\n"
                + "    \"am\": \"Armenia\",\n"
                + "    \"ao\": \"Angola\",\n"
                + "    \"aq\": \"Antarctica\",\n"
                + "    \"ar\": \"Argentina\",\n"
                + "    \"as\": \"American Samoa\",\n"
                + "    \"at\": \"Austria\",\n"
                + "    \"au\": \"Australia\",\n"
                + "    \"aw\": \"Aruba\",\n"
                + "    \"ax\": \"Åland Islands\",\n"
                + "    \"az\": \"Azerbaijan\",\n"
                + "    \"ba\": \"Bosnia and Herzegovina\",\n"
                + "    \"bb\": \"Barbados\",\n"
                + "    \"bd\": \"Bangladesh\",\n"
                + "    \"be\": \"Belgium\",\n"
                + "    \"bf\": \"Burkina Faso\",\n"
                + "    \"bg\": \"Bulgaria\",\n"
                + "    \"bh\": \"Bahrain\",\n"
                + "    \"bi\": \"Burundi\",\n"
                + "    \"bj\": \"Benin\",\n"
                + "    \"bl\": \"Saint Barthélemy\",\n"
                + "    \"bm\": \"Bermuda\",\n"
                + "    \"bn\": \"Brunei\",\n"
                + "    \"bo\": \"Bolivia\",\n"
                + "    \"bq\": \"Caribbean Netherlands\",\n"
                + "    \"br\": \"Brazil\",\n"
                + "    \"bs\": \"Bahamas\",\n"
                + "    \"bt\": \"Bhutan\",\n"
                + "    \"bv\": \"Bouvet Island\",\n"
                + "    \"bw\": \"Botswana\",\n"
                + "    \"by\": \"Belarus\",\n"
                + "    \"bz\": \"Belize\",\n"
                + "    \"ca\": \"Canada\",\n"
                + "    \"cc\": \"Cocos (Keeling) Islands\",\n"
                + "    \"cd\": \"DR Congo\",\n"
                + "    \"cf\": \"Central African Republic\",\n"
                + "    \"cg\": \"Republic of the Congo\",\n"
                + "    \"ch\": \"Switzerland\",\n"
                + "    \"ci\": \"Côte d'Ivoire (Ivory Coast)\",\n"
                + "    \"ck\": \"Cook Islands\",\n"
                + "    \"cl\": \"Chile\",\n"
                + "    \"cm\": \"Cameroon\",\n"
                + "    \"cn\": \"China\",\n"
                + "    \"co\": \"Colombia\",\n"
                + "    \"cr\": \"Costa Rica\",\n"
                + "    \"cu\": \"Cuba\",\n"
                + "    \"cv\": \"Cape Verde\",\n"
                + "    \"cw\": \"Curaçao\",\n"
                + "    \"cx\": \"Christmas Island\",\n"
                + "    \"cy\": \"Cyprus\",\n"
                + "    \"cz\": \"Czechia\",\n"
                + "    \"de\": \"Germany\",\n"
                + "    \"dj\": \"Djibouti\",\n"
                + "    \"dk\": \"Denmark\",\n"
                + "    \"dm\": \"Dominica\",\n"
                + "    \"do\": \"Dominican Republic\",\n"
                + "    \"dz\": \"Algeria\",\n"
                + "    \"ec\": \"Ecuador\",\n"
                + "    \"ee\": \"Estonia\",\n"
                + "    \"eg\": \"Egypt\",\n"
                + "    \"eh\": \"Western Sahara\",\n"
                + "    \"er\": \"Eritrea\",\n"
                + "    \"es\": \"Spain\",\n"
                + "    \"et\": \"Ethiopia\",\n"
                + "    \"fi\": \"Finland\",\n"
                + "    \"fj\": \"Fiji\",\n"
                + "    \"fk\": \"Falkland Islands\",\n"
                + "    \"fm\": \"Micronesia\",\n"
                + "    \"fo\": \"Faroe Islands\",\n"
                + "    \"fr\": \"France\",\n"
                + "    \"ga\": \"Gabon\",\n"
                + "    \"gb\": \"United Kingdom\",\n"
                + "    \"gb-eng\": \"England\",\n"
                + "    \"gb-nir\": \"Northern Ireland\",\n"
                + "    \"gb-sct\": \"Scotland\",\n"
                + "    \"gb-wls\": \"Wales\",\n"
                + "    \"gd\": \"Grenada\",\n"
                + "    \"ge\": \"Georgia\",\n"
                + "    \"gf\": \"French Guiana\",\n"
                + "    \"gg\": \"Guernsey\",\n"
                + "    \"gh\": \"Ghana\",\n"
                + "    \"gi\": \"Gibraltar\",\n"
                + "    \"gl\": \"Greenland\",\n"
                + "    \"gm\": \"Gambia\",\n"
                + "    \"gn\": \"Guinea\",\n"
                + "    \"gp\": \"Guadeloupe\",\n"
                + "    \"gq\": \"Equatorial Guinea\",\n"
                + "    \"gr\": \"Greece\",\n"
                + "    \"gs\": \"South Georgia\",\n"
                + "    \"gt\": \"Guatemala\",\n"
                + "    \"gu\": \"Guam\",\n"
                + "    \"gw\": \"Guinea-Bissau\",\n"
                + "    \"gy\": \"Guyana\",\n"
                + "    \"hk\": \"Hong Kong\",\n"
                + "    \"hm\": \"Heard Island and McDonald Islands\",\n"
                + "    \"hn\": \"Honduras\",\n"
                + "    \"hr\": \"Croatia\",\n"
                + "    \"ht\": \"Haiti\",\n"
                + "    \"hu\": \"Hungary\",\n"
                + "    \"id\": \"Indonesia\",\n"
                + "    \"ie\": \"Ireland\",\n"
                + "    \"il\": \"Israel\",\n"
                + "    \"im\": \"Isle of Man\",\n"
                + "    \"in\": \"India\",\n"
                + "    \"io\": \"British Indian Ocean Territory\",\n"
                + "    \"iq\": \"Iraq\",\n"
                + "    \"ir\": \"Iran\",\n"
                + "    \"is\": \"Iceland\",\n"
                + "    \"it\": \"Italy\",\n"
                + "    \"je\": \"Jersey\",\n"
                + "    \"jm\": \"Jamaica\",\n"
                + "    \"jo\": \"Jordan\",\n"
                + "    \"jp\": \"Japan\",\n"
                + "    \"ke\": \"Kenya\",\n"
                + "    \"kg\": \"Kyrgyzstan\",\n"
                + "    \"kh\": \"Cambodia\",\n"
                + "    \"ki\": \"Kiribati\",\n"
                + "    \"km\": \"Comoros\",\n"
                + "    \"kn\": \"Saint Kitts and Nevis\",\n"
                + "    \"kp\": \"North Korea\",\n"
                + "    \"kr\": \"South Korea\",\n"
                + "    \"kw\": \"Kuwait\",\n"
                + "    \"ky\": \"Cayman Islands\",\n"
                + "    \"kz\": \"Kazakhstan\",\n"
                + "    \"la\": \"Laos\",\n"
                + "    \"lb\": \"Lebanon\",\n"
                + "    \"lc\": \"Saint Lucia\",\n"
                + "    \"li\": \"Liechtenstein\",\n"
                + "    \"lk\": \"Sri Lanka\",\n"
                + "    \"lr\": \"Liberia\",\n"
                + "    \"ls\": \"Lesotho\",\n"
                + "    \"lt\": \"Lithuania\",\n"
                + "    \"lu\": \"Luxembourg\",\n"
                + "    \"lv\": \"Latvia\",\n"
                + "    \"ly\": \"Libya\",\n"
                + "    \"ma\": \"Morocco\",\n"
                + "    \"mc\": \"Monaco\",\n"
                + "    \"md\": \"Moldova\",\n"
                + "    \"me\": \"Montenegro\",\n"
                + "    \"mf\": \"Saint Martin\",\n"
                + "    \"mg\": \"Madagascar\",\n"
                + "    \"mh\": \"Marshall Islands\",\n"
                + "    \"mk\": \"North Macedonia\",\n"
                + "    \"ml\": \"Mali\",\n"
                + "    \"mm\": \"Myanmar\",\n"
                + "    \"mn\": \"Mongolia\",\n"
                + "    \"mo\": \"Macau\",\n"
                + "    \"mp\": \"Northern Mariana Islands\",\n"
                + "    \"mq\": \"Martinique\",\n"
                + "    \"mr\": \"Mauritania\",\n"
                + "    \"ms\": \"Montserrat\",\n"
                + "    \"mt\": \"Malta\",\n"
                + "    \"mu\": \"Mauritius\",\n"
                + "    \"mv\": \"Maldives\",\n"
                + "    \"mw\": \"Malawi\",\n"
                + "    \"mx\": \"Mexico\",\n"
                + "    \"my\": \"Malaysia\",\n"
                + "    \"mz\": \"Mozambique\",\n"
                + "    \"na\": \"Namibia\",\n"
                + "    \"nc\": \"New Caledonia\",\n"
                + "    \"ne\": \"Niger\",\n"
                + "    \"nf\": \"Norfolk Island\",\n"
                + "    \"ng\": \"Nigeria\",\n"
                + "    \"ni\": \"Nicaragua\",\n"
                + "    \"nl\": \"Netherlands\",\n"
                + "    \"no\": \"Norway\",\n"
                + "    \"np\": \"Nepal\",\n"
                + "    \"nr\": \"Nauru\",\n"
                + "    \"nu\": \"Niue\",\n"
                + "    \"nz\": \"New Zealand\",\n"
                + "    \"om\": \"Oman\",\n"
                + "    \"pa\": \"Panama\",\n"
                + "    \"pe\": \"Peru\",\n"
                + "    \"pf\": \"French Polynesia\",\n"
                + "    \"pg\": \"Papua New Guinea\",\n"
                + "    \"ph\": \"Philippines\",\n"
                + "    \"pk\": \"Pakistan\",\n"
                + "    \"pl\": \"Poland\",\n"
                + "    \"pm\": \"Saint Pierre and Miquelon\",\n"
                + "    \"pn\": \"Pitcairn Islands\",\n"
                + "    \"pr\": \"Puerto Rico\",\n"
                + "    \"ps\": \"Palestine\",\n"
                + "    \"pt\": \"Portugal\",\n"
                + "    \"pw\": \"Palau\",\n"
                + "    \"py\": \"Paraguay\",\n"
                + "    \"qa\": \"Qatar\",\n"
                + "    \"re\": \"Réunion\",\n"
                + "    \"ro\": \"Romania\",\n"
                + "    \"rs\": \"Serbia\",\n"
                + "    \"ru\": \"Russia\",\n"
                + "    \"rw\": \"Rwanda\",\n"
                + "    \"sa\": \"Saudi Arabia\",\n"
                + "    \"sb\": \"Solomon Islands\",\n"
                + "    \"sc\": \"Seychelles\",\n"
                + "    \"sd\": \"Sudan\",\n"
                + "    \"se\": \"Sweden\",\n"
                + "    \"sg\": \"Singapore\",\n"
                + "    \"sh\": \"Saint Helena\",\n"
                + // original line: {"    \"sh\": \"Saint Helena, Ascension and Tristan da Cunha\",\n" +}
                "    \"si\": \"Slovenia\",\n"
                + "    \"sj\": \"Svalbard and Jan Mayen\",\n"
                + "    \"sk\": \"Slovakia\",\n"
                + "    \"sl\": \"Sierra Leone\",\n"
                + "    \"sm\": \"San Marino\",\n"
                + "    \"sn\": \"Senegal\",\n"
                + "    \"so\": \"Somalia\",\n"
                + "    \"sr\": \"Suriname\",\n"
                + "    \"ss\": \"South Sudan\",\n"
                + "    \"st\": \"São Tomé and Príncipe\",\n"
                + "    \"sv\": \"El Salvador\",\n"
                + "    \"sx\": \"Sint Maarten\",\n"
                + "    \"sy\": \"Syria\",\n"
                + "    \"sz\": \"Eswatini (Swaziland)\",\n"
                + "    \"tc\": \"Turks and Caicos Islands\",\n"
                + "    \"td\": \"Chad\",\n"
                + "    \"tf\": \"French Southern and Antarctic Lands\",\n"
                + "    \"tg\": \"Togo\",\n"
                + "    \"th\": \"Thailand\",\n"
                + "    \"tj\": \"Tajikistan\",\n"
                + "    \"tk\": \"Tokelau\",\n"
                + "    \"tl\": \"Timor-Leste\",\n"
                + "    \"tm\": \"Turkmenistan\",\n"
                + "    \"tn\": \"Tunisia\",\n"
                + "    \"to\": \"Tonga\",\n"
                + "    \"tr\": \"Turkey\",\n"
                + "    \"tt\": \"Trinidad and Tobago\",\n"
                + "    \"tv\": \"Tuvalu\",\n"
                + "    \"tw\": \"Taiwan\",\n"
                + "    \"tz\": \"Tanzania\",\n"
                + "    \"ua\": \"Ukraine\",\n"
                + "    \"ug\": \"Uganda\",\n"
                + "    \"um\": \"United States Minor Outlying Islands\",\n"
                + "    \"us\": \"United States\",\n"
                + "    \"uy\": \"Uruguay\",\n"
                + "    \"uz\": \"Uzbekistan\",\n"
                + "    \"va\": \"Vatican City (Holy See)\",\n"
                + "    \"vc\": \"Saint Vincent and the Grenadines\",\n"
                + "    \"ve\": \"Venezuela\",\n"
                + "    \"vg\": \"British Virgin Islands\",\n"
                + "    \"vi\": \"United States Virgin Islands\",\n"
                + "    \"vn\": \"Vietnam\",\n"
                + "    \"vu\": \"Vanuatu\",\n"
                + "    \"wf\": \"Wallis and Futuna\",\n"
                + "    \"ws\": \"Samoa\",\n"
                + "    \"xk\": \"Kosovo\",\n"
                + "    \"ye\": \"Yemen\",\n"
                + "    \"yt\": \"Mayotte\",\n"
                + "    \"za\": \"South Africa\",\n"
                + "    \"zm\": \"Zambia\",\n"
                + "    \"zw\": \"Zimbabwe\"\n"
                + "}";

        // THE CODE BELOW THIS POINT WAS TAKEN FROM https://mkyong.com/java/how-to-convert-java-map-to-from-json-jackson/ IN ORDER TO CONVERT JSON STRING TO JAVA MAP 
        ObjectMapper mapperObj = new ObjectMapper();

        codesAndCountry = mapperObj.readValue(jsonStr,
                new TypeReference<HashMap<String, String>>() {
        });

        // below for testing purposes only
        /*        System.out.println(jsonStr);
        
        for (Map.Entry<String, String> entry : codesAndCountry.entrySet()) {
        System.out.println("Key = " + entry.getKey()
        + ", Value = " + entry.getValue());
        }*/
    }

}
