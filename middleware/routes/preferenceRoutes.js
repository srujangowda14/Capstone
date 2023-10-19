const express = require('express');
const router = express.Router();
const axios = require("axios");
const url = 'http://ec2-13-127-16-91.ap-south-1.compute.amazonaws.com:8080/preferences/prefs'



// Define your route handlers for /classA
router.get('/', (req, res) => {
    res.status(200)
    res.json({'message': 'Hello from /preference route!'});
});

router.get("/prefs", async (req, res) => {
    console.log(req.body)
    try{
        const response = await axios.get(url, req.body)
        console.log(response.data)
    
        res.json(response.data)
    }
    catch(error){

        console.log(error.response.data)
        res.status(error.response.data.status).json(error.response.data)

}});

router.post("/prefs", async (req, res) => {
    console.log(req.body)


    try{
        const response = await axios.post(url, req.body)
        console.log(response.data)
    
        res.json(response.data)
    }
    catch(error){

        console.log(error.response.data)
        res.status(error.response.data.status).json(error.response.data)

}});


router.put("/prefs", async (req, res) => {
        console.log(req.body)
        try{
            const response = await axios.put(url, req.body)
            console.log(response.data)
        
            res.json(response.data)
        }
        catch(error){
    
            console.log(error.response.data)
            res.status(error.response.data.status).json(error.response.data)
    
}});






module.exports = router;