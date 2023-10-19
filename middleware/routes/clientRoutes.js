const express = require('express');
const router = express.Router();
const axios = require("axios");
const url = 'http://ec2-13-127-16-91.ap-south-1.compute.amazonaws.com:8080/client'



// Define your route handlers for /classA
router.get('/', (req, res) => {
    res.status(200)
    res.json({'message': 'Hello from /client route!'});
});

router.post("/login", async (req, res) => {
    console.log(req.body)
    
    try{
        const response = await axios.post(url + "/login", req.body)
        console.log(response.data)
    
        res.json(response.data)
    }
    catch(error){


        console.log(error.response.data)
        if(error.response.data.email === 'Invalid email' || error.response.data.email === "Email doesn't exist"){
            res.status(500).json(error.response.data)
        }
        else{
            res.status(401).json(error.response.data)
        }

       
    }

});

router.post("/register", async (req, res) => {
    console.log(req.body)
    
    try{
        const response = await axios.post(url + "/register", req.body)
        console.log(response.data)
    
        res.json(response.data)
    }
    catch(error){
        console.log(error.response.data.message)
        if(error.response.data.contains("database")){
            res.status(500).json(error.response.data.message)
        }
        else if(error.response.data.contains("mapping")){
            res.status(400).json(error.response.data.message)
        }     
    }
    

});




module.exports = router;