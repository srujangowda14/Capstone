export class Report {

    constructor( 
        public instrumentId:string,
        public tradeId:number,
        public quantity:number,
        public price:number,
        public cashValue:number,
        public profit:number,
    ){}
   
}
