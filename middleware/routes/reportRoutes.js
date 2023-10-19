const express = require('express');
const router = express.Router();
const axios = require("axios");
const url = 'http://ec2-13-127-16-91.ap-south-1.compute.amazonaws.com:8080/report';

router.get("/buyReport/:clientId", async (req, res) => {
    const clientId = req.params.clientId;
    console.log(clientId);
    
    try{
        const response = await axios.get(`${url}/buyReport/${clientId}`, req.body)
        console.log(response.data)
    
        res.json(response.data)
    }
    catch(error){
        console.log(error.response.data)
        if(error.response.data.contains("error")){
            res.status(500).json(error.response.data)
        } 
    }

});

router.get("/sellReport/:clientId", async (req, res) => {
    const clientId = req.params.clientId;
    console.log(clientId);
    
    try{
        const response = await axios.get(`${url}/sellReport/${clientId}`, req.body)
        console.log(response.data)
    
        res.json(response.data)
    }
    catch(error){
        console.log(error.response.data)
        if(error.response.data.contains("error")){
            res.status(500).json(error.response.data)
        } 
    }

});

module.exports = router;