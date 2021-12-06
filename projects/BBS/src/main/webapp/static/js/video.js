const sideBar = document.querySelector("#side-bar-vi");
const main = document.querySelector("#main-vi");
const body = document.querySelector("#body-vi");
const tagButton = main.querySelector(".tag-container-vi");
const tagSpan = tagButton.querySelector("span");
const taglist = tagButton.querySelector("ul");

function extendSidebar(event){
    
}

function tagDisplay(event){
    if(tagSpan.style.display == ""){
        tagSpan.style.display = "none";
        taglist.style.display = "";
        tagButton.style.width = "550px";
        tagButton.style.height = "120px";
    }
    else{
        tagSpan.style.display = "";
        taglist.style.display = "none";
        tagButton.style.width = "150px";
        tagButton.style.height = "40px";
    }
}


function init(){
    window.addEventListener("load", extendSidebar);
    tagButton.addEventListener("click",tagDisplay);
    console.dir(tagButton);
}

init();