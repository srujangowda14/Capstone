export class Portfolio {
    constructor(
        public instrumentId: string,
        public instrumentDescription: string,
        public quantity: number,
        public boughtPrice: number,
        public totalInvestment: number,
        public clientId: number
    ){}
}
