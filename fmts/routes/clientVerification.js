const express = require('express');
const router = express.Router();
const tokenDAO = require('../repositories/AccessTokensDAO');


// verify user identity: email and client ID are derivatives and must match. Client ID can be generated from email
router.post('/', (request, response, next) => {
    let token;
    let client = request.body;
    let status = 200;
    //let currBody = "n/a";
    console.log('Client Verification POST request made');
    try {
        //  validate that email has proper format
        // check identity by email and or clientID
        console.log(request.body)

        if (validateEmail(client.email) && checkIdentity(client)) {
            //generate token for future trades
            console.log('token generated')
            client.token = generateToken(client.email);
        }
        else {
            status = 401;

        }
    } catch (error) {
        console.log('Error: ', error);
        status = 404;
    }

    if (status === 200) {

        console.log(client)
        if(client.hasOwnProperty(token)){
            tokenDAO.addNewToken(client.token, client.token);
        }
        else{
            //client.currBody = 'n/a'
        }
        
        
        //currBody = JSON.stringify(client);
    }


    if(!client.hasOwnProperty("token")){
        client.token = null;
    }

    //response.status(status).send(currBody);
    response.status(status).send(client)

})

// validate that email has proper format
function validateEmail(mail) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
        console.log('valid email')
        return (true);
    }
    console.log("You have entered an invalid email address!");
    return (false);
}

// email must alwyas be provided
function checkIdentity(client) {
    if (client.email == undefined || client.email == null ||
        client.email.toUpperCase().includes('@NOCUSTOMER')) {
        return false;
    }
    // if email only then generate clientId
    if (client.clientId == "" && client.email != "" && client.email != undefined) {
        client.clientId = generateID(client);
    }
    else { // reject when client ID is illegal
        let confirmId = generateID(client);
        if (confirmId != client.clientId) {
            console.log('invalid clientid')
            return false;
        }
    }

    return true;
}

// generate unique id based on email
function generateID(client) {
    return Math.abs(client.email.split("").reduce(function (a, b) {
        a = ((a << 5) - a) + b.charCodeAt(0); 
        return a & a
    }, 0)) + 123456;
}

// generate unique authorization token based on email
function generateToken(email) {
    let token = Math.abs(email.split("").reduce(function (a, b) {
        a = ((a << 5) - a) + b.charCodeAt(0); 
        return a & a
    }, 0));
    // add timestamp to token for validity
    return token;
}


module.exports = router;
module.exports.validateEmail = validateEmail;
module.exports.generateID = generateID;
module.exports.generateToken = generateToken;