var result = true;

var ERROR_FILL_ALL_FIELDS = "Fill all fields",
    ERROR_LOGIN_IS_TOO_SHORT = "Login is too short. Should consist of more than 4 symbols",
    ERROR_PASSWORDS_DO_NOT_MATH = "Passwords do not match",
    ERROR_CARD_NUMBER_IS_TOO_SHORT = "Card number is too short. Should consist of 16 numbers",
    ERROR_INVALID_MONTH_INDEX = "Incorrect month",
    ERROR_INVALID_YEAR = "Incorrect year",
    ERROR_INVALID_SECURITY_CODE = "Invalid security code",
    ERROR_FILL_DESCRIPTION_FIELD = "Fill description",
    ERROR_INVALID_SELECTED_DATES = "Invalid selected dates";

var VALID_LOGIN_LENGTH = 4,
    VALID_CARD_NUMBER_LENGTH = 16,
    FIRST_MONTH_INDEX = 1,
    LAST_MONTH_INDEX = 12;

var currentYear = new Date().getFullYear();

var validationError = document.getElementById("validation-error");
validationError.innerHTML = "";

function validateLogInForm() {

    var login = document.forms[0]["login"].value,
        password = document.forms[0]["password"].value;

    if (!login || !password) {
        validationError.innerHTML = ERROR_FILL_ALL_FIELDS;
        result = false;
    }

    if (login && password) {
        validationError.innerHTML = "";
    }

    return result;

}

function validateUserRegistrationForm() {

    var name = document.forms[0]["name"].value,
        lastName = document.forms[0]["lastName"].value,
        login = document.forms[0]["login"].value,
        password = document.forms[0]["password"].value,
        confirmPassword = document.forms[0]["confirmPassword"].value;

    if (password && confirmPassword && password !== confirmPassword) {
        validationError.innerHTML = ERROR_PASSWORDS_DO_NOT_MATH;
        result = false;
    }

    if (login.length < VALID_LOGIN_LENGTH) {
        validationError.innerHTML = ERROR_LOGIN_IS_TOO_SHORT;
        result = false;
    }

    if (!name || !lastName || !login || !password || !confirmPassword) {
        validationError.innerHTML = ERROR_FILL_ALL_FIELDS;
        result = false;
    }

    if (name && lastName && login && password && confirmPassword) {
        validationError.innerHTML = "";
    }

    return result;

}

function validatePayRequestForm() {

    var cardNumber = document.forms[0]["cardNumber"].value,
        month = document.forms[0]["month"].value,
        year = document.forms[0]["year"].value,
        owner = document.forms[0]["owner"].value,
        securityCode = document.forms[0]["securityCode"].value;

    if (!cardNumber || !month || !year || !owner || !securityCode) {
        validationError.innerHTML = ERROR_FILL_ALL_FIELDS;
        result = false;
    }

    if (cardNumber.length < VALID_CARD_NUMBER_LENGTH) {
        validationError.innerHTML = ERROR_CARD_NUMBER_IS_TOO_SHORT;
        result = false;
    }

    if (month < FIRST_MONTH_INDEX || month > LAST_MONTH_INDEX) {
        validationError.innerHTML = ERROR_INVALID_MONTH_INDEX;
        result = false;
    }

    if (year < currentYear) {
        validationError.innerHTML = ERROR_INVALID_YEAR;
        result = false;
    }

    if (securityCode.length !== 3) {
        validationError.innerHTML = ERROR_INVALID_SECURITY_CODE;
        result = false;
    }

    if (cardNumber && month && year && owner && securityCode) {
        validationError.innerHTML = "";
    }

    return result;

}

function validateAddBlockDescriptionForm() {

    var description = document.forms[0]["blockDescription"].value;

    if (!description) {
        validationError.innerHTML = ERROR_FILL_DESCRIPTION_FIELD;
        result = false;
    }

    if (description) {
        validationError.innerHTML = "";
    }

    return result;

}

function validateBookingForm() {

    var amountOfPlaces = document.forms[0]["amountOfPlaces"].value,
        dateFrom = document.forms[0]["dateFrom"].value,
        dateTo = document.forms[0]["dateTo"].value;

    if(!amountOfPlaces || dateFrom.getTime() == "" || dateTo.getTime() == "") {
        validationError.innerHTML = ERROR_FILL_ALL_FIELDS;
        result = false;
    }

    if(getDaysBetweenTwoDates(dateTo, dateFrom) <= 0) {
        validationError.innerHTML = ERROR_INVALID_SELECTED_DATES;
        result = false;
    }

    if(amountOfPlaces && dateFrom && dateTo) {
        validationError.innerHTML = "";
    }

    return result;

}

function getDaysBetweenTwoDates(dateFrom, dateTo) {

    var hours = 24,
        minutes = 60,
        seconds = 60,
        milliseconds = 1000,
        oneDay = hours * minutes * seconds * milliseconds;

    var timeDifference = Math.abs(dateTo.getTime() - dateFrom.getTime());

    return Math.ceil(timeDifference / oneDay);

}