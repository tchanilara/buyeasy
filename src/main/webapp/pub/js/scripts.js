
  document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.querySelector("#login-form");
  
    if (loginForm) {
      loginForm.addEventListener("submit", (e) => {
        e.preventDefault();
        validateLoginInputs();
      });
    }
  });
  
  const form = document.querySelector("#form");
  const email = document.querySelector("#username");
  const password = document.querySelector("#password");
  
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    validateInputs();
  });
  
  const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector(".error");
  
    errorDisplay.innerText = message;
    inputControl.classList.add("error");
    inputControl.classList.remove("success");
  
    return false;
  };
  
  const setSuccess = (element) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector(".error");
  
    errorDisplay.innerText = "";
    inputControl.classList.add("success");
    inputControl.classList.remove("error");
  
    return true;
  };
  
  const isValidEmail = (email) => {
    const reg =
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return reg.test(String(email).toLowerCase());
  };
  const validateInputs = () => {
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
  
    let isEmailValid =
      emailValue !== "" && isValidEmail(emailValue) && setSuccess(email);
    let isPasswordValid =
      passwordValue !== "" && passwordValue.length >= 8 && setSuccess(password);
  
    if (emailValue === "") {
      isEmailValid = setError(email, "Email is required");
    } else if (!isValidEmail(emailValue)) {
      isEmailValid = setError(email, "Provide a valid email address");
    }
  
    if (passwordValue === "") {
      isPasswordValid = setError(password, "Password is required");
    } else if (passwordValue.length < 8) {
      isPasswordValid = setError(
        password,
        "Password must be at least 8 characters."
      );
    }
  
    // Check if all inputs are valid
    if (isEmailValid && isPasswordValid) {
      form.submit(); // Submit the form if everything is valid
    }
  };