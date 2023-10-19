export class TransactionHistory {

    symbol : string;
    name : string;
    price : number;
    quantity : number;
    ti : number;
    type : string;
    date : Date;
    constructor(symbol : string,name : string,price : number,quantity : number,ti : number,type : string,date : Date,){
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.quantity = quantity; 
        this.ti = ti;
        this.type = type;
        this.date= date;
    }

}
