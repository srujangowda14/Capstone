const express = require('express');
const router = express.Router();
const tokenDao = require('../repositories/AccessTokensDAO');
const tradeDao = require('../repositories/tradeDAO');
const clientVerification = require('./clientVerification.js');

router.post('/trade', (request, response) => {

    //console.log(request.body)
    let order = readOrder(request);
    let trade;
    console.log(order.token)
    console.log(request.body)

    if (tokenDao.checkToken(order.token)) {
        trade = tradeDao.performTrade(order);
        //response.send(JSON.stringify(trade));
        console.log('verified')

        if (clientVerification.validateEmail(order.email)) {  
            console.log(order)  // validate email
            let id = clientVerification.generateID(order);
            let token = clientVerification.generateToken(order.email);
            //console.log(id)
            //console.log(token)
            if (id == order.clientId && token == order.token) {
                console.log('matched')
                console.log('performing trade')
                trade = tradeDao.performTrade(order);
                //console.log('executed trade here')
                console.log(trade)
                response.send(JSON.stringify(trade));
            }
            else{
                response.status(401).send();
            }
        }

        
    }
    
    else {
        response.status(406).send();
    }
});

function readOrder(request) {
    let order = new Object();
    order.instrumentId = request.body.instrumentId;
    order.quantity = request.body.quantity;
    order.targetPrice = request.body.targetPrice;
    order.direction = request.body.direction;
    order.clientId = request.body.clientId;
    order.email = request.body.email;
    order.token = request.body.token;

    return order;
}

router.get('/instruments/:category', (request, response, next) => {
    console.log('GET: Instruments request');

    let instrumentList = tradeDao.getInstruments(request.params.category);
    // reset token vaility for another 5 minutes
    tokenDao.updateTokenValidity(request.body.token)

    if (instrumentList.length == 0) {
        response.sendStatus(204);
    }
    response.end(JSON.stringify(instrumentList));
});

router.get('/instruments', (request, response, next) => {
    console.log('GET: Instruments request');

    let instrumentList = tradeDao.getInstruments();
    // reset token vaility for another 5 minutes
    tokenDao.updateTokenValidity(request.body.token)

    if (instrumentList.length == 0) {
        response.sendStatus(204);
    }
    response.end(JSON.stringify(instrumentList));
});

router.get('/prices/:category', (request, response, next) => {
    console.log('GET: Prices request');
    let prices = tradeDao.getPrices(request.params.category);
    if (prices.length == 0) {
        response.sendStatus(204);
    }

    response.end(JSON.stringify(prices));
});

router.get('/prices', (request, response, next) => {
    console.log('GET: Prices request');
    let prices = tradeDao.getPrices(request.query.category);
    if (prices.length == 0) {
        response.sendStatus(204);
    }

    response.end(JSON.stringify(prices));
});

router.post('/prices', (request, response, next) => {
    console.log('POST: Prices request');
    console.log(request.body.token);

    //console.log(request.body.token);
    if (tokenDao.checkToken(request.body.token) == true) {
        let prices = tradeDao.getPrices(request.query.category);

        // reset token vaility for another 5 minutes
        tokenDao.updateTokenValidity(request.body.token)

        if (prices.length === 0) {
            response.sendStatus(204);
        }

        response.end(JSON.stringify(prices));
    }
    else {
        response.sendStatus(401);
    }

});


module.exports = router;