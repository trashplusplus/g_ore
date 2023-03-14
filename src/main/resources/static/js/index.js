const method = "POST";
const RequestURL = "/done";

submitBtn = document.querySelector("#regFormSubmitBtn");
submitBtn.addEventListener('click',handleClick);
// get button element and add event listener



// handle click function
function handleClick(event){
    console.log('button clicked');

    // get inputs elements when button pressed
    username = document.querySelector('#name').value;
    password = document.querySelector('#password').value;
    console.log(`username: ${username}\n password: ${password}`);

    // create object with username and password;
    userdata = {username: username,
    password: password};
    console.log(userdata);

    // create AJAX object
    const xhr = new XMLHttpRequest();
    xhr.open(method,RequestURL,false);
    // send request
    xhr.send(userdata);
    
    
    console.log(`Request status: ${xhr.status}\n
    Response type: ${xhr.responseType}\n
    Response: ${xhr.response}\n
    ResponseText: ${xhr.responseText}\n`);
}
