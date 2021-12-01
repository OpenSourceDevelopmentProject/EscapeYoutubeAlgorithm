const form = document.querySelector(".js-form-in");
const input = form.querySelector("input");

function handleSubmit(event){
    event.preventDefault();
    const saveValue = input.value;
    console.log(saveValue);
}

function init(){
    form.addEventListener("submit", handleSubmit);
}

init();