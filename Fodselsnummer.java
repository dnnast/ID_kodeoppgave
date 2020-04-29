/**
* The Fodselsnummer program implements an application that validates fodselsnummer.
*
* @author Anastasiia Danshina
* @version 1.0
* @since 2020-04-27
*/



class Fodselsnummer{

  /**
  * This method is used to check length, format of date of birth and control digits.
  * @param fodselsnummer This is the parameter to check
  * @return boolean This returns a boolean parameter: true - valid, false - invalid fodselsnummer
  */
  public boolean validateFodselsnummer(String fodselsnummer){
    // first check length, it has to be 11
    if (fodselsnummer.length() != 11) return false;
    // check format of birth date
    else if (!checkBirthDateFormat(fodselsnummer)) return false;
    // there is no reason to check individual digits, it is a 3-digits number anyways
    // check control digits
    else if (!checkControlDigits(fodselsnummer)) return false;

    return true;
  }

  /**
  * This method is used to check format of date of birth .
  * @param fodselsnummer This is the parameter to check
  * @return boolean This returns a boolean parameter: true - valid, false - invalid fodselsnummer
  */
  public boolean checkBirthDateFormat(String fodselsnummer){
    int day = Integer.parseInt(fodselsnummer.substring(0,2));
    int month = Integer.parseInt(fodselsnummer.substring(2,4));
    int year = Integer.parseInt(fodselsnummer.substring(4,6));
    int individualDigits = Integer.parseInt(fodselsnummer.substring(6,9));

    boolean leapYear = false;
    // check leap year
    // note: 1900 is not leap year
    if(year == 0 && (individualDigits >= 0 && individualDigits <= 499)){
      leapYear = false;
    }else if(year % 4 == 0){
      leapYear = true;
    }

    // check month format: number between 1 and 12
    if(!(1 <= month && month <= 12)) return false;

    // check day format: number between 1 and lastDay
    int lastDay = getNumberOfDaysInAMonth(month);
    // if leapYear
    if (month == 2 && leapYear == true) lastDay += 1;
    if (!(day >= 1 && day <= lastDay)) return false;

    return true;
  }

  /**
  * This method is used to get number of days in a month
  * @param month
  * @return int This returns number of days
  */
  public int getNumberOfDaysInAMonth(int month){
    int maxDays = 0;
    switch(month)
		{
		  case 1:
		  case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
			    maxDays = 31;
			  	break;

			case 4:
			case 6:
			case 9:
			case 11:
				maxDays = 30;
				break;

			case 2:
				maxDays = 28;
				break;
		  }
      return maxDays;
  }

  /**
  * This method is used to check control digits.
  * @param fodselsnummer This is the parameter to check
  * @return boolean This returns a boolean parameter: true - valid, false - invalid fodselsnummer
  */
  public boolean checkControlDigits(String fodselsnummer){
    int d1 = Integer.parseInt(fodselsnummer.substring(0,1));
    int d2 = Integer.parseInt(fodselsnummer.substring(1,2));
    int m1 = Integer.parseInt(fodselsnummer.substring(2,3));
    int m2 = Integer.parseInt(fodselsnummer.substring(3,4));
    int y1 = Integer.parseInt(fodselsnummer.substring(4,5));
    int y2 = Integer.parseInt(fodselsnummer.substring(5,6));
    int i1 = Integer.parseInt(fodselsnummer.substring(6,7));
    int i2 = Integer.parseInt(fodselsnummer.substring(7,8));
    int i3 = Integer.parseInt(fodselsnummer.substring(8,9));
    int k1_original = Integer.parseInt(fodselsnummer.substring(9,10));
    int k2_original = Integer.parseInt(fodselsnummer.substring(10,11));

    // formula for the 1st control digit
    int k1 = 11 - ((3 * d1 + 7 * d2 + 6 * m1 + 1 * m2 + 8 * y1 + 9 * y2 + 4 * i1 + 5 * i2 + 2 * i3) % 11);
    if (k1 == 11) k1 = 0;
    // formula for the 2nd control digit
    int k2 = 11 - ((5 * d1 + 4 * d2 + 3 * m1 + 2 * m2 + 7 * y1 + 6 * y2 + 5 * i1 + 4 * i2 + 3 * i3 + 2 * k1) % 11);

    if (k2 == 11) k2 = 0;

    if(k1 == 10 || k2 == 10) return false;
    // compare calculated control digits with the last two digits of fodselsnummer
    else if(k1 == k1_original && k2 == k2_original) return true;

    return false;
  }

  /**
   * The main method which makes use of validateFodselsnummer method.
   * @param args Unused.
   * @return Nothing.
   */
  public static void main(String[] args) {
    String fodselsnummer = "09123356982";
    Fodselsnummer fsn = new Fodselsnummer();
    Boolean val = fsn.validateFodselsnummer(fodselsnummer);
    System.out.println("Fodselsnummer: " + fodselsnummer);
    System.out.println("Valid fodselsnummer: " + val);
  }
}
