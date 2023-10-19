
export class TradeHistory {
    constructor(
        public cash_value: number,
        public quantity: number,
        public direction: string,
        public instrument_id: string,
        public client_id: number,
        public trade_id: string,
        public execution_price: number,
        public timestamp : string
    ){}
}