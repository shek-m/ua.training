package homework04.task01.registrationForm;

public interface RegexPatterns {
    //Regex patterns
    //Surname (Latin, Cyrillic)
    String REGEX_SURNAME = "^([A-ZÀ-ÿА-Я][-,a-zа-я. ']+[ ]*)+$";
    //Name (Latin, Cyrillic)
    String REGEX_NAME = "^([A-ZА-Я][a-zа-я-]+[ ]*)+$";
    //Middle name (Latin, Cyrillic)
    String REGEX_MIDDLE_NAME = "^([A-ZА-Я][a-zа-я-]+)+$";
    //Nickname
    String REGEX_NICKNAME = "^[\\S^@A-Za-z]+$";
    //Comment
    String REGEX_COMMENT = "^(.|\\s)+$";
    //City number: "+CCCTTTTXXXXXXX" C = country code (1 to 3 dig) T = city code (1 to 8 dig) X = number (5 to 7 dig)
    String REGEX_CITY_NUMBER = "^\\+\\d{1,3}(\\()*\\d{1,8}(\\))*\\d{1,3}(-)*\\d{1,2}(-)*\\d{1,2}$";
    //Mobile number
    String REGEX_MOBILE_NUMBER = "^\\+\\d{1,3}(\\()*\\d{1,8}(\\))*\\d{1,3}(-)*\\d{1,2}(-)*\\d{1,2}$";
    //E-mail "shekera@mail.com"
    String REGEX_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    //ZIP code
    String REGEX_ZIP = "^[0-9]{5}(?:-[0-9]{4})?$";
    //City
    String REGEX_CITY = "[A-ZА-Я][A-ZА-Яa-zа-я- ]+";
    //Street
    String REGEX_STREET = "[A-ZА-Я][A-Za-zА-Яа-я., -]+";
    //Street number
    String REGEX_STREET_NUMBER = "[\\d/A-Za-zА-Яа-я]+";
    //Apartment number (optional, for private houses)
    String REGEX_APARTMENT_NUMBER = "[\\d/A-Za-zА-Яа-я]*";
    //Date "dd/mm/yyyy"
    String REGEX_DATE = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
}