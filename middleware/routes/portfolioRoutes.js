const express = require('express');
const router = express.Router();
const axios = require("axios");
const url = 'http://ec2-13-127-16-91.ap-south-1.compute.amazonaws.com:8080/portfolio'

router.get("/:clientId", async (req, res) => {
    const clientId = req.params.clientId;
    console.log(clientId);
    
    try{
        const response = await axios.get(`${url}/${clientId}`, req.body)
        if(response.data.length==0)
            res.status(204);
        res.json(response.data)
    }
    catch(error){
        console.log(error)
        if(error.response.data.contains("portfolio database")){
            res.status(500).json(error.response.data)
        }  
    }

});

module.exports = router;