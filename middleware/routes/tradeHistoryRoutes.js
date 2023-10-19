const express = require('express');
const router = express.Router();
const axios = require("axios");
const url = 'http://ec2-13-127-16-91.ap-south-1.compute.amazonaws.com:8080/history/'


router.get("/:clientId", async (req, res) => {


    try{
        const response = await axios.get(url + req.params.clientId);

        res.json(response.data)
    }
    catch(error){
        console.log(error.data)
        // console.log(error.response.data)
        // res.status(error.response.data.status).json(error.response.data)

    }

});


module.exports = router;