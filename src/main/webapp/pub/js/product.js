
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
  const code = document.querySelector("#code");
  const name = document.querySelector("#name");
  const price = document.querySelector("#price");
  const imageUrl = document.querySelector("#imageUrl");
  const description = document.querySelector("#description");

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

  const validateInputs = () => {
    const codeValue = code.value.trim();
    const priceValue = price.value.trim();
    const nameValue = name.value.trim();
    const descriptionValue = description.value.trim();
    const imageUrlValue = imageUrl.value.trim();


    if (nameValue === "") {
      isNameValid = setError(name, "Name is required");
    }

  if (priceValue === "") {
        isPriceValid = setError(price, "Price is required");
      }

    // Check if all inputs are valid
    if (isNameValid && isPriceValid) {
      form.submit(); // Submit the form if everything is valid
    }
  };