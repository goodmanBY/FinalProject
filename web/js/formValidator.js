function validateUserRegistrationForm() {

    var result = true;

    var ERROR_FILL_ALL_FIELDS = "Fill all fields",
        ERROR_LOGIN_IS_TOO_SHORT = "Login is too short. Should consist of more than 4 symbols",
        ERROR_PASSWORDS_DO_NOT_MATH = "Passwords do not match";

    var validationError = document.getElementById("validation-error");

    var name = document.forms[0]["name"].value,
        lastName = document.forms[0]["lastName"].value,
        login = document.forms[0]["login"].value,
        password = document.forms[0]["password"].value,
        confirmPassword = document.forms[0]["confirmPassword"].value;

    if(password && confirmPassword && password !== confirmPassword) {
        validationError.innerHTML = ERROR_PASSWORDS_DO_NOT_MATH;
        result = false;
    }

    if(login.length < 4) {
        validationError.innerHTML = ERROR_LOGIN_IS_TOO_SHORT;
        result = false;
    }

    if(!name || !lastName || !login || !password || !confirmPassword) {
        validationError.innerHTML = ERROR_FILL_ALL_FIELDS;
        result = false;
    }

    return result;

}