const express = require("express")
var cors = require('cors')
require('dotenv').config()

const app = express()
const PORT = process.env.PORT || 3021

const tradeRoutes = require("./routes/tradeRoutes")
const clientRoutes = require("./routes/clientRoutes")
const preferenceRoutes = require("./routes/preferenceRoutes")
const portfolioRoutes = require("./routes/portfolioRoutes");
const reportRoutes = require("./routes/reportRoutes");

const historyRoutes = require("./routes/tradeHistoryRoutes")
const roboadvisorRoutes = require("./routes/roboadvisorRoutes")
app.use(cors())

app.use(express.json())
app.use(express.urlencoded({ extended: true }))

app.use('/trade', tradeRoutes)
app.use('/history',historyRoutes)
app.use('/client', clientRoutes)

app.use('/preferences', preferenceRoutes)

app.use('/portfolio',portfolioRoutes);
app.use('/roboadvisor/analyse',roboadvisorRoutes)

app.use("/report",reportRoutes);


app.listen(PORT,()=>{
    console.log(`Server running on port : ${PORT}`)
})

module.exports = app