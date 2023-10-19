export class TradeExecDetails {
    constructor(
        public instrumentID: string,
        public instrumentDescription: string,
        public quantity: number,
        public boughtPrice: number,
    ){

    }
}
