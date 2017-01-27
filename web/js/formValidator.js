var ERROR_FILL_ALL_FIELDS = "Fill all fields",
    ERROR_FILL_FIELD = "Fill field",
    ERROR_LOGIN_IS_TOO_SHORT = "Login should consist of more than 4 symbols",
    ERROR_PASSWORD_IS_TOO_SHORT = "Password should consist of more than 6 symbols",
    ERROR_PASSWORDS_DO_NOT_MATH = "Passwords do not match",
    ERROR_INCORRECT_FIRST_SYMBOL = "Incorrect first symbol",
    ERROR_FOUND_WHITESPACE = "Input data without any whitespace",
    ERROR_INVALID_CARD_NUMBER = "Card number should consist of 16 numbers",
    ERROR_INVALID_MONTH_INDEX = "Incorrect month",
    ERROR_INVALID_YEAR = "Incorrect year",
    ERROR_INVALID_OWNER_FIELD = "Name and last name should be in upper case",
    ERROR_INVALID_SECURITY_CODE = "Invalid security code",
    ERROR_FILL_DESCRIPTION_FIELD = "Fill description",
    ERROR_INVALID_SELECTED_DATES = "Invalid selected dates";

var REGEX_INCORRECT_FIRST_SYMBOL = /[^A-zА-я]/,
    REGEX_WHITESPACE = /\s/,
    REGEX_VALID_CARD_NUMBER = /^([0-9]{16})$/,
    REGEX_VALID_SECURITY_CODE_ = /^([0-9]{3})$/;

var VALID_LOGIN_LENGTH = 4,
    VALID_PASSWORD_LENGTH = 6,
    FIRST_MONTH_INDEX = 1,
    LAST_MONTH_INDEX = 12;


var currentYear = new Date().getFullYear(),
    maxValidYear = currentYear + 5;

var validationError = document.getElementById("validation-error");

function validateLogInForm(form) {

    var result = true;

    var login = form["login"].value,
        password = form["password"].value;

    if (!login || !password) {
        validationError.innerHTML = ERROR_FILL_ALL_FIELDS;
        result = false;
    }

    return result;

}

function validateUserRegistrationForm(form) {

    var result = true;

    var name = form["name"].value,
        lastName = form["lastName"].value,
        login = form["login"].value,
        password = form["password"].value,
        confirmPassword = form["confirmPassword"].value;

    if (!name || !lastName || !login || !password || !confirmPassword) {
        validationError.innerHTML = ERROR_FILL_ALL_FIELDS;
        result = false;
    }

    if ((name && name.match(REGEX_INCORRECT_FIRST_SYMBOL)) || (lastName && lastName.match(REGEX_INCORRECT_FIRST_SYMBOL)
        || (login && login.match(REGEX_INCORRECT_FIRST_SYMBOL)))) {
        validationError.innerHTML = ERROR_INCORRECT_FIRST_SYMBOL;
        result = false;
    }

    if (name.match(REGEX_WHITESPACE) || lastName.match(REGEX_WHITESPACE) || login.match(REGEX_WHITESPACE)) {
        validationError.innerHTML = ERROR_FOUND_WHITESPACE;
        result = false;
    }

    if (password && confirmPassword && password !== confirmPassword) {
        validationError.innerHTML = ERROR_PASSWORDS_DO_NOT_MATH;
        result = false;
    }

    if (password.length < VALID_PASSWORD_LENGTH) {
        validationError.innerHTML = ERROR_PASSWORD_IS_TOO_SHORT;
        result = false;
    }

    if (login.length < VALID_LOGIN_LENGTH) {
        validationError.innerHTML = ERROR_LOGIN_IS_TOO_SHORT;
        result = false;
    }

    return result;

}

function validatePayRequestForm(form) {

    var result = true;

    var cardNumber = form["cardNumber"].value,
        month = form["month"].value,
        year = form["year"].value,
        owner = form["owner"].value,
        securityCode = form["securityCode"].value;

    if (!cardNumber || !month || !year || !owner || !securityCode) {
        validationError.innerHTML = ERROR_FILL_ALL_FIELDS;
        result = false;
    }

    if (!cardNumber.match(REGEX_VALID_CARD_NUMBER)) {
        validationError.innerHTML = ERROR_INVALID_CARD_NUMBER;
        result = false;
    }

    if (month < FIRST_MONTH_INDEX || month > LAST_MONTH_INDEX) {
        validationError.innerHTML = ERROR_INVALID_MONTH_INDEX;
        result = false;
    }

    if (owner !== owner.toUpperCase()) {
        validationError.innerHTML = ERROR_INVALID_OWNER_FIELD;
        result = false;
    }

    if (year < currentYear || year > maxValidYear) {
        validationError.innerHTML = ERROR_INVALID_YEAR;
        result = false;
    }

    if (!securityCode.match(REGEX_VALID_SECURITY_CODE_)) {
        validationError.innerHTML = ERROR_INVALID_SECURITY_CODE;
        result = false;
    }

    return result;

}

function validateAddBlockDescriptionForm(form) {

    var result = true;

    var description = form["blockDescription"].value;

    if (!description) {
        validationError.innerHTML = ERROR_FILL_DESCRIPTION_FIELD;
        result = false;
    }

    return result;

}

function validateBookingForm(form) {

    var result = true;

    var amountOfPlaces = form["amountOfPlaces"].value,
        dateFrom = form["dateFrom"].value,
        dateTo = form["dateTo"].value;

    if (!amountOfPlaces || dateFrom == "" || dateTo == "") {
        validationError.innerHTML = ERROR_FILL_ALL_FIELDS;
        result = false;
    }

    if (!checkDates(dateFrom, dateTo)) {
        validationError.innerHTML = ERROR_INVALID_SELECTED_DATES;
        result = false;
    }

    if (getDaysBetweenTwoDates(dateFrom, dateTo) <= 0) {
        validationError.innerHTML = ERROR_INVALID_SELECTED_DATES;
        result = false;
    }

    return result;

}

function validateRoomCost(form) {

    var result = true;

    var roomCost = form["roomCost"].value;

    if(!roomCost) {
        validationError.innerHTML = ERROR_FILL_FIELD;
        result = false;
    }

    return result;
}

function validateDiscountValue(form) {

    var result = true;

    var discountValue = form["discountValue"].value;


    if(!discountValue) {
        validationError.innerHTML = ERROR_FILL_FIELD;
        result = false;
    }

    return result;
}

function getDaysBetweenTwoDates(stringDateFrom, stringDateTo) {

    var HOURS = 24,
        MINUTES = 60,
        SECONDS = 60,
        MILLISECONDS = 1000,
        ONE_DAY = HOURS * MINUTES * SECONDS * MILLISECONDS;

    var dateFrom = new Date(stringDateFrom);
    var dateTo = new Date(stringDateTo);
    var timeDiff = dateTo.getTime() - dateFrom.getTime();
    return Math.ceil(timeDiff / ONE_DAY);

}

function checkDates(stringDateFrom, stringDateTo) {

    var result = true;

    var currentDay = daysPassed(new Date());
    var dayFrom = daysPassed(new Date(stringDateFrom));
    var dayTo = daysPassed(new Date(stringDateTo));


    if (dayFrom - currentDay < 0) {
        result = false;
    }

    if (dayTo - currentDay <= 0) {
        result = false;
    }

    return result;

}

function daysPassed(dt) {
    var current = new Date(dt.getTime());
    var previous = new Date(dt.getFullYear(), 0, 1);

    return Math.ceil((current - previous + 1) / 86400000);
}