const form = document.querySelector(".js-form-in");
const input = form.querySelector("input");

//리스트 크기 10인거 3종류.

function handleSubmit(event){
    event.preventDefault();
    const saveValue = input.value;
    console.log(saveValue);
}

function init(){
    form.addEventListener("submit", handleSubmit);
}

init();